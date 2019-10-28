package br.com.unipar.gdsystem.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.CaixaDAO;
import br.com.unipar.gdsystem.model.Caixa;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class AbrirCaixaController implements Initializable{

	private static Stage stage;
	Caixa caixa = new Caixa();
	CaixaDAO caixaDAO = new CaixaDAO();
	
	@FXML
    private AnchorPane abrirCaixa;

    @FXML
    private Label lblCaixaFechado;

    @FXML
    private TextField txtAbertura;

    @FXML
    private TextField txtValorAbertura;

    @FXML
    private TextField txtFechamento;

    @FXML
    private TextField txtValorFechamento;

    @FXML
    private ButtonBar bbBtn;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancelar;

    @FXML
    void onConfirmarAction(ActionEvent event) {
    	if (!isOpen()) {
    		caixa.setDataAbertura(txtAbertura.getText());
    		caixa.setValorAbertura(new BigDecimal(txtValorAbertura.getText()));
    		caixa.setSituacao("Aberto");
			
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("");
    		alert.setHeaderText("O Caixa foi aberto");
    		alert.showAndWait();
    		
    		lblCaixaFechado.setText("Caixa Aberto");
    		lblCaixaFechado.setTextFill(Paint.valueOf("#00da28"));
    		txtAbertura.setDisable(true);
    		txtValorAbertura.setDisable(true);
    		txtFechamento.setDisable(false);
    		txtValorFechamento.setDisable(false);
    		
		}else {
			Locale locale = new Locale("pt","BR");
	    	GregorianCalendar calendar = new GregorianCalendar();
	    	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm", locale);
			
	    	lblCaixaFechado.setText("Caixa Fechado");
    		lblCaixaFechado.setTextFill(Paint.valueOf("#ff0000"));
    		
    		txtAbertura.setDisable(true);
    		txtValorAbertura.setDisable(true);
    		txtFechamento.setDisable(false);
    		txtValorFechamento.setDisable(false);
    		txtAbertura.setText(formatador.format(calendar.getTime()));
 
    		caixa.setDataFechamento(txtFechamento.getText());
    		caixa.setValorFechamento(new BigDecimal(txtValorFechamento.getText()));
    		caixa.setSituacao("Fechado");
    		
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("");
    		alert.setHeaderText("O Caixa foi fechado");
    		alert.showAndWait();
		}

    	caixaDAO.caixa(caixa);
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
    	OpenCloseStage.getStage().close();
    }

    public static void setStage(Stage stage) {
    	AbrirCaixaController.stage = stage;
	}
	
	public Stage getStage() {
		return stage;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Locale locale = new Locale("pt","BR");
    	GregorianCalendar calendar = new GregorianCalendar();
    	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm", locale);
    	
    	if(isOpen()) {
    		lblCaixaFechado.setText("Caixa Aberto");
    		lblCaixaFechado.setTextFill(Paint.valueOf("#00da28"));
    		txtAbertura.setText(caixaDAO.getDataAbertura());
    		txtValorAbertura.setText(caixaDAO.getValorAbertura());
    		txtAbertura.setDisable(true);
    		txtValorAbertura.setDisable(true);
    		txtFechamento.setDisable(false);
    		txtValorFechamento.setDisable(false);
    		txtFechamento.setText(formatador.format(calendar.getTime()));
    	}else {
    		lblCaixaFechado.setText("Caixa Fechado");
    		lblCaixaFechado.setTextFill(Paint.valueOf("#ff0000"));
    		txtAbertura.setDisable(false);
    		txtValorAbertura.setDisable(false);
    		txtFechamento.setDisable(true);
    		txtValorFechamento.setDisable(true);
    		txtAbertura.setText(formatador.format(calendar.getTime()));
    	}
	}
	
	private Boolean isOpen() {
    	return caixaDAO.isOpen() ? true : false;
	}
}