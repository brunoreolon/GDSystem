package br.com.unipar.gdsystem.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.enums.FormasPagamentoEnum;
import br.com.unipar.gdsystem.util.AlertUTIL;
import br.com.unipar.gdsystem.util.Crediario;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PagamentoController implements Initializable {

	public static PagamentoController pagamentoController;
	
	private BigDecimal valorTotal = VendaController.vendaController.getValorTotal();
	private BigDecimal troco = new BigDecimal("0");
	private BigDecimal valorRecebido = new BigDecimal("0");
	private BigDecimal caixaAtualizado = new BigDecimal("0");
	private BigDecimal v = new BigDecimal("0");
	private Integer totalParcelas;
	
	@FXML private AnchorPane apPagamento;
	@FXML private ComboBox<FormasPagamentoEnum> cbFormaPagamento;
	@FXML private StackPane spFormasPagamento;
	@FXML private Pane pDinheiro;
	@FXML private TextField txtValorCompra;
	@FXML private TextField txtValorRecebido;
	@FXML private TextField txtTroco;
	@FXML private Pane pCrediario;
	@FXML private TextField txtValorCompraCred;
	@FXML private Spinner<Integer> spParcelas;
	@FXML private TableView<Crediario> tvParcelas;
	@FXML private TableColumn<Crediario, Integer> tbcParcela;
	@FXML private TableColumn<Crediario, BigDecimal> tbcValor;
	@FXML private Pane pCredito;
	@FXML private Pane pDebito;
	@FXML private ButtonBar bbBtn;
	@FXML private Button btnOk;
	@FXML private Button btnCancelar;
	@FXML private Button btnOkP;

	public PagamentoController() {
		pagamentoController = this;
	}

	@FXML
	void onOkAction(ActionEvent event) {
		if (txtValorRecebido.getText().equals("") && pDinheiro.isVisible()) {
			AlertUTIL.alertInformation("", "Informe o valor recebido");
			return;
		}
		
		if (valorRecebido.compareTo(valorTotal) == -1) {
			AlertUTIL.alertInformation("", "Valor insuficiente");
			return;
		}
		
		if (pDinheiro.isVisible()) {
			calcularTroco();
			
			VendaController.vendaController.setLblTotalPago(String.valueOf("R$ " + valorRecebido));
			VendaController.vendaController.setLblTroco("");
			VendaController.vendaController.setLblTroco("R$ " + String.valueOf(troco));
			VendaController.vendaController.setFinalizarVisivel(false);
		}
		
		VendaController.getStage().close();
	}

	@FXML
	void onCancelarAction(ActionEvent event) {
		VendaController.getStage().close();
	}
	@FXML
	void onSelecionadoAction(ActionEvent event) {
		if (cbFormaPagamento.getSelectionModel().getSelectedIndex() == 0) {
			txtValorCompra.setText(String.valueOf(valorTotal));
			setPaneVisivel(true, false, false, false);
		}
		if (cbFormaPagamento.getSelectionModel().getSelectedIndex() == 1) {
			txtValorCompraCred.setText(String.valueOf(valorTotal));
			setPaneVisivel(false, true, false, false);
		}
		if (cbFormaPagamento.getSelectionModel().getSelectedIndex() == 2) {
			setPaneVisivel(false, false, true, false);
		}
		if (cbFormaPagamento.getSelectionModel().getSelectedIndex() == 3) {
			setPaneVisivel(false, false, false, true);
		}
	}
	
	public BigDecimal getCaixaAtualizado() {
		return caixaAtualizado;
	}
	
	 private void calcularTroco() {
		 valorRecebido = new BigDecimal(txtValorRecebido.getText());
		 
		 if (valorRecebido.compareTo(valorTotal) <= 0) {
			 caixaAtualizado = valorRecebido;
			 txtTroco.setText("0,00");
			 return;
		 }
		 
		 troco = valorRecebido.subtract(valorTotal);
		 txtTroco.setText(String.valueOf(troco));
		 caixaAtualizado = valorRecebido.subtract(troco);
	 }
	
	private void setPaneVisivel(Boolean boleto, Boolean crediario, Boolean credito, Boolean debito) {
		pDinheiro.setVisible(boleto);
		pCrediario.setVisible(crediario);
		pCredito.setVisible(credito);
		pDebito.setVisible(debito);
	}
	
	private void loadComboBox() {
		cbFormaPagamento.getItems().addAll(FormasPagamentoEnum.values());
	}
	
	@FXML
	void teste() {
		if (spParcelas.getValue() > 10) {
			return;
		}
		
		totalParcelas = spParcelas.getValue();
	}
	
	
	@FXML
	void onOkPAction() {
		Crediario crediario = null;
		if (spParcelas.getValue() == 0) {
			return;
		}
		crediario = new Crediario(totalParcelas, valorTotal);
		ObservableList<Crediario> list = crediario.observableListCrediario();
		
		listar(list);
	}
	
	private void listar(ObservableList<Crediario> observableList) {
		tbcParcela.setCellValueFactory(new PropertyValueFactory<>("numeroParcelas"));
		tbcValor.setCellValueFactory(new PropertyValueFactory<>("valorParcela"));

		tvParcelas.setItems(observableList);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadComboBox();
		
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
		spParcelas.setValueFactory(valueFactory);
	}

}
