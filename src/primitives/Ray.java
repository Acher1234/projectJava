package primitives;

/**
 * The type Ray.
 */
public class Ray
{
    /**
     * The Direction.
     */
    protected Vector direction;

    /**
     * Point presence boolean.
     *
     * @param point the point
     * @return the boolean
     */
//********************METHODES****************
    public Boolean PointPresence(Point3D point)
    {
        double TX = point.coordX / direction.point.coordX;
        double TY = point.coordY / direction.point.coordY;
        double TZ = point.coordZ / direction.point.coordZ;
        return (TX  == TY  && TY == TZ);

    }

    /**
     * Instantiates a new Ray.
     *
     * @param coordX the coord x
     * @param coordY the coord y
     * @param coordZ the coord z
     * @throws SpecialException the special exception
     */
//*******************CONSTRUCTOR*******************************
    public Ray(double coordX, double coordY, double coordZ) throws SpecialException
    {
        direction = new Vector(coordX, coordY, coordZ);
    }

}
