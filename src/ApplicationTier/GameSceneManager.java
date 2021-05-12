package ApplicationTier;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
  private Enemy enemy;
  private Token token;

  private static final int MOVE_DISTANCE = 5;
  private static final int GAME_HEIGHT = 400;
  private static final int GAME_WIDTH = 600;
  private boolean NORTH, EAST, SOUTH, WEST;

  private int tokenNum;

  private AnimationTimer gameLoop;

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
      createPlayer();
      createEnemy();
      createToken();
      initializeListeners();
      gameLoop();
      gameScreen_Stage.show();
   }


   private void initializeListeners()
   {
      gameScreen_Scene.setOnKeyPressed(keyEvent ->
      {
         switch (keyEvent.getCode())
         {
            case W -> NORTH = true;
            case A -> EAST = true;
            case S -> SOUTH = true;
            case D -> WEST = true;
         }

         gameScreen_Scene.setOnKeyReleased(keyEvent1 ->
         {
            switch (keyEvent1.getCode())
            {
               case W -> NORTH = false;
               case S -> SOUTH = false;
               case A -> EAST = false;
               case D -> WEST = false;
            }
         });
      });
   }

   private void moveDirection()
   {
      if(NORTH && !SOUTH && !EAST && !WEST)
         player.move(0, -MOVE_DISTANCE);
      if(NORTH && WEST && !EAST && !SOUTH)
         player.move(MOVE_DISTANCE, -MOVE_DISTANCE);
      if(NORTH && EAST && !WEST && !SOUTH)
         player.move(-MOVE_DISTANCE, -MOVE_DISTANCE);
      if(SOUTH && WEST && !EAST && !NORTH)
         player.move(MOVE_DISTANCE, MOVE_DISTANCE);
      if(SOUTH && EAST && !WEST && !NORTH)
         player.move(-MOVE_DISTANCE, MOVE_DISTANCE);
      if(SOUTH && !NORTH && !EAST && !WEST)
         player.move(0, MOVE_DISTANCE);
      if(EAST && !NORTH && !SOUTH && !WEST)
         player.move(-MOVE_DISTANCE, 0);
      if(WEST && !NORTH && !SOUTH && !EAST)
         player.move(MOVE_DISTANCE, 0);


      player.move();
   }

   private void playerCollidedWithToken()
   {
      //TODO finish when tokens and enemies are in an arrayList[]

      //tokenNum++;
      //gameScreen_Pane.getChildren().remove(token);
      //token.clear()?
   }

   private void enemyMove()
   {
      enemy.move();
      enemy.draw();
   }

   private void tokenMove()
   {
      token.move();
      token.draw();
   }

   private void createPlayer()
   {
      player = new Player(gameScreen_Pane);
      player.draw();
   }

   private void createEnemy()
   {
      // make array list for all enemies
      enemy = new Enemy(gameScreen_Pane);
      enemy.draw();
   }

   private void createToken()
   {
      token = new Token(gameScreen_Pane);
      token.draw();
   }

   private void gameLoop()
   {
      gameLoop = new AnimationTimer()
      {
         @Override
         public void handle(long l)
         {
            moveDirection();
            enemyMove();
            tokenMove();
            if(player.collidedWith(token))
               playerCollidedWithToken();
         }
      };
      gameLoop.start();
   }
}
