/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.requestconverteradmin;

import com.mycompany.requestconverteradmin.data.Record;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author 041AlikinOS
 */
public class AddElementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField code1Input;

    @FXML
    private TextField code2Input;

    @FXML
    private TextField code3Input;

    @FXML
    private TextField nameSubjectInput;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelAdd;
    
    
    private Boolean modalResult = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image warning = new Image(getClass().getResource("/com/mycompany/requestconverteradmin/static/icons/warning.png").toExternalForm(), 30, 30, true, true);
        Pattern numbers = Pattern.compile("\\d{3}");
        Tooltip attention = new Tooltip("Поле не может быть пустым, а также не может содержать символ - \";\"");
        Tooltip attentionRequest = new Tooltip("Поле не может быть пустым и должно содержать только 3 цифры");
        attention.setGraphic(new ImageView(warning));
        attentionRequest.setGraphic(new ImageView(warning));
        attention.setStyle("-fx-font-size:14px;");
        attentionRequest.setStyle("-fx-font-size:14px;");
        
        nameSubjectInput.setTooltip(attention);
        code1Input.setTooltip(attentionRequest);
        code2Input.setTooltip(attentionRequest);
        code3Input.setTooltip(attentionRequest);
        addButton.disableProperty().bind(patternTextAreaBinding(code1Input, numbers).not()
                .or(patternTextAreaBinding(code2Input, numbers).not())
                .or(patternTextAreaBinding(code3Input, numbers).not())
                .or(Bindings.createBooleanBinding(() -> {
                    return nameSubjectInput.getText().contains(";");
                }, nameSubjectInput.textProperty()))
                .or(Bindings.isEmpty(nameSubjectInput.textProperty()))
                .or(Bindings.createBooleanBinding(() -> {
                    return nameSubjectInput.getText().isBlank();
                }, nameSubjectInput.textProperty())));

       

    }
    
    @FXML
    void cancelAdd(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void handleAddButton(ActionEvent event) {
        this.modalResult = true;
        
       
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();

    }
    
    public Record getRecord() {
        
     //    System.out.println(((Stage)((Node)event.getSource()).getScene().getWindow()).getTitle().contains("Запросы пенсионно-социального характера"));
     
        Record record = new Record();
        record.setSubject(code1Input.getText().trim());
        record.setOpfr(code2Input.getText().trim());
        record.setUpfr(code3Input.getText().trim());
        record.setName(nameSubjectInput.getText().trim());
        return record;

    }

    BooleanBinding patternTextAreaBinding(TextField textField, Pattern pattern) {
        BooleanBinding binding = Bindings.createBooleanBinding(()
                -> pattern.matcher(textField.getText()).matches(), textField.textProperty());
        return binding;
    }

    public Boolean getModalResult() {
        return modalResult;
    }
    
    

}
