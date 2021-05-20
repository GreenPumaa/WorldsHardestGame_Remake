package ApplicationTier;

import java.util.LinkedList;

/**
 * This class manages a list of PFigures.
 * It allows the program to add and remove figures, as well as update their
 * positions on screen.
 * @author Nathan Sandvig, Daniel Hungness
 */
public class PFigureList
{
   /**
    * The list of PFigures being managed by the class
    */
   private final LinkedList<PFigure> figures;

   /**
    * Default constructor
    */
   public PFigureList() { figures = new LinkedList<>(); }

   /**
    * Returns a figure
    * @return The requested figure
    */
   public PFigure figure(int index)
   {
      return figures.get(index);
   }

   /**
    * Returns the size of the list
    * @return The size of the list
    */
   public int size()
   {
      return figures.size();
   }

   /**
    * Adds a new PFigure to the list
    * @param p The PFigure being added
    */
   public void add(PFigure p)
   {
      figures.add(p);
   }

   /**
    * Removes a PFigure from the list
    * @param p The PFigure being removed
    */
   public void remove(PFigure p)
   {
      figures.remove(p);
   }

   /**
    * Clears the list
    */
   public void clear()
   {
      figures.clear();
   }
}