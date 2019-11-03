package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VendaController implements Initializable {

	private static Stage stage;

	@FXML private AnchorPane apVenda;
	@FXML private Pane pTop;
	@FXML private Label lblPedido;
	@FXML private TextField txtPedido;
	@FXML private Label lblData;
	@FXML private TextField txtData;
	@FXML private Label lblHora;
	@FXML private TextField txtHora;
	@FXML private Label lblCliente;
	@FXML private TextField txtCpf;
	@FXML private Button btnAddCpf;
	@FXML private TextField txtNome;
	@FXML private Button btnAddNome;
	@FXML private Label lblItem;
	@FXML private TextField txtItem;
	@FXML private Button btnAddItem;
	@FXML private TextField txtDescricaoItem;
	@FXML private Label lblPreCad;
	@FXML private TextField txtPrecCad;
	@FXML private Label lblDescPor;
	@FXML private TextField txtDescPor;
	@FXML private Label lblDescDin;
	@FXML private TextField txtDescDin;
	@FXML private Label lblQtd;
	@FXML private TextField txtQtd;
	@FXML private Label lblEst;
	@FXML private TextField txtEstoque;
	@FXML private Button btnAddProduto;
	@FXML private Label lblValorTotal;
	@FXML private TableView<?> tvItens;
	@FXML private TableColumn<?, ?> tbcItem;
	@FXML private TableColumn<?, ?> tbcCodigo;
	@FXML private TableColumn<?, ?> tbcDescricao;
	@FXML private TableColumn<?, ?> tbcUn;
	@FXML private TableColumn<?, ?> tbcQtd;
	@FXML private TableColumn<?, ?> tbcPrecoUni;
	@FXML private TableColumn<?, ?> tbcDescPor;
	@FXML private TableColumn<?, ?> tbcDescDin;
	@FXML private TableColumn<?, ?> tbcSubTotal;
	@FXML private Pane pBotton;
	@FXML private Label lblDescontos;
	@FXML private Label txtDescontos;
	@FXML private Label lblTotalPago;
	@FXML private Label txtTotalPago;
	@FXML private Label lblTroco;
	@FXML private Label txtTroco;
	@FXML private ButtonBar bbBotoes;
	@FXML private Button btnFinalizar;
	@FXML private Button btnPagamento;
	@FXML private Button btnRemoverItem;
	@FXML private Button btnNovaVenda;
	@FXML private Button btnFinalizar1;
	@FXML private Button btnSair;

	@FXML
	void onSairVendaAction(ActionEvent event) {
		PrincipalController.getStage().close();
	}

	@FXML
	void onAbrirPagamentoAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/Pagamento.fxml", "Visualizar Produto", false);
		setStage(OpenCloseStage.getStage());
	}

	public void setStage(Stage stage) {
		VendaController.stage = stage;
	}

	public static Stage getStage() {
		return stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
