package scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
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
	
	private static MenuScreen instance;
	private Canvas headline;
	private Canvas blank;
	private Canvas blank1;
	private Canvas blank2;
	private VBox menu;
	private VBox menu1;
	private Button startBtn;
	private Button exitBtn;
	private Button stage1Btn;
	private Button stage2Btn;
	private Button backBtn;
	// private Button configBtn;
	private ImageView test1;
	private ImageView test2;
	private Boolean isSelectStartBtn=false;
	private Boolean isSelectExitBtn =false;
	private Boolean isPushStartBtn =false;
	private Boolean isPushExitBtn = false;
	private int width, height;

	public MenuScreen() {
		instance=this;
		width = ConfigurableOption.getInstance().getScreenWidth();
		height = ConfigurableOption.getInstance().getScreenHeight();
		this.setPrefSize(width, height);
		this.setStyle("-fx-background-color:black;");
		RenderableHolder.getInstance().loadResource();

		headline = new Canvas(768, 300);
		GraphicsContext gc = headline.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.getInstance().titlemenu, 234, 50);
		this.setTop(headline);
		
		test1= new ImageView(RenderableHolder.getInstance().startBtn);
		test2= new ImageView(RenderableHolder.getInstance().startBtn);
		
		


	

		menu = new VBox();
		menu.setAlignment(Pos.TOP_CENTER);

		startBtn = new Button("New Game");
		startBtn.setPrefHeight(40);
		startBtn.setPrefWidth(140);
		startBtn.setGraphic(test2);
		menu.getChildren().add(0, startBtn);
		
		
		blank = new Canvas(768,30);
		menu.getChildren().add(1,blank);
		blank1 = new Canvas(768,10);
		blank2 =new Canvas(768,10);
		
		exitBtn = new Button("Exit");
		exitBtn.setPrefHeight(30);
		exitBtn.setPrefWidth(120);
		menu.getChildren().add(2, exitBtn);
		menu.getChildren().add(3,test1);
		
		
		
		
		
		
		

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


		startBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				test2.setImage(RenderableHolder.getInstance().startBtnEnter);
				test1.setImage(RenderableHolder.getInstance().startBtn);
				
			}
		});


		startBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				Main.instance.toggleScene("gameScene");
				
			}
		});
		startBtn.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				test2.setImage(RenderableHolder.getInstance().startBtnClick);	
				test1.setImage(RenderableHolder.getInstance().startBtn);	
			}
		});
		startBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				test2.setImage(RenderableHolder.getInstance().startBtn);
				test1.setImage(RenderableHolder.getInstance().startBtn);
				
			}
		});
		
		exitBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				test1.setImage(RenderableHolder.getInstance().startBtnEnter);
				test2.setImage(RenderableHolder.getInstance().startBtn);
				
				
			}
		});
		exitBtn.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				test1.setImage(RenderableHolder.getInstance().startBtnClick);	
				test2.setImage(RenderableHolder.getInstance().startBtn);	
			}
		});

		exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				System.exit(0);

			}
		});
		exitBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				test1.setImage(RenderableHolder.getInstance().startBtn);
				test2.setImage(RenderableHolder.getInstance().startBtn);
				
			}
		});
		
		
		
	}

	public void applyResize() {
		this.setPrefSize(ConfigurableOption.getInstance().getScreenWidth(),
				ConfigurableOption.getInstance().getScreenHeight());
	}
	
	public void setmenu(int select){
		if(select==0){
			this.setCenter(menu);
		}
		else if(select==1){
			this.setCenter(menu1);
		}
	}
	public void updatemenu(){
		if(isPushStartBtn){
			test2.setImage(RenderableHolder.getInstance().startBtnClick);
			test1.setImage(RenderableHolder.getInstance().startBtn);
		}
		else if(isPushExitBtn){
			test1.setImage(RenderableHolder.getInstance().startBtnClick);
			test2.setImage(RenderableHolder.getInstance().startBtn);
		}
		else if(isSelectStartBtn){
			test2.setImage(RenderableHolder.getInstance().startBtnEnter);
			test1.setImage(RenderableHolder.getInstance().startBtn);
		}
		else if(isSelectExitBtn){
			test1.setImage(RenderableHolder.getInstance().startBtnEnter);
			test2.setImage(RenderableHolder.getInstance().startBtn);
		}
		
	}
	public void reset(){
		test2.setImage(RenderableHolder.getInstance().startBtn);
		test1.setImage(RenderableHolder.getInstance().startBtn);
		isPushExitBtn=false;
		isPushStartBtn=false;
		isSelectExitBtn=false;
		isSelectStartBtn=false;
	}

	public Boolean getIsSelectStartBtn() {
		return isSelectStartBtn;
	}

	public void setIsSelectStartBtn(Boolean isSelectStartBtn) {
		this.isSelectStartBtn = isSelectStartBtn;
	}

	public Boolean getIsSelectExitBtn() {
		return isSelectExitBtn;
	}

	public void setIsSelectExitBtn(Boolean isSelectExitBtn) {
		this.isSelectExitBtn = isSelectExitBtn;
	}

	public Boolean getIsPushStartBtn() {
		return isPushStartBtn;
	}

	public void setIsPushStartBtn(Boolean isPushStartBtn) {
		this.isPushStartBtn = isPushStartBtn;
	}

	public Boolean getIsPushExitBtn() {
		return isPushExitBtn;
	}

	public void setIsPushExitBtn(Boolean isPushExitBtn) {
		this.isPushExitBtn = isPushExitBtn;
	}

}
