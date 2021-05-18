package ApplicationTier;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameGUI extends Application
{
   @Override
   public void start(Stage primaryStage) {
      try
      {
         StartSceneManager gm = new StartSceneManager();
         primaryStage = gm.getStartScreen_Stage();
         primaryStage.show();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

   }

   public static void main(String[] args) {launch(args);}


}
