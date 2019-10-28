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
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FecharCaixaController implements Initializable{

	Caixa caixa = new Caixa();
	CaixaDAO caixaDAO = new CaixaDAO();
	BigDecimal valorAbertura;
	
	
	@FXML
    private Label lblCaixaAberto;

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
    	caixa.setDataFechamento(txtFechamento.getText());
    	caixa.setValorFechamento(new BigDecimal(txtValorFechamento.getText()));
    	caixa.setSituacao("Fechado");
    	caixa.setDiferenca(valorAbertura.subtract(new BigDecimal(txtValorFechamento.getText())));
    	
    	caixaDAO.caixa(caixa);
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("");
		alert.setHeaderText("O Caixa foi fechado");
		alert.showAndWait();
		
		btnOk.setDisable(true);
		lblCaixaAberto.setText("Caixa fechado");
		lblCaixaAberto.setTextFill(Paint.valueOf("#ff0000"));
		btnCancelar.setText("Sair");
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
    	OpenCloseStage.getStage().close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		caixa = caixaDAO.getCaixa();
		
		txtAbertura.setText(caixa.getDataAbertura());
		txtValorAbertura.setText(caixa.getValorAbertura().toString());
		valorAbertura = caixa.getValorAbertura();
		
		Locale locale = new Locale("pt","BR");
    	GregorianCalendar calendar = new GregorianCalendar();
    	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm", locale);
    	
    	txtFechamento.setText(formatador.format(calendar.getTime()));
	}


}
