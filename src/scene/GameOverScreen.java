package scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

public class GameOverScreen extends BorderPane {
	private ImageView menu;
	private Canvas headline;
	private Button btnrtm;
	private GridPane bottom;

	public GameOverScreen() {
		this.setPrefSize(768, 512);
		this.setStyle("-fx-background-color:black;");
		// RenderableHolder.getInstance().loadResource();
		headline = new Canvas(768, 400);
		GraphicsContext gc = headline.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.getInstance().gameOver, 84, 100);
		this.setTop(headline);

		bottom = new GridPane();
		bottom.setPadding(new Insets(10, 40, 40, 10));
		btnrtm = new Button();
		btnrtm.setStyle("-fx-background-color: black;-fx-background-radius: 0;");
		menu = new ImageView(RenderableHolder.getInstance().rtmNomal);

		btnrtm.setGraphic(menu);
		bottom.add(btnrtm, 0, 0);
		bottom.setAlignment(Pos.CENTER_RIGHT);
		this.setCenter(bottom);

		btnrtm.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmEnter);

			}
		});
		btnrtm.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				menu.setImage(RenderableHolder.getInstance().rtmNomal);

			}
		});

		btnrtm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.instance.toggleScene("menuScene");

			}
		});

	}

}
