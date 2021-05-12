package ApplicationTier;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GameGUI extends Application
{
   private Pane rootPane = new Pane();
   private Player player = new Player(rootPane);
   private Enemy enemy  = new Enemy(rootPane);

   @Override
   public void start(Stage primaryStage) throws Exception
   {
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
