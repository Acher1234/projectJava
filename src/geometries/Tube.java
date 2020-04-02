package geometries;

import primitives.*;


/**
 * The type Tube.
 */
public class Tube extends RadialGeometry {
    /**
     * The Axis ray.
     */
    Ray _axisRay;

    /**
     * Instantiates a new Tube.
     *
     * @param _radius the radius
     * @param axisRay the axis ray
     */
    public Tube(double _radius,Ray axisRay) {
        super(_radius);
        _axisRay = axisRay;
    }

    /**
     * Instantiates a new Tube.
     *
     * @param temp    the temp
     * @param axisRay the axis ray
     */
    public Tube(RadialGeometry temp,Ray axisRay) {
        super(temp);
        _axisRay = axisRay;
    }

    //*************METHODE***************
    @Override
    public Vector getNormal(Point3D temp)
    {
    return null;
    }

    /**
     * Gets axis ray.
     *
     * @return the axis ray
     */
    public Ray get_axisRay() {
        return _axisRay;
    }
}
