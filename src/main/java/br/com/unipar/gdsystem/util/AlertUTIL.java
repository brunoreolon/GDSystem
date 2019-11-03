package br.com.unipar.gdsystem.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUTIL {

	private static Alert alert;

	public static void alertConfirmation(String titulo, String mensagem) {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		alert.showAndWait();
	}

	public static void alertInformation(String titulo, String mensagem) {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		alert.showAndWait();
	}

	public static void alertWarning(String titulo, String mensagem) {
		alert = new Alert(AlertType.WARNING);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		alert.showAndWait();
	}

	public static void alertError(String titulo, String mensagem) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		alert.showAndWait();
	}
}
