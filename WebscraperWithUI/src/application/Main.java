package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Main class to run Javafx. 
 * @author aakash
 *
 */
public class Main  {
	
	/**
	 * Main method 
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Application.launch(ImageApp.class,args);
		} catch (Exception e) {
			System.out.println("Error invalid input. Please try again.");
		} // try 
	} // main
} // Main 
