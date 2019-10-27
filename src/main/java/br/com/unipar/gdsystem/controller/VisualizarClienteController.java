package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.ClienteDAO;
import br.com.unipar.gdsystem.model.Cliente;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class VisualizarClienteController implements Initializable {

	private static Stage stage;
	private ClienteDAO clienteDao;
//	private Cliente cliente;
	
	@FXML private AnchorPane apVisualizarCliente;
	@FXML private HBox hbFiltro;
	@FXML private TextField txtNome;
    @FXML private Button btnNome;
    @FXML private TextField txtCpf;
    @FXML private Button btnCpf;
    @FXML private TextField txtRg;
    @FXML private Button btnRg;
	@FXML private TableView<Cliente> tvCliente;
	@FXML private TableColumn<Cliente, Integer> tbcId;
	@FXML private TableColumn<Cliente, String> tbcNome;
	@FXML private TableColumn<Cliente, String> tbcCpf;
	@FXML private TableColumn<Cliente, String> tbcTelefone;
	@FXML private TableColumn<Cliente, String> tbcCelular;
	@FXML private TableColumn<Cliente, String> tbcEndereco;
	@FXML private Button btnAdicionar;
	@FXML private Button btnAtualizar;
	@FXML private Button btnRemover;

	public void setStage(Stage stage) {
		VisualizarClienteController.stage = stage;
	}

	public static Stage getStage() {
		return stage;
	}

	@FXML
	void onPesquisarNome(ActionEvent event) {
//		clienteDao.searchNome(txtNome.getText());
		
	}

	@FXML
	void onPesquisarCpf(ActionEvent event) {
//		cliente = clienteDao.searchCpf(txtCpf.getText());
	}

	@FXML
	void onPesquisarRg(ActionEvent event) {
//		cliente = clienteDao.searchRg(txtRg.getText());
	}

	@FXML
	private void onAbrirCadClienteAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/CadastroCliente.fxml", "Cadastro de Cliente", false);
		setStage(OpenCloseStage.getStage());
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

		clienteDao.remove(id);
		onAtualizarAction();
	}

	@FXML
	public Integer onGetId() {
		Object object = tvCliente.getSelectionModel().getSelectedItem();

		if (object == null) {
			return -1;
		}

		btnRemover.setDisable(false);

		Integer id = ((Cliente) object).getId();
		
		return id != null ? id : -1;
	}

	private void initTable() {
		tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tbcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tbcCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
		tbcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

		tvCliente.setItems(foo());
	}

	private ObservableList<Cliente> foo() {
		clienteDao = new ClienteDAO();

		return FXCollections.observableArrayList(clienteDao.getList());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTable();
	}
}
