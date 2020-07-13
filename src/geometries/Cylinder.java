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

//************************Variable*************
    /**
     * The Height.
     */
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

    /**
     * gets normal override
     * @param temp
     * @return
     */
    @Override
    public Vector getNormal(Point3D temp)
    {
        return super.getNormal(temp);
    }

    /**
     * finds intersections override
     * @param ray
     * @return
     */
    @Override
    public List<Point3D> findIntersection(Ray ray)
    {
        List<Point3D> List =  super.findIntersection(ray);
        List<Point3D> List2 = new ArrayList<Point3D>();
        boolean isPoint = false;
        for(Point3D point : List)
        {
            Vector v1 = point.subtract(_axisRay.getPOO());
            double size = v1.lengthSquared() - (_radius*_radius);
            if(size <= _height)
            {
                isPoint = true;
                List2.add(point);
            }
        }
        if(isPoint)
        {
            return List2;
        }
        return null;
    }
}
