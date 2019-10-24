package br.com.unipar.gdsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AbrirCaixaController implements Initializable{

    @FXML
    private Label lblCaixaFechado;

    @FXML
    private Label lblAbertura;

    @FXML
    private TextField txtAbertura;

    @FXML
    private Label lblValorAbertura;

    @FXML
    private TextField txtValorAbertura;

    @FXML
    private Label lblFechamento;

    @FXML
    private TextField txtFechamento;

    @FXML
    private Label lblValor;

    @FXML
    private TextField txtValor;

    @FXML
    private ButtonBar bbBtn;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancelar;

    @FXML
    void onConfirmarAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("");
		alert.setHeaderText("O Caixa foi aberto");
		alert.showAndWait();
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
    	OpenCloseStage.getStage().close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}