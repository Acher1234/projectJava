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
     * gets normal override from the points
     *
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
     * finds intersections override
     * @param ray the ray
     * @return
     */
   @Override
    public List<Point3D> findIntersection(Ray ray) {
       List<Point3D> List = new ArrayList<Point3D>();
       Vector U = null;
       try {
           U = _center.subtract(ray.getPOO());
       } catch (Exception e) {
           List.add(ray.getPoint(_radius));
           return List;
       }
       double tm = alignZero(U.dotProduct(ray.getDirection()));// dot product beetween the U and the ray vecteur
       double d = U.lengthSquared() - (tm * tm);
       double th = (_radius * _radius) - d;
       if (th < 0) {
           return null;
       }
       th = sqrt(th);
       double t1 = alignZero(tm - th);
       t1 = (double) Math.round(t1 * 100) / 100;
       double t2 = alignZero(tm + th);
       t2 = (double) Math.round(t2 * 100) / 100;
       if(t1 < 0 && t2 < 0)
       {
           return null;
       }
       if (t1 >= 0 &&  t2 < 0) {
           List.add(ray.getPoint(alignZero(t1)));
           return List;
       } else if (t2 >= 0 && t1 < 0) {
           List.add(ray.getPoint(alignZero(t2)));
           return List;
       } else {
           List.add(ray.getPoint(alignZero(t1)));
           List.add(ray.getPoint(alignZero(t2)));
           return List;
       }
   }
}
