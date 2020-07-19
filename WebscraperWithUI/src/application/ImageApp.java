package application;
import javafx.animation.KeyFrame;
import javafx.scene.layout.StackPane;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Separator;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
import javafx.scene.input.MouseEvent;

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
	private Button button;
	private Label label;
	private TextField textField;
	private HBox hb;
	private Text title;
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		// variable creation 
		GridPane gp = new GridPane();
		gp.setGridLinesVisible(true);
		title = new Text("YAHOO WEBSCRAPER!");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
		vbox = new VBox();
		button = new Button("Load"); // loads the website details 
		label = new Label("Ticker Symbol: ");
		textField = new TextField();
		hb = new HBox();
		
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(0,10,10,10));
		
		GridPane.setFillWidth(label, true);
		GridPane.setFillWidth(button, true);

		label.setMaxWidth(Double.MAX_VALUE);
		GridPane.setHalignment(label, HPos.CENTER);
		
		button.setMaxSize(400, 400);
		
		
		EventHandler<ActionEvent> x = value -> {
			System.out.println("Hello World");

		};
		
		button.setOnAction(x);
		
		
		vbox.getChildren().addAll(hb);
		gp.add(title, 0, 0,2,1);
		gp.add(label,0,1);
		gp.add(textField, 1, 1);
		gp.add(button, 3, 1, 2, 1);
		
		Scene scene = new Scene(gp,350,400);
		stage.setTitle("Yahoo Webscraper");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		
	}
	
	
	

}
