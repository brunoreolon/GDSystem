package br.com.unipar.gdsystem.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.CaixaDAO;
import br.com.unipar.gdsystem.enums.Situacao;
import br.com.unipar.gdsystem.model.Caixa;
import br.com.unipar.gdsystem.util.AlertUTIL;
import br.com.unipar.gdsystem.util.DataHoraUTIL;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class AbrirFecharCaixaController implements Initializable{

	private static Stage stage;
	private Caixa caixa = new Caixa();
	private CaixaDAO caixaDAO = new CaixaDAO();
	private BigDecimal diferenca;
	
    @FXML private Label lblTitulo;
    @FXML private Label lblSubTitulo;
	@FXML private AnchorPane abrirCaixa;
    @FXML private Label lblCaixaFechado;
    @FXML private TextField txtAbertura;
    @FXML private TextField txtValorAbertura;
    @FXML private TextField txtFechamento;
    @FXML private TextField txtValorFechamento;
    @FXML private ButtonBar bbBtn;
    @FXML private Button btnOk;
    @FXML private Button btnCancelar;

    @FXML
    void onConfirmarAction(ActionEvent event) {
    	if (isOpen()) {
    		Caixa caixaUp = CaixaDAO.getCaixa();
    		caixaUp.setDataFechamento(Calendar.getInstance());
    		caixaUp.setValorFechamento(new BigDecimal(txtValorFechamento.getText()));
    		caixaUp.setSituacao(Situacao.FECHADO);
        	
        	diferenca = caixaUp.getValorAbertura().subtract(new BigDecimal(txtValorFechamento.getText()));
        	caixaUp.setDiferenca(diferenca);

        	caixaDAO.update(caixaUp);
        	
        	AlertUTIL.alertConfirmation("", "O Caixa foi fechado");
    		
    		lblTitulo.setText("Caixa Fechado");
    		lblSubTitulo.setText("O caixa foi fechado!");
    		lblCaixaFechado.setText("Caixa Fechado");
    		lblCaixaFechado.setTextFill(Paint.valueOf("#ff0000"));
    		txtAbertura.setDisable(true);
    		txtValorAbertura.setDisable(true);
    		txtFechamento.setDisable(true);
    		txtValorFechamento.setDisable(true);
    		
    		btnOk.setDisable(true);
        	btnCancelar.setText("Sair");
        	
        	PrincipalController.principalController.setLblAbertoFechado(false);
        	
    		return;
		}
    	
    	caixa.setDataAbertura(Calendar.getInstance());
    	caixa.setValorAbertura(new BigDecimal(txtValorAbertura.getText()));
    	caixa.setSituacao(Situacao.ABERTO);
    	
    	caixaDAO.add(caixa);
    	
    	AlertUTIL.alertConfirmation("", "O Caixa foi aberto");
    	
    	PrincipalController.principalController.setLblAbertoFechado(true);
    	
		OpenCloseStage.getStage().close();;
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
    	OpenCloseStage.getStage().close();
    }

    public static void setStage(Stage stage) {
    	AbrirFecharCaixaController.stage = stage;
	}
	
	public Stage getStage() {
		return stage;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(!isOpen()) {
    		abrirCaixa();
    	}else {
    		fecharCaixa();
    	}
	}

	private void abrirCaixa() {
		lblTitulo.setText("Abrir Caixa");
		lblSubTitulo.setText("Para abrir o caixa informe o valor de abertura");
		lblCaixaFechado.setText("Caixa Fechado");
		lblCaixaFechado.setTextFill(Paint.valueOf("#ff0000"));
		txtAbertura.setDisable(false);
		txtValorAbertura.setDisable(false);
		txtFechamento.setDisable(true);
		txtValorFechamento.setDisable(true);
		txtAbertura.setText(DataHoraUTIL.getDataHora());
	}

	private void fecharCaixa() {
		lblTitulo.setText("Fechar Caixa");
		lblSubTitulo.setText("Para fechar o caixa informe o valor de fechamento");
		lblCaixaFechado.setText("Caixa Aberto");
		lblCaixaFechado.setTextFill(Paint.valueOf("#00da28"));
		txtAbertura.setText(DataHoraUTIL.converterDataHoraString(CaixaDAO.getDataAbertura()));
		txtValorAbertura.setText(caixaDAO.getValorAbertura());
		txtAbertura.setDisable(true);
		txtValorAbertura.setDisable(true);
		txtFechamento.setDisable(false);
		txtValorFechamento.setDisable(false);
		txtFechamento.setText(DataHoraUTIL.getDataHora());
	}
	
	private Boolean isOpen() {
    	return caixaDAO.isOpen() ? true : false;
	}
}