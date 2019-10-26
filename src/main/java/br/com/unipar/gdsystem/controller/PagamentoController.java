package br.com.unipar.gdsystem.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.enums.FormasPagamentoEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PagamentoController implements Initializable {

	@FXML
	private AnchorPane apPagamento;

	@FXML
	private Label lblFormaPAg;

	@FXML
	private ComboBox<FormasPagamentoEnum> cbFormaPagamento;

	@FXML
	private StackPane spFormasPagamento;

	@FXML
	private Pane pBoleto;

	@FXML
	private Label lblValorCompra;

	@FXML
	private TextField txtValorCompra;

	@FXML
	private Label lblValorRecebido;

	@FXML
	private TextField txtValorRecebido;

	@FXML
	private Label lblTroco;

	@FXML
	private TextField txtTroco;

	@FXML
	private Pane pCrediario;

	@FXML
	private TextField txtValorCompraCred;

	@FXML
	private Label lblValorCompraCre;

	@FXML
	private Label lblParcelas;

	@FXML
	private Spinner<?> spParcelas;

	@FXML
	private TableView<?> tvParcelas;

	@FXML
	private TableColumn<?, ?> tbcParcela;

	@FXML
	private TableColumn<?, ?> tbcValor;

	@FXML
	private Pane pCredito;

	@FXML
	private Pane pDebito;

	@FXML
	private ButtonBar bbBtn;

	@FXML
	private Button btnOk;

	@FXML
	private Button btnCancelar;

	@FXML
	void onOkAction(ActionEvent event) {
		VendaController.getStage().close();
	}

	@FXML
	void onCancelarAction(ActionEvent event) {
		VendaController.getStage().close();
	}

	private void loadComboBox() {
		cbFormaPagamento.getItems().addAll(FormasPagamentoEnum.values());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadComboBox();
	}

}
