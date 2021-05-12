package ApplicationTier;

import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class Enemy extends PFigure
{
   private int xVel = 20;
   private int yVel = 20;
   private ImageView imageView;

   public Enemy(Pane p)
   {
      // TODO PR is subject to change. Its a temporary 1.
      super(50,  50,  110,  78, 1, p );
      try
      {
         imageView = new ImageView("file:dmg.jpg"); // find enemy pic
         draw();
      }
      catch ( Exception e )
      {
         e.printStackTrace();
      }
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
   }

   @Override
   public void draw()
   {
      if( imageView != null )
      {
         imageView.setX(x);
         imageView.setY(y);
         imageView.setFitHeight(height);
         imageView.setFitWidth(width);
         living_pane.getChildren().clear();
         living_pane.getChildren().add(imageView);
         living_pane.setVisible(true);
      }
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
