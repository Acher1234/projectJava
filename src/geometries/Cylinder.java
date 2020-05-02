package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
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
     *
     * @param _radius the radius
     * @param axisRay the axis ray
     * @param _height the height
     */
    public Cylinder(double _radius, Ray axisRay,double _height) {
        super(_radius, axisRay);
        this._height = _height;
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

    //************************METHODE****************
    @Override
    public Vector getNormal(Point3D temp)
    {
        return super.getNormal(temp);
    }
    @Override
    public List<Point3D> findIntersection(Ray ray)
    {
        List<Point3D> List =  super.findIntersection(ray);
        List<Point3D> setCandidate = new ArrayList<Point3D>();
        Point3D P2Point = _axisRay.getPOO().Add(_axisRay.getDirection().scale(_height));
        Plane P1 = new Plane(_axisRay.getPOO(),_axisRay.getDirection());
        Plane P2 = new Plane(P2Point,_axisRay.getDirection());
        Vector testNull;
        for (int i = 0;i < List.size();i++)// for the intersection without the plane
        {
            double t = _axisRay.getDirection().dotProduct(List.get(i).subtract(_axisRay.getPOO()));
            Point3D o = _axisRay.getPOO().Add(_axisRay.getDirection().scale(t));
            if(o.subtract(_axisRay.getPOO()).length() > _height)
            {
                List.remove(i);
            }
        }
        setCandidate = P1.findIntersection(ray);
        if(setCandidate != null)
        {
            try
            {
                testNull =   setCandidate.get(0).subtract(P2Point);
            }
            catch (Exception e)
            {
                List.add(_axisRay.getPOO());
                testNull = null;
            }
            if(testNull != null && testNull.length() < _radius)
            {
                List.add(setCandidate.get(0));
            }
        }
        setCandidate = P2.findIntersection(ray);
        if(setCandidate != null)
        {
            try
            {
              testNull =   setCandidate.get(0).subtract(P2Point);
            }
            catch (Exception e)
            {
                List.add(P2Point);
                testNull =null;
            }

            if(testNull != null && testNull.length() < _radius)
            {
                List.add(setCandidate.get(0));
            }
        }
        return List;
    }
}
