package geometries;

/**
 * The type Radial geometry.
 */
public abstract class RadialGeometry implements Geometry
{
    /**
     * The Radius.
     */
//************************Variable****************
    double _radius;


    /**
     * Gets radius.
     *
     * @return the radius
     */
//************************METHODE****************
    public double get_radius()
    {
        return _radius;
    }

    /**
     * Instantiates a new Radial geometry.
     *
     * @param _radius the radius
     */
    public RadialGeometry(double _radius)
    {
        this._radius = _radius;
    }

    /**
     * Instantiates a new Radial geometry.
     *
     * @param temp the temp
     */
    public RadialGeometry(RadialGeometry temp)
    {
        this._radius = temp._radius;
    }
}
