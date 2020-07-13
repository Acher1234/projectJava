package geometries;

import primitives.Point3D;
import primitives.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.sqrt;
import static primitives.Util.alignZero;

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

    /**
     * gets normal override
     * @param temp the temp
     * @return
     */
    @Override
    public Vector getNormal(Point3D temp)
    {
        return  _center.subtract(temp).normalized();
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point3D get_center() {
        return _center;
    }

    /**
     * find intersections override
     * @param ray
     * @return
     */
    @Override
    public
    List<Point3D> findIntersection(Ray ray)
    {
        Vector U =_center.subtract(ray.getPOO());

        double tm = alignZero(ray.getDirection().dotProduct(U));// dot product beetween the U and the ray vecteur
        double d = Math.sqrt(U.lengthSquared() - (tm*tm));
        double th = (_radius*_radius);
        th -= ((d)*(d));
        if(th < 0)
        {
            return null;
        }
        th = sqrt(th);
        List<Point3D> List = new ArrayList<Point3D>();
        if(U.length() < _radius)//there is only one point
        {
            if(tm-th > 0)
            {
                List.add(ray.getPoint(alignZero(tm-th)));
            }
            if(tm-th > 0)
            {
                List.add(ray.getPoint(alignZero(tm-th)));
            }
            return List;
        }
        double scale = alignZero(tm-th);
        List.add(ray.getPoint(scale));
        List.add(ray.getPoint(alignZero(tm+th)));
        return List;
    }
}
