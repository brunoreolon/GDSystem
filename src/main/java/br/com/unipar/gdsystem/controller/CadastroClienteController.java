package br.com.unipar.gdsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.ClienteDAO;
import br.com.unipar.gdsystem.model.Cliente;
import br.com.unipar.gdsystem.util.AlertUTIL;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastroClienteController implements Initializable {

	private static Stage stage;
	
	@FXML private AnchorPane apCadCliente;
	@FXML private Pane pInformacao;
	@FXML private Label lblNome;
	@FXML private TextField txtNome;
	@FXML private Label lblCpf;
	@FXML private TextField txtCpf;
	@FXML private Label lblRg;
	@FXML private TextField txtRg;
	@FXML private Label lblSexo;
	@FXML private RadioButton rbMasc;
	@FXML private RadioButton rbFem;
	@FXML private Label lblNascimento;
	@FXML private DatePicker cNascimento;
	@FXML private Pane pEndereco;
	@FXML private Label lblCep;
	@FXML private TextField txtCep;
	@FXML private Label lblNumero;
	@FXML private TextField txtNumero;
	@FXML private Label lblEndereco;
	@FXML private TextField txtEndereco;
	@FXML private Label lblBairro;
	@FXML private TextField txtBairro;
	@FXML private Label lblCidade;
	@FXML private TextField txtCidade;
	@FXML private Label lblReferencia;
	@FXML private TextField txtReferencia;
	@FXML private Label lblComplemento;
	@FXML private TextField txtComplemento;
	@FXML private Pane pContato;
	@FXML private Label lblTelefone;
	@FXML private TextField txtTelefone;
	@FXML private Label lblCelular;
	@FXML private TextField txtCelular;
	@FXML private Label lblEmail;
	@FXML private TextField txtEmail;
	@FXML private Button btnAdicionar;

	@FXML
	void onCadastrarClienteAction(ActionEvent event) {
		if (isEmpty()) {
			AlertUTIL.alertWarning("Aviso", "Campos com * são obrigatórios");

			return;
		}

		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();

		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtCpf.getText());
		cliente.setRg(txtRg.getText());
		cliente.setCep(txtCep.getText());
		cliente.setEndereco(txtEndereco.getText());
		cliente.setBairro(txtBairro.getText());
		cliente.setNumero(txtNumero.getText());
		cliente.setCidade(txtCidade.getText());
		cliente.setReferencia(txtReferencia.getText());
		cliente.setComplemento(txtComplemento.getText());
		cliente.setTelefone(txtTelefone.getText());
		cliente.setCelular(txtCelular.getText());
		cliente.setEmail(txtEmail.getText());

		clienteDAO.add(cliente);
		
		AlertUTIL.alertInformation("Caixa de diálogo Informações", "Cliente cadastrado com sucesso!");

		reset();
	}

	@FXML
	void onCancelarAction(ActionEvent event) {
		reset();
		OpenCloseStage.getStage().close();
	}

	private void reset() {
		txtNome.setText("");
		txtCpf.setText("");
		txtRg.setText("");
		txtCep.setText("");
		txtEndereco.setText("");
		txtBairro.setText("");
		txtNumero.setText("");
		txtReferencia.setText("");
		txtComplemento.setText("");
		txtCidade.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtEmail.setText("");
	}

	private Boolean isEmpty() {
		return txtNome.getText().isEmpty() || 
				txtCpf.getText().isEmpty() || 
				txtTelefone.getText().isEmpty() || 
				txtCelular.getText().isEmpty() || 
				txtEmail.getText().isEmpty();
	}

	public static void setStage(Stage stage) {
		CadastroClienteController.stage = stage;
	}
	
	public Stage getStage() {
		return stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
