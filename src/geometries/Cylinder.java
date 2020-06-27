package geometries;

import primitives.*;
import primitives.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static primitives.Util.alignZero;

import java.util.*;

/**
 * The type Cylinder.
 */
public class Cylinder extends Tube
{
    /**
     * The Height.
     */
//************************Variable*************
    double _height;


    /**
     * Instantiates a new Cylinder.
     * @param _radius
     * @param axisRay
     * @param material
     */
    public Cylinder(double _radius, Ray axisRay, Material material) {
        super(_radius, axisRay);
        this._material=material;
    }

    /**
     * Instantiates a new Cylinder.
     * @param emission
     * @param material
     * @param _radius
     * @param axisRay
     */
    public Cylinder(Color emission,Material material,double _radius, Ray axisRay) {
        super(emission, _radius, axisRay);
        this._material=material;
    }

    /**
     * Instantiates a new Cylinder.
     * @param temp
     * @param axisRay
     * @param material
     */
    public Cylinder(RadialGeometry temp, Ray axisRay, Material material) {
        super(temp, axisRay);
        this._material=material;
    }


    /**
     * Instantiates a new Cylinder.
     *
     * @param _radius the radius
     * @param axisRay the axis ray
     * @param _height the height
     */
    public Cylinder(double _radius, Ray axisRay, double _height) {
        super(_radius, axisRay);
        this._height = _height;
    }

    /**
     * Instantiates a new Cylinder.
     * @param _radius
     * @param axisRay
     * @param _height
     * @param emission
     */
    public Cylinder(double _radius, Ray axisRay, double _height,Color emission) {
        this(_radius,axisRay,_height);
        this._emmission = emission;
    }

    /**
     * Instantiates a new Cylinder.
     *
     * @param temp    the temp
     * @param axisRay the axis ray
     * @param _heigh  the heigh
     */
    public Cylinder(RadialGeometry temp, Ray axisRay,double _heigh) {
        super(temp, axisRay);
        this._height = _height;
    }

    /**
     * Instantiates a new Cylinder.
     * @param emission
     * @param temp
     * @param axisRay
     * @param _heigh
     */
    public Cylinder(Color emission,double temp, Ray axisRay,double _heigh) {
        this(temp,axisRay,_heigh);
        this._emmission = emission;
    }

    /**
     * Instantiates a new Cylinder.
     * @param emission
     * @param material
     * @param temp
     * @param axisRay
     * @param _heigh
     */
    public Cylinder(Color emission,Material material,double temp, Ray axisRay,double _heigh) {
        this(temp,axisRay,_heigh);
        this._emmission = emission;
        this._material = material;
    }

    //************************METHODE****************

    /**
     * get vector normal from a point
     * @param temp
     * @return
     */
    @Override
    public Vector getNormal(Point3D temp)
    {
        return super.getNormal(temp);
    }

    /**
     * find all intersections and return them
     * @param ray
     * @return
     */
    @Override
    public List<Intersectable.GeoPoint> findIntersection(Ray ray)
    {
        List<Intersectable.GeoPoint> Listtemp =  super.findIntersection(ray);
        if(Listtemp == null)
        {
            return null;
        }
        List<Intersectable.GeoPoint> ListTemp = new ArrayList<GeoPoint>();
        List<Intersectable.GeoPoint> List = new ArrayList<GeoPoint>();
        for (GeoPoint temp:Listtemp)
        {
            ListTemp.add(new GeoPoint(this,temp.point));
        }
        List<Intersectable.GeoPoint> setCandidate = new ArrayList<Intersectable.GeoPoint>();
        Point3D P2Point = _axisRay.getPOO().Add(_axisRay.getDirection().scale(_height));
        Plane P1 = new Plane(_axisRay.getPOO(),_axisRay.getDirection());
        Plane P2 = new Plane(P2Point,_axisRay.getDirection());
        Vector testNull;
        for (int i = 0;i < ListTemp.size();i++)// for the intersection without the plane
        {
            double t = _axisRay.getDirection().dotProduct(ListTemp.get(i).point.subtract(_axisRay.getPOO()));
            Point3D o = _axisRay.getPOO().Add(_axisRay.getDirection().scale(t));
            if(!(o.subtract(_axisRay.getPOO()).length() > _height))
            {
                List.add(ListTemp.get(i));
            }
        }
        setCandidate = P1.findIntersection(ray);
        if(setCandidate != null)
        {
            try
            {
                testNull =   setCandidate.get(0).point.subtract(P2Point);
            }
            catch (Exception e)
            {
                List.add(new Intersectable.GeoPoint(this,_axisRay.getPOO()));
                testNull = null;
            }
            if(testNull != null && testNull.length() < _radius)
            {
                List.add(new Intersectable.GeoPoint(this,setCandidate.get(0).point));
            }
        }
        setCandidate = P2.findIntersection(ray);
        if(setCandidate != null)
        {
            try
            {
              testNull =   setCandidate.get(0).point.subtract(P2Point);
            }
            catch (Exception e)
            {
                List.add(new GeoPoint(this,P2Point));
                testNull =null;
            }

            if(testNull != null && testNull.length() < _radius)
            {
                List.add(setCandidate.get(0));
            }
        }
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
            double t = tempPoint.point.Distance(ray.getPOO());
            if(alignZero(t-max)<=0)
            {
                tempReturn.add(tempPoint);
                flag = true;
            }
        }
        return flag ? tempReturn : null;
    }

    public double get_height() {
        return _height;
    }
}
