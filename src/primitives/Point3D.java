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
    protected double coordX;
    /**
     * The Coord y.
     */
    protected double coordY;
    /**
     * The Coord z.
     */
    protected double coordZ;

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


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Substract vector.
     *
     * @param temp the temp
     * @return the vector
     * @throws SpecialException the special exception
     */
    public Vector Substract(Point3D temp) throws SpecialException {
        return new Vector(temp.coordX-coordX,temp.coordY-coordY,temp.coordZ-coordZ);
    }

    /**
     * Add point 3 d.
     *
     * @param temp the temp
     * @return the point 3 d
     * @throws SpecialException the special exception
     */
    public Point3D Add(Vector temp) throws SpecialException {
        return new Point3D(temp.point.coordX+coordX,temp.point.coordY+coordY,temp.point.coordZ+coordZ);
    }

    /**
     * Distance square  from a point to an other.
     *
     * @param temp the temp
     * @return the double
     */
    public double DistanceSquare(Point3D temp)
    {
        double NewX =  (temp.coordX-coordX) * (temp.coordX-coordX);
        double NewY = (temp.coordY-coordY) * (temp.coordY-coordY);
        double NewZ = (temp.coordZ-coordZ) * (temp.coordZ-coordZ);
        return (NewX+NewY+NewZ);
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
    public double getCoordX() {
        return coordX;
    }

    /**
     * Gets coord y.
     *
     * @return the coord y
     */
    public double getCoordY() {
        return coordY;
    }

    /**
     * Gets coord z.
     *
     * @return the coord z
     */
    public double getCoordZ() {
        return coordZ;
    }

    /**
     * Sets x.
     *
     * @param coordX the coord x
     * @throws SpecialException the special exception
     */
    public void setcoordX(double coordX) throws SpecialException {
        this.coordX = coordX;
    }

    /**
     * Sets y.
     *
     * @param coordY the coord y
     * @throws SpecialException the special exception
     */
    public void setcoordY(double coordY) throws SpecialException {
        this.coordY = coordY;
    }

    /**
     * Sets z.
     *
     * @param coordZ the coord z
     * @throws SpecialException the special exception
     */
    public void setcoordZ(double coordZ) throws SpecialException {
        this.coordZ = coordZ;
    }
}
