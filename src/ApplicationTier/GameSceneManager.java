package ApplicationTier;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * GameSceneManager controls the game.
 * Controls visuals, user controls, and conditions.
 * @author Hunter Liddell, Noah Mullendore, Carter Klare
 */
public class GameSceneManager
{
    // Game Visuals
  private final AnchorPane gameScreen_Pane;
  private final Scene gameScreen_Scene;
  private final Stage gameScreen_Stage;
  private final Label tokenLabel = new Label();

    // Class declarations
  private Player player;
  private Token token;
  private seekingEnemy seekingEnemy;
  private final PFigureList enemyList = new PFigureList();
  private GameEnd_SceneManager gameEnd;
  private static AnimationTimer gameLoop;
  private final StartSceneManager sc = new StartSceneManager();

    // Constants
  private static final int MAX_ENEMIES = 7, TARGET_TOKENS = 20;
  private static final int GAME_HEIGHT = 600, GAME_WIDTH = 900;
  private static final int SPEED_MULTIPLIER = 2, MOVE_DISTANCE = 4;

    // Game logic
  private boolean NORTH, EAST, SOUTH, WEST;
  private int numTokens = 0;

   /**
    * Class constructor
    * Sets the stage, scene, and pane.
    */
  public GameSceneManager()
  {
     gameScreen_Pane = new AnchorPane();
     gameScreen_Scene = new Scene(gameScreen_Pane, GAME_WIDTH, GAME_HEIGHT);
     gameScreen_Stage = new Stage();
     gameScreen_Stage.setTitle("Token Run - Application");
     gameScreen_Stage.setScene(gameScreen_Scene);
     gameScreen_Stage.setResizable(false);
  }

   /**
    * Initializes a new game with default values, including randomly
    * generated values.
    */
   public void newGame()
   {
      gameScreen_Pane.getChildren().addAll(tokenLabel);
      initializeToken();
      setBackground();
      createPlayer();
      createEnemies();
      createSeekingEnemy();
      createToken();
      initializeListeners();

      gameLoop();
      gameScreen_Stage.show();
   }

   /**
    * Sets the background color of the game screen.
    */
   private void setBackground()
   {
      Background bg = new Background(new BackgroundFill(Color.ALICEBLUE
         , null, null));
      gameScreen_Pane.setBackground(bg);
   }

   /**
    * Initializes the listeners for the player to move/stop.
    * Uses simple booleans to check which direction the player is moving.
    */
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

   /**
    * Updates token count on the GUI.
    */
   private void updateTokenCount()
   {
      tokenLabel.setText("Tokens: " + numTokens + "/" + TARGET_TOKENS);
   }

   /**
    * Sets padding, font, and text for the token.
    */
   private void initializeToken()
   {
      final int v = 20, v3 = 200;
      tokenLabel.setPadding(new Insets(v, GAME_WIDTH, GAME_HEIGHT,
              GAME_WIDTH - v3));
      tokenLabel.setFont(Font.font("Verdana", FontWeight.BOLD,
              FontPosture.REGULAR, v));
      tokenLabel.setText("Tokens: " + numTokens + "/" + TARGET_TOKENS);
   }

   /**
    * Moves the linear enemies.
    */
   private void enemiesMove()
   {
      for(int i = 0; i < MAX_ENEMIES; i++)
      {
         Enemy currEnemy = (Enemy) enemyList.figure(i);
         currEnemy.move();
         currEnemy.draw();
      }
   }

   /**
    * Creates and draws the linear enemies.
    */
   private void createEnemies()
   {
      for(int i = 0; i < MAX_ENEMIES; i++)
      {
         Enemy newEnemy = new Enemy(gameScreen_Pane);
         newEnemy.draw();
         enemyList.add(newEnemy);
      }
   }

   /**
    * Creates and draws the seeking enemy.
    */
   private void createSeekingEnemy()
   {
      seekingEnemy = new seekingEnemy(gameScreen_Pane);
      seekingEnemy.draw();
   }

   /**
    * Moves the player in the direction that the user specifies with W,A,S,D.
    */
   private void playerMoveDirection()
   {
      if(NORTH && !SOUTH && !EAST && !WEST)           // Up
         player.move(0, -MOVE_DISTANCE);
      if(SOUTH && !NORTH && !EAST && !WEST)           // Down
         player.move(0, MOVE_DISTANCE);
      if(EAST && !NORTH && !SOUTH && !WEST)           // Right
         player.move(-MOVE_DISTANCE, 0);
      if(WEST && !NORTH && !SOUTH && !EAST)           // Left
         player.move(MOVE_DISTANCE, 0);
      if(NORTH && WEST && !EAST && !SOUTH)            // Up, Left
         player.move(MOVE_DISTANCE, -MOVE_DISTANCE);
      if(NORTH && EAST && !WEST && !SOUTH)            // Up, Right
         player.move(-MOVE_DISTANCE, -MOVE_DISTANCE);
      if(SOUTH && WEST && !EAST && !NORTH)            // Down, Left
         player.move(MOVE_DISTANCE, MOVE_DISTANCE);
      if(SOUTH && EAST && !WEST && !NORTH)            // Down, Right
         player.move(-MOVE_DISTANCE, MOVE_DISTANCE);

      player.move();
   }

   /**
    * Checks to see if the player has collected half of the token goal count.
    * @return True if tokens is half of goal, false otherwise
    */
   private boolean halfTokensCollected()
   { return (numTokens == (TARGET_TOKENS / 2)); }

   /**
    * Speeds up all enemy velocity
    */
   private void speedUpEnemies()
   {
      for(int i = 0; i < MAX_ENEMIES; i++)
      {
         Enemy currEnemy = (Enemy) enemyList.figure(i);
         currEnemy.set_xVel(currEnemy.get_xVel() * SPEED_MULTIPLIER);
         currEnemy.set_yVel(currEnemy.get_yVel() * SPEED_MULTIPLIER);
      }
   }

   /**
    * Checks if the game has been exited.
    */
   private void checkGameExit()
   {
      gameScreen_Stage.setOnCloseRequest(windowEvent ->
      {
         gameLoop.stop();
         gameScreen_Stage.hide();
         sc.getStartScreen_Stage().show();
      });
   }

   /**
    * If the player has collided with the token, the current token
    * disappears, a new token is created, and if the token count is at
    * half, the enemy velocity is increased.
    */
   private void playerCollidedWithToken()
   {
      numTokens++;
      if (halfTokensCollected())
         speedUpEnemies();
      gameScreen_Pane.getChildren().remove(token);
      token.clear();
      createToken();
   }

   /**
    * Moves and redraws the seekingEnemy.
    */
   private void seekingEnemyMove()
   {
      seekingEnemy.move();
      seekingEnemy.draw();
   }

   /**
    * Moves and redraws the token.
    */
   private void tokenMove()
   {
      token.move();
      token.draw();
   }

   /**
    * Creates and draws the player
    */
   private void createPlayer()
   {
      player = new Player(gameScreen_Pane);
      player.draw();
   }

   /**
    * Creates and draws the token.
    */
   private void createToken()
   {
      token = new Token(gameScreen_Pane);
      token.draw();
   }

   /**
    * Checks if the player has collided with any enemy type on screen.
    * @return True if collided, false otherwise.
    */
   private boolean enemyHit()
   {
      boolean hit = false;
      for(int i = 0; i < MAX_ENEMIES; i++)
      {
         Enemy currEnemy = (Enemy) enemyList.figure(i);
         if(player.collidedWith(currEnemy))
            hit = true;
      }
      if (player.collidedWith(seekingEnemy))
         hit = true;
      return hit;
   }

   /**
    * Checks if the player has reached the target token goal.
    * @return True if token count has reached the goal, false otherwise.
    */
   public boolean tokenTargetGot()
   {
      boolean gotAllTokens = false;
      if(numTokens == TARGET_TOKENS)
         gotAllTokens = true;
      return gotAllTokens;
   }

   /**
    * Runs a game loop to help the game run smoothly. Otherwise variables
    * are not updated properly.
    * Checks if the game has been won or lost and opens the endGame Stage.
    */
   private void gameLoop()
   {
      final int LOST = 0, WON = 1;
      gameEnd = new GameEnd_SceneManager();
      gameLoop = new AnimationTimer()
      {
         @Override
         public void handle(long l)
         {
            checkGameExit();
            playerMoveDirection();
            enemiesMove();
            seekingEnemyMove();
            tokenMove();

            if(enemyHit())
            {
               gameEnd.endGame(LOST, gameScreen_Stage);
               gameLoop.stop();
            }
            if(player.collidedWith(token))
            {
               playerCollidedWithToken();
               updateTokenCount();
               if(tokenTargetGot())
               {
                  enemyList.clear();
                  numTokens = 0;
                  player = null;
                  gameEnd.endGame(WON, gameScreen_Stage);
                  gameLoop.stop();
               }
            }
         }
      };
      gameLoop.start();
   }
}
