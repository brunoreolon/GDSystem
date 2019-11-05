package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.ClienteDAO;
import br.com.unipar.gdsystem.dao.ProdutoDAO;
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
	
	private Cliente cliente = new Cliente();
	private ClienteDAO clienteDao = new ClienteDAO();
	private Produto produto1 = new Produto();
	private ProdutoVenda produto = new ProdutoVenda();
	private ProdutoDAO produtoDao = new ProdutoDAO();
	
	private List<ProdutoVenda> produtos = new ArrayList<ProdutoVenda>();
	private static Stage stage;

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
	@FXML private TableView<ProdutoVenda> tvItens;
	@FXML private TableColumn<ProdutoVenda, Integer> tbcItem;
//	@FXML private TableColumn<ProdutoVenda, String> tbcCodigo;
//	@FXML private TableColumn<ProdutoVenda, String> tbcDescricao;
//	@FXML private TableColumn<ProdutoVenda, String> tbcUn;
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
		
		txtDescDin.setEditable(true);
		txtDescPor.setEditable(true);
		txtQtd.setEditable(true);
		
//		txtDescricaoItem.setText(produto.getDescricao());
//		txtPrecoUnitario.setText(String.valueOf(produto.getPrecoUnitario()));
//		txtEstoque.setText(String.valueOf(produto.getQuantidadeTotal()));
		
		btnAddItem.setDisable(false);
	}

	
	@FXML
	void onAddItemListaAction(ActionEvent event) {
		if (Integer.parseInt(txtQtd.getText()) > Integer.parseInt(txtEstoque.getText())) {
			AlertUTIL.alertInformation("", "Estoque insuficiente");
			return;
		}
		
		produtos.add(produto);
		
		txtDescricaoItem.setText("");
		txtItem.setText("");
		txtPrecoUnitario.setText("");
		txtDescPor.setText("");
		txtDescDin.setText("");
		txtQtd.setText("");
		txtEstoque.setText("");

		listar();
		
		btnAddItem.setDisable(true);
//		btnFinalizar.setDisable(false);
	}

	@FXML
	void onFinalizarAction(ActionEvent event) {
//		VendaDAO vendaDAO = new VendaDAO();
		
//		pedido.setData(DataHoraUTIL.getDataHora());
//		pedido.setCliente(txtNome.getText());
//		pedido.setCpf(txtCpf.getText());
//		pedido.setProdutos(produtos);
		
//		pedidoDAO.add(produtos);
//		pedidoDAO.add(pedido);
	}
	
	
	@FXML
	void onAbrirPagamentoAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/Pagamento.fxml", "Visualizar Produto", false);
		setStage(OpenCloseStage.getStage());
	}
	
	@FXML
	void onNovaVendaAction(ActionEvent event) {
		resetar();
		AlertUTIL.alertInformation("", "nova venda");
	}

	@FXML
	void onSairVendaAction(ActionEvent event) {
		PrincipalController.getStage().close();
	}

	private Produto getProduto() {
		return produto1 = produtoDao.search(Integer.parseInt(txtItem.getText()));
	}
	
	public ObservableList<ProdutoVenda> observableListProduto() {
		return FXCollections.observableArrayList(produtos);
	}

	private void resetar() {
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
//		tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
//		tbcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
//		tbcUn.setCellValueFactory(new PropertyValueFactory<>("unidade"));
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnAddItem.setDisable(true);
		btnFinalizar.setDisable(true);
		txtData.setText(DataHoraUTIL.getData());
		txtHora.setText(DataHoraUTIL.getHora());
	}

	public void setFinalizarVisivel(Boolean b) {
		btnFinalizar.setDisable(b);
	}
}
