package ApplicationTier;

import javafx.scene.layout.Pane;
/**
 * Creates base for figures.
 * @author
 */
public abstract class PFigure implements Comparable
{
   private int x, y;           // Current position of the figure
   private int radius;         // Drawn (displayed) this size
   private int priority;       // Can use to determine "winner"
   private Pane pane;          // Panel the figure lives on

   /*
   public PFigure ( int posX, int posY, int radius, int pr, Pane p )
   {
      this.x = posX;
      this.y = posY;
      this.radius = radius;
      this.priority = pr;
      this.pane = p;
   }
   */


   /**
    *
    * @param
    * @return
    */
   @Override
   public int compareTo(Object o)
   {
      if( o instanceof PFigure )
         return priority - ((PFigure)o).priority;
      return Integer.MAX_VALUE;
   }

   /**
    * Checks if the figure has collided with this.
    * @param p the figure in the question.
    * @return true if the figures have collided, false otherwise.
    */
   public boolean collidedWith ( PFigure p )
   {
      if (  p == null )
         return false;

      return ( x + radius ) >= p.x && ( p.x + p.radius ) >= x &&
         ( y + radius ) >= p.y && ( p.y + p.radius ) >= y;
   }

   /**
    * Changes the position of the figure.
    * @param deltaX amount to change the x coordinate by.
    * @param deltaY amount to change the y coordinate by.
    */
   public void move ( int deltaX, int deltaY )
   {
      x = x + deltaX;
      y = y + deltaY;
   }

   /**
    * Hides the figure.
    */
   public void hide()
   {
      pane.setVisible(false);
   }

   /**
    * Move method to be overridden.
    */
   public void move()
   {
   }


   /**
    * Abstract draw method to be overridden.
    */
   abstract public void draw();


}