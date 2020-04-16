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

    /**
     * Instantiates a new Plane.
     *
     * @param _point1 the point 1
     * @param _point2 the point 2
     * @param _point3 the point 3
     */
    public Plane(Point3D _point1,Point3D _point2,Point3D _point3) {
        this._p = _point1;
        Vector VectorPlane1 = _point2.subtract(_point1);
        Vector VectorPlane2 = _point3.subtract(_point1);
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
        this._normal = Normal.normalized();
    }
    public Plane(Plane other){
        this._p = new Point3D(other._p);
        this._normal = new Vector(other._normal);
    }


    @Override
    public Vector getNormal(Point3D temp)
    {
        return _normal;
    }
    public Vector getNormal() {
        return _normal;
    }
    public Point3D getPoint()
    {
        return _p;
    }
}
