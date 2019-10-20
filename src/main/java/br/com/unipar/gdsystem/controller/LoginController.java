package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.LoginDAO;
import br.com.unipar.gdsystem.inicio.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("campo Usuário e Senha dem ser preenchidos");
			alert.showAndWait();
			
			return;
		}
		
		if (!LoginDAO.validarLogin(txtUsuario.getText(), pwSenha.getText())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Aviso");
			alert.setHeaderText("Usuário ou senha incorreto");
			alert.showAndWait();

			return;
		}
		
		Login.getStage().close();
		principal();
	}
	
	private void principal() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/br/com/unipar/gdsystem/view/Principal.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setResizable(true);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
