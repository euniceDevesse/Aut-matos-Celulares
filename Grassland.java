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


    private static Piece Meadow[][];
    private Piece MeadowShadow[][];


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
        for(int j = 0; j < this.j; j++)
            for(int i = 0; i < this.i; i++)
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

        Piece position = Meadow[x][y];

        Meadow[x][y] = (position.getType() == EMPTY) ? new Carrots(CARROT) : position; 
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

        Piece position = Meadow[x][y];

        Meadow[x][y] = (position.getType() == EMPTY) ? new Rabbit(RABBIT, 1) : position; 

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
        return Meadow[x][y].getType();
    }


    public void copyMeadow(Piece meadow[][], Piece meadow2[][])
    {
        for(int j = 0; j < this.j; j++)
            for(int i = 0; i < this.i; i++)
                meadow2[i][j] = meadow[i][j];
    }
    /**
     *  timeStep() performs a simulation timestep 
     *  @return a meadow representing the elapse of one timestep.
     */

    public Piece[][] timeStep() throws Exception
    {
        // Replace the following line with your solution.
        Piece positionCurrent;
        MeadowShadow = new Piece[this.i][this.j];
        copyMeadow(Meadow, MeadowShadow);

        for(int y = 0; y < this.j; y++)
        {
            count_empty = 0; 
            count_rabbit = 0; 
            count_carrot = 0;
            
            for(int x = 0; x < this.i; x++)
            {
                positionCurrent = Meadow[x][y];
 
                search_execute(Meadow[x][Math.floorMod((y - 1) , this.i)].getType(), x, Math.floorMod((y + 1) , this.i), positionCurrent);
                search_execute(Meadow[Math.floorMod((x - 1) , this.j)][y].getType(), Math.floorMod((x - 1) , this.j) , y, positionCurrent);
                search_execute(Meadow[Math.floorMod((x + 1) , this.j)][y].getType(), Math.floorMod((x + 1) , this.j) , y, positionCurrent);
                search_execute(Meadow[x][Math.floorMod((y + 1), this.i)].getType(), x, Math.floorMod((y + 1), this.i) , positionCurrent);
                
                search_execute(Meadow[Math.floorMod((x - 1) , this.j)][Math.floorMod((y + 1) , this.i)].getType(),Math.floorMod((x - 1) , this.j),Math.floorMod((y + 1) , this.i), positionCurrent);
                search_execute(Meadow[Math.floorMod((x + 1) , this.j)][Math.floorMod((y + 1) , this.i)].getType(),Math.floorMod((x + 1) , this.j),Math.floorMod((y + 1) , this.i), positionCurrent);
                search_execute(Meadow[Math.floorMod((x - 1) , this.j)][Math.floorMod((y - 1) , this.i)].getType(),Math.floorMod((x - 1) , this.j),Math.floorMod((y - 1) , this.i), positionCurrent);
                search_execute(Meadow[Math.floorMod((x + 1) , this.j)][Math.floorMod((y - 1) , this.i)].getType(),Math.floorMod((x + 1) , this.j),Math.floorMod((y - 1) , this.i), positionCurrent);
            
            }
        }

        copyMeadow(MeadowShadow, Meadow);
        return Meadow;
    }
    
    public void search_execute(int type, int x, int y, Piece positionCurrent) throws Exception
    {
        switch (type) 
        {
            case EMPTY:
                count_empty += 1;
                break;
            case RABBIT:
                count_rabbit += 1;
                break;
            case CARROT:
                count_carrot += 1;
                break;
        }

        if( positionCurrent.getType() == RABBIT )
        {
            if ( count_carrot + count_empty + count_rabbit == 8 )
            {
                Rabbit AuxRabbit = (Rabbit) positionCurrent;

                if ( count_empty == 8 )
                {
                    AuxRabbit.incremetTime();
                    if ( AuxRabbit.getTimeStep() > this.starveTime )
                        Piece.toKill(x, y, MeadowShadow);
                }
                else if ( type == CARROT && count_carrot == 1 )
                    {
                        Piece.toKill(x, y, MeadowShadow);
                    }
            }
        }

        if ( positionCurrent.getType() == CARROT )
        {
            if ( count_rabbit > 1 )
            {
                if ( count_carrot + count_empty + count_rabbit == 8 )
                    Piece.toReproduce(RABBIT, x, y, MeadowShadow);
            }
        }

        if( positionCurrent.getType() == EMPTY )
        {
            if( count_carrot + count_empty + count_rabbit == 8 )
            {
                if ( count_carrot > 1 && (count_rabbit >= 0 && count_rabbit < 2) )
                {
                    Piece.toReproduce(CARROT, x, y, MeadowShadow);
                }

                if ( count_carrot > 1 &&  count_rabbit > 1 )
                {
                    Piece.toReproduce(RABBIT, x, y, MeadowShadow);
                }
            }
        }
    }

    /*
    public void showInfo(int x, int y)
    {
        System.out.println(Meadow[x][y].toString() + " " + Piece.equalsTo(Meadow[0][0], Meadow[1][1]));
    }
    */
}
