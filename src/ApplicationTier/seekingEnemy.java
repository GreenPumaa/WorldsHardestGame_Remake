package ApplicationTier;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import java.util.Random;

/**
 * Class that controls the seeking enemy. Moves and draws the seeking enemy.
 * @author Hunter Liddell, Noah Mullendore, Carter Klare
 */
public class seekingEnemy extends PFigure
{
   private Group seekingEnemy;

   private static final int STARTING_POS_X = 200;
   private static final int STARTING_POS_Y = 300;
   private static final int DEFAULT_WIDTH = 10;
   private static final int DEFAULT_HEIGHT = 10;
   private static final int PRIORITY = 0;

   private final Random enemySpawn = new Random();

   private final int randX = enemySpawn.nextInt(900);
   private final int randY = enemySpawn.nextInt(600);


   /**
    * Parameterized constructor
    * @param enemyPane Living pane
    */
   public seekingEnemy(Pane enemyPane)
   {
      super(STARTING_POS_X,  STARTING_POS_Y,  DEFAULT_HEIGHT,
              DEFAULT_WIDTH, PRIORITY, enemyPane );
      this.x = randX;
      this.y = randY;
      super.living_pane.setPrefSize(DEFAULT_HEIGHT,DEFAULT_WIDTH);
   }

   /**
    * Overridden method to move the seeking enemy.
    * This enemy tracks the players current location and constantly moves
    * in its direction.
    */
   @Override
   public void move()
   {
      double xPos, yPos;
      int xVel = 1, yVel = 1;
      xPos = Player.getX();
      yPos = Player.getY();

      if(this.x >= xPos)
         x = x - xVel;
      if(this.x <= xPos)
         x = x + xVel;

      if(this.y >= yPos)
         y = y - yVel;
      if(this.y <= yPos)
         y = y + yVel;

      living_pane.getChildren().remove(seekingEnemy);
   }

   /**
    * Overridden method to draw the seeking enemy as it moves.
    */
   @Override
   public void draw()
   {
      Circle eye1 = new Circle(x - 2, y - 3, 3);
      eye1.setStroke(Color.BLACK);
      eye1.setFill(Color.YELLOW);
      Circle eye2 = new Circle(x + 2, y - 3, 3);
      eye2.setStroke(Color.BLACK);
      eye2.setFill(Color.YELLOW);
      Circle pupil1 = new Circle(x - 2, y - 2.5, 1.5);
      pupil1.setFill(Color.BLACK);
      Circle pupil2 = new Circle(x + 2, y - 2.5, 1.5);
      pupil2.setFill(Color.BLACK);
      Ellipse mouth = new Ellipse(x, y + 4, 5, 1.5);
      mouth.setFill(Color.YELLOWGREEN);

      Polygon enemyOutline = new Polygon(); //coordinates of the polygon
      // vertices
      enemyOutline.getPoints().addAll(x - 0.0, y - 12.0,
              x + 3.0, y - 9.0,//inside - in 3 down 3
              x + 10.0, y - 6.0,
              x + 7.0, y - 3.0,
              x + 12.0, y - 0.0,
              x + 7.0, y + 3.0,
              x + 10.0, y + 6.0,
              x + 3.0, y + 9.0,
              x + 0.0, y + 12.0,
              x - 3.0, y + 9.0,
              x - 10.0, y + 6.0,
              x - 7.0, y + 3.0,
              x - 12.0, y - 0.0,
              x - 7.0, y - 3.0,
              x - 10.0, y - 6.0,
              x - 3.0, y - 9.0,
              x - 0.0, y - 12.0);
      enemyOutline.setFill(Color.PURPLE);
      enemyOutline.setStroke(Color.BLACK);

      seekingEnemy = new Group(enemyOutline, eye1, eye2, pupil1, pupil2, mouth);
      living_pane.getChildren().add(seekingEnemy);
   }
}

