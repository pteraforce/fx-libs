package com.pteraforce.fxpanes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Allows for the creation of a pane with a titled border around it. This is 
 * similar to <code>BorderFactory.createTitledBorder</code> in Swing. 
 * 
 * This class is only a wrapper pane that goes around another pane. When 
 * creating an instance of this class, you must define the pane that goes inside
 * of the border.
 * 
 * @author Tyler Smith
 * @version 1.0.0
 */
public class TitledBorderPane extends StackPane {
	private Label titleLabel;
	
	/**
	 * Creates a new titled border pane with the given title and inner pane.
	 * The default JavaFX styles will be used. 
	 * 
	 * @param title The title to display in the border of this pane.
	 * @param innerPane The pane to display inside the border.
	 */
	public TitledBorderPane(String title, Pane innerPane) {		
		titleLabel = new Label(" " + title + " ");
		titleLabel.getStyleClass().add("titlePaneTitle");
		StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
		
	    innerPane.getStyleClass().add("titlePaneContent");

	    getStyleClass().add("titlePaneBorder");
	    getChildren().addAll(titleLabel, innerPane);
	    getStylesheets().add("com/pteraforce/fxpanes/Style.css");
	    
	    setBackgroundColor("#F4F4F4");
	}
	
	/**
	 * Sets the background color of this pane. Note that, while this will change
	 * the background color of this pane and its title, it <strong>will not
	 * </strong> change the background color of the inner pane.
	 * 
	 * @param color The color to apply to the background. This method accepts
	 * a hex-code for the value, with or without the hash symbol on the front.
	 */
	public void setBackgroundColor(String color) {
		String backgroundColor = "-fx-background-color: ";
		if (color.indexOf('#') < 0) backgroundColor += "#"; // add the # for hex
		backgroundColor += color;
		
		this.setStyle(backgroundColor);
		titleLabel.setStyle(backgroundColor);
	}
}