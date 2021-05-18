package ApplicationTier;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.Random;

public class Token extends PFigure
{
   private ImageView imageView;

   private final Random tokenSpawn = new Random();

   private boolean side1 = false, side4 = false, side3 = false,side2 = false;
   private static final int STARTING_POS_X = 200, STARTING_POS_Y = 300;
   private static final int DEFAULT_WIDTH = 10, DEFAULT_HEIGHT = 10;
   private static final int PRIORITY = 1;

   private final int randX = tokenSpawn.nextInt(600);
   private final int randY = tokenSpawn.nextInt(400);
   private final int xVel = 2, yVel = 2;
   private final int startX, startY;;

   public Token(Pane enemyPane)
   {
      // TODO PR is subject to change. Its a temporary 1.
      super(STARTING_POS_X,  STARTING_POS_Y,  DEFAULT_HEIGHT,
              DEFAULT_WIDTH, PRIORITY, enemyPane );
      this.x = randX;
      this.y = randY;

      if(this.y < 50)
         this.y = this.y + 50;
      if(this.x < 50)
         this.x = this.x + 50;

      startY = this.y;
      startX = this.x;

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

   @Override
   public void move()
   {
      if((startY - y <= 50) && side1 == false)
      {
         y = y - yVel;
      }
      else
      {
         side1 = true;
         if((startX - x <= 50) && side1 == true && side2 == false)
         {
            x = x - xVel;
         }
         else
         {
            side2 = true;
            if((y <= startY) && side2 == true && side3 == false)
            {
               y = y + yVel;
            }
            else
            {
               side3 = true;
               if((x <= startX) && side3 == true && side4 == false)
               {
                  x = x + xVel;
               }
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

   public void clear()
   {
      imageView.setImage(null);
   }
}
