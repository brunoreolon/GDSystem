package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.CaixaDAO;
import br.com.unipar.gdsystem.util.AlertUTIL;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {

	private static Stage stage;
	private CaixaDAO caixaDao = new CaixaDAO();
	public static PrincipalController principalController;

	@FXML private AnchorPane apPrincipal;
	@FXML private Menu menuVenda;
	@FXML private MenuItem miVenda;
	@FXML private Menu menuCaixa;
	@FXML private MenuItem miAbrirFecharCaixa;
	@FXML private MenuItem miRelatorio;
	@FXML private MenuItem miSangria;
	@FXML private MenuItem miSuprimento;
	@FXML private Menu menuCadastro;
	@FXML private MenuItem miProduto;
	@FXML private MenuItem miCliente;
	@FXML private Menu menuConfiguracao;
	@FXML private MenuItem miCadUsuario;
	@FXML private MenuItem miEmpresa;
	@FXML private Menu menuHelp;
	@FXML private ButtonBar bbPrincipal;
	@FXML private Button btnProduto;
	@FXML private Button btnCliente;
	@FXML private Button btnVenda;
	@FXML private Label lblAbertoFechado;
	
	
	@FXML
	public void onAbrirVendaAction(ActionEvent event) throws IOException {
		if(!caixaDao.isOpen()) {
    		AlertUTIL.alertWarning("Caixa fechado", "O Caixa está fechado, para realizar uma venda abra o caixa!");
    		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/AbrirFecharCaixa.fxml", "Vendas", false);
    		return;
    	}
		
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/Venda.fxml", "Vendas", true);
		seStage(OpenCloseStage.getStage());
		
	}

	@FXML
	void onAbrirfecharCaixaAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/AbrirFecharCaixa.fxml", "Abrir/Fechar Caixa", false);
		seStage(OpenCloseStage.getStage());
	}

	@FXML
	void onFecharCaixaAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/FecharCaixa.fxml", "Fechar Caixa", false);
		seStage(OpenCloseStage.getStage());
	}

	@FXML
	void onAbrirSangriaAction(ActionEvent event) throws IOException {
		if(!caixaDao.isOpen()) {
			AlertUTIL.alertWarning("Caixa fechado", "O Caixa está fechado, para realizar uma sangria abra o caixa!");
    		return;
    	}
		
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/Sangria.fxml", "Sangria", false);
		seStage(OpenCloseStage.getStage());
	}

	@FXML
	public void onAbrirCadastroUsuarioAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/CadastroUsuario.fxml", "Cadastro de Usuario", false);
		seStage(OpenCloseStage.getStage());
	}

	@FXML
	void onAbrirCliente(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/VisualizarCliente.fxml", "Visualizar Cliente", true);
		seStage(OpenCloseStage.getStage());
	}

	@FXML
	void onAbrirCadastroCliente(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/CadastroCliente.fxml", "Cadastro de Cliente", false);
		seStage(OpenCloseStage.getStage());
	}

	@FXML
	void onAbrirProduto(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/VisualizarProduto.fxml", "Visualizar Produto", true);
		seStage(OpenCloseStage.getStage());
	}

	@FXML
	void onAbrirCadastroProdutoAction(ActionEvent event) throws IOException {
		OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/CadastroProduto.fxml", "Cadastro de Produto", false);
		seStage(OpenCloseStage.getStage());
	}

	public void setLblAbertoFechado(Boolean abrir) {
		if (abrir) {
			lblAbertoFechado.setText("Caixa Aberto");
			lblAbertoFechado.setTextFill(Paint.valueOf("#00da28"));
			
		}else {
			lblAbertoFechado.setText("Caixa Fechado");
			lblAbertoFechado.setTextFill(Paint.valueOf("#ff0000"));
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public void seStage(Stage stage) {
		PrincipalController.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		principalController = this;
		
		if (caixaDao.isOpen()) {
			setLblAbertoFechado(true);
		}else {
			setLblAbertoFechado(false);
		}
		
	}
}
