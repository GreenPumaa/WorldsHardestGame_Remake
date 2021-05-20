package ApplicationTier;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Begins the game by creating a new start screen manager object and
 * showing it.
 * @author Hunter Liddell, Noah Mullendore, Carter Klare
 */
public class GameGUI extends Application
{
   /**
    * Start method to call the opening of the main menu stage.
    * Overridden start() method to call a custom start.
    * @param primaryStage Main menu stage
    */
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

   /**
    * Main method
    * Entry point of the program
    * Launches javafx arguments
    * @param args arguments
    */
   public static void main(String[] args) {launch(args);}


}
