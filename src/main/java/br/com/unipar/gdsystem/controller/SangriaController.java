package br.com.unipar.gdsystem.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.CaixaDAO;
import br.com.unipar.gdsystem.dao.SangriaDAO;
import br.com.unipar.gdsystem.model.Caixa;
import br.com.unipar.gdsystem.model.Sangria;
import br.com.unipar.gdsystem.util.AlertUTIL;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SangriaController implements Initializable{

	private SangriaDAO sangriaDao = new SangriaDAO();
	private CaixaDAO caixaDao = new CaixaDAO();
	private Sangria sangria = new Sangria();
	private Caixa caixa = new Caixa();
	
	
    @FXML private Label lblSaldoAtual;
    @FXML private TextField txtSaldoAtual;
    @FXML private Label lblValorSangria;
    @FXML private TextField txtValorSangria;
    @FXML private Label lblValorPosSangria;
    @FXML private TextField txtValorPosSangria;
    @FXML private Label lblMotivo;
    @FXML private TextArea txtMotivo;
    @FXML private ButtonBar bbBtn;
    @FXML private Button btnOk;
    @FXML private Button btnCancelar;

    @FXML
    void onConfirmarAction(ActionEvent event) {
    	if (txtValorSangria.getText().equals("")) {
    		AlertUTIL.alertWarning("", "Informe o valor da sangria");
    		return;
		}
    	
    	if (txtMotivo.getText().equals("")) {
    		AlertUTIL.alertWarning("", "Informe o motivo");
    		return;
    	}
    	
    	
    	
    	realizarSangria();
    	txtValorSangria.setEditable(false);
    	txtMotivo.setEditable(false);
    }
    
    @FXML
    void onCancelarAction(ActionEvent event) {
    	OpenCloseStage.getStage().close();
    }

	private void realizarSangria() {
		txtValorPosSangria.setText(calcularValorSangria());
    	
    	sangria.setCaixa(caixaDao.getCaixa());
    	sangria.setSaldoAtual(new BigDecimal(txtSaldoAtual.getText()));
    	sangria.setValorSangria(new BigDecimal(txtValorSangria.getText()));
    	sangria.setValorPosSangria(new BigDecimal(txtValorPosSangria.getText()));
    	sangria.setMotivo(txtMotivo.getText());
    	
    	caixa = caixaDao.getCaixa();
    	caixa.setValorAtual(new BigDecimal(txtValorPosSangria.getText()));

    	CaixaDAO.update(caixa);
    	sangriaDao.add(sangria);
    	
    	btnOk.setVisible(false);
    	btnCancelar.setText("Sair");
    	
    	AlertUTIL.alertInformation("", "Sangria realizada com sucesso");
	}

    private String calcularValorSangria() {
    	return String.valueOf(new BigDecimal((txtSaldoAtual.getText())).subtract(new BigDecimal((txtValorSangria.getText()))));
    }

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		txtSaldoAtual.setText(String.valueOf(caixaDao.getCaixa().getValorAtual()));
	}
}
