package com.mycompany.requestconverteradmin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private Scene scene;
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }
    
    

    public static void setStage(Stage stage) {
        App.stage = stage;
    }

 
    
    
    

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 900, 600);
        stage.setScene(scene);
        stage.setTitle("Конвертер запросов Админ");
        stage.getIcons().add(new Image(getClass().getResource("/com/mycompany/requestconverteradmin/static/icons/icon.png").toExternalForm()));
        stage.show();
        
    }

     void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}