package PresentationTier;

import ApplicationTier.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameGUI extends Application
{
      public static void main(String[] args) {launch(args);}

      @Override
      public void start(Stage primaryStage) throws Exception
      {
         Parent root = FXMLLoader.load(getClass().getResource("C:\\Users\\HP" +
            "\\Documents\\School\\JuniorS2\\OOPS1\\Final " +
            "Project\\src\\PresentationTier\\sample.fxml"));
         primaryStage.setTitle("Worlds Hardest Game Remake");
         primaryStage.setScene(new Scene(root, 600, 600));
         primaryStage.show();
         Player newPlayer = Player.getInstance();
         newPlayer.draw();
         newPlayer.movePlayer(primaryStage.getScene());
      }


}
