package ApplicationTier;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import java.util.Random;

/**
 * Enemy class holds the information needed for the linear enemy. Extends
 * PFigure.
 * Instantiates the linear enemy starting direction, movement, and drawing
 * on the pane.
 * @author Hunter Liddell, Noah Mullendore, Carter Klare
 */
public class Enemy extends PFigure
{
   private int xVel = 1;
   private int yVel = 1;

   private Group enemy;

   private static final int STARTING_POS_X = 200, STARTING_POS_Y = 300;
   private static final int DEFAULT_WIDTH = 10, DEFAULT_HEIGHT = 10;
   private static final int PRIORITY = 0;

   private static final int DOWN_RIGHT_DIRECTION = 1, UP_LEFT_DIRECTION = 4;
   private static final int DOWN_LEFT_DIRECTION = 2, UP_RIGHT_DIRECTION = 3;

   private final Random enemySpawn = new Random();

   /**
    * Parameterized constructor.
    * @param enemyPane - The pane that the enemy will be created on.
    */
   public Enemy(Pane enemyPane)
   {
      // TODO PR is subject to change. Its a temporary 0 (for enemy).
      super(STARTING_POS_X,  STARTING_POS_Y,  DEFAULT_HEIGHT,
              DEFAULT_WIDTH, PRIORITY, enemyPane );

      final int randX = enemySpawn.nextInt(900);
      final int randY = enemySpawn.nextInt(600);
      this.x = randX;
      this.y = randY;

      setStartingDirection();

      super.living_pane.setPrefSize(DEFAULT_HEIGHT,DEFAULT_WIDTH);
   }

   /**
    * Sets the linear enemies starting direction.
    */
   private void setStartingDirection()
   {
      final int direction = enemySpawn.nextInt(5);
      switch (direction) {
         case DOWN_RIGHT_DIRECTION -> {
            xVel = 1;
            yVel = 1;
         }
         case DOWN_LEFT_DIRECTION -> {
            xVel = -1;
            yVel = 1;
         }
         case UP_RIGHT_DIRECTION -> {
            xVel = 1;
            yVel = -1;
         }
         case UP_LEFT_DIRECTION -> {
            xVel = -1;
            yVel = -1;
         }
      }
   }

   /**
    * Overridden move() method from PFigure. Moves the linear enemy by
    * changing its x and y values, deleting it, then redrawing in the new
    * position.
    */
   @Override
   public void move()
   {
      if ( xVel < 0 && x <= 0 || xVel > 0 && x + width >= living_pane.getWidth())
         xVel = - xVel;
      if ( yVel < 0 && y <= 0 || yVel > 0 && y + height >= living_pane.getHeight())
         yVel = - yVel;
      x = x + xVel;
      y = y + yVel;

      living_pane.getChildren().remove(enemy);
   }

   /**
    * Overridden draw() method from PFigure.
    * Draws the linear enemy with its x and y coordinates, color, and type.
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
      enemyOutline.getPoints().addAll(
         x - 0.0, y - 12.0,
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
      enemyOutline.setFill(Color.RED);
      enemyOutline.setStroke(Color.BLACK);

      enemy = new Group(enemyOutline, eye1, eye2, pupil1, pupil2, mouth);
      living_pane.getChildren().add(enemy);
   }

   /**
    * Sets x velocity.
    * @param xVel x velocity.
    */
   public void set_xVel(int xVel) {
      this.xVel = xVel;
   }

   /**
    * Sets the y velocity.
    * @param yVel y velocity.
    */
   public void set_yVel(int yVel) {
      this.yVel = yVel;
   }

   /**
    * Gets the x velocity.
    * @return xVel
    */
   public int get_xVel() {
      return xVel;
   }

   /**
    * Gets the y velocity.
    * @return yVel
    */
   public int get_yVel() {
      return yVel;
   }
}
