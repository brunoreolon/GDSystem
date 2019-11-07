package br.com.unipar.gdsystem.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.CaixaDAO;
import br.com.unipar.gdsystem.enums.SituacaoEnum;
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
	private CaixaDAO caixaDao = new CaixaDAO();
	
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
    		fecharCaixa();
		}else {
			abrirCaixa();
		}
    }

    @FXML
    void onCancelarAction(ActionEvent event) {
    	OpenCloseStage.getStage().close();
    }

    private void abrirCaixa() {
		caixa.setDataAbertura(Calendar.getInstance());
    	caixa.setValorAbertura(new BigDecimal(txtValorAbertura.getText()));
    	caixa.setValorAtual(new BigDecimal(txtValorAbertura.getText()));
    	caixa.setSituacao(SituacaoEnum.ABERTO);
    	
    	CaixaDAO.add(caixa);
    	
    	AlertUTIL.alertInformation("", "O Caixa foi aberto");
    	
    	PrincipalController.principalController.setLblAbertoFechado(true);
    	
		OpenCloseStage.getStage().close();
	}

	private void fecharCaixa() {
		caixa = caixaDao.getCaixa();
		
		caixa.setDataFechamento(Calendar.getInstance());
		caixa.setValorFechamento(new BigDecimal(txtValorFechamento.getText()));
		caixa.setDiferenca(caixa.getValorAbertura().subtract(new BigDecimal(txtValorFechamento.getText())));
		caixa.setSituacao(SituacaoEnum.FECHADO);

		caixaDao.update(caixa);
		
		AlertUTIL.alertInformation("", "O Caixa foi fechado");
		
		setLbl("Caixa Fechado", "O caixa foi fechado!", "Caixa Fechado", "#ff0000");
		setTxt(true, true, true, true);
		
		btnOk.setDisable(true);
		btnCancelar.setText("Sair");
		
		PrincipalController.principalController.setLblAbertoFechado(false);
	}

    public static void setStage(Stage stage) {
    	AbrirFecharCaixaController.stage = stage;
	}
	
	public Stage getStage() {
		return stage;
	}
    
	private Boolean isOpen() {
		return caixaDao.isOpen();
	}

	private void setLbl(String setTitulo, String setSubTitulo, String setCaixaFechado, String setCor) {
		lblTitulo.setText(setTitulo);
		lblSubTitulo.setText(setSubTitulo);
		lblCaixaFechado.setText(setCaixaFechado);
		lblCaixaFechado.setTextFill(Paint.valueOf(setCor));
	}
	
	private void setTxt(Boolean aberturaDisable, Boolean ValorAberturaDisabe, Boolean FechamentoDisabe, Boolean ValorFechamentotDisabe) {
		txtAbertura.setDisable(aberturaDisable);
		txtValorAbertura.setDisable(ValorAberturaDisabe);
		txtFechamento.setDisable(FechamentoDisabe);
		txtValorFechamento.setDisable(ValorFechamentotDisabe);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(!isOpen()) {
			setLbl("Abrir Caixa", "Para abrir o caixa informe o valor de abertura", "Caixa Fechado", "#ff0000");
			setTxt(false, false, true, true);
			
			txtAbertura.setText(DataHoraUTIL.getDataHoraString());
		}else {
			setLbl("Fechar Caixa", "Para fechar o caixa informe o valor de fechamento", "Caixa Aberto", "#00da28");
			setTxt(true, true, false, false);
			
			txtAbertura.setText(DataHoraUTIL.converterDataHoraString(caixaDao.getDataAbertura()));
			txtValorAbertura.setText(caixaDao.getValorAbertura());
			txtFechamento.setText(DataHoraUTIL.getDataHoraString());
		}
	}
}