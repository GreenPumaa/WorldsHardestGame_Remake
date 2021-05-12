package PresentationTier;

import ApplicationTier.Enemy;
import ApplicationTier.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class GameGUI extends Application
{
   Player newPlayer;
   private Pane root = new Pane();

   public static void main(String[] args) {launch(args);

   }

   private Parent createContent()
   {
      root.setPrefSize(600, 600);
      return root;
   }

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      primaryStage.setScene(new Scene(createContent()));
      primaryStage.show();
   }
}


