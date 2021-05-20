package ApplicationTier;

import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

/**
 * Creates base for figures.
 * @author Donna Gavin?
 */
public abstract class PFigure implements Comparable
{
   protected int x, y;           // Current position of the figure
   protected int height, width;  // Drawn (displayed) this size
   protected int priority;       // Can use to determine "winner"
   protected Pane living_pane;   // Panel the figure lives on

   /**
    * Parameterized constructor
    * @param posX X Coordinate.
    * @param posY Y Coordinate.
    * @param _height Height of the figure.
    * @param _width Width of the figure.
    * @param pr Priority of the figure.
    * @param p Pane that the figure lives on.
    */
   public PFigure ( int posX, int posY, int _height, int _width, int pr, Pane p )
   {
      this.x = posX;
      this.y = posY;
      this.height = _height;
      this.width = _width;
      this.priority = pr;
      this.living_pane = p;
   }

   /**
    * Compares the priorities of this figure and the passed figure.
    * @param o the passed PFigure
    * @return This priority - o.priority
    */
   @Override
   public int compareTo(@NotNull Object o)
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
      return ( x + width ) >= p.x && ( p.x + p.width ) >= x &&
         ( y + height ) >= p.y && ( p.y + p.height ) >= y;
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
      this.living_pane.setVisible(false);
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