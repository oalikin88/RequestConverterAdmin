package com.mycompany.requestconverteradmin;

import com.mycompany.requestconverteradmin.data.Client;
import com.mycompany.requestconverteradmin.data.ClientDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PrimaryController implements Initializable {

   
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    

    @FXML
    private MenuBar menuBar;



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
    private TreeView<Client> tree;
    
     private static ObservableList<Client> clientData = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке
        
        // заполняем таблицу данными
        var clients2 = ClientDAO.getInstance().findAll();
        TreeItem<Client> root = new TreeItem<>(new Client("Регион"));
         TreeItem<Client> parent = new TreeItem<>();
        String buf = "";
        
        for(int i = 0; i < clients2.size(); i++) {
            
            if(!buf.equals(clients2.get(i).getSubject()) ) {
                parent = new TreeItem<>(clients2.get(i));
                        root.getChildren().add(parent);
            } else {
                parent.getChildren().add(new TreeItem<>(clients2.get(i)));
                
            }      
            buf = clients2.get(i).getSubject();  
            
        }
        
        root.setExpanded(true);
        tree.setRoot(root);
        
         MultipleSelectionModel<TreeItem<Client>> selectionModel = tree.getSelectionModel();
         
         tree.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

        @Override
        public void changed(ObservableValue observable, Object oldValue,
                Object newValue) {

            TreeItem<Client> selectedItem = (TreeItem<Client>) newValue;
            subjectID.setText(selectedItem.getValue().getSubject());
            opfrID.setText(selectedItem.getValue().getOpfr());
            upfrID.setText(selectedItem.getValue().getUpfr());
            System.out.println("Selected Text : " + selectedItem.getValue().getSubject());
            // do what ever you want 
        }

      });

       // subjectID.setText(selectionModel.getSelectedItem().getValue().getSubject()); 
        
        btn.setOnAction(event -> {
            System.out.println(selectionModel.getSelectedItem().getValue().getSubject());
        });
    }
    
   

    private void initData() {
        var clients = ClientDAO.getInstance().findAll();
        for (Client client : clients) {
            clientData.add(new Client(
                    client.getSubject(),
                    client.getOpfr(),
                    client.getUpfr(),
                    client.getName()
            ));
        }
    

    }

}




