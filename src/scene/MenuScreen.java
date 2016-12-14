package scene;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.ConfigurableOption;
import main.Main;
import render.RenderableHolder;

public class MenuScreen extends BorderPane {

	private Canvas headline;
	private Canvas blank;
	private VBox menu;
	private Button startBtn;
	private Button exitBtn;
	// private Button configBtn;
	private ImageView exitBtnImg;
	private ImageView startBtnImg;
	private Boolean selectStartBtn = true;
	private Boolean selectExitBtn = false;
	private Boolean pushStartBtn = false;
	private Boolean pushExitBtn = false;
	private int width, height;

	public MenuScreen() {
		width = ConfigurableOption.getInstance().getScreenWidth();
		height = ConfigurableOption.getInstance().getScreenHeight();
		this.setPrefSize(width, height);
		this.setStyle("-fx-background-color:black;");
		RenderableHolder.getInstance().loadResource();

		headline = new Canvas(768, 280);
		GraphicsContext gc = headline.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.getInstance().titlemenu, 234, 50);
		this.setTop(headline);

		exitBtnImg = new ImageView(RenderableHolder.getInstance().exitBtn);
		startBtnImg = new ImageView(RenderableHolder.getInstance().startBtnEnter);

		menu = new VBox();
		menu.setAlignment(Pos.TOP_CENTER);

		startBtn = new Button();
		startBtn.setPrefHeight(40);
		startBtn.setPrefWidth(140);
		startBtn.setStyle("-fx-background-color:black;");
		startBtn.setGraphic(startBtnImg);
		menu.getChildren().add(0, startBtn);

		blank = new Canvas(768, 10);
		menu.getChildren().add(1, blank);

		exitBtn = new Button();
		exitBtn.setStyle("-fx-background-color:black;");
		exitBtn.setPrefHeight(30);
		exitBtn.setPrefWidth(120);
		exitBtn.setGraphic(exitBtnImg);
		menu.getChildren().add(2, exitBtn);

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
				startBtnImg.setImage(RenderableHolder.getInstance().startBtnEnter);
				exitBtnImg.setImage(RenderableHolder.getInstance().exitBtn);
				selectExitBtn = false;
				selectStartBtn = true;

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
				startBtnImg.setImage(RenderableHolder.getInstance().startBtnClick);
				exitBtnImg.setImage(RenderableHolder.getInstance().exitBtn);
			}
		});
		/*
		 * startBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) {
		 * startBtnImg.setImage(RenderableHolder.getInstance().startBtn);
		 * exitBtnImg.setImage(RenderableHolder.getInstance().exitBtn);
		 * 
		 * } });
		 */

		exitBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				exitBtnImg.setImage(RenderableHolder.getInstance().exitBtnEnter);
				startBtnImg.setImage(RenderableHolder.getInstance().startBtn);
				selectExitBtn = true;
				selectStartBtn = false;

			}
		});
		exitBtn.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				exitBtnImg.setImage(RenderableHolder.getInstance().exitBtnClick);
				startBtnImg.setImage(RenderableHolder.getInstance().startBtn);
			}
		});

		exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				System.exit(0);

			}
		});
		/*
		 * exitBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) {
		 * exitBtnImg.setImage(RenderableHolder.getInstance().exitBtnEnter);
		 * startBtnImg.setImage(RenderableHolder.getInstance().startBtn);
		 * selectExitBtn=true; selectStartBtn=false;
		 * 
		 * } });
		 */

	}


	public void updatemenu() {
		if (pushStartBtn) {
			startBtnImg.setImage(RenderableHolder.getInstance().startBtnClick);
			exitBtnImg.setImage(RenderableHolder.getInstance().exitBtn);
		} else if (pushExitBtn) {
			exitBtnImg.setImage(RenderableHolder.getInstance().exitBtnClick);
			startBtnImg.setImage(RenderableHolder.getInstance().startBtn);
		} else if (selectStartBtn) {
			startBtnImg.setImage(RenderableHolder.getInstance().startBtnEnter);
			exitBtnImg.setImage(RenderableHolder.getInstance().exitBtn);
		} else if (selectExitBtn) {
			exitBtnImg.setImage(RenderableHolder.getInstance().exitBtnEnter);
			startBtnImg.setImage(RenderableHolder.getInstance().startBtn);
		}

	}

	public void reset() {
		startBtnImg.setImage(RenderableHolder.getInstance().startBtnEnter);
		exitBtnImg.setImage(RenderableHolder.getInstance().exitBtn);
		pushExitBtn = false;
		pushStartBtn = false;
		selectExitBtn = false;
		selectStartBtn = true;
	}

	public Boolean getSelectStartBtn() {
		return selectStartBtn;
	}

	public void setSelectStartBtn(Boolean isSelectStartBtn) {
		this.selectStartBtn = isSelectStartBtn;
	}

	public Boolean getSelectExitBtn() {
		return selectExitBtn;
	}

	public void setSelectExitBtn(Boolean isSelectExitBtn) {
		this.selectExitBtn = isSelectExitBtn;
	}

	public Boolean getPushStartBtn() {
		return pushStartBtn;
	}

	public void setPushStartBtn(Boolean isPushStartBtn) {
		this.pushStartBtn = isPushStartBtn;
	}

	public Boolean getPushExitBtn() {
		return pushExitBtn;
	}

	public void setPushExitBtn(Boolean isPushExitBtn) {
		this.pushExitBtn = isPushExitBtn;
	}

}
