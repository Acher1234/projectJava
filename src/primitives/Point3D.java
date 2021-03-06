package primitives;

/**
 * The type Point 3 d.
 */
public class Point3D
{
    /**
     * The constant ZERO.
     */
    public static Point3D ZERO = new Point3D(0,0,0);
    /**
     * The Coord x.
     */
    protected Coordinate coordX;
    /**
     * The Coord y.
     */
    protected Coordinate coordY;
    /**
     * The Coord z.
     */
    protected Coordinate coordZ;

    /**
     * Instantiates a new Point 3 d.
     *
     * @param coordX the coord x
     * @param coordY the coord y
     * @param coordZ the coord z
     */
//*******************CONSTRUCTOR*******************************
    public Point3D(double coordX, double coordY, double coordZ)
    {
        this.coordX = new Coordinate(coordX);
        this.coordY = new Coordinate(coordY);
        this.coordZ = new Coordinate(coordZ);
    }

    public Point3D(Coordinate coordX, Coordinate coordY, Coordinate coordZ)
    {
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordZ = coordZ;
    }

    /**
     * Instantiates a new Point 3 d.
     *
     * @param point the point
     */
    public Point3D(Point3D point)
    {
        this.coordX = point.coordX;
        this.coordY = point.coordY;
        this.coordZ = point.coordZ;
    }
    //************METHODES****************



    public int compareTo(Point3D other) {
        if(!coordX.equals(other.coordX))
            return 0;
        if(!coordY.equals(other.coordY))
            return 0;
        if(!coordZ.equals(other.coordZ))
            return 0;
        return 1;
    }

    /**
     * Substract vector.
     *
     * @param temp the temp
     * @return the vector
     */
    public Vector subtract(Point3D temp) {
        return new Vector(temp.coordX.get()-coordX.get(),temp.coordY.get()-coordY.get(),temp.coordZ.get()-coordZ.get());
    }

    /**
     * Add point 3d.
     *
     * @param temp the temp
     * @return the point 3 d
     */
    public Point3D Add(Vector temp){
        return new Point3D((new Coordinate(temp.head.coordX.get()+coordX.get())),(new Coordinate(temp.head.coordY.get()+coordY.get())),(new Coordinate(temp.head.coordZ.get()+coordZ.get())));
    }

    /**
     * Distance square  from a point to an other.
     *
     * @param temp the temp
     * @return the double
     */
    public double DistanceSquare(Point3D temp)
    {
        double NewX =  (temp.coordX.get()-coordX.get()) * (temp.coordX.get()-coordX.get());
        double NewY = (temp.coordY.get()-coordY.get()) * (temp.coordY.get()-coordY.get());
        double NewZ = (temp.coordZ.get()-coordZ.get()) * (temp.coordZ.get()-coordZ.get());
        return (NewX+NewY+NewZ);
    }

    public String toString() // this function writes what are the coordinates of the point.
    {
        return String.format("(%.2f, %.2f, %.2f)", coordX.get(), coordY.get(), coordZ.get()); // format de cdavid.
    }

    /**
     * Distance from a point to an other.
     *
     * @param temp the temp
     * @return the double
     */
    public double Distance(Point3D temp)
    {
        return Math.sqrt(DistanceSquare(temp));
    }

    /**
     * Gets coord x.
     *
     * @return the coord x
     */
//-------------------------------GET/SET----------------
    public Coordinate getCoordX() {
        return coordX;
    }

    /**
     * Gets coord y.
     *
     * @return the coord y
     */
    public Coordinate getCoordY() {
        return coordY;
    }

    /**
     * Gets coord z.
     *
     * @return the coord z
     */
    public Coordinate getCoordZ() {
        return coordZ;
    }

    /**
     * Sets x.
     *
     * @param coordX the coord x
     */
    public void setcoordX(double coordX){
        this.coordX = new Coordinate(coordX);
    }

    /**
     * Sets y.
     *
     * @param coordY the coord y
     */
    public void setcoordY(double coordY) {
        this.coordY = new Coordinate(coordY);
    }

    /**
     * Sets z.
     *
     * @param coordZ the coord z
     */
    public void setcoordZ(double coordZ) {
        this.coordY = new Coordinate(coordY);
    }

}
