package ApplicationTier;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

import java.util.Random;

public class Enemy extends PFigure
{
   private int xVel = 1;
   private int yVel = 1;
   private ImageView imageView;

   Group enemy;

   private static final int STARTING_POS_X = 200;
   private static final int STARTING_POS_Y = 300;
   private static final int DEFAULT_WIDTH = 10;
   private static final int DEFAULT_HEIGHT = 10;
   private static final int PRIORITY = 0;

   Random enemySpawn = new Random();

   private int randX = enemySpawn.nextInt(900);
   private int randY = enemySpawn.nextInt(600);


   public Enemy(Pane enemyPane)
   {
      // TODO PR is subject to change. Its a temporary 0 (for enemy).
      super(STARTING_POS_X,  STARTING_POS_Y,  DEFAULT_HEIGHT,
              DEFAULT_WIDTH, PRIORITY, enemyPane );

      this.x = randX;
      this.y = randY;

      super.living_pane.setPrefSize(DEFAULT_HEIGHT,DEFAULT_WIDTH);
   }

   @Override
   public void move()
   {
      if ( xVel < 0 && x <= 0 || xVel > 0 && x + width >= living_pane.getWidth())
         xVel = - xVel;
      if ( yVel < 0 && y <= 0 || yVel > 0 && y + height >= living_pane.getHeight())
         yVel = - yVel;
      x = x + xVel;
      y = y + yVel;

      //Polygon.union(enemy, enemy);

     // draw();

      //Move right
      //enemy.getPoints().setAll(x + 10.0);
      living_pane.getChildren().remove(enemy);

   }

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
      enemyOutline.getPoints().addAll(new Double[]{
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
              x - 0.0, y - 12.0,
      });
      enemyOutline.setFill(Color.RED);
      enemyOutline.setStroke(Color.BLACK);

      enemy = new Group(enemyOutline, eye1, eye2, pupil1, pupil2, mouth);
      living_pane.getChildren().add(enemy);
   }
}


/*
   private Circle enemy;
   private final Line enemyPath = new Line();
   private final PathTransition transition = new PathTransition();

   private static final Duration TRANSLATE_DURATION = Duration.seconds(0.25);
   private int startPosX, startPosY, radius, priority;
   private Pane pane;

   public Enemy(Pane pane)
   {
      this.startPosX = this.startPosY = 50;
      this.radius = 10;
      this.priority = 2;
   }

   @Override
   public void draw()
   {
      enemy.setFill(Color.BLUE);
   }

   private void enemyTransition()
   {
      transition.setNode(enemy);
      transition.setDuration((Duration.INDEFINITE));
      transition.setPath(enemyPath);
      transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
      transition.setCycleCount(PathTransition.INDEFINITE);
      transition.play();
   }

   private TranslateTransition createTranslateTransition(final Circle circle)
   {
      TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
      transition.setOnFinished(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent t)
         {
            circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
            circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
            circle.setTranslateX(0);
            circle.setTranslateY(0);
         }
      });
      return transition;
   }

}*/
