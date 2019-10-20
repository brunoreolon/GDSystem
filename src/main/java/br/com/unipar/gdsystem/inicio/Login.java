package br.com.unipar.gdsystem.inicio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

	private static Stage stage;

	@Override
	public void start(Stage stage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/br/com/unipar/gdsystem/view/Principal.fxml"));
			Scene scene = new Scene(parent);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Login");
			setStage(stage);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Login.stage = stage;
	}
}
