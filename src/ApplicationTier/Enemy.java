package ApplicationTier;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import static java.awt.Color.BLUE;

public class Enemy extends PFigure
{
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
}
