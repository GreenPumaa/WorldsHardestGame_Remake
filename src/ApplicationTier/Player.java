package ApplicationTier;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Controls the player object and extends PFigure.
 * Draws and moves the player.
 * @author Hunter Liddell, Noah Mullendore, Carter Klare
 */
public class Player extends PFigure
{
   private static Rectangle player;

   private static final int STARTING_POS_X = 200, STARTING_POS_Y = 200;
   private static final int DEFAULT_WIDTH = 10, DEFAULT_HEIGHT = 10;
   private static final int PRIORITY = 1;

   /**
    * Parameterized constructor
    * @param playerPane livingPane
    */
   Player(Pane playerPane)
   {
      super(STARTING_POS_X, STARTING_POS_Y, DEFAULT_HEIGHT, DEFAULT_WIDTH,
         PRIORITY, playerPane);
      final int preferenceSize = 10;
      super.living_pane.setPrefSize(preferenceSize,preferenceSize);
   }

   /**
    * Overridden method to draw the player on the living pane.
    */
   @Override
   public void draw()
   {
      player = new Rectangle(width, height);
      player.setX(super.x);
      player.setY(super.y);
      player.setFill(Color.LIGHTSEAGREEN);
      player.setStroke(Color.BLACK);

      living_pane.getChildren().add(player);
   }

   /**
    * Overridden method to move the player on the living pane.
    * Checks if the player is in bounds of the screen and if it tries to
    * leave those bounds, player cannot move any farther.
    */
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
   }

   /**
    * @return players x coordinate.
    */
   public static double getX()
   {
      return player.getX();
   }

   /**
    * @return players y coordinate.
    */
   public static double getY()
   {
      return player.getY();
   }

}