package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main  {
	
	public static void main(String[] args) {
		
		try {
			Application.launch(ImageApp.class,args);
		} catch (Exception e) {
			System.out.println("Error invalid input. Please try again.");
		}
	}
}
