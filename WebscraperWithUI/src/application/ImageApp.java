package application;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.event.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Webscrapes Yahoo Finance for information about the searched for stock. 
 * Takes in the stock token and outputs : Previous Close, Open, Bid, Ask,
 * Day's Range, 52 Week Range, Volume, Avg.Volume, Market Cap, Beta, PE Ratio,
 * EPS, Earnings Date, Forward Dividend & Yield, Ex-Dividend Date, and 1y Target
 * Est. Utilizes Jsoup to gather the data from the website. 
 * @author aakash
 * 
 */
public class ImageApp extends Application {

	private Button button;
	private Label label;
	private TextField textField;
	private Text title;
	private TableView<Stocks> table;
	private ObservableList<Stocks> label1 = FXCollections.observableArrayList(); // utilized by TableView 
	// stores all the different stock elements 


	
	/**
	 * Runs the UI interface and displays the application to the user. 
	 * 
	 * @param stage the window that hosts all of the different UI elements 
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		// variable creation 
		GridPane gp = new GridPane();
		//gp.setGridLinesVisible(true);
		title = new Text("YAHOO FINANCE WEBSCRAPER!");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
		button = new Button("Load"); // loads the website details 
		label = new Label("Ticker Symbol: ");
		textField = new TextField();
		
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
		firstCol.setCellValueFactory(new PropertyValueFactory<>("stockName")); // all used for table view 
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
			//action(textField.getText());
			if (action(textField.getText())) { // if an invalid text is placed in the text bar, nothing happens, otherwise button works as intended
				table.setItems(label1);
				firstCol.setText(label1.get(0).getComp());
				secondCol.setText("Current Price: " + label1.get(0).getCurr());
			} // if
			
		}; // eventHandler
		
		button.setOnAction(x);
		
		
		gp.add(title, 0, 0,2,1);
		gp.add(label,0,1);
		gp.add(textField, 1, 1);
		gp.add(button, 3, 1, 5, 1);
		gp.add(table, 0, 3,9,5);
		
		Scene scene = new Scene(gp,360,500);
		stage.setTitle("Yahoo Webscraper");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		
	} // start
	
	public boolean action(String text) {
		Document website;
		try {
			website = Jsoup.connect("https://finance.yahoo.com/quote/" + text.toUpperCase() + "?p=" + text.toUpperCase()).get();
			Elements prices = website.getElementsByClass("Ta(end) Fw(600) lh(14px)"); // scrapes the javascript and html elements of the webiste 
			Elements names = website.getElementsByClass("C($primaryColor) W(51%)");
			Elements companyName = website.getElementsByClass("D(ib) Fz(18px)");
			Elements currentPrice = website.getElementsByClass("Trsdu(0.3s) Fw(b) "
					+ "Fz(36px) Mb(-4px) D(ib)");
			int counter = 0;
			while (counter < prices.size()) {
				label1.add(new Stocks(names.get(counter).text(),prices.get(counter).text()));
				counter++;
			} // while
			label1.get(0).setComp(companyName.text());
			label1.get(0).setCurr(currentPrice.text());
			return true;

		} catch (Exception e) {
			System.out.println("Invalid Text Provided");
			return false;
		} // try 
	} // action
	
	/**
	 * Class used to store the data of the different elements of the {@code Stock} being examined. Utilized by 
	 * {@code TableView} and {@code TableColumn}.
	 * @author aakash
	 *
	 */
	public class Stocks {
		
		private final SimpleStringProperty stockName;
		private final SimpleStringProperty stockPrice;
		private String stockCompanyName;
		private String stockCurrentP;
		
		/**
		 * Constructor for class.
		 * @param name
		 * @param price
		 */
		private Stocks(String name, String price) {
			this.stockName = new SimpleStringProperty(name);
			this.stockPrice = new SimpleStringProperty(price);
			stockCompanyName = "";
			stockCurrentP = "";
		} // Stocks
		
		/*
		 * Provides price of the {@code Stock} when it closed. 
		 * 
		 * @return the {@code Stock} price
		 */
		public String getStockPrice() {
			return stockPrice.get();
		} // getStockPrice
		
		/**
		 * Provides the name of the {@code Stock}.
		 * 
		 * @return the name of the {@code Stock}
		 */
		public String getStockName() {
			return stockName.get();
		} // getStockName
		
		/**
		 * Sets the name of the {@code Stock}
		 * 
		 * @param name what the stock's name, not token, is 
		 */
		public void setStockName(String name) {
			stockName.set(name.toUpperCase());
		} // setStockName
		
		/**
		 * Sets the price of the {@code Stock}
		 * 
		 * @param price the price of the {@code Stock} 
		 */
		public void setStockPrice(String price) { 
			stockPrice.set(price);
		} // setStockPrice
		
		/**
		 * Returns the company name 
		 * @return the name of the company 
		 */
		public String getComp() {
			return this.stockCompanyName;
		} // getComp 
		
		/**
		 * Returns the current {@code Stock} price that fluctuates
		 * 
		 * @return the most current {@code Stock} price, subject to change every refresh 
		 */
		public String getCurr() {
			return this.stockCurrentP;
		} // getCurr
		
		/**
		 * Sets the {@code Stock's} company 
		 * 
		 * @param x the {@code Stock's} company name 
		 */
		public void setComp(String x) {
			stockCompanyName = x;
		} // setComp
		
		/**
		 * Sets the current {@code Stock} price
		 * 
		 * @param x the current {@code Stock} price
		 */
		public void setCurr(String x) {
			stockCurrentP = x;
		} // setCurr
	} // start
	

} // imageView 
