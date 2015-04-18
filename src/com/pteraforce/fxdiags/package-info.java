/**
 * Contains a library of dialogs windows that can be used with JavaFX. These
 * classes are structured around the Dialogs API that was added in Java 8u40.
 * 
 * Common dialogs, such as alert dialogs, text input dialogs, and choice
 * dialogs, can be created using the built-in Dialogs API. This API defines 
 * supplementary dialogs that are not included in the core API.
 * 
 * As of the time this was written, the <code>setHeight</code> and
 * <code>setWidth</code> methods of <code>Dialog</code> didn't appear to be 
 * working properly. Unfortunately, since these methods are final, these classes
 * cannot override the methods to fix the issue. To resize the dialog please
 * use <code>getDialogPane().setPrefWidth(double width)</code>,
 * <code>getDialogPane().setMinWidth(double width)</code>,
 * <code>getDialogPane().setMaxWidth(double width)</code>,
 * <code>getDialogPane().setPrefHeight(double width)</code>,
 * <code>getDialogPane().setMinHeight(double width)</code>, and
 * <code>getDialogPane().setMaxHeight(double width)</code>.
 * 
 * @author Tyler Smith
 */
package com.pteraforce.fxdiags;