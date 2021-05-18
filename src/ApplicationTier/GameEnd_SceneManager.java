package ApplicationTier;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameEnd_SceneManager
{
   private final Pane endGame_Pane;
   private final Scene endGame_Scene;
   private final Stage endGame_Stage;
   private Player player;

   private GameSceneManager game = new GameSceneManager();
   private StartSceneManager sc = new StartSceneManager();
   private PFigureList enemyList;

   private static final int endGame_PaneX = 200, endGame_PaneY = 200;

   private static final int GAME_LOST = 0, GAME_WON = 1;
   private Object seekingEnemy;

   public GameEnd_SceneManager()
   {
      endGame_Pane = new Pane();
      endGame_Scene = new Scene(endGame_Pane, endGame_PaneX, endGame_PaneY);
      endGame_Stage = new Stage();
      endGame_Stage.setScene(endGame_Scene);
   }

   public void endGame(int endType, Stage gameScene)
   {
      String endGame_Text;
      try{ freezeGame();}
      catch(Exception e){}


      gameScene.hide();
      endGame_Stage.show();

      add_endGame_Children();
      windowCloseCheck();

      if(endType == GAME_WON)
      {
         endGame_Text = "Congratulations! You won!\n Play again?";
         endGame_Pane.getChildren().add(create_GameFinished_Label(endGame_Text));
      }
      if(endType == GAME_LOST)
      {
         endGame_Text = "You lost! Play again?";
         endGame_Pane.getChildren().add(create_GameFinished_Label(endGame_Text));
      }
   }

   private Button mainScreen_Button()
   {
      Button mainScreen_Btn = new Button("Main menu");
      mainScreen_Btn.setLayoutX((endGame_Pane.getWidth() / 2) - 32);
      mainScreen_Btn.setLayoutY(150);

      mainScreen_Btn.setOnMousePressed(mouseEvent -> {
         endGame_Stage.hide();
         sc.getStartScreen_Stage().show();
      });
      return mainScreen_Btn;
   }

   private void windowCloseCheck()
   {
      endGame_Stage.setOnCloseRequest(WindowEvent->{
         System.exit(0);
      });
   }

   private Button playAgain_Button()
   {
      Button playAgain_Button = new Button("Play again");
      playAgain_Button.setLayoutX((endGame_Pane.getWidth() / 2) - 32);
      playAgain_Button.setLayoutY(75);

      playAgain_Button.setOnMousePressed(mouseEvent -> {
         endGame_Stage.hide();
         game.newGame();
      });
      return playAgain_Button;
   }

   private Label create_GameFinished_Label(String endingLabel)
   {
      Label endText = new Label();
      endText.setText(endingLabel);
      endText.setTextAlignment(TextAlignment.CENTER);
      endText.setLayoutX(75);
      return endText;
   }

   private void add_endGame_Children()
   {
      endGame_Pane.getChildren().addAll(mainScreen_Button(),
         playAgain_Button());
   }

   private void freezeGame()
   {
      player.living_pane.getChildren().retainAll();
      for (int i = 0; i < enemyList.size(); i++)
      {
         enemyList.remove(enemyList.figure(i));
         enemyList.clear();
      }
      seekingEnemy seeking = (seekingEnemy)seekingEnemy;
      seeking.getSeekingEnemy().getChildren().removeAll();
   }
}
