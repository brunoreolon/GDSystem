package br.com.unipar.gdsystem.inicio;

import br.com.unipar.gdsystem.util.OpenCloseStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

	public static Stage stage;
	public static Class thisClass;

	public Start() {
		Start.thisClass = getClass();
		new OpenCloseStage();
	}
	

	@Override
	public void start(Stage stage) {
		Start.stage = stage;

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/br/com/unipar/gdsystem/view/Principal.fxml"));
			Scene scene = new Scene(parent);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Login");
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
}