/* Grassland.java */

/**
 *  The Grassland class defines an object that models an meadow full of rabbits and
 *  carrots.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *  public Grassland(int i, int j, int starveTime);
 *
 *  that creates an empty meadow having width i and height j, in which rabbits
 *  starve after starveTime timesteps.
 */
public class Grassland 
{
    /**
     *  Do not rename these constants.  WARNING:  if you change the numbers, you
     *  will need to recompile Test4.java.  Failure to do so will give you a very
     *  hard-to-find bug.
     */

    public final static int EMPTY = 0, RABBIT = 1, CARROT = 2;

    /**
     *  Define any variables associated with an Grassland object here.  These
     *  variables MUST be private.
     */
    private int i, j, starveTime;
    private int count_empty, count_rabbit, count_carrot;


    private Piece Meadow[][];

    /**
     *  Grassland() is a constructor that creates an empty meadow having width i and
     *  height j, in which rabbits starve after starveTime timesteps.
     *  @param i is the width of the meadow.
     *  @param j is the height of the meadow.
     *  @param starveTime is the number of timesteps rabbits survive without food.
     */

    public Grassland(int i, int j, int starveTime) 
    {
        // Your solution here.
        this.i = i;
        this.j = j;
        this.starveTime = starveTime;

        Meadow = new Piece[i][j];
        initialize();
    }

    public void initialize()
    {
        for(int i = 0; i < this.i; i++)
            for(int j = 0; j < this.j; j++)
                Meadow[i][j] = new Piece(EMPTY);
    }

    /**
     *  width() returns the width of an Grassland object.
     *  @return the width of the meadow.
     */

    public int width() 
    {
        // Replace the following line with your solution.
        return this.i;
    }

    /**
     *  height() returns the height of an Grassland object.
     *  @return the height of the meadow.
     */

    public int height() 
    {
        // Replace the following line with your solution.
        return this.j;
    }

    /**
     *  starveTime() returns the number of timesteps rabbits survive without food.
     *  @return the number of timesteps rabbits survive without food.
     */

    public int starveTime() 
    {
        // Replace the following line with your solution.
        return this.starveTime;
    }

    /**
     *  addCarrot() places a carrot in cell (x, y) if the cell is empty.  If the
     *  cell is already occupied, leave the cell as it is.
     *  @param x is the x-coordinate of the cell to place a carrot in.
     *  @param y is the y-coordinate of the cell to place a carrot in.
     */

    public void addCarrot(int x, int y) throws Exception
    {
        // Your solution here.
        if ( ( x >= this.i || x < 0  ) || (y >= this.j || y < 0) ) throw new Exception("Invalid parameter");

        Piece position = this.Meadow[x][y];

        this.Meadow[x][y] = (position.getType() == EMPTY) ? new Carrots(CARROT) : position; 
    }

    /**
     *  addRabbit() (with two parameters) places a newborn rabbit in cell (x, y) if
     *  the cell is empty.  A "newborn" rabbit is equivalent to a rabbit that has
     *  just eaten.  If the cell is already occupied, leave the cell as it is.
     *  @param x is the x-coordinate of the cell to place a rabbit in.
     *  @param y is the y-coordinate of the cell to place a rabbit in.
     */

    public void addRabbit(int x, int y) throws Exception
    {
        // Your solution here.
        if ( ( x >= this.i && x < 0  ) && (y >= this.j && y < 0) ) throw new Exception("Invalid paramet");

        Piece position = this.Meadow[x][y];

        this.Meadow[x][y] = (position.getType() == EMPTY) ? new Rabbit(RABBIT, 1) : position; 

    }

    /**
     *  cellContents() returns EMPTY if cell (x, y) is empty, CARROT if it contains
     *  a carrot, and RABBIT if it contains a rabbit.
     *  @param x is the x-coordinate of the cell whose contents are queried.
     *  @param y is the y-coordinate of the cell whose contents are queried.
     */

    public int cellContents(int x, int y) 
    {
        // Replace the following line with your solution.
        return this.Meadow[x][y].getType();
    }

    /**
     *  timeStep() performs a simulation timestep 
     *  @return a meadow representing the elapse of one timestep.
     */


    public Grassland timeStep() 
    {
        // Replace the following line with your solution.

        for(int j = 0; j < this.j; j++)
        {
            count_empty = 0; 
            count_rabbit = 0; 
            count_carrot = 0;
            
            for(int i = 0; i < this.i; i++)
            {
                countPieceNeighbor(Meadow[j][(i - 1) % this.i].getType(), i, j, );
                countPieceNeighbor(Meadow[j][(i + 1) % this.i].getType(), i, j, );
                countPieceNeighbor(Meadow[(j - 1) % this.j][i].getType(), i , j, );
                countPieceNeighbor(Meadow[(j + 1) % this.j][i].getType(), i , j, );

                countPieceNeighbor(Meadow[(j - 1) % this.j][(i + 1) % this.i].getType(), i, j, );
                countPieceNeighbor(Meadow[(j + 1) % this.j][(i + 1) % this.i].getType(), i, j, );
                countPieceNeighbor(Meadow[(j - 1) % this.j][(i - 1) % this.i].getType(), i, j, );
                countPieceNeighbor(Meadow[(j + 1) % this.j][(i - 1) % this.i].getType(), i, j, );

                if( this.Meadow[i][j].getType() == RABBIT )
                {
                    
                }
                else if( this.Meadow[i][j].getType() == CARROT )
                    {

                    }
                    else //then is empty
                    {

                    }
            }
        }

        return new Grassland(this.i, this.j, this.starveTime);
    }
    
    public void countPieceNeighbor(int type, int x, int y)
    {
        switch (type) 
        {
            case EMPTY:
                count_empty += 1;
                break;
            case RABBIT:
                count_rabbit += 1;
            case CARROT:
                count_carrot += 1;
                break;
        }
    }

    /*
    public void showInfo(int x, int y)
    {
        System.out.println(Meadow[x][y].toString() + " " + Piece.equalsTo(Meadow[0][0], Meadow[1][1]));
    }
    */
}
