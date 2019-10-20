package br.com.unipar.gdsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.UsuarioDAO;
import br.com.unipar.gdsystem.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CadastroUsuarioController implements Initializable {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private Label lblNome;
	@FXML
	private TextField txtNome;
	@FXML
	private Label lblCpf;
	@FXML
	private TextField txtCpf;
	@FXML
	private Label lblUsuario;
	@FXML
	private TextField txtUsuario;
	@FXML
	private Label lblCracha;
	@FXML
	private TextField txtCracha;
	@FXML
	private Label lblAcesso;
	@FXML
	private ComboBox<String> cbAcesso;
	@FXML
	private Label lblEmail;
	@FXML
	private TextField txtEmail;
	@FXML
	private Label lblTelefone;
	@FXML
	private TextField txtTelefone;
	@FXML
	private Label lblCelular;
	@FXML
	private TextField txtCelular;
	@FXML
	private ImageView imgPerfil;
	@FXML
	private Button btnaddImgPerfil;
	@FXML
	private Label lblSenha;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private Label lblConfSenha;
	@FXML
	private PasswordField txtConfSenha;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnSair;

	@FXML
	public void onSalvarAction() {
		if (empty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("Todos os campos devem ser preenchidos");
			alert.showAndWait();

			return;
		}

		if (!txtConfSenha.getText().equals(txtSenha.getText())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Senha");
			alert.setHeaderText("A confirmação da senha não confere.");
			alert.showAndWait();

			return;
		}

		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuario.setNome(txtNome.getText());
		usuario.setCpf(txtCpf.getText());
		usuario.setUsuario(txtUsuario.getText());
		usuario.setCracha(txtCracha.getText());
		usuario.setEmail(txtEmail.getText());
		usuario.setTelefone(txtTelefone.getText());
		usuario.setCelular(txtCelular.getText());
		usuario.setSenha(txtSenha.getText());

		usuarioDAO.add(usuario);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Caixa de diálogo Informações");
		alert.setHeaderText("Usuário salvo com sucesso!");
		alert.showAndWait();

		reset();
	}

	@FXML
	void onSairAction(ActionEvent event) {
		reset();
		PrincipalController.getStage().close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	private void reset() {
		txtNome.setText("");
		txtCpf.setText("");
		txtUsuario.setText("");
		txtCracha.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtSenha.setText("");
		txtConfSenha.setText("");
	}

	private Boolean empty() {
		return txtNome.getText().isEmpty() || txtCpf.getText().isEmpty() || txtUsuario.getText().isEmpty()
				|| txtCracha.getText().isEmpty() || txtEmail.getText().isEmpty() || txtSenha.getText().isEmpty()
				|| txtConfSenha.getText().isEmpty();
	}

}
