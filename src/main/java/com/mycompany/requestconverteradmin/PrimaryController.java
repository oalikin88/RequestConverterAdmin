package com.mycompany.requestconverteradmin;

import com.mycompany.requestconverteradmin.data.Record;
import com.mycompany.requestconverteradmin.data.ClientDAO;
import com.mycompany.requestconverteradmin.data.Content;
import com.mycompany.requestconverteradmin.data.TreeViewManipulations;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventDispatchChain;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TreeViewSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

public class PrimaryController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem about;
    
    @FXML
    private ProgressIndicator statusIndicator;

    @FXML
    private Label statusLabel;

    @FXML
    private Label statusOutput;

    @FXML
    private Button btn;

    @FXML
    private TextField subjectID;

    @FXML
    private TextField opfrID;

    @FXML
    private TextField upfrID;

    @FXML
    private TreeView<Record> tree;

    @FXML
    private MenuItem addElement;

    @FXML
    private MenuItem delElement;

    @FXML
    private TextField addNameElementId;

    @FXML
    private TextField addOpfrId;

    @FXML
    private TextField addSubjectId;

    @FXML
    private TextField addUpfrId;

    private int recordID;
    private List<Record> records;
    private TreeItem<Record> selectedItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        records = ClientDAO.getInstance().findAll();

        // Формируем список регионов и заполняем таблицу данными
        TreeItem<Record> root = TreeViewManipulations.updateTreeViewList(records);
        tree.setRoot(root);

        MultipleSelectionModel<TreeItem<Record>> selectionModel = tree.getSelectionModel();

        tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                    Object newValue) {

                selectedItem = (TreeItem<Record>) newValue;

                recordID = selectedItem.getValue().getId();
                subjectID.setText(selectedItem.getValue().getSubject());
                opfrID.setText(selectedItem.getValue().getOpfr());
                upfrID.setText(selectedItem.getValue().getUpfr());

                System.out.println("Selected Text : " + selectedItem.getValue().getName());
            }

        });

        tree.setOnMouseMoved(event -> {
//            Annotation[] annotations = event.getPickResult().getIntersectedNode().accessibleTextProperty().getBean().getClass().getAnnotations();
//           for(Annotation a : annotations) {
//               System.out.println(a.annotationType());
//               for(Method m : a.annotationType().getMethods()) {
//                   Annotation[][] parameterAnnotations = m.getParameterAnnotations();
//                   for(int i = 0; i < parameterAnnotations.length; i++) {
//                       for(int j = 0; j < i; j++) {
//                           System.out.println(parameterAnnotations[i][j]);
//                       }
//                   }
//                       System.out.println(m.getParameterAnnotations());
//                   
//                   
//               }
//           }

//             System.out.println("********************************");
//
//            Node intersectedNode = event.getPickResult().getIntersectedNode();
//           String s =  intersectedNode.toString();
//             System.out.println(s);
//             System.out.println(event.getPickResult().getIntersectedNode().getStyleClass());
//             
//             System.out.println("--------------");
//        
//          System.out.println("*****");
        });

        // При нажатии на кнопку добавить создаём диалоговое окно с формой для ввода нового элемента
        addElement.setOnAction(event -> {

            Dialog<ButtonType> dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();
            dialog.setTitle("Добавление нового элемента");
            dialog.setHeaderText("Добавление нового элемента в " + selectionModel.getSelectedItems().iterator().next().getValue());

            GridPane gridPane = new GridPane();
            gridPane.setVgap(10);
            gridPane.setHgap(10);
            dialogPane.setContent(gridPane);

            TextField name = new TextField();

            TextField code1 = new TextField();
            TextField code2 = new TextField();
            TextField code3 = new TextField();
            HBox hbox1 = new HBox();
            VBox vbox = new VBox();
            StackPane stackPane1 = new StackPane();
            StackPane stackPane2 = new StackPane();
            StackPane stackPane3 = new StackPane();

            stackPane1.getChildren().add(code1);
            stackPane2.getChildren().add(code2);
            stackPane3.getChildren().add(code3);

            StackPane.setMargin(code1, new Insets(15, 15, 15, 15));
            StackPane.setMargin(code2, new Insets(15, 15, 15, 15));
            StackPane.setMargin(code3, new Insets(15, 15, 15, 15));

            hbox1.getChildren().addAll(stackPane1, stackPane2, stackPane3);
            vbox.getChildren().add(name);
            VBox.setMargin(name, new Insets(25, 15, 0, 15));
            Label nameLabel = new Label("Название элемента");
            Label codeLabel = new Label("Код");
            gridPane.add(nameLabel, 0, 0);
            gridPane.add(vbox, 1, 0);
            gridPane.add(codeLabel, 0, 1);
            gridPane.add(hbox1, 1, 1);

            GridPane.setMargin(nameLabel, new Insets(25, 0, 0, 15));
            GridPane.setMargin(codeLabel, new Insets(0, 0, 0, 15));
            dialog.getDialogPane().getButtonTypes().addAll(
                    new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE),
                    new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE));

            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent()) {
                if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                    String subject = code1.getText();
                    String opfr = code2.getText();
                    String upfr = code3.getText();
                    String nameRegion = name.getText();
                    ClientDAO.getInstance().addRecord(subject, opfr, upfr, nameRegion);
                    Record rec = new Record(subject, opfr, upfr, nameRegion);
                    TreeItem<Record> newRecordItem = new TreeItem<>(rec);
                    selectedItem.getChildren().add(newRecordItem);
                    tree.refresh();
                    System.out.println(dialog.resultProperty());
                }
            } else if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                System.out.println("Нажата кнопка отмена");
            }

        });

        delElement.setOnAction(event -> {
            Dialog<ButtonType> dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();
            dialog.setTitle("Удаление элемента");
            dialog.setHeaderText("Вы действительно хотите удалить из списка элемент: " + selectionModel.getSelectedItems().iterator().next().getValue() + "?");
            dialog.getDialogPane().getButtonTypes().addAll(
                    new ButtonType("Удалить", ButtonBar.ButtonData.YES),
                    new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE));

            Optional<ButtonType> result = dialog.showAndWait();  
            
            
            if (result.isPresent()) {
                if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.YES) {
                    int id = selectedItem.getValue().getId();
                    ClientDAO.getInstance().deleteRecord(id);
                    
                    selectedItem.getParent().getChildren().remove(selectedItem);
                      
//                             (e -> {
//                    
//                        if(e.getValue().getId() == id) {
//                            selectedItem.getParent().getChildren().remove(e);
//                            tree.refresh();
//                        } 
//                    });
                    tree.refresh();
//                forEach(e -> {
//                        if(e.getValue().getId() == id) {
//                            selectedItem.getParent().getChildren().remove(e);
//                            tree.refresh();
//                        } 
//                    
//                    });
                  
                } else if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                    System.out.println("Нажата кнопка отмена");
                }
            }
        });

    }
    
    @FXML
    void actionImport(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл для импорта");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/desktop"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        List<String> content = Content.getContent(selectedFile);
        String[][] prepareContent = Content.listToTwoArray(content);
        ClientDAO.getInstance().importRecords(prepareContent);
        
        
    }

    @FXML
    public void submit() {
        String subject = subjectID.getText();
        String opfr = opfrID.getText();
        String upfr = upfrID.getText();

        ClientDAO.getInstance().editRecord(recordID, subject, opfr, upfr);
        tree.refresh();
        updateTreeViewItem();

        System.out.println("Submit");
    }
    
        @FXML
    void actionAbout(ActionEvent event) {
         Dialog<ButtonType> dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();
            dialog.setWidth(300);
            dialog.setHeight(300);
            dialogPane.setMaxHeight(300);
            dialogPane.setMaxWidth(300);
            dialog.setTitle("О программе");
            dialog.setHeaderText(null);
            TextFlow textFlow = new TextFlow();
            VBox vBox = new VBox();
            Text author = new Text("by Oleg Alikin");
            Text info = new Text("Отдел эксплуатации и сопровождения информационных подсистем Отделения ПФР по Белгородской области.");
            info.setWrappingWidth(250);
            textFlow.getChildren().add(vBox);
            vBox.getChildren().addAll(author, info);
            dialogPane.setContent(textFlow);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

            Optional<ButtonType> result = dialog.showAndWait();  
    }
    
 

    private void updateTreeViewItem() {
        TreeItem selectedItem = tree.getSelectionModel().getSelectedItem();
        Record selectedClient = (Record) selectedItem.getValue();
        selectedClient.setSubject(subjectID.getText());
        selectedClient.setOpfr(opfrID.getText());
        selectedClient.setUpfr(upfrID.getText());

    }

}
