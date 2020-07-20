package application;
import javafx.animation.KeyFrame;
import javafx.scene.layout.StackPane;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private TableView<Stocks> table;
	//private String[] label1 = new String[16];
	private ObservableList<Stocks> label1 = FXCollections.observableArrayList();
	private ObservableList<Stocks> number = FXCollections.observableArrayList();


	
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
		
		// creating the table
		table = new TableView<>();
		table.setEditable(true);
		TableColumn<Stocks,Stocks> firstCol = new TableColumn<>("");
		firstCol.setPrefWidth(160);
		TableColumn<Stocks,Stocks> secondCol = new TableColumn<>(" ");
		secondCol.setPrefWidth(200);
		table.setPlaceholder(new Label("Nothing to display"));
		table.getColumns().clear();
		table.getItems().clear();
		firstCol.setCellValueFactory(new PropertyValueFactory<>("stockName"));
		secondCol.setCellValueFactory(new PropertyValueFactory<>("stockPrice"));
		
		table.setItems(label1);
		table.getColumns().add(firstCol);
		table.getColumns().add(secondCol);

		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(0,0,10,10));
		
		GridPane.setFillWidth(label, true);
		GridPane.setFillWidth(button, true);

		label.setMaxWidth(Double.MAX_VALUE);
		GridPane.setHalignment(label, HPos.CENTER);
		
		button.setMaxSize(400, 400);
		
		
		EventHandler<ActionEvent> x = value -> {
			table.getItems().clear();
			action(textField.getText());
			table.setItems(label1);
		};
		
		button.setOnAction(x);
		
		
		gp.add(title, 0, 0,2,1);
		gp.add(label,0,1);
		gp.add(textField, 1, 1);
		gp.add(button, 3, 1, 5, 1);
		gp.add(table, 0, 3,9,5);
		
		Scene scene = new Scene(gp,350,400);
		stage.setTitle("Yahoo Webscraper");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		
	}
	
	public void action(String text) {
		Document website;
		try {
			website = Jsoup.connect("https://finance.yahoo.com/quote/" + text.toUpperCase() + "?p=" + text.toUpperCase()).get();
			Elements prices = website.getElementsByClass("Ta(end) Fw(600) lh(14px)"); //
			Elements names = website.getElementsByClass("C($primaryColor) W(51%)");
			int counter = 0;
			while (counter < prices.size()) {
				//label1.add(names.get(counter).text());
				//number.add(prices.get(counter).text());
				//System.out.println(label1.get(counter).toUpperCase() + ": \t" + number.get(counter));
				label1.add(new Stocks(names.get(counter).text(),prices.get(counter).text()));
				System.out.println(label1.get(counter).getStockName() + ": \t" + 
				label1.get(counter).getStockPrice());
				counter++;
			}

		} catch (Exception e) {
			System.out.println("Invalid Text Provided");
		}
	}
	
	public static class Stocks {
		
		private final SimpleStringProperty stockName;
		private final SimpleStringProperty stockPrice;
		
		private Stocks(String name, String price) {
			this.stockName = new SimpleStringProperty(name);
			this.stockPrice = new SimpleStringProperty(price);
		}
		
		public String getStockPrice() {
			return stockPrice.get();
		}
		
		public String getStockName() {
			return stockName.get();
		}
		
		public void setStockName(String name) {
			stockName.set(name.toUpperCase());
		}
		
		public void setStockPrice(String price) { 
			stockPrice.set(price);
		}
	}
	
	
	

}
