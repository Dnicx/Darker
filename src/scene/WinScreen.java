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
		top =new Canvas(768 ,380);
		GraphicsContext gc = top.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.getInstance().victory, 84, 50);
		
		this.setTop(top);
		
		
		menu =new ImageView(RenderableHolder.getInstance().rtmEnter);
		exit =new ImageView(RenderableHolder.getInstance().exitBtnV2);
		bottom= new GridPane();
		bottom.setAlignment(Pos.TOP_CENTER);
		bottom.setHgap(10);
		exitBtn=new Button();
		rtmBtn =new Button();
		exitBtn.setStyle("-fx-background-color:black;");
		rtmBtn.setStyle("-fx-background-color:black;");
		exitBtn.setGraphic(exit);
		rtmBtn.setGraphic(menu);
		this.setCenter(bottom);
		bottom.add(rtmBtn, 0, 0);
		bottom.add(exitBtn, 1, 0);
		
		rtmBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmNomal);
				exit.setImage(RenderableHolder.getInstance().exitBtnV2);
				selectrtmBtn=true;
				selectExitBtn=false;
				
			}
		});
		
		rtmBtn.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmV2Click);	
				exit.setImage(RenderableHolder.getInstance().exitBtnV2);	
			}
		});

		rtmBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmV2Click);	
				exit.setImage(RenderableHolder.getInstance().exitBtnV2);	
				Main.instance.toggleScene(Main.menu);
				
			}
		});
		rtmBtn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				reset();
			}
		});
		
		exitBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				exit.setImage(RenderableHolder.getInstance().exitBtnV2Enter);
				menu.setImage(RenderableHolder.getInstance().rtmEnter);
				selectExitBtn=true;
				selectrtmBtn=false;
				
				
			}
		});
		exitBtn.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmEnter);	
				exit.setImage(RenderableHolder.getInstance().exitBtnV2Click);	
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
				reset();
				
			}
		});
		
		
		
	}

	
	public void updatemenu(){
		if(pushrtmBtn){
			menu.setImage(RenderableHolder.getInstance().rtmV2Click);
			exit.setImage(RenderableHolder.getInstance().exitBtnV2);
		}
		else if(pushExitBtn){
			exit.setImage(RenderableHolder.getInstance().exitBtnV2Click);
			menu.setImage(RenderableHolder.getInstance().rtmEnter);
		}
		else if(selectrtmBtn){
			menu.setImage(RenderableHolder.getInstance().rtmNomal);
			exit.setImage(RenderableHolder.getInstance().exitBtnV2);
		}
		else if(selectExitBtn){
			exit.setImage(RenderableHolder.getInstance().exitBtnV2Enter);
			menu.setImage(RenderableHolder.getInstance().rtmEnter);
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
		menu.setImage(RenderableHolder.getInstance().rtmEnter);
		exit.setImage(RenderableHolder.getInstance().exitBtnV2);
		pushExitBtn=false;
		pushrtmBtn=false;
		selectExitBtn=false;
		selectrtmBtn=false;
	}
}
