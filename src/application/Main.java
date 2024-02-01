package application;
	

import application.telas.Tela1;
import application.telas.Tela2;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	public void start(Stage primaryStage) {
		
		Tela1 tela1 = new Tela1();
		Tela2 tela2 = new Tela2(tela1); 
				
		HBox vboxPrincipal = new HBox(tela1,tela2);
		HBox.setHgrow(tela1, Priority.ALWAYS);
        HBox.setHgrow(tela2, Priority.ALWAYS);
		
		//*****************CSS*********************//
		tela1.getStyleClass().add("Tela1");
		tela2.getStyleClass().add("Tela2");
		
		vboxPrincipal.getStyleClass().add("TelaPrincipal");
		
		
		//****************************************//
		
		Scene scene = new Scene(vboxPrincipal , 1300, 650);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("ASCII");
		primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
	    primaryStage.setScene(scene);
	    primaryStage.show();
		
		
	}


    public static void main(String[] args) {
        launch(args);
    }

}
