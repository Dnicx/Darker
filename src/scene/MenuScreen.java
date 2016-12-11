package scene;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.ConfigurableOption;
import main.Main;

public class MenuScreen extends BorderPane{
	private GridPane headline;
	private VBox menu;
	private Button startBtn;
	private Button configBtn;
	private int width,height;
	
	public MenuScreen(){
		width = ConfigurableOption.getInstance().getScreenWidth();
		height = ConfigurableOption.getInstance().getScreenHeight();
		this.setPrefSize(width, height);
		
		headline= new GridPane();
		this.setTop(headline);
		
		menu=new VBox();
		menu.setAlignment(Pos.CENTER);
		
		startBtn=new Button("New Game");
		startBtn.setPrefHeight(40);
		startBtn.setPrefWidth(140);
		menu.getChildren().add(0,startBtn);
		
		/*configBtn=new Button("Config");
		configBtn.setPrefHeight(40);
		configBtn.setPrefWidth(120);
		menu.getChildren().add(1, configBtn);*/
		this.setCenter(menu);
		
		/*configBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Main.instance.toggleScene("configScene");
				
			}
		});*/
		startBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Main.instance.toggleScene("gameScene");
				
			}
		});
	}
	public void applyResize(){
		this.setPrefSize(ConfigurableOption.getInstance().getScreenWidth(),ConfigurableOption.getInstance().getScreenHeight());
	}

}
