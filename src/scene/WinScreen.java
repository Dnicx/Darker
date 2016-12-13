package scene;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.Main;
import render.RenderableHolder;

public class WinScreen extends BorderPane{
	private ImageView exit;
	private ImageView menu;
	private Button rtmBtn;
	private Button exitBtn;
	private Canvas top;
	private GridPane bottom;
	private Boolean selectrtmBtn=false;
	private Boolean selectExitBtn =false;
	private Boolean pushrtmBtn =false;
	private Boolean pushExitBtn = false;
	
	public WinScreen(){
		this.setPrefSize(768, 512);
		this.setStyle("-fx-background-color:black;");
		top =new Canvas(768 ,400);
		GraphicsContext gc = top.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.getInstance().gameOver, 84, 70);
		
		this.setTop(top);
		
		
		menu =new ImageView(RenderableHolder.getInstance().rtmNomal);
		exit =new ImageView(RenderableHolder.getInstance().startBtn);
		bottom= new GridPane();
		bottom.setAlignment(Pos.CENTER);
		exitBtn=new Button();
		rtmBtn =new Button();
		exitBtn.setGraphic(exit);
		rtmBtn.setGraphic(menu);
		this.setCenter(bottom);
		bottom.add(rtmBtn, 0, 0);
		bottom.add(exitBtn, 1, 0);
		
		rtmBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmEnter);
				exit.setImage(RenderableHolder.getInstance().startBtn);
				
			}
		});
		
		rtmBtn.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmEnter);	
				exit.setImage(RenderableHolder.getInstance().startBtn);	
			}
		});

		rtmBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmEnter);	
				exit.setImage(RenderableHolder.getInstance().startBtn);	
				Main.instance.toggleScene("gameScene");
				
			}
		});
		rtmBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmNomal);
				exit.setImage(RenderableHolder.getInstance().startBtn);
				
			}
		});
		
		exitBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				exit.setImage(RenderableHolder.getInstance().startBtnEnter);
				menu.setImage(RenderableHolder.getInstance().rtmNomal);
				
				
			}
		});
		exitBtn.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmNomal);	
				exit.setImage(RenderableHolder.getInstance().startBtnClick);	
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
				exit.setImage(RenderableHolder.getInstance().startBtn);
				menu.setImage(RenderableHolder.getInstance().rtmNomal);
				
			}
		});
		
		
		
	}

	
	public void updatemenu(){
		if(pushrtmBtn){
			menu.setImage(RenderableHolder.getInstance().rtmEnter);
			exit.setImage(RenderableHolder.getInstance().startBtn);
		}
		else if(pushExitBtn){
			exit.setImage(RenderableHolder.getInstance().startBtnClick);
			menu.setImage(RenderableHolder.getInstance().rtmNomal);
		}
		else if(selectrtmBtn){
			menu.setImage(RenderableHolder.getInstance().rtmEnter);
			exit.setImage(RenderableHolder.getInstance().startBtn);
		}
		else if(selectExitBtn){
			exit.setImage(RenderableHolder.getInstance().startBtnEnter);
			menu.setImage(RenderableHolder.getInstance().rtmNomal);
		}
		
	}
	public Boolean getSelectrtmBtn() {
		return selectrtmBtn;
	}


	public void setSelectrtmBtn(Boolean selectrtmBtn) {
		this.selectrtmBtn = selectrtmBtn;
	}


	public Boolean getSelectExitBtn() {
		return selectExitBtn;
	}


	public void setSelectExitBtn(Boolean selectExitBtn) {
		this.selectExitBtn = selectExitBtn;
	}


	public Boolean getPushrtmBtn() {
		return pushrtmBtn;
	}


	public void setPushrtmBtn(Boolean pushrtmBtn) {
		this.pushrtmBtn = pushrtmBtn;
	}


	public Boolean getPushExitBtn() {
		return pushExitBtn;
	}


	public void setPushExitBtn(Boolean pushExitBtn) {
		this.pushExitBtn = pushExitBtn;
	}


	public void reset(){
		menu.setImage(RenderableHolder.getInstance().rtmNomal);
		exit.setImage(RenderableHolder.getInstance().startBtn);
		pushExitBtn=false;
		pushrtmBtn=false;
		selectExitBtn=false;
		selectrtmBtn=false;
	}
}
