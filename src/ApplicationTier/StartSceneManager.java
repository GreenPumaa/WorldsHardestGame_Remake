package ApplicationTier;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Controls the main menu Stage.
 * Allows the user to start a new game.
 * @author Hunter Liddell, Noah Mullendore, Carter Klare
 */
public class StartSceneManager
{
      // Visuals
   private final AnchorPane startScreen_Pane;
   private final Scene startScreen_Scene;
   private final Stage startScreen_Stage;

      // Scene Dimensions
   private static final int GAME_HEIGHT = 200, GAME_WIDTH = 300;

   /**
    * Constructor to initialize the start screen Pane, Scene, Stage, and
    * label.
    * Also adds children to the pane.
    */
   public StartSceneManager()
   {
      startScreen_Pane = new AnchorPane();
      startScreen_Scene = new Scene(startScreen_Pane, GAME_WIDTH, GAME_HEIGHT);
      startScreen_Stage = new Stage();
      startScreen_Stage.setScene(startScreen_Scene);
      startScreen_Stage.setTitle("Token Run - Main Menu");
      startScreen_Stage.setResizable(false); //
      setBackGround();
      add_StartPane_Children();
   }

   /**
    * Gets the start screen stage.
    * @return startScreen_Stage
    */
   public Stage getStartScreen_Stage() { return startScreen_Stage; }

   /**
    * Adds the start game button and welcome label to the pane.
    */
   private void add_StartPane_Children()
   {
      startScreen_Pane.getChildren().addAll(StartGame_Button(),
         create_WelcomeLabel_Label());
   }

   /**
    * Creates and initializes the welcome label.
    * @return The welcome label
    */
   private Label create_WelcomeLabel_Label()
   {
      final int layoutX = 120, layoutY = 20;
      Label welcome = new Label();
      welcome.setText("Welcome to\n\nTOKEN RUN");
      welcome.setTextAlignment(TextAlignment.CENTER);
      welcome.setLayoutY(layoutY);
      welcome.setLayoutX(layoutX);

      return welcome;
   }

   /**
    * Creates and initializes the start game button.
    * @return startGame_Btn
    */
   private Button StartGame_Button()
   {
      final int halfWidth = 2, layoutX = 35, layoutY = 100;
      Button startGame_Btn = new Button("Start Game!");
      startGame_Btn.setLayoutX((startScreen_Pane.getWidth() / halfWidth)
         - layoutX);
      startGame_Btn.setLayoutY(layoutY);

      startGame_Btn.setOnMousePressed(mouseEvent -> {
         GameSceneManager game = new GameSceneManager();
         startScreen_Stage.hide();
         game.newGame();
      });
      return startGame_Btn;
   }

   /**
    * Creates and sets the panes background color.
    */
   private void setBackGround()
   {
      Background bg = new Background(new BackgroundFill(Color.LIGHTGRAY
         , null, null));
      startScreen_Pane.setBackground(bg);
   }
}
