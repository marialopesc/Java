

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;


public class JavaFXTemplate extends Application {

	private Button button1, button2;
	private TextField t1, t2;
	private VBox root;
	private Scene scene;
	private EventHandler<ActionEvent> handler, handler2;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Maria Bezerra - Homework 3");

		//Setting up 2 buttons
		button1 = new Button("Button 1");
		button2 = new Button("Button 2");

		//Sizes for buttons
		button1.setPrefSize(200, 50);
		button2.setPrefSize(200, 50);

		//Setting up the 2 text Fields
		t1 = new TextField();
		t2 = new TextField();

		//and their sizes
		t1.setPrefHeight(100);
		t2.setPrefHeight(100);

		t1.setPromptText("Enter text here then press button 1");
		t2.setEditable(false);
		t2.setText("Final string goes here");

		//Anonymous for button 1
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String textFrom1 = t1.getText();
				String newToDisplay = textFrom1 + ": from the center text field!";
				t2.setText(newToDisplay);
				button1.setDisable(true); //disable button 1 so it can not be pressed
				button1.setText("pressed");

			}
		});

		//Event handler for button2 using lambda expression
		button2.setOnAction(actionEvent-> {
			t1.clear();
			t2.clear();
			t2.setText("Final string goes here");
			button1.setDisable(false);
			button1.setText("Button one");
		});

		//setUp buttons on the left side of VBox
		VBox buttonBox = new VBox(10,button1, button2);	//10 = space between buttons
		buttonBox.setAlignment(Pos.CENTER);	//centered buttons vertically
		buttonBox.setPadding(new Insets(10));	//padding


		//Setting up the border Pane
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(15));

		//Text fields in the border Pane
		borderPane.setLeft(buttonBox);	// Set VBox to the left of the BorderPane
		borderPane.setCenter(t1);
		borderPane.setRight(t2);

		//Extras: playing with styling
		//Adding background color
		borderPane.setStyle("-fx-background-color: #FFA987;");

		// Java: Set font family, size, and color
		buttonBox.setStyle("-fx-font-family: 'Avenir'; -fx-font-size: 18px; -fx-text-fill: #333;");


		scene = new Scene(borderPane, 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
