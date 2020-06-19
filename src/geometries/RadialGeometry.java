package geometries;

import primitives.Color;

/**
 * The type Radial geometry.
 */
public abstract class RadialGeometry extends Geometry
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
    public RadialGeometry( Color emission,double _radius)
    {
        this._radius = _radius;
        this._emmission = emission;
    }

    /**
     * Instantiates a Radial geometry.
     * @param _radius
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
        this._emmission = temp._emmission;
    }
}
