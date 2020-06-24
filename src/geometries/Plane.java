package geometries;

import primitives.*;
import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The type Plane.
 */
public class Plane extends Geometry implements FlatGeometry
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
     * Instantiates a new  Plane
     * @param emission
     * @param _point1
     * @param _point2
     * @param _point3
     */
    public Plane(Color emission,Point3D _point1,Point3D _point2,Point3D _point3) {
        this(_point1,_point2,_point3);
        this._emmission = emission;
    }

    /**
     * Instantiates a new Plane
     * @param emission
     * @param material
     * @param _point1
     * @param _point2
     * @param _point3
     */
    public Plane(Color emission,Material material,Point3D _point1,Point3D _point2,Point3D _point3) {
        this(emission,_point1,_point2,_point3);
        this._material = material;
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
     * Instantiates a new Plane
     * @param emission
     * @param _point1
     * @param Normal
     */
    public Plane(Color emission,Point3D _point1,Vector Normal)
    {
        this(_point1,Normal);
        this._emmission = emission;
    }

    /**
     * Instantiates a new Plane
     * @param emission
     * @param material
     * @param _point1
     * @param Normal
     */
    public Plane(Color emission,Material material,Point3D _point1,Vector Normal)
    {
        this(emission,_point1,Normal);
        this._material = material;
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

    /**
     * Instantiates a new Plane.
     * @param emission
     * @param other
     */
    public Plane(Color emission,Plane other){
        this(other);
        this._emmission = emission;
    }

    /**
     * Instantiates a new Plane.
     * @param emission
     * @param material
     * @param other
     */
    public Plane(Color emission,Material material,Plane other)
    {
        this(emission,other);
        this._material = material;
    }


    /**
     * gets normal
     * @param temp the temp
     * @return
     */
    @Override
    public Vector getNormal(Point3D temp)
    {
        return _normal.normalized();
    }

    /**
     * Gets normal.
     *
     * @return the normal
     */
    public Vector getNormal() {
        return _normal.normalized();
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

    /**
     * return list of intersections's points
     * @param ray the ray
     * @return
     */
    @Override
    public List<Intersectable.GeoPoint> findIntersection(Ray ray)
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
        if(_p.equals(ray.getPOO()))
        {
            List<Intersectable.GeoPoint> List = new ArrayList<Intersectable.GeoPoint>();
            List.add(new Intersectable.GeoPoint(this,_p));
            return List;
        }
        Vector planToPoint = _p.subtract(ray.getPOO());
        double numerator = _normal.dotProduct(planToPoint);
        double denominator = ray.getDirection().dotProduct(_normal);
        double t = alignZero(numerator/denominator);
        if(t<0)
        {
            return null;
        }
        List<Intersectable.GeoPoint> List = new ArrayList<Intersectable.GeoPoint>();
        List.add(new Intersectable.GeoPoint(this,ray.getPoint(t)));
        return List;
    }

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
            double t;
            try {
                t = tempPoint.point.subtract(ray.getPOO()).length();
            }catch (Exception E)
            {
                t = 0;
            }
            if(alignZero(t-max)<=0 && (t!=0))
            {
                tempReturn.add(tempPoint);
                flag = true;
            }
        }
        return flag ? tempReturn : null;
    }
}
