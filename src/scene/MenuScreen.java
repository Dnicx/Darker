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
	private Canvas blank;
	private Canvas blank1;
	private Canvas blank2;
	private VBox menu;
	private Button startBtn;
	private Button exitBtn;
	private Button stage1Btn;
	private Button stage2Btn;
	private Button backBtn;
	// private Button configBtn;
	private int width, height;

	public MenuScreen() {
		width = ConfigurableOption.getInstance().getScreenWidth();
		height = ConfigurableOption.getInstance().getScreenHeight();
		this.setPrefSize(width, height);
		this.setStyle("-fx-background-color:black;");
		RenderableHolder.getInstance().loadResource();

		headline = new Canvas(768, 300);
		GraphicsContext gc = headline.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.getInstance().titlemenu, 234, 50);
		this.setTop(headline);

		menu = new VBox();
		menu.setAlignment(Pos.TOP_CENTER);

		startBtn = new Button("New Game");
		startBtn.setPrefHeight(40);
		startBtn.setPrefWidth(140);
		menu.getChildren().add(0, startBtn);
		
		
		blank = new Canvas(768,30);
		menu.getChildren().add(1,blank);
		blank1 = new Canvas(768,10);
		blank2 =new Canvas(768,10);
		
		exitBtn = new Button("Exit");
		exitBtn.setPrefHeight(30);
		exitBtn.setPrefWidth(120);
		menu.getChildren().add(2, exitBtn);
		
		
		stage1Btn = new Button("Stage 1");
		stage1Btn.setPrefSize(120, 30);
		stage2Btn = new Button("Stage 2");
		stage2Btn.setPrefSize(120, 30);
		
		backBtn =new Button("Back");
		backBtn.setPrefSize(120, 30);
		
		
		

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
					menu.getChildren().clear();
					menu.getChildren().add(0, stage1Btn);
					menu.getChildren().add(1,blank1);
					menu.getChildren().add(2, stage2Btn);
					menu.getChildren().add(3, blank2);
					menu.getChildren().add(4, backBtn);
					//Main.instance.toggleScene("gameScene");
					
				}

			}
		});

		startBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				menu.getChildren().clear();
				menu.getChildren().add(0, stage1Btn);
				menu.getChildren().add(1,blank1);
				menu.getChildren().add(2, stage2Btn);
				menu.getChildren().add(3, blank2);
				menu.getChildren().add(4, backBtn);
				//Main.instance.toggleScene("gameScene");

			}
		});
		
		exitBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					System.exit(0);
				}

			}
		});

		exitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);

			}
		});
		
		stage1Btn.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					Main.instance.toggleScene("gameScene");
					menu.getChildren().clear();
					menu.getChildren().add(0,startBtn);
					menu.getChildren().add(1, blank);
					menu.getChildren().add(2, exitBtn);
					
				}

			}
		});

		stage1Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.instance.toggleScene("gameScene");
				menu.getChildren().clear();
				menu.getChildren().add(0,startBtn);
				menu.getChildren().add(1, blank);
				menu.getChildren().add(2, exitBtn);

			}
		});

		
		stage2Btn.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					Main.instance.toggleScene("gameScene");
					menu.getChildren().clear();
					menu.getChildren().add(0,startBtn);
					menu.getChildren().add(1, blank);
					menu.getChildren().add(2, exitBtn);
					
				}

			}
		});

		stage2Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.instance.toggleScene("gameScene");
				menu.getChildren().clear();
				menu.getChildren().add(0,startBtn);
				menu.getChildren().add(1, blank);
				menu.getChildren().add(2, exitBtn);

			}
		});
		
		
		backBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					menu.getChildren().clear();
					menu.getChildren().add(0,startBtn);
					menu.getChildren().add(1, blank);
					menu.getChildren().add(2, exitBtn);
					
				}

			}
		});

		backBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				menu.getChildren().clear();
				menu.getChildren().add(0,startBtn);
				menu.getChildren().add(1, blank);
				menu.getChildren().add(2, exitBtn);

			}
		});
		
	}

	public void applyResize() {
		this.setPrefSize(ConfigurableOption.getInstance().getScreenWidth(),
				ConfigurableOption.getInstance().getScreenHeight());
	}

}
