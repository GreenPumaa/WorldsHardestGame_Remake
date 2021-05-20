package ApplicationTier;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.Random;

/**
 * Class to control the token.
 * Draws and moves the token ans well as initializing it with a file image.
 * @author Hunter Liddell, Noah Mullendore, Carter Klare
 */
public class Token extends PFigure
{
   private ImageView imageView;

   private final Random tokenSpawn = new Random();

   private boolean side1 = false, side4 = false, side3 = false,side2 = false;
   private static final int STARTING_POS_X = 200, STARTING_POS_Y = 300;
   private static final int DEFAULT_WIDTH = 10, DEFAULT_HEIGHT = 10;
   private static final int PRIORITY = 1;

   private int startX, startY;

   /**
    * Parameterized constructor
    * @param livingPane Pane that the token lives on
    */
   public Token(Pane livingPane)
   {
      super(STARTING_POS_X,  STARTING_POS_Y,  DEFAULT_HEIGHT,
              DEFAULT_WIDTH, PRIORITY, livingPane);
      setTokenSpawn();
      initializeToken();
   }

   /**
    * initialize token width, height, and file.
    */
   private void initializeToken()
   {
      try
      {
         imageView = new ImageView("file:token.jpg");
         imageView.setFitWidth(DEFAULT_WIDTH);
         imageView.setFitHeight(DEFAULT_HEIGHT);
      }
      catch ( Exception e )
      {
         e.printStackTrace();
      }
   }

   /**
    * Sets the token spawn points with random values.
    */
   private void setTokenSpawn()
   {
      final int randX = tokenSpawn.nextInt(600);
      final int randY = tokenSpawn.nextInt(400);

      this.x = randX;
      this.y = randY;

      if(this.y < 50)
         this.y = this.y + 50;
      if(this.x < 50)
         this.x = this.x + 50;

      startY = this.y;
      startX = this.x;
   }

   /**
    * Overridden move method from PFigure.
    * Moves the token in a square pattern.
    */
   @Override
   public void move()
   {
      final int yVel = 2;
      final int xVel = 2;
      if((startY - y <= 50) && !side1)
         y = y - yVel;
      else
      {
         side1 = true;
         if(startX - x <= 50 && !side2)
            x = x - xVel;
         else
         {
            side2 = true;
            if(y <= startY && !side3)
               y = y + yVel;
            else
            {
               side3 = true;
               if(x <= startX && !side4)
                  x = x + xVel;
               else
               {
                  side1 = false;
                  side2 = false;
                  side3 = false;
               }
            }
         }
      }
      living_pane.getChildren().remove(imageView);
   }

   /**
    * Overridden draw method from PFigure.
    * Sets the coordinates, height, width, and adds it to the pane's children.
    */
   @Override
   public void draw() {
      if( imageView != null )
      {
         imageView.setX(x);
         imageView.setY(y);
         imageView.setFitHeight(height);
         imageView.setFitWidth(width);

         living_pane.getChildren().add(imageView);
      }
   }

   /**
    * Sets the image to null;
    */
   public void clear()
   {
      imageView.setImage(null);
   }
}
