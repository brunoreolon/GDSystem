package br.com.unipar.gdsystem.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.CaixaDAO;
import br.com.unipar.gdsystem.dao.SangriaDAO;
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
	private Sangria sangria = new Sangria();
	
    @FXML private Label lblSaldoAtual;
    @FXML private TextField txtSaldoAtual;
    @FXML private Label lblValorSangria;
    @FXML private TextField txtValorSangria;
    @FXML private Label lblValorPosSangria;
    @FXML private TextField txtVAlorPosSangria;
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
    	
    	sangria.setCaixa(CaixaDAO.getCaixaAberto());
    	sangria.setValorSangria(new BigDecimal(txtValorSangria.getText()));
    	sangria.setMotivo(txtMotivo.getText());
    	sangriaDao.add(sangria);
    	
    	btnOk.setDisable(true);
    	btnCancelar.setText("Sair");
    	
    	AlertUTIL.alertConfirmation("", "Sangria realizada com sucesso");
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
