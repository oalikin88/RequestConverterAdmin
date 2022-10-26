package com.mycompany.requestconverteradmin;

import com.mycompany.requestconverteradmin.data.Record;
import com.mycompany.requestconverteradmin.data.ClientDAO;
import com.mycompany.requestconverteradmin.data.Content;
import com.mycompany.requestconverteradmin.data.Request;
import com.mycompany.requestconverteradmin.data.TreeViewManipulations;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private MenuItem addRequestAction;

    @FXML
    private MenuItem delRequestAction;

    @FXML
    private MenuItem exit;

    @FXML
    private TextField addNameElementId;

    @FXML
    private TextField addOpfrId;

    @FXML
    private TextField addSubjectId;

    @FXML
    private TextField addUpfrId;

    @FXML
    private GridPane requestGrid;
    @FXML
    private TextField requestValue;

    @FXML
    private TreeView<Request> treeRequest;
    @FXML
    private TextField requestShortValue;

    private int recordID;
    private List<Record> records;
    private TreeItem<Record> selectedRecordItem;
    private TreeItem<Request> selectedRequestItem;
    private List<Request> requests;
    private int requestID;
    private TreeItem<Record> root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ClientDAO clientRecords = new ClientDAO();
        ClientDAO clientRequests = new ClientDAO();
        records = clientRecords.findAllRecords();
        requests = clientRequests.findAllRequests();

        // Формируем список регионов и заполняем таблицу данными
        root = TreeViewManipulations.updateTreeViewRecordList(records);
        TreeItem<Request> parent = TreeViewManipulations.updateTreeViewRequestList(requests);
        tree.setRoot(root);
        treeRequest.setRoot(parent);

        MultipleSelectionModel<TreeItem<Record>> selectionModel = tree.getSelectionModel();
        MultipleSelectionModel<TreeItem<Request>> selectionModelRequest = treeRequest.getSelectionModel();

        tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                    Object newValue) {

                selectedRecordItem = (TreeItem<Record>) newValue;

                recordID = selectedRecordItem.getValue().getId();
                subjectID.setText(selectedRecordItem.getValue().getSubject());
                opfrID.setText(selectedRecordItem.getValue().getOpfr());
                upfrID.setText(selectedRecordItem.getValue().getUpfr());

                System.out.println("Selected Text : " + selectedRecordItem.getValue().getName());
            }

        });

        treeRequest.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                    Object newValue) {

                selectedRequestItem = (TreeItem<Request>) newValue;

                requestID = selectedRequestItem.getValue().getId();
                requestShortValue.setText(selectedRequestItem.getValue().getShortName());
                requestValue.setText(selectedRequestItem.getValue().getName());

                System.out.println("Selected Text : " + selectedRequestItem.getValue().getName());
            }

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
                    clientRecords.addRecord(subject, opfr, upfr, nameRegion);
                    Record rec = new Record(subject, opfr, upfr, nameRegion);
                    TreeItem<Record> newRecordItem = new TreeItem<>(rec);
                    selectedRecordItem.getChildren().add(newRecordItem);
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
                    int id = selectedRecordItem.getValue().getId();
                    clientRecords.deleteRecord(id);
                    selectedRecordItem.getParent().getChildren().remove(selectedRecordItem);
                    tree.refresh();
                } else if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                    System.out.println("Нажата кнопка отмена");
                }
            }
        });

        delRequestAction.setOnAction(event -> {
            Dialog<ButtonType> dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();
            dialog.setTitle("Удаление элемента");
            dialog.setHeaderText("Вы действительно хотите удалить из списка элемент: " + selectionModelRequest.getSelectedItems().iterator().next().getValue() + "?");
            dialog.getDialogPane().getButtonTypes().addAll(
                    new ButtonType("Удалить", ButtonBar.ButtonData.YES),
                    new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE));
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent()) {
                if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.YES) {
                    int id = selectedRequestItem.getValue().getId();
                    clientRequests.deleteRequest(id);
                    selectedRequestItem.getParent().getChildren().remove(selectedRequestItem);
                    treeRequest.refresh();
                } else if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                    System.out.println("Нажата кнопка отмена");
                }
            }
        });

        addRequestAction.setOnAction(event -> {
            Dialog<ButtonType> dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();
            dialog.setTitle("Добавление нового запроса");
            dialog.setHeaderText("Добавление нового запроса");
            VBox vbox = new VBox();
            dialogPane.setContent(vbox);
            Label labelName = new Label("Полное название запроса");
            TextField fieldName = new TextField();
            Label labelShortName = new Label("Сокращённое название запроса");
            TextField fieldShortName = new TextField();
            StackPane stackPane1 = new StackPane();
            StackPane stackPane2 = new StackPane();
            StackPane stackPane3 = new StackPane();
            StackPane stackPane4 = new StackPane();
            stackPane1.getChildren().add(labelName);
            stackPane2.getChildren().add(fieldName);
            stackPane3.getChildren().add(labelShortName);
            stackPane4.getChildren().add(fieldShortName);
            vbox.getChildren().addAll(stackPane1, stackPane2, stackPane3, stackPane4);
            dialog.getDialogPane().getButtonTypes().addAll(
                    new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE),
                    new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE));
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent()) {
                if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                    String name = fieldName.getText();
                    String shortName = fieldShortName.getText();

                    clientRequests.addRequest(name, shortName);
                    Request recuest = new Request(name, shortName);
                    TreeItem<Request> newRequestItem = new TreeItem<>(recuest);
                    selectedRequestItem.getParent().getChildren().add(newRequestItem);
                    treeRequest.refresh();
                    System.out.println(dialog.resultProperty());
                }
            } else if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                System.out.println("Нажата кнопка отмена");
            }
        });

        exit.setOnAction(event -> {
            Platform.exit();
        });

    }

    @FXML
    void actionImport(ActionEvent event) throws IOException {
        ClientDAO clientRecords = new ClientDAO();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Предупреждение");
        alert.setHeaderText("При импорте файла произойдёт очистка базы данных!");
        alert.setContentText("Вы уверены что хотите выполнить импорт файла?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            clientRecords.eraseSpr();
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите файл для импорта");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/desktop"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            List<String> content = Content.getContent(selectedFile);
            String[][] prepareContent = Content.recordListTransform(content);
            clientRecords.importRecords(prepareContent);
        }

    }

    @FXML
    public void submit() {
        ClientDAO clientRecords = new ClientDAO();
        String subject = subjectID.getText();
        String opfr = opfrID.getText();
        String upfr = upfrID.getText();

        clientRecords.editRecord(recordID, subject, opfr, upfr);
        tree.refresh();
        updateTreeViewRecordItem();

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

    @FXML
    void actionSubmitRequest(ActionEvent event) {
        ClientDAO clientRequest = new ClientDAO();
        String name = requestValue.getText();
        String shortName = requestShortValue.getText();
        clientRequest.editRequest(requestID, name, shortName);
        treeRequest.refresh();
        updateTreeViewRequestItem();

    }

    private void updateTreeViewRecordItem() {
        TreeItem selectedItem = tree.getSelectionModel().getSelectedItem();
        Record selectedClient = (Record) selectedItem.getValue();
        selectedClient.setSubject(subjectID.getText());
        selectedClient.setOpfr(opfrID.getText());
        selectedClient.setUpfr(upfrID.getText());

    }

    private void updateTreeViewRequestItem() {
        TreeItem selectedItem = treeRequest.getSelectionModel().getSelectedItem();
        Request selectedClient = (Request) selectedItem.getValue();
        selectedClient.setName(requestValue.getText());
        selectedClient.setShortName(requestShortValue.getText());
    }

    @FXML
    void actionSearch(ActionEvent event) {

        Dialog<ButtonType> dialog = new Dialog();
        DialogPane dialogPane = dialog.getDialogPane();
        dialog.setTitle("Поиск");
        dialog.setHeaderText("Поиск элемента");
        dialog.initModality(Modality.NONE);
        GridPane gridPane = new GridPane();
        dialogPane.setContent(gridPane);

        Label searchRequestLabel = new Label("Найти");
        TextField searchRequestField = new TextField();
        gridPane.add(searchRequestLabel, 0, 0);
        gridPane.add(searchRequestField, 1, 0);
        GridPane.setMargin(searchRequestLabel, new Insets(0, 15, 0, 0));
        dialog.getDialogPane().getButtonTypes().addAll(
                new ButtonType("Найти", ButtonBar.ButtonData.OK_DONE),
                new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE));

        Optional<ButtonType> result = dialog.showAndWait();
        TreeItem<Record> rootSearch = new TreeItem<>(new Record("Запросы"));
        TreeItem<Record> parentSearch = new TreeItem<>();
        List<Record> childList = new ArrayList<>();
        List<Record> parentList = new ArrayList<>();
        
        
        if (result.isPresent()) {
            if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                String target = searchRequestField.getText().trim().toLowerCase();
                int parentCount;
                int childCount;
                System.out.println("(|)(|)(|)(|)(|)(|)(|)(|)");
                for(int i = 0; i < root.getChildren().size(); i++) {
                    parentList.add(root.getChildren().get(i).getValue());
                    System.out.println(root.getChildren().get(i).getValue().getName());
                }
                System.out.println("(|)(|)(|)(|)(|)(|)(|)(|)");
                for ( parentCount = 0; parentCount < root.getChildren().size(); parentCount++) {
                    for ( childCount = 0; childCount < root.getChildren().get(parentCount).getChildren().size(); childCount++) {
                        if (root.getChildren().get(parentCount).getChildren().get(childCount).getValue().getName().toLowerCase().contains(target)) {
                     //       System.out.println(root.getChildren().get(parentCount).getChildren().get(childCount).getParent().getValue().getName());
                            childList.add(root.getChildren().get(parentCount).getChildren().get(childCount).getValue());
                            System.out.println(root.getChildren().get(parentCount).getChildren().get(childCount).getValue());
                            
                     //       searchItemsChilds.getChildren().add(root.getChildren().get(parentCount).getChildren().get(childCount));
                            
                        }
                    }   
                }
                for(int out = 0; out < parentList.size(); out++) {
                    parentSearch = new TreeItem<>(parentList.get(out));
                    rootSearch.getChildren().add(parentSearch);
                    for(int inner = 0; inner < childList.size(); inner++) {
                if (childList.get(inner).getSubject().equals(parentList.get(out).getSubject())
                        && (!childList.get(inner).getOpfr().equals(parentList.get(out).getOpfr())
                        || !childList.get(inner).getUpfr().equals(parentList.get(out).getUpfr()))) {
                    parentSearch.getChildren().add(new TreeItem<>(childList.get(inner)));
                }
            }
                        
                    
                }
                
                
                rootSearch.getChildren().setAll(rootSearch.getChildren().filtered(e -> !e.getChildren().isEmpty()).stream().collect(Collectors.toList()));
                rootSearch.setExpanded(true);
                rootSearch.getChildren().iterator().forEachRemaining(e -> e.setExpanded(true));
                tree.setRoot(rootSearch);
                tree.refresh();
                System.out.println(dialog.resultProperty() + ", " + target);
            }
        } else if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            System.out.println("Нажата кнопка отмена");
        }

    }

}
