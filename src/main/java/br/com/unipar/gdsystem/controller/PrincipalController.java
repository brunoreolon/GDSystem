package br.com.unipar.gdsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrincipalController implements Initializable{
	
	private static Stage stage;
	
	@FXML
    private AnchorPane apPrincipal;

    @FXML
    private Menu menuVenda;

    @FXML
    private MenuItem miVenda;

    @FXML
    private Menu menuCaixa;

    @FXML
    private MenuItem miAbrirFecharCaixa;

    @FXML
    private MenuItem miRelatorio;

    @FXML
    private MenuItem miSangria;

    @FXML
    private MenuItem Suprimento;

    @FXML
    private Menu menuCadastro;

    @FXML
    private MenuItem miProduto;

    @FXML
    private MenuItem miCliente;

    @FXML
    private Menu menuConfiguracao;

    @FXML
    private MenuItem miCadUsuario;

    @FXML
    private MenuItem miEmpresa;

    @FXML
    private Menu menuHelp;

    @FXML
    private ButtonBar bbPrincipal;

    @FXML
    private Button btnProduto;

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnVenda;
    
    @FXML
    public void onAbrirVendaAction(ActionEvent event) throws IOException {
    	carregarTela("/br/com/unipar/gdsystem/view/Venda.fxml");
    }
    
    @FXML
    void onAbrirCaixaAction(ActionEvent event) throws IOException {
    	carregarTela("/br/com/unipar/gdsystem/view/AbrirCaixa.fxml");
    }

    @FXML
    void onFecharCaixaAction(ActionEvent event) throws IOException {
    	carregarTela("/br/com/unipar/gdsystem/view/fecharCaixa.fxml");
    }

    @FXML
    void onAbrirSangriaAction(ActionEvent event) throws IOException {
    	carregarTela("/br/com/unipar/gdsystem/view/Sangria.fxml");
    }
	
    @FXML
    public void onAbrirCadastroUsuarioAction(ActionEvent event) throws IOException {
		carregarTela("/br/com/unipar/gdsystem/view/CadastroUsuario.fxml");
    }
	
	@FXML
    void onAbrirCliente(ActionEvent event) throws IOException {
		carregarTela("/br/com/unipar/gdsystem/view/VisualizarCliente.fxml");
    }
	
	@FXML
    void onAbrirCadastroCliente(ActionEvent event) throws IOException {
		carregarTela("/br/com/unipar/gdsystem/view/CadastroCliente.fxml");
    }
	
	@FXML
    void onAbrirProduto(ActionEvent event) throws IOException {
		carregarTela("/br/com/unipar/gdsystem/view/VisualizarProduto.fxml");
    }
	
	@FXML
    void onAbrirCadastroProdutoAction(ActionEvent event) throws IOException {
		carregarTela("/br/com/unipar/gdsystem/view/CadastroProduto.fxml");
    }
	
	public void setStage(Stage stage) {
		PrincipalController.stage = stage;
	}
	
	public static Stage getStage() {
		return stage;
	}

	private void carregarTela(String fmxl) throws IOException {
		String caminho = fmxl;
		Parent parent = FXMLLoader.load(getClass().getResource(caminho));
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		setStage(stage);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
