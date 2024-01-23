
public class Rabbit extends Piece
{
    public Rabbit(int type, int timeStep)
    {
        super(type, timeStep);
    }

    public void toEat()
    {
        setTimeStep(1);
    }

    public void incremetTime()
    {
        setTimeStep(getTimeStep() + 1);
    }
}