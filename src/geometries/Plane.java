package geometries;

import primitives.*;
import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The type Plane.
 */
public class Plane extends Geometry
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
        this._p = _point1;
        this._normal = Normal.normalized();
    }

    /**
     * Instantiates a new Plane.
     *
     * @param other the other
     */
    public Plane(Plane other){
        this._p = new Point3D(other._p);
        this._normal = new Vector(other._normal);
    }


    @Override
    public Vector getNormal(Point3D temp)
    {
        return _normal;
    }

    /**
     * Gets normal.
     *
     * @return the normal
     */
    public Vector getNormal() {
        return _normal;
    }

    /**
     * Gets point.
     *
     * @return the point
     */
    public Point3D getPoint()
    {
        return _p;
    }

    @Override
    public List<Point3D> findIntersection(Ray ray)
    {
        if(isZero(ray.getDirection().dotProduct(_normal)) || isZero(ray.getDirection().dotProduct(_normal)))//parralel to the plan
        {

            return null;
        }
        Vector U;
        try {
            U = _normal.crossProduct(ray.getDirection());
        }
        catch (Exception e)
        {
            //perpendicular
        }
        Vector planToPoint = _p.subtract(ray.getPOO());
        double numerator = _normal.dotProduct(planToPoint);
        double denominator = ray.getDirection().dotProduct(_normal);
        double t = alignZero(numerator/denominator);
        if(t<0)
        {
            return null;
        }
        List<Point3D> List = new ArrayList<Point3D>();
        List.add(ray.getPoint(t));
        return List;
    }
}
