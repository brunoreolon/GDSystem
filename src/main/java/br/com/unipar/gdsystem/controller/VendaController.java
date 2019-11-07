package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.CaixaDAO;
import br.com.unipar.gdsystem.dao.ClienteDAO;
import br.com.unipar.gdsystem.dao.ProdutoDAO;
import br.com.unipar.gdsystem.model.Caixa;
import br.com.unipar.gdsystem.model.Cliente;
import br.com.unipar.gdsystem.model.Produto;
import br.com.unipar.gdsystem.model.ProdutoVenda;
import br.com.unipar.gdsystem.util.AlertUTIL;
import br.com.unipar.gdsystem.util.DataHoraUTIL;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VendaController implements Initializable {

	public static VendaController vendaController;
	private static Stage stage;

	private Cliente cliente = new Cliente();
	private ClienteDAO clienteDao = new ClienteDAO();
	private Produto produto = new Produto();
	private ProdutoVenda produtoVenda = new ProdutoVenda();
	private ProdutoDAO produtoDao = new ProdutoDAO();
	private List<Produto> produtos = new ArrayList<Produto>();
	private List<ProdutoVenda> produtoVendaList = new ArrayList<ProdutoVenda>();
	
	Integer qtd = 0;
	BigDecimal descPorcentagem = new BigDecimal("0");
	BigDecimal descDinheiro = new BigDecimal("0");
	BigDecimal totDesconto = new BigDecimal("0");
	BigDecimal precoUnitario = new BigDecimal("0");
	BigDecimal subTotal =  new BigDecimal("0");
	BigDecimal valorTotal = new BigDecimal("0");

	@FXML private AnchorPane apVenda;
	@FXML private Pane pTop;
	@FXML private TextField txtPedido;
	@FXML private TextField txtData;
	@FXML private TextField txtHora;
	@FXML private TextField txtCpf;
	@FXML private Button btnAddCpf;
	@FXML private TextField txtNome;
	@FXML private TextField txtItem;
	@FXML private Button btnPesquisarItem;
	@FXML private TextField txtDescricaoItem;
	@FXML private TextField txtPrecoUnitario;
	@FXML private TextField txtDescPor;
	@FXML private TextField txtDescDin;
	@FXML private TextField txtQtd;
	@FXML private TextField txtEstoque;
	@FXML private Button btnAddItem;
	@FXML private Label lblValorTotal;
	@FXML private TableView<Produto> tvItens;
	@FXML private TableColumn<Produto, Integer> tbcItem;
	@FXML private TableColumn<Produto, String> tbcCodigo;
	@FXML private TableColumn<Produto, String> tbcDescricao;
	@FXML private TableColumn<Produto, String> tbcUn;
//	@FXML private TableColumn<ProdutoVenda, Integer> tbcQtd;
//	@FXML private TableColumn<ProdutoVenda, BigDecimal> tbcPrecoUni;
//	@FXML private TableColumn<ProdutoVenda, Integer> tbcDescPor;
//	@FXML private TableColumn<ProdutoVenda, BigDecimal> tbcDescDin;
//	@FXML private TableColumn<ProdutoVenda, BigDecimal> tbcSubTotal;
	@FXML private Pane pBotton;
	@FXML private Label txtDescontos;
	@FXML private Label txtTotalPago;

	@FXML private Label txtTroco;
	@FXML private ButtonBar bbBotoes;
	@FXML private Button btnFinalizar;
	@FXML private Button btnPagamento;
	@FXML private Button btnRemoverItem;
	@FXML private Button btnNovaVenda;
	@FXML private Button btnImprimir;
	@FXML private Button btnSair;

	public VendaController() {
		vendaController = this;
	}
	
	@FXML
	void onPesquisarCpfAction(ActionEvent event) {
		try {
			cliente = clienteDao.searchCpf(txtCpf.getText());
		} catch (Exception e) {
			AlertUTIL.alertInformation("", "Cliente nao encontrado");
		}
		
		txtNome.setText(cliente.getNome());
	}
	
	@FXML
	void onPesquisarItemAction(ActionEvent event) {
		if (txtItem.getText().equals("")) {
			AlertUTIL.alertInformation("", "informe o código do produto");
			return;
		}
		
		try {
			getProduto();
		} catch (Exception e) {
			AlertUTIL.alertInformation("", "Produto nao encontrado");
		}
		

		txtDescricaoItem.setText(produto.getDescricao());
		txtPrecoUnitario.setText(String.valueOf(produto.getPrecoUnitario()));
		txtEstoque.setText(String.valueOf(produto.getQuantidadeTotal()));

		txtDescDin.setEditable(true);
		txtDescPor.setEditable(true);
		txtQtd.setEditable(true);
		
		btnAddItem.setDisable(false);
	}

	
	@FXML
	void onAddItemListaAction(ActionEvent event) {
		if (!txtDescPor.getText().equals("")) {
			descPorcentagem = new BigDecimal(txtDescPor.getText());
		}
		
		if (!txtDescDin.getText().equals("")) {
			descDinheiro = new BigDecimal(txtDescDin.getText());
		}
		
		if (!txtQtd.getText().equals("")) {
			qtd = Integer.parseInt(txtQtd.getText());
		}
		
		if (qtd > produto.getQuantidadeTotal()) {
			AlertUTIL.alertInformation("", "Estoque insuficiente");
			
			return;
		}
		
		if (txtQtd.getText().equals("")) {
			AlertUTIL.alertWarning("", "Informe a quantidade");
			return;
		}
		
		calcularPreco();
		
		txtDescontos.setText(String.valueOf(totDesconto));
//		txtTotalPago.setText(String.valueOf(totDesconto));
//		txtTroco.setText(String.valueOf(totDesconto));
		
		produtoVenda.setQuantidade(qtd);
		produtoVenda.setSubTotal(subTotal);
		
		produtos.add(produto);
		
		resetItem();
		listar();
		
		btnAddItem.setDisable(true);
	}

	@FXML
	void onFinalizarAction(ActionEvent event) {
		CaixaDAO c = new CaixaDAO();
		Caixa caixa = c.getCaixa();
		caixa.setValorAtual(caixa.getValorAtual().add(PagamentoController.pagamentoController.getCaixaAtualizado()));
		
		c.update(caixa);
	}
	
	
	@FXML
	void onAbrirPagamentoAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/Pagamento.fxml", "Visualizar Produto", false);
		setStage(OpenCloseStage.getStage());
	}
	
	@FXML
	void onNovaVendaAction(ActionEvent event) {
		resetAll();
		AlertUTIL.alertInformation("", "nova venda");
	}

	@FXML
	void onSairVendaAction(ActionEvent event) {
		PrincipalController.getStage().close();
	}


	private void calcularPreco() {
		precoUnitario = produto.getPrecoUnitario();
		subTotal = precoUnitario.multiply(new BigDecimal(qtd));

		BigDecimal desconto = descPorcentagem.multiply(subTotal).divide(new BigDecimal("100"));
		subTotal = subTotal.subtract(desconto);
		totDesconto = totDesconto.add(desconto);

		subTotal = subTotal.subtract(descDinheiro);
		totDesconto = totDesconto.add(descDinheiro);
		
		valorTotal = valorTotal.add(subTotal);
		lblValorTotal.setText(String.valueOf(valorTotal));
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
	private Produto getProduto() {
		return produto = produtoDao.search(Integer.parseInt(txtItem.getText()));
	}
	
	public void setTxtTotalPago(String txtTotalPago) {
		this.txtTotalPago.setText(txtTotalPago);;
	}

	public void setTxtTroco(String txtTroco) {
		this.txtTroco.setText(txtTroco);
	}
	
	public ObservableList<Produto> observableListProduto() {
		return FXCollections.observableArrayList(produtos);
	}
	
	private void resetItem() {
		txtDescricaoItem.setText("");
		txtItem.setText("");
		txtPrecoUnitario.setText("");
		txtDescPor.setText("");
		txtDescDin.setText("");
		txtQtd.setText("");
		txtEstoque.setText("");
	}

	private void resetAll() {
		txtData.setText(DataHoraUTIL.getData());
		txtHora.setText(DataHoraUTIL.getHora());
		txtCpf.setText("");
		txtNome.setText("");
		txtItem.setText("");
		txtDescricaoItem.setText("");
		txtPrecoUnitario.setText("");
		txtDescPor.setText("");
		txtDescDin.setText("");
		txtQtd.setText("");
		txtEstoque.setText("");
		lblValorTotal.setText("R$ 0,00");
		txtDescontos.setText("R$ 0,00");
		txtTotalPago.setText("R$ 0,00");
		txtTroco.setText("R$ 0,00");
		tvItens.getItems().clear();
	}
	
	private void listar() {
		tbcItem.setCellValueFactory(new PropertyValueFactory<>("id"));
		tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tbcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tbcUn.setCellValueFactory(new PropertyValueFactory<>("unidade"));
//		tbcQtd.setCellValueFactory(new PropertyValueFactory<>("quantidadeTotal"));
//		tbcPrecoUni.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
//		tbcDescPor.setCellValueFactory(new PropertyValueFactory<>("celular"));
//		tbcDescDin.setCellValueFactory(new PropertyValueFactory<>("endereco"));
//		tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("endereco"));

		tvItens.setItems(observableListProduto());
	}
	
	public void setStage(Stage stage) {
		VendaController.stage = stage;
	}

	public static Stage getStage() {
		return stage;
	}
	
	public void setFinalizarVisivel(Boolean b) {
		btnFinalizar.setDisable(b);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnAddItem.setDisable(true);
		btnFinalizar.setDisable(true);
		txtData.setText(DataHoraUTIL.getData());
		txtHora.setText(DataHoraUTIL.getHora());
	}	
}
