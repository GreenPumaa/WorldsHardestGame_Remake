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
 *
 */
public class StartSceneManager
{
   private static final int GAME_HEIGHT = 200;
   private static final int GAME_WIDTH = 300;
   private AnchorPane startScreen_Pane;
   private Scene startScreen_Scene;
   private Stage startScreen_Stage;
   private static StartSceneManager startSceneManager = null;

   /**
    *
    */
   public StartSceneManager()
   {
      startScreen_Pane = new AnchorPane();
      startScreen_Scene = new Scene(startScreen_Pane, GAME_WIDTH, GAME_HEIGHT);
      startScreen_Stage = new Stage();
      startScreen_Stage.setScene(startScreen_Scene);
      startScreen_Stage.setTitle("Worlds Hardest Game Remake");
      setBackGround();
      add_StartPane_Children();
   }

   /**
    * @return
    */
   public Stage getStartScreen_Stage() { return startScreen_Stage; }

   /**
    *
    */
   private void add_StartPane_Children()
   {
      startScreen_Pane.getChildren().addAll(StartGame_Button(),
         create_WelcomeLabel_Label());
   }

   /**
    * @return
    */
   private Label create_WelcomeLabel_Label()
   {
      Label welcome = new Label();
      welcome.setText("Welcome to\nTHE WORLDS HARDEST GAME\n the Remake!");
      welcome.setTextAlignment(TextAlignment.CENTER);
      welcome.setLayoutX(75);
      return welcome;
   }

   /**
    * @return
    */
   private Button StartGame_Button()
   {
      Button startGame_Btn = new Button("Start Game!");
      startGame_Btn.setLayoutX((startScreen_Pane.getWidth() / 2) - 32);
      startGame_Btn.setLayoutY(75);

      startGame_Btn.setOnMousePressed(mouseEvent -> {
         GameSceneManager game = new GameSceneManager();
         startScreen_Stage.hide();
         game.newGame();
      });
      return startGame_Btn;
   }

   /**
    *
    */
   private void setBackGround()
   {
      Background bg = new Background(new BackgroundFill(Color.LIGHTSEAGREEN
         , null, null));
      startScreen_Pane.setBackground(bg);
   }
}
