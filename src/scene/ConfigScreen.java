package scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import main.ConfigurableOption;
import main.Main;

public class ConfigScreen extends BorderPane {
	private int width;
	private int height;
	private GridPane topConfig;
	private FlowPane centerConfig;
	private GridPane bottomConfig;
	private Button menuBtn;
	private Button applyBtn;

	public ConfigScreen() {
		width = ConfigurableOption.getInstance().getScreenWidth();
		height = ConfigurableOption.getInstance().getScreenHeight();
		this.setPrefSize(width, height);
		/* ======Top===================== */
		topConfig = new GridPane();
		topConfig.setPadding(new Insets(10, 10, 10, 30));
		topConfig.setAlignment(Pos.CENTER_LEFT);
		Label headConfig = new Label("Setting");
		headConfig.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-fill: #555;");
		topConfig.add(headConfig, 0, 0);
		this.setTop(topConfig);

		/* =====Center================== */
		centerConfig = new FlowPane();
		centerConfig.setHgap(10);
		centerConfig.setVgap(10);
		centerConfig.setAlignment(Pos.CENTER);
		Label lbwidth = new Label("WIDTH");
		TextField tfWidth = new TextField();
		tfWidth.setText("768");
		Label lbheight = new Label("HEIGHT");
		TextField tfHeight = new TextField();
		tfHeight.setText("512");
		centerConfig.getChildren().addAll(lbwidth, tfWidth, lbheight, tfHeight);
		Label lbBright = new Label("Brightness");
		TextField tfBright = new TextField();
		tfBright.setText("0");
		centerConfig.getChildren().addAll(lbBright, tfBright);
		this.setCenter(centerConfig);

		/* ==============Bottom=========== */
		bottomConfig = new GridPane();
		bottomConfig.setPrefHeight(50);
		menuBtn = new Button("Back");
		applyBtn = new Button("Apply");
		topConfig.setPadding(new Insets(10, 10, 20, 10));
		bottomConfig.setAlignment(Pos.CENTER);
		bottomConfig.add(menuBtn, 0, 0);
		bottomConfig.add(applyBtn, 1, 0);
		bottomConfig.setHgap(10);
		bottomConfig.setVgap(20);
		this.setBottom(bottomConfig);

		// ====apply=====
		applyBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					ConfigurableOption.getInstance().setBrightness(Integer.parseInt(tfHeight.getText()));
					ConfigurableOption.getInstance().setScreenHeight(Integer.parseInt(tfHeight.getText()));
					ConfigurableOption.getInstance().setScreenWidht(Integer.parseInt(tfWidth.getText()));
					// Main.instance.resizeStage();
				}

			}
		});
		applyBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ConfigurableOption.getInstance().setBrightness(Integer.parseInt(tfHeight.getText()));
				ConfigurableOption.getInstance().setScreenHeight(Integer.parseInt(tfHeight.getText()));
				ConfigurableOption.getInstance().setScreenWidht(Integer.parseInt(tfWidth.getText()));
				// Main.instance.resizeStage();
			}

		});

		menuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.instance.toggleScene("menuScene");

			}
		});

		menuBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					Main.instance.toggleScene("menuScene");
				}

			}
		});

	}

	public void applyResize() {
		this.setPrefSize(ConfigurableOption.getInstance().getScreenWidth(),
				ConfigurableOption.getInstance().getScreenHeight());
	}

}
