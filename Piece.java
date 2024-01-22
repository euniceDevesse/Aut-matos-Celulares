public  class Piece
{
    private int timeStep, type;

    public Piece(int type)
    {
        this.type = type;
    }

    public Piece(int type, int timeStep)
    {
        this(type);
        this.timeStep = timeStep;
    }

    public static void toReproduce(int type, int posX, int posY, Object objectPiece[][])
    {
        objectPiece[posX][posY] = (type == Grassland.CARROT) ? new Carrots(Grassland.CARROT) : new Rabbit(Grassland.RABBIT, 1);
    }

    public void toKill(int posX, int posY, Object objectPiece[][])
    {
        objectPiece[posX][posY] = new Piece(Grassland.EMPTY);
    }

    public int getType()
    {
        return this.type;
    }
    public void setType(int type)
    {
        this.type = type;
    }

    public int getTimeStep()
    {
        return this.timeStep;
    }
    public void setTimeStep(int timeStep)
    {
        this.timeStep = timeStep;
    }

    public String toString()
    {
        String value = "Type: ";
        
        if (this.type == Grassland.CARROT)
            value += "Carrot ";
        else if (this.type == Grassland.RABBIT)
                value += "Rabbit ";
            else 
                value += "Empty ";
            
        return value += "timestep: " + this.timeStep;     
    }

    public static Boolean equalsTo(Piece Piece1, Piece Piece2)
    {
        return Piece1.getClass().equals(Piece2.getClass());
    }
}
