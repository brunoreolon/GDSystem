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
import br.com.unipar.gdsystem.dao.ProdutoVendaDAO;
import br.com.unipar.gdsystem.dao.VendaDAO;
import br.com.unipar.gdsystem.model.Caixa;
import br.com.unipar.gdsystem.model.Cliente;
import br.com.unipar.gdsystem.model.Produto;
import br.com.unipar.gdsystem.model.ProdutoVenda;
import br.com.unipar.gdsystem.model.Venda;
import br.com.unipar.gdsystem.util.AlertUTIL;
import br.com.unipar.gdsystem.util.DataHoraUTIL;
import br.com.unipar.gdsystem.util.GeradorNumeroVenda;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VendaController implements Initializable {

	public static VendaController vendaController;
	private static Stage stage;

	private Cliente cliente = new Cliente();
	private ClienteDAO clienteDao = new ClienteDAO();
	private Produto produto = null;
	private ProdutoVenda produtoVenda = null;
	private ProdutoDAO produtoDao = new ProdutoDAO();
	private VendaDAO vendaDAO = new VendaDAO();
	private Venda venda = null;
	private List<ProdutoVenda> produtoVendaList = new ArrayList<ProdutoVenda>();
	private ProdutoVendaDAO produtoVendaDao = new ProdutoVendaDAO();
	private static Integer numeroVenda = 1;
	
	Integer qtd = 0;
	BigDecimal descPorcentagem = new BigDecimal("0");
	BigDecimal descDinheiro = new BigDecimal("0");
	BigDecimal totDesconto = new BigDecimal("0");
	BigDecimal precoUnitario = new BigDecimal("0");
	BigDecimal subTotal =  new BigDecimal("0");
	BigDecimal valorTotal = new BigDecimal("0");
	BigDecimal totDescPorcentagem = new BigDecimal("0");
	BigDecimal totDescDinheiro = new BigDecimal("0");

	@FXML private AnchorPane apVenda;
	@FXML private Pane pTop;
	@FXML private TextField txtNumVenda;
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
	@FXML private MenuItem removerProduto;
	@FXML private TableView<ProdutoVenda> tvProdutos;
	@FXML private TableColumn<ProdutoVenda, String> tbcCodigo;
	@FXML private TableColumn<ProdutoVenda, String> tbcDescricao;
	@FXML private TableColumn<ProdutoVenda, String> tbcUn;
	@FXML private TableColumn<ProdutoVenda, Integer> tbcQtd;
	@FXML private TableColumn<ProdutoVenda, BigDecimal> tbcPrecoUni;
	@FXML private TableColumn<ProdutoVenda, Integer> tbcDescPor;
	@FXML private TableColumn<ProdutoVenda, BigDecimal> tbcDescDin;
	@FXML private TableColumn<ProdutoVenda, BigDecimal> tbcSubTotal;
	@FXML private Pane pBotton;
	@FXML private Label lblDescontos;
	@FXML private Label lblTotalPago;
	@FXML private Label lblTroco;
	@FXML private ButtonBar bbBotoes;
	@FXML private Button btnFinalizar;
	@FXML private Button btnPagamento;
	@FXML private Button btnRemoverItem;
	@FXML private Button btnNovaVenda;
	@FXML private Button btnImprimir;
	@FXML private Button btnSair;
	@FXML private ImageView imgFinalizar;
	@FXML private ImageView imgPagamento;
	@FXML private ImageView imgRemover;
	@FXML private ImageView imgNVenda;
	@FXML private ImageView imgImprimir;
	@FXML private ImageView imgTV;
	
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
			produto = produtoDao.search(Integer.parseInt(txtItem.getText()));
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
		txtDescDin.setEditable(false);
		txtDescPor.setEditable(false);
		txtQtd.setEditable(false);
		
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
			txtQtd.setEditable(true);
			return;
		}
		
		if (txtQtd.getText().equals("")) {
			AlertUTIL.alertWarning("", "Informe a quantidade");
			txtQtd.setEditable(true);
			return;
		}
		
		btnPagamento.setDisable(false);
		calcularPreco();
		
		lblDescontos.setText(String.valueOf("R$ " + totDesconto));
		resetItem();
		
		produtoVenda = new ProdutoVenda();
		produtoVenda.setCodigo(produto.getCodigo());
		produtoVenda.setDescricao(produto.getDescricao());
		produtoVenda.setUnidade(produto.getUnidade());
		produtoVenda.setQuantidade(qtd);
		produtoVenda.setPrecoUnitario(produto.getPrecoUnitario());
		produtoVenda.setDescPorcentagem(descPorcentagem);
		produtoVenda.setDescDinheiro(descDinheiro);
		produtoVenda.setSubTotal(subTotal);
		
		produtoVendaList.add(produtoVenda);
		
		listar();
		
		btnAddItem.setDisable(true);
	}

	@FXML
	void onFinalizarAction(ActionEvent event) {
		try {
			cliente = clienteDao.searchCpf(txtCpf.getText());
		} catch (Exception e) {
			new Cliente().setNome("Cliente");
		}
		venda = new Venda();
		produtoVenda.setVenda(venda);
		
		venda.setNumeroVenda(numeroVenda);
		
		if (!cliente.equals(null)) {
			venda.setCliente(cliente);
		}
		
		venda.setDataVenda(DataHoraUTIL.getDataHora());
		venda.setValorTotal(valorTotal);
		venda.setDescontoPorcentagem(totDescPorcentagem);
		venda.setDescontoDinheiro(totDescDinheiro);
		venda.setProdutos(produtoVendaList);
		
		CaixaDAO c = new CaixaDAO();
		Caixa caixa = c.getCaixa();
		caixa.setValorAtual(caixa.getValorAtual().add(PagamentoController.pagamentoController.getCaixaAtualizado()));
		
		c.update(caixa);
		
		for (ProdutoVenda produtoVenda : produtoVendaList) {
			Integer quantidade = produtoVenda.getQuantidade();
			
			Produto p = produtoDao.search((produtoVenda.getCodigo()));
			Integer novaQuantidade = p.getQuantidadeTotal() - quantidade;
			p.setQuantidadeTotal(novaQuantidade);
			
			produtoDao.update(p);
		}
		
		vendaDAO.add(venda);
		produtoVendaDao.add(produtoVendaList);
		
		AlertUTIL.alertInformation("", "Venda realizada com sucesso!");
		
		numeroVenda = GeradorNumeroVenda.getProximaNumeroVenda();
		txtNumVenda.setText(String.valueOf(numeroVenda));
		
		btnFinalizar.setDisable(true);
		
		resetAll();
	}
	
	@FXML
	void onAbrirPagamentoAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/Pagamento.fxml", "Visualizar Produto", false);
		setStage(OpenCloseStage.getStage());
	}
	
	@FXML
	private void onRemoverAction() {
		Integer id = onGetId();
		
		if (id == -1) {
			return;
		}
		
		produtoVendaList.remove(new ProdutoVenda(id));
		
		btnRemoverItem.setDisable(true);
		listar();
	}

	@FXML
	public Integer onGetId() {
		Object object = tvProdutos.getSelectionModel().getSelectedItem();
		
		if (object == null) {
			return -1;
		}

		btnRemoverItem.setDisable(false);

		Integer id = ((ProdutoVenda) object).getId();
		return id != null ? id : -1;
	}
	
	@FXML
	void onNovaVendaAction(ActionEvent event) {
		AlertUTIL.alertConfirmation("", "Deseja realizar uma nova venda?");
		
		txtNumVenda.setText(String.valueOf(numeroVenda));
		
		resetAll();
	}

	@FXML
	void onSairVendaAction(ActionEvent event) {
		PrincipalController.getStage().close();
	}

	private void calcularPreco() {
		precoUnitario = produto.getPrecoUnitario();
		subTotal = precoUnitario.multiply(new BigDecimal(qtd));

		BigDecimal desconto = descPorcentagem.multiply(subTotal).divide(new BigDecimal("100"));
		totDescPorcentagem = totDescPorcentagem.add(desconto);
		
		subTotal = subTotal.subtract(desconto);
		totDesconto = totDesconto.add(desconto);

		totDescDinheiro = totDescDinheiro.add(descDinheiro);
		subTotal = subTotal.subtract(descDinheiro);
		totDesconto = totDesconto.add(descDinheiro);
		
		valorTotal = valorTotal.add(subTotal);
		lblValorTotal.setText("");
		lblValorTotal.setText("R$ " + String.valueOf(valorTotal));
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
	public void setLblTotalPago(String totalPago) {
		this.lblTotalPago.setText(totalPago);;
	}

	public void setLblTroco(String troco) {
		this.lblTroco.setText(troco);
	}
	
	public ObservableList<ProdutoVenda> observableListProduto() {
		return FXCollections.observableArrayList(produtoVendaList);
	}
	
	private void resetItem() {
		txtDescricaoItem.setText("");
		txtItem.setText("");
		txtPrecoUnitario.setText("");
		txtDescPor.setText("");
		txtDescDin.setText("");
		txtEstoque.setText(" ");
		txtEstoque.setText("");
	}

	private void resetAll() {
		txtData.setText(DataHoraUTIL.getDataString());
		txtHora.setText(DataHoraUTIL.getHoraString());
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
		lblDescontos.setText("R$ 0,00");
		lblTotalPago.setText("R$ 0,00");
		lblTroco.setText("R$ 0,00");
		produtoVendaList.clear();
		tvProdutos.getItems().clear();
		
		descPorcentagem = new BigDecimal("0");
		descDinheiro = new BigDecimal("0");
		totDesconto = new BigDecimal("0");
		precoUnitario = new BigDecimal("0");
		subTotal =  new BigDecimal("0");
		valorTotal = new BigDecimal("0");
		totDescPorcentagem = new BigDecimal("0");
		totDescDinheiro = new BigDecimal("0");
	}
	
	private void listar() {
		tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tbcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tbcUn.setCellValueFactory(new PropertyValueFactory<>("unidade"));
		tbcQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tbcPrecoUni.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
		tbcDescPor.setCellValueFactory(new PropertyValueFactory<>("descPorcentagem"));
		tbcDescDin.setCellValueFactory(new PropertyValueFactory<>("descDinheiro"));
		tbcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

		tvProdutos.setItems(observableListProduto());
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
		txtNumVenda.setText(String.valueOf(numeroVenda));
		txtData.setText(DataHoraUTIL.getDataString());
		txtHora.setText(DataHoraUTIL.getHoraString());
		
		Image image = new Image("imagens/check32.png");
		imgFinalizar.setImage(image);
		
		image = new Image("imagens/pagamento.png");
		imgPagamento.setImage(image);
		
		image = new Image("imagens/deletarCancelar32.png");
		imgRemover.setImage(image);
		
		image = new Image("imagens/deletarCancelar16.png");
		imgTV.setImage(image);
		
		image = new Image("imagens/venda.png");
		imgNVenda.setImage(image);
		
		image = new Image("imagens/imprimir.png");
		imgImprimir.setImage(image);
		
	}	
}
