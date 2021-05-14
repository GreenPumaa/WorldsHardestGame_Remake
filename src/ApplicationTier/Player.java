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
   static Rectangle player;

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
      super.living_pane.setPrefSize(10,10);
   }

   @Override
   public void draw()
   {
      player = new Rectangle(width, height);
      player.setX(super.x);
      player.setY(super.y);

      //player.setLayoutX();
      //player.setLayoutY();
      player.setFill(Color.LIGHTSEAGREEN);
      player.setStroke(Color.BLACK);

      living_pane.getChildren().add(player);
   }

   @Override
   public void move()
   {
      if(x < 0 || y < 0 || x > (living_pane.getWidth() - DEFAULT_WIDTH) || y > (living_pane.getHeight() - DEFAULT_HEIGHT))
      {
         if(x < 0 && y < 0) // top left corner
         {
            x = 0;
            y = 0;
         }
         else if(x < 0 && y > living_pane.getHeight() - DEFAULT_HEIGHT) // bottom left corner
         {
            y = ((int) living_pane.getHeight()) - DEFAULT_HEIGHT;
            x = 0;
         }
         else if(x > (living_pane.getWidth() - DEFAULT_WIDTH) && (y > living_pane.getHeight() - DEFAULT_HEIGHT)) // bottom right corner
         {
            y = ((int) living_pane.getHeight()) - DEFAULT_HEIGHT;
            x = ((int) living_pane.getWidth()) - DEFAULT_WIDTH;
         }
         else if(y < 0 && x > (living_pane.getWidth() - DEFAULT_WIDTH)) // top right corner
         {
            y = 0;
            x = ((int) living_pane.getWidth()) - DEFAULT_WIDTH;
         }
         else if(y < 0) // top wall
            y = 0;
         else if(x < 0) // middle left wall
            x = 0;
         else if(y > living_pane.getHeight() - DEFAULT_HEIGHT) //
            // bottom wall
            y = ((int) living_pane.getHeight()) - DEFAULT_HEIGHT;
         else // right wall
            x = ((int) living_pane.getWidth()) - DEFAULT_WIDTH;
      }

      player.setX(x);
      player.setY(y);
      //living_pane.setLayoutX(living_pane.getLayoutX());
   }

   public static double getX()
   {
      return player.getX();
   }

   public static double getY()
   {
      return player.getY();
   }

}