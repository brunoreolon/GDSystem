package br.com.unipar.gdsystem.controller;

import java.io.IOException;

import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class VisualizarClienteController {

	private static Stage stage;
	
	@FXML
    private AnchorPane apVisualizarCliente;

    @FXML
    private HBox hbFiltro;

    @FXML
    private TableView<?> tvCliente;

    @FXML
    private TableColumn<?, ?> tbcId;

    @FXML
    private TableColumn<?, ?> tbcNome;

    @FXML
    private TableColumn<?, ?> tbcCpf;

    @FXML
    private TableColumn<?, ?> tbcTelefone;

    @FXML
    private TableColumn<?, ?> tbcCelular;

    @FXML
    private TableColumn<?, ?> tbcEndereco;

    @FXML
    private Button btnAdicionar;

    @FXML
    void onAbrirCadClienteAction(ActionEvent event) throws IOException {
    	OpenCloseStage.loadStage("/br/com/unipar/gdsystem/view/CadastroCliente.fxml", "Cadastro de Cliente", false);
    	setStage(OpenCloseStage.getStage());
    }

	public void setStage(Stage stage) {
		VisualizarClienteController.stage = stage;
	}
	
	public static Stage getStage() {
		return stage;
	}
}
