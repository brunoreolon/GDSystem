package br.com.unipar.gdsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable{

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button btnLogar;
    @FXML private TextField txtUsuario;
    @FXML private TextField txtSenha;
    @FXML private CheckBox cbLembrar;
    @FXML private ImageView imgUsuario;
    
    @FXML 
    public void onLogarAction(ActionEvent event) {
    	
    }

    @FXML
    void initialize() {
        assert btnLogar != null : "fx:id=\"btnLogar\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtSenha != null : "fx:id=\"txtSenha\" was not injected: check your FXML file 'Login.fxml'.";
        assert cbLembrar != null : "fx:id=\"cbLembrar\" was not injected: check your FXML file 'Login.fxml'.";
        assert imgUsuario != null : "fx:id=\"imgUsuario\" was not injected: check your FXML file 'Login.fxml'.";

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
