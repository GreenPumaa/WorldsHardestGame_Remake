package ApplicationTier;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class Player extends PFigure
{
   private static Player instance = null;

   private static final int STARTING_POS_X = 200;
   private static final int STARTING_POS_Y = 200;
   private static final int DEFAULT_WIDTH = 10;
   private static final int DEFAULT_HEIGHT = 10;
   private static final int PRIORITY = 1;


   public static Player getInstance(Pane p)
   {
      if(instance == null)
         instance = new Player(p);
      return instance;
   }

   public Player(Pane playerPane)
   {
      super(STARTING_POS_X, STARTING_POS_Y, DEFAULT_HEIGHT, DEFAULT_WIDTH,
         PRIORITY, playerPane);
   }

   @Override
   public void draw()
   {
      /*Ellipse elip1 = new Ellipse(x + width/2, y + 20, width/4, height / 4);
      elip1.setStroke(Color.RED);
      elip1.setFill(Color.BLACK);

      elip1.setLayoutX(5);
      elip1.setLayoutY(5);*/

      Rectangle player = new Rectangle(width, height);
      player.setLayoutX(50);
      player.setLayoutY(50);
      player.setFill(Color.RED);
      player.setStroke(Color.BLACK);

      living_pane.getChildren().add(player);
   }

   @Override
   public void move()
   {
      living_pane.setOnKeyPressed(new EventHandler<KeyEvent>()
      {
         @Override
         public void handle(KeyEvent keyEvent)
         {
            switch(keyEvent.getCode())
            {
               case W:
                  y += 5;
               case A:
                  x -= 5;
               case S:
                  y -= 5;
               case D:
                  x += 5;
               default:
                  break;
            }
         }
      });
   }
}
