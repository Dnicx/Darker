package scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.ConfigurableOption;
import main.Main;
import render.RenderableHolder;

public class MenuScreen extends BorderPane {
	private Canvas headline;
	private VBox menu;
	private Button startBtn;
	// private Button configBtn;
	private int width, height;

	public MenuScreen() {
		width = ConfigurableOption.getInstance().getScreenWidth();
		height = ConfigurableOption.getInstance().getScreenHeight();
		this.setPrefSize(width, height);
		this.setStyle("-fx-background-color:black;");
		RenderableHolder.getInstance().loadResource();

		headline = new Canvas(768, 250);
		GraphicsContext gc = headline.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.getInstance().titlemenu, 234, 50);
		this.setTop(headline);

		menu = new VBox();
		menu.setAlignment(Pos.CENTER);

		startBtn = new Button("New Game");
		startBtn.setPrefHeight(40);
		startBtn.setPrefWidth(140);
		menu.getChildren().add(0, startBtn);

		/*
		 * configBtn=new Button("Config"); configBtn.setPrefHeight(40);
		 * configBtn.setPrefWidth(120); menu.getChildren().add(1, configBtn);
		 */
		this.setCenter(menu);

		/*
		 * configBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {
		 * 
		 * @Override public void handle(KeyEvent event) {
		 * if(event.getCode()==KeyCode.ENTER){
		 * Main.instance.toggleScene("configScene"); }
		 * 
		 * } }); configBtn.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) {
		 * Main.instance.toggleScene("configScene");
		 * 
		 * } });
		 */

		startBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					Main.instance.toggleScene("gameScene");
				}

			}
		});

		startBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.instance.toggleScene("gameScene");

			}
		});
	}

	public void applyResize() {
		this.setPrefSize(ConfigurableOption.getInstance().getScreenWidth(),
				ConfigurableOption.getInstance().getScreenHeight());
	}

}
