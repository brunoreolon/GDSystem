package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VisualizarProdutoController implements Initializable {

	private static Stage stage;
	
	@FXML
	private AnchorPane apCadProduto;

	@FXML
	private Label lblCodigo;

	@FXML
	private Label lblDepartamento;

	@FXML
	private Label lblMarca;

	@FXML
	private TextField txCodigo;

	@FXML
	private TextField txtDepartamento;

	@FXML
	private TextField txtMarca;

	@FXML
	private Button btnCodigo;

	@FXML
	private Button btnDepartamento;

	@FXML
	private Button btnMarca;

	@FXML
	private TableView<?> tvProduto;

	@FXML
	private TableColumn<?, ?> tbcCodigo;

	@FXML
	private TableColumn<?, ?> tbcDescricao;

	@FXML
	private TableColumn<?, ?> tbcUn;

	@FXML
	private TableColumn<?, ?> tbcDepartamento;

	@FXML
	private TableColumn<?, ?> tbcEstoque;

	@FXML
	private TableColumn<?, ?> tbcValorVenda;

	@FXML
	private Button btnCadastrar;

	@FXML
	void onCadProdutoAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/CadastroProduto.fxml", "Visualizar Produto", false);
		setStage(OpenCloseStage.getStage());
	}

	public void setStage(Stage stage) {
		VisualizarProdutoController.stage = stage;
	}
	
	public static Stage getStage() {
		return stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
