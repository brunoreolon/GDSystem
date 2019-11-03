package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.LoginDAO;
import br.com.unipar.gdsystem.inicio.Start;
import br.com.unipar.gdsystem.util.AlertUTIL;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private ImageView imgUsuario;
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField pwSenha;
	@FXML
	private CheckBox cbLembrar;
	@FXML
	private Button btnLogar;

	@FXML
	public void onLogarAction(ActionEvent event) throws IOException {
		logar();
	}
	
	private void logar() throws IOException {
		if (txtUsuario.getText().isEmpty() || pwSenha.getText().isEmpty()) {
			AlertUTIL.alertWarning("Aviso", "campo Usuário e Senha devem ser preenchidos");
			
			return;
		}
		
		if (!LoginDAO.validarLogin(txtUsuario.getText(), pwSenha.getText())) {
			AlertUTIL.alertError("Aviso", "Usuário ou senha incorreto");

			return;
		}
		
		Start.getStage().close();
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/Principal.fxml", "Principal", true);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image image = new Image("imagens/usuario.png");
		imgUsuario.setImage(image);
		imgUsuario.setCache(true);
	}
}
