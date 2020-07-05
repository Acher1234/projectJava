package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

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
     *
     * @param _radius  the radius
     * @param axisRay  the axis ray
     * @param material the material
     */
    public Cylinder(double _radius, Ray axisRay, Material material) {
        super(_radius, axisRay);
        this._material=material;
    }

    /**
     * Instantiates a new Cylinder.
     *
     * @param emission the emission
     * @param material the material
     * @param _radius  the radius
     * @param axisRay  the axis ray
     */
    public Cylinder(Color emission,Material material,double _radius, Ray axisRay) {
        super(emission, _radius, axisRay);
        this._material=material;
    }

    /**
     * Instantiates a new Cylinder.
     *
     * @param temp     the temp
     * @param axisRay  the axis ray
     * @param material the material
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
     *
     * @param _radius  the radius
     * @param axisRay  the axis ray
     * @param _height  the height
     * @param emission the emission
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
     *
     * @param emission the emission
     * @param temp     the temp
     * @param axisRay  the axis ray
     * @param _heigh   the heigh
     */
    public Cylinder(Color emission,double temp, Ray axisRay,double _heigh) {
        this(temp,axisRay,_heigh);
        this._emmission = emission;
    }

    /**
     * Instantiates a new Cylinder.
     *
     * @param emission the emission
     * @param material the material
     * @param temp     the temp
     * @param axisRay  the axis ray
     * @param _heigh   the heigh
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
            if(alignZero(t) == 0)
            {
                List.add(ListTemp.get(i));
                continue;
            }
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

    /**
     * Gets height.
     *
     * @return the height
     */
    public double get_height() {
        return _height;
    }

    @Override
    public double getMaxX()
    {
            return this._axisRay.getDirection().getHead().getCoordX().get()*_height + _axisRay.getPOO().getCoordX().get() + _radius;
    }

    @Override
    public double getMaxY() {
            return this._axisRay.getDirection().getHead().getCoordY().get()*_height + _axisRay.getPOO().getCoordY().get()  + _radius;
    }

    @Override
    public double getMaxZ() {
            return this._axisRay.getDirection().getHead().getCoordZ().get()*_height + _axisRay.getPOO().getCoordZ().get() + _radius;
    }

    @Override
    public double getMinX() {
            return this._axisRay.getDirection().getHead().getCoordX().get()*-_height + _axisRay.getPOO().getCoordX().get()  - _radius;
    }

    @Override
    public double getMinY() {
            return this._axisRay.getDirection().getHead().getCoordY().get()*-_height + _axisRay.getPOO().getCoordY().get()  - _radius;
    }

    @Override
    public double getMinZ() {
            return this._axisRay.getDirection().getHead().getCoordZ().get()*-_height + _axisRay.getPOO().getCoordZ().get()   - _radius;
    }
}
