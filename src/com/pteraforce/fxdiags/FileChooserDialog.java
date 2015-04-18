package com.pteraforce.fxdiags;

import java.io.File;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;

/**
 * Displays a dialog window with a text field and browse button, prompting
 * the user to choose a file. When the user clicks browse, a
 * <code>FileChooser</code> is opened, where the user can select a file.
 * 
 * @author Tyler Smith
 * @version 1.0.0
 */
public class FileChooserDialog extends Dialog<File> {
	private final Button browseButton = new Button("Browse...");
	private final GridPane grid;
	private final Label label;
	private final TextField textField;
	
	private File file = null;
	private FileChooser.ExtensionFilter[] filters;

	/**
	 * Creates a new file chooser dialog that accepts any file types.
	 */
	public FileChooserDialog() {
		final DialogPane dialogPane = getDialogPane();
		
		// textfield
		textField = new TextField();
		GridPane.setHgrow(textField, Priority.ALWAYS);
		GridPane.setFillWidth(textField, true);
		textField.setEditable(false);
		
		// label
		label = createContentLabel(dialogPane.getContentText());
		label.setPrefWidth(Region.USE_COMPUTED_SIZE);
		label.textProperty().bind(dialogPane.contentTextProperty());
		GridPane.setColumnSpan(label, 2);
		
		// button
		browseButton.setOnAction(event -> getFile());
		
		grid = new GridPane();
		grid.setHgap(10);
		grid.setMaxWidth(Double.MAX_VALUE);
		grid.setAlignment(Pos.CENTER_LEFT);
		
		dialogPane.contentTextProperty().addListener(o -> updateGrid());

		setTitle("File Upload");
		dialogPane.setHeaderText("Choose a File");
		dialogPane.getStyleClass().add("text-input-dialog");
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		updateGrid();
		
		setResultConverter((dialogButton) -> dialogButton == ButtonType.OK ? file : null);
	}
	
	/**
	 * Creates a new file chooser dialog that only accepts files of the types
	 * specified.
	 * 
	 * @param filters The types of files to accept.
	 */
	public FileChooserDialog(FileChooser.ExtensionFilter... filters) {
		this();
		this.filters = filters;
	}
	
	/**
	 * Refreshes the grid to display the current components.
	 */
	private void updateGrid() {
		grid.getChildren().clear();
		
		grid.add(label, 0, 0);
		grid.add(textField, 0, 1);
		grid.add(browseButton, 1, 1);
		getDialogPane().setContent(grid);
		
		Platform.runLater(() -> textField.requestFocus());
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
	 * Displays a <code>FileChooser</code> and stores the file that was 
	 * selected by the user. The path of the file is then applied to the text
	 * field.
	 */
	private void getFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Upload a Spreadsheet");
		fileChooser.getExtensionFilters().addAll(filters);
		// only assign the file if one was selected, otherwise leave it unchanged
		File tempFile = fileChooser.showOpenDialog(getDialogPane().getScene().getWindow());
		if (tempFile != null) file = tempFile;
		
		updateTextField();
	}
	
	/**
	 * Applies the path of the currently-stored file to the text field.
	 */
	private void updateTextField() {
		try {
			textField.setText(file.getAbsolutePath());
		} catch (NullPointerException e) {
			// no file has been selected, do nothing
		}
	}
}