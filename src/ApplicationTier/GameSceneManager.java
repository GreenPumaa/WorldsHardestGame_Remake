package ApplicationTier;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameSceneManager
{
  private final AnchorPane gameScreen_Pane;
  private final Scene gameScreen_Scene;
  private final Stage gameScreen_Stage;
  private Stage startScreen_Stage;

  private Player player;

  private static final int MOVE_DISTANCE = 5;
  private static final int GAME_HEIGHT = 400;
  private static final int GAME_WIDTH = 600;

  public GameSceneManager()
  {
     gameScreen_Pane = new AnchorPane();
     gameScreen_Scene = new Scene(gameScreen_Pane, GAME_WIDTH, GAME_HEIGHT);
     gameScreen_Stage = new Stage();
     gameScreen_Stage.setScene(gameScreen_Scene);
  }

   public void newGame(Stage startScreen)
   {
      this.startScreen_Stage = startScreen;
      startScreen.hide();
      gameScreen_Stage.show();
      createPlayer();
      initializeListeners();
   }


   private void initializeListeners()
   {
      gameScreen_Scene.setOnKeyPressed(new EventHandler<KeyEvent>()
      {
         @Override
         public void handle(KeyEvent keyEvent)
         {
            switch(keyEvent.getCode())
            {
               case W:
                  player.move(0, MOVE_DISTANCE);
               case A:
                  player.move(MOVE_DISTANCE * (-1), 0);
               case S:
                  player.move(0, MOVE_DISTANCE * (-1));
               case D:
                  player.move(MOVE_DISTANCE, 0);
               default:
                  break;
            }
         }
      });

      gameScreen_Pane.setOnKeyReleased(new EventHandler<KeyEvent>()
      {
         @Override
         public void handle(KeyEvent keyEvent)
         {
            
         }
      });
   }

   private void createPlayer()
   {
      player = new Player(gameScreen_Pane).getInstance(gameScreen_Pane);
      player.draw();
   }

   private void createEnemies()
   {
      Enemy enemy = new Enemy(gameScreen_Pane);
      enemy.draw();
   }
}
