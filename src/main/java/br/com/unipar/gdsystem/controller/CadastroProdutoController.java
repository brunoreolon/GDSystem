package br.com.unipar.gdsystem.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.unipar.gdsystem.dao.ProdutoDAO;
import br.com.unipar.gdsystem.model.Produto;
import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastroProdutoController implements Initializable {

	private static Stage stage;
	
	@FXML private AnchorPane apCadProduto;
	@FXML private Pane pIdentificacao;
	@FXML private TextField txtCodInterno;
//	@FXML private TextField txtCodBarras;
	@FXML private TextField txtDescricao;
	@FXML private Pane pEspecificacao;
	@FXML private TextField txtUnidade;
	@FXML private TextField txtPesoBruto;
	@FXML private TextField txtPesoLiquido;
	@FXML private Pane pEstoque;
	@FXML private TextField txtQtdTotal;
	@FXML private TextField txtQtdMinimo;
	@FXML private Pane pValor;
	@FXML private TextField txtPrecoUnitario;
	@FXML private TextField txtValorMinimo;
	@FXML private TextField txtDescontoMaximo;
	@FXML private Pane pInfoAdcional;
	@FXML private TextField txtMarca;
	@FXML private TextField txtDepartamento;
	@FXML private ButtonBar bbBtn;
	@FXML private Button btnCadastrar;
	@FXML private Button btnCancelar;

	@FXML
	void onCadastrarAction(ActionEvent event) {
		if (empty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("Todos os campos são obrigatórios");
			alert.showAndWait();

			return;
		}

		Produto produto = new Produto();
		ProdutoDAO produtoDAO = new ProdutoDAO();

		produto.setCodigo(Integer.parseInt(txtCodInterno.getText()));
		produto.setDescricao(txtDescricao.getText());
		produto.setUnidade(txtUnidade.getText());
		produto.setPesoBruto(Double.parseDouble(txtPesoBruto.getText()));
		produto.setPesoLiquido(Double.parseDouble(txtPesoLiquido.getText()));
		produto.setQuantidadeTotal(Integer.parseInt(txtQtdTotal.getText()));
		produto.setQuantidadeMinima(Integer.parseInt(txtQtdMinimo.getText()));
		produto.setPrecoUnitario(new BigDecimal(txtPrecoUnitario.getText()));
		produto.setValorMinimo(new BigDecimal(txtValorMinimo.getText()));
		produto.setDescontoMaximo(new BigDecimal(txtDescontoMaximo.getText()));
		produto.setMarca(txtMarca.getText());
		produto.setDepartamento(txtDepartamento.getText());
		
		produtoDAO.add(produto);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Caixa de diálogo Informações");
		alert.setHeaderText("Produto cadastrado com sucesso!");
		alert.showAndWait();

		reset();
	}

	@FXML
	void onCancelarAction(ActionEvent event) {
		reset();
		OpenCloseStage.getStage().close();
	}
	
	private void reset() {
		txtCodInterno.setText("");
		txtDescricao.setText("");
		txtUnidade.setText("");
		txtPesoBruto.setText("");
		txtPesoLiquido.setText("");
		txtQtdTotal.setText("");
		txtQtdMinimo.setText("");
		txtPrecoUnitario.setText("");
		txtValorMinimo.setText("");
		txtDescontoMaximo.setText("");
		txtMarca.setText("");
		txtDepartamento.setText("");
	}

	private Boolean empty() {
		return txtCodInterno.getText().isEmpty() ||  txtDescricao.getText().isEmpty() || 
				txtUnidade.getText().isEmpty() || txtPesoBruto.getText().isEmpty() || txtPesoLiquido.getText().isEmpty() || 
				txtQtdTotal.getText().isEmpty() || txtQtdMinimo.getText().isEmpty() || txtPrecoUnitario.getText().isEmpty() || 
				txtValorMinimo.getText().isEmpty() || txtDescontoMaximo.getText().isEmpty() || txtMarca.getText().isEmpty() || 
				txtDepartamento.getText().isEmpty();
	}

	public static void setStage(Stage stage) {
		CadastroProdutoController.stage = stage;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
