package geometries;

import primitives.*;

/**
 * The type Plane.
 */
public class Plane implements Geometry
{
    /**
     * The P.
     */
//************************Variable****************
    Point3D _p;
    /**
     * The Normal.
     */
    Vector _normal;

    //************************METHODE****************
    @Override
    public Vector getNormal(Point3D temp)
    {
        return _normal;
    }

    /**
     * Instantiates a new Plane.
     *
     * @param _point1 the point 1
     * @param _point2 the point 2
     * @param _point3 the point 3
     * @throws SpecialException the special exception
     */
    public Plane(Point3D _point1,Point3D _point2,Point3D _point3) throws SpecialException {
        this._p = _point1;
        Vector VectorPlane1 = _point1.subtract(_point2);
        Vector VectorPlane2 = _point3.subtract(_point2);
        this._normal = VectorPlane1.crossProduct(VectorPlane2);

    }

    /**
     * Instantiates a new Plane.
     *
     * @param _point1 the point 1
     * @param Normal  the normal
     */
    public Plane(Point3D _point1,Vector Normal)
    {
        this._p = _p;
        this._normal = Normal;
    }

    public Vector getNormal() {
        return _normal;
    }
}
