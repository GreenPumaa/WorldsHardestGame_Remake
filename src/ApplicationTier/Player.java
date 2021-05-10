package ApplicationTier;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Player extends PFigure
{
   Circle player;
   private static Player instance = null;
   private static final int KEYBOARD_MOVEMENT = 5;
   private int startPosX, startPosY, radius, priority;
   private Stage pane;

   public static Player getInstance()
   {
      if(instance == null)
         instance = new Player(getInstance().pane);
      return instance;
   }

   private Player(Stage pane)
   {
      this.startPosX = startPosY = 20;
      this.radius = 10;
      this.priority = 1;
      this.pane = pane;
   }

   @Override
   public void draw()
   {
      player.setFill(Color.RED);
   }

   public void movePlayer(Scene scene)
   {
      scene.setOnKeyPressed(new EventHandler<KeyEvent>()
      {
         @Override
         public void handle(KeyEvent event)
         {
            switch (event.getCode())
            {
               case UP:
                  player.setCenterY(player.getCenterY() - KEYBOARD_MOVEMENT);
                  break;
               case RIGHT:
                  player.setCenterX(player.getCenterX() + KEYBOARD_MOVEMENT);
                  break;
               case DOWN:
                  player.setCenterY(player.getCenterY() + KEYBOARD_MOVEMENT);
                  break;
               case LEFT:
                  player.setCenterX(player.getCenterX() - KEYBOARD_MOVEMENT);
                  break;
            }
         }
      });
   }
}
