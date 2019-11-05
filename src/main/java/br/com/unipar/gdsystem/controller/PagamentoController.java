package br.com.unipar.gdsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.enums.FormasPagamentoEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PagamentoController implements Initializable {

	@FXML private AnchorPane apPagamento;
	@FXML private ComboBox<FormasPagamentoEnum> cbFormaPagamento;
	@FXML private StackPane spFormasPagamento;
	@FXML private Pane pBoleto;
	@FXML private TextField txtValorCompra;
	@FXML private TextField txtValorRecebido;
	@FXML private TextField txtTroco;
	@FXML private Pane pCrediario;
	@FXML private TextField txtValorCompraCred;
	@FXML private Spinner<?> spParcelas;
	@FXML private TableView<?> tvParcelas;
	@FXML private TableColumn<?, ?> tbcParcela;
	@FXML private TableColumn<?, ?> tbcValor;
	@FXML private Pane pCredito;
	@FXML private Pane pDebito;
	@FXML private ButtonBar bbBtn;
	@FXML private Button btnOk;
	@FXML private Button btnCancelar;

	@FXML
	void onOkAction(ActionEvent event) {
		VendaController.vendaController.setFinalizarVisivel(false);
		
		VendaController.getStage().close();
	}

	@FXML
	void onCancelarAction(ActionEvent event) {
		VendaController.getStage().close();
	}
	@FXML
	void onSelecionadoAction(ActionEvent event) {
		if (cbFormaPagamento.getSelectionModel().getSelectedIndex() == 0) {
			setPaneVisivel(true, false, false, false);
		}
		if (cbFormaPagamento.getSelectionModel().getSelectedIndex() == 1) {
			setPaneVisivel(false, true, false, false);
		}
		if (cbFormaPagamento.getSelectionModel().getSelectedIndex() == 2) {
			setPaneVisivel(false, false, true, false);
		}
		if (cbFormaPagamento.getSelectionModel().getSelectedIndex() == 3) {
			setPaneVisivel(false, false, false, true);
		}
	}

	private void setPaneVisivel(Boolean boleto, Boolean crediario, Boolean credito, Boolean debito) {
		pBoleto.setVisible(boleto);
		pCrediario.setVisible(crediario);
		pCredito.setVisible(credito);
		pDebito.setVisible(debito);
	}
	
	private void loadComboBox() {
		cbFormaPagamento.getItems().addAll(FormasPagamentoEnum.values());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadComboBox();
	}

}
