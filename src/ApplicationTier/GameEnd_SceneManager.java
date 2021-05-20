package ApplicationTier;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Class to control the final stage after the game has been won or lost.
 * Sets the label text depending on win or lost and allows the user to
 * return to the main menu or play a new game.
 * @author Hunter Liddell, Noah Mullendore, Carter Klare
 */
public class GameEnd_SceneManager
{
   private final Pane endGame_Pane;
   private final Scene endGame_Scene;
   private final Stage endGame_Stage;

   private final GameSceneManager game = new GameSceneManager();
   private final StartSceneManager sc = new StartSceneManager();

   private static final int endGame_PaneX = 200, endGame_PaneY = 200;
   private static final int GAME_LOST = 0, GAME_WON = 1;

   /**
    * Constructor
    * Initializes the Stage, Scene, and Pane.
    */
   public GameEnd_SceneManager()
   {
      endGame_Pane = new Pane();
      endGame_Scene = new Scene(endGame_Pane, endGame_PaneX, endGame_PaneY);
      endGame_Stage = new Stage();
      endGame_Stage.setTitle("Game Over");
      endGame_Stage.setScene(endGame_Scene);
      endGame_Stage.setResizable(false); // Cannot resize application
   }

   /**
    * Opens the final scene and displays the respective message depending
    * if the game was won or lost.
    * @param endType Integer to control what message to display.
    * @param gameScene Current game scene. Used to hide it.
    */
   public void endGame(int endType, Stage gameScene)
   {
      String endGame_Text;
      gameScene.hide();
      endGame_Stage.show();

      add_endGame_Children();
      windowCloseCheck();

      if(endType == GAME_WON)
      {
         endGame_Text = "Congratulations!\n You won!\n Play again?";
         endGame_Pane.getChildren().add(create_GameFinished_Label(endGame_Text));
      }
      if(endType == GAME_LOST)
      {
         endGame_Text = "Well... Good Try. \n You lost!\n Play again?";
         endGame_Pane.getChildren().add(create_GameFinished_Label(endGame_Text));
      }
   }

   /**
    * Creates and initializes the button that takes the user back to the main
    * menu screen.
    * @return mainScreen_Btn : Button
    */
   private Button mainScreen_Button()
   {
      final int halfWidth = 2, layoutX = 32, layoutY = 125;
      Button mainScreen_Btn = new Button("Main menu");
      mainScreen_Btn.setLayoutX((endGame_Pane.getWidth() / halfWidth)
         - layoutX);
      mainScreen_Btn.setLayoutY(layoutY);

      mainScreen_Btn.setOnMousePressed(mouseEvent -> {
         endGame_Stage.hide();
         sc.getStartScreen_Stage().show();
      });
      return mainScreen_Btn;
   }

   /**
    * Event handler to check if the end game stage is attempted to be closed
    * by the user.
    * Takes the user back to the main menu if handled.
    */
   private void windowCloseCheck()
   {
      endGame_Stage.setOnCloseRequest(WindowEvent->{
         this.endGame_Stage.hide();
         sc.getStartScreen_Stage().show();
      });
   }

   /**
    * Creates and initializes the play again button.
    * @return playAgain_Button
    */
   private Button playAgain_Button()
   {
      final int halfWidth = 2, layoutX = 32, layoutY = 75;
      Button playAgain_Button = new Button("Play again");
      playAgain_Button.setLayoutX((endGame_Pane.getWidth() / halfWidth)
         - layoutX);
      playAgain_Button.setLayoutY(layoutY);

      playAgain_Button.setOnMousePressed(mouseEvent -> {
         endGame_Stage.hide();
         game.newGame();
      });
      return playAgain_Button;
   }

   /**
    * Creates and initializes the game finished label.
    * @param endingLabel String that hold the respective text to whether
    *                    the game was won or lost.
    * @return endText - Label
    */
   private Label create_GameFinished_Label(String endingLabel)
   {
      final int layoutX = 55;
      Label endText = new Label();
      endText.setText(endingLabel);
      endText.setTextAlignment(TextAlignment.CENTER);
      endText.setLayoutX(layoutX);
      return endText;
   }

   /**
    * Adds the main menu and play again buttons to the pane.
    */
   private void add_endGame_Children()
   {
      endGame_Pane.getChildren().addAll(mainScreen_Button(),
         playAgain_Button());
   }

}
