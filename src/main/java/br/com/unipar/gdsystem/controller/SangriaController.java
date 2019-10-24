package br.com.unipar.gdsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SangriaController implements Initializable{

    @FXML
    private Label lblSaldoAtual;

    @FXML
    private TextField txtSaldoAtual;

    @FXML
    private Label lblValorSangria;

    @FXML
    private TextField txtValorSangria;

    @FXML
    private Label lblValorPosSangria;

    @FXML
    private TextField txtVAlorPosSangria;

    @FXML
    private Label lblMotivo;

    @FXML
    private TextArea txtMotivo;

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
		alert.setHeaderText("Sangria realizada com sucesso");
		alert.showAndWait();
    }
    
    @FXML
    void onCancelarAction(ActionEvent event) {
    	OpenCloseStage.getStage().close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
