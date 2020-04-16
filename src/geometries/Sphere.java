package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.sqrt;
import static primitives.Util.*;

/**
 * The type Sphere.
 */
public class Sphere extends RadialGeometry
{
    /**
     * The Center.
     */
//************************Variable*************
    Point3D _center;

    /**
     * Instantiates a new Sphere.
     *
     * @param _radius the radius
     * @param center  the center
     */
    public Sphere(double _radius,Point3D center)
    {
        super(_radius);
        this._center = center;
    }

    /**
     * Instantiates a new Sphere.
     *
     * @param temp   the temp
     * @param center the center
     */
    public Sphere(RadialGeometry temp,Point3D center)
    {
        super(temp);
        this._center = center;
    }

    //************************METHODE****************
    @Override
    public Vector getNormal(Point3D temp)
    {
       return  temp.subtract(_center).normalized();
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point3D get_center() {
        return _center;
    }


    @Override
    public List<Point3D> findIntersection(Ray ray) {
        return null;
    }
}
