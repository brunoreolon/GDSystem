package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.ProdutoDAO;
import br.com.unipar.gdsystem.model.Produto;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VisualizarProdutoController implements Initializable {

	private static Stage stage;
	private ProdutoDAO produtoDao = new ProdutoDAO();
	
	@FXML private AnchorPane apCadProduto;
	@FXML private Label lblCodigo;
	@FXML private Label lblDepartamento;
	@FXML private Label lblMarca;
	@FXML private TextField txtCodigo;
	@FXML private TextField txtDepartamento;
	@FXML private TextField txtMarca;
	@FXML private Button btnCodigo;
	@FXML private Button btnDepartamento;
	@FXML private Button btnMarca;
	@FXML private TableView<Produto> tvProduto;
	@FXML private TableColumn<Produto, String> tbcCodigo;
	@FXML private TableColumn<Produto, String> tbcDescricao;
	@FXML private TableColumn<Produto, String> tbcUn;
	@FXML private TableColumn<Produto, String> tbcDepartamento;
	@FXML private TableColumn<Produto, Integer> tbcEstoque;
	@FXML private TableColumn<Produto, BigDecimal> tbcValorVenda;
	@FXML private Button btnCadastrar;
	@FXML private Button btnAtualizar;
	@FXML private Button btnRemover;

	@FXML
	void onCadProdutoAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/CadastroProduto.fxml", "Cadastro Produto", false);
		setStage(OpenCloseStage.getStage());
	}

	public void setStage(Stage stage) {
		VisualizarProdutoController.stage = stage;
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	@FXML
	private void onAtualizarAction() {
		initTable();
	}

	@FXML
	private void onRemoverAction() {
		Integer id = onGetId();

		if (id == -1) {
			return;
		}
		produtoDao.remove(id);
		onAtualizarAction();
	}

	@FXML
	public Integer onGetId() {
		Object object = tvProduto.getSelectionModel().getSelectedItem();

		if (object == null) {
			return -1;
		}

		btnRemover.setDisable(false);

		Integer id = ((Produto) object).getId();
		
		return id != null ? id : -1;
	} 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTable();
	}
	
	private void initTable() {
		tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tbcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tbcUn.setCellValueFactory(new PropertyValueFactory<>("unidade"));
		tbcDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
		tbcEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidadeTotal"));
		tbcValorVenda.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));

		tvProduto.setItems(foo());
	}
	
	private ObservableList<Produto> foo(){
		return FXCollections.observableArrayList(produtoDao.getList());
	}
}
