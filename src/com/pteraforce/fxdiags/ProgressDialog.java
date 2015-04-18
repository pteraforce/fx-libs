package com.pteraforce.fxdiags;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Displays a dialog window with a progress bar. The most common usage of this
 * dialog window is as a loading dialog.
 * 
 * @author Tyler Smith
 * @version 1.0.0
 */
public class ProgressDialog extends Dialog<Boolean> {
	private final VBox vbox;
	private final Label label;
	private final ProgressBar progressBar;
	
	private boolean isFinished = false;
	
	/**
	 * Creates a new progress dialog at 0% progress.
	 */
	public ProgressDialog() {
		this(0.0d);
	}
	
	/**
	 * Creates a new progress dialog with the given progress.
	 * 
	 * @param progress The progress to apply to the progress bar. Must be 
	 * between 0 and 1.
	 */
	public ProgressDialog(double progress) {
		final DialogPane dialogPane = getDialogPane();
		
		// layout manager
		vbox = new VBox();
		vbox.setFillWidth(true);
		vbox.setMaxWidth(Double.MAX_VALUE);
		vbox.setAlignment(Pos.CENTER_LEFT);
		
		// progress bar
		progressBar = new ProgressBar(progress);
		progressBar.prefWidthProperty().bind(vbox.widthProperty().subtract(10));
		
		// label
		label = createContentLabel(dialogPane.getContentText());
		label.setPrefWidth(Region.USE_COMPUTED_SIZE);
		label.textProperty().bind(dialogPane.contentTextProperty());
		
		dialogPane.contentTextProperty().addListener(o -> updateGrid());
		
		setTitle("Please Wait");
		dialogPane.setHeaderText("Loading...");
		dialogPane.getStyleClass().add("text-input-dialog");
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		updateGrid();
		
		setResult(isFinished);
	}
	
	/**
	 * Set the current state of the progress bar. This method accepts a double
	 * from 0-1 and fills in the progress bar accordingly.
	 * 
	 * @param progress The progress to apply to the progress bar. Must be 
	 * between 0 and 1.
	 */
	public void setProgress(double progress) {
		progressBar.setProgress(progress);
	}
	
	/**
	 * Performs the function of <code>DialogPane.createContentLabel</code>. The
	 * built-in JavaFX dialogs call that method, but this class cannot access
	 * it. This method is used by this class instead.
	 *  
	 * @param text The text to be applied to the label.
	 * @return A new label for the dialog.
	 */
	private static Label createContentLabel(String text) {
		Label label = new Label(text);
		label.setMaxWidth(Double.MAX_VALUE);
		label.setMaxHeight(Double.MAX_VALUE);
		label.getStyleClass().add("content");
		label.setWrapText(true);
		label.setPrefWidth(360);
		return label;
	}
	
	/**
	 * Refreshes the grid to display the current components.
	 */
	private void updateGrid() {
		vbox.getChildren().clear();
		
		vbox.getChildren().add(progressBar);
		vbox.getChildren().add(label);
		getDialogPane().setContent(vbox);
		
		Platform.runLater(() -> progressBar.requestFocus());
	}
}