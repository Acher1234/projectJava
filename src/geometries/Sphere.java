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
     * @param emission the emission
     * @param material the material
     * @param _radius  the radius
     * @param center   the center
     */
    public Sphere(Color emission,Material material,double _radius,Point3D center)
    {
        super(emission,_radius);
        this._center = center;
        this._material = material;
    }

    /**
     * Instantiates a new Sphere.
     *
     * @param emission the emission
     * @param _radius  the radius
     * @param center   the center
     */
    public Sphere(Color emission,double _radius,Point3D center)
    {
        super(emission,_radius);
        this._center = center;
    }

    @Override
    public boolean isInInside(Point3D temp) {
        if(temp.Distance(_center) == _radius)
        {
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxX() {
        return this._center.Add(new Vector(1,0,0).scale(this._radius)).getCoordX().get();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxY() {
        return this._center.Add(new Vector(0,1,0).scale(this._radius)).getCoordY().get();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxZ() {
        return this._center.Add(new Vector(0,0,1).scale(this._radius)).getCoordZ().get();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinX() {
        return this._center.Add(new Vector(-1,0,0).scale(this._radius)).getCoordX().get();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinY() {
        return this._center.Add(new Vector(0,-1,0).scale(this._radius)).getCoordY().get();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinZ() {
        return this._center.Add(new Vector(0,0,-1).scale(this._radius)).getCoordZ().get();
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
     *
     * @param temp the temp
     * @return
     */
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
    public List<Intersectable.GeoPoint> findIntersection(Ray ray) {
       List<Intersectable.GeoPoint> List = new ArrayList<Intersectable.GeoPoint>();
       Vector U = null;
       U = _center.subtract(ray.getPOO());
       if(U.getHead() == null)
       {
           List.add(new Intersectable.GeoPoint(this,ray.getPoint(_radius)));
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
           List.add(new Intersectable.GeoPoint(this,ray.getPoint(alignZero(t1))));
           return List;
       } else if (t2 >= 0 && t1 < 0) {
           List.add(new Intersectable.GeoPoint(this,ray.getPoint(alignZero(t2))));
           return List;
       } else {
           List.add(new Intersectable.GeoPoint(this,ray.getPoint(alignZero(t1))));
           List.add(new Intersectable.GeoPoint(this,ray.getPoint(alignZero(t2))));
           return List;
       }
   }

    /**
     *
     * @param ray the ray
     * @param max the max
     * @return
     */
    @Override
    public List<GeoPoint> findIntersection(Ray ray, double max) {
        boolean flag = false;
        List<Intersectable.GeoPoint> tempList = this.findIntersection(ray);
        List<Intersectable.GeoPoint> tempReturn  = new ArrayList<Intersectable.GeoPoint>();
        if(tempList == null)
        {
            return null;
        }
        for (Intersectable.GeoPoint tempPoint:tempList)
        {
            double t = tempPoint.point.Distance(ray.getPOO());
            if(alignZero(t-max)<=0)
            {
                tempReturn.add(tempPoint);
                flag = true;
            }
        }
        return flag ? tempReturn : null;
    }
}
