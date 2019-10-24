package br.com.unipar.gdsystem.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenCloseStage {
	
	public static Stage stage;
	public static Class thisClass;

	public OpenCloseStage() {
		OpenCloseStage.thisClass = getClass();
	}
	
	public static void loadStage(String nameFile, String titlePage, Boolean isResizable) {
		try {
			Parent parent = FXMLLoader.load(thisClass.getClass().getResource(nameFile));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setTitle(titlePage);
			stage.setScene(scene);
			stage.setResizable(isResizable);
			setStage(stage);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeStage(Stage stage) {
		stage.close();
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		OpenCloseStage.stage = stage;
	}
}
