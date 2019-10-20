package br.com.unipar.gdsystem.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class VisualizarClienteController {

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
    	Parent parent = FXMLLoader.load(getClass().getResource("/br/com/unipar/gdsystem/view/CadastroCliente.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
    }
}
