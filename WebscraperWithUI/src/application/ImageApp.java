package application;
import javafx.animation.KeyFrame;
import javafx.scene.layout.StackPane;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.Separator;
import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.scene.layout.TilePane;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.lang.Runnable;
import java.lang.Thread;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Application;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.*;
import org.jsoup.select.Elements;

public class ImageApp extends Application {

	private VBox vbox;
	private ImageView imgOne;
	private ImageView imgTwo;
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		vbox = new VBox();
		Button button = new Button("Load");
		
		EventHandler<ActionEvent> x = value -> {
			System.out.println("Hello World");
		};
		button.setOnAction(x);
		
		vbox.getChildren().add(button);
		Scene scene = new Scene(vbox);
		stage.setTitle("Yahoo Webscraper");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		
	}
	

}
