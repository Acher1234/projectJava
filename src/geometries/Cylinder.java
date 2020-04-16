package geometries;

import org.hamcrest.core.Is;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

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
        Vector findCerclePoint = new Vector();
        Vector BaseplanNormal = new Plane(_axisRay.getPOO(),_axisRay.getPOO().Add(_axisRay.getDirection()),_axisRay.getPOO().Add(_axisRay.getDirection().scale(-1)))._normal;//recup the nomal plane of the cercle
       // i get the basis vector to calcul the direct beetween the point and the base cercle
        Vector hypotenuse  = temp.subtract(_axisRay.getPOO());// i get the hypotenuse with the ray to have the distance beetween the point and the base
        double heigth = hypotenuse.add(_axisRay.getDirection().normalize().scale(_radius)).length();
        BaseplanNormal = BaseplanNormal.scale(heigth);
        Point3D cerclePoint = temp.Add(BaseplanNormal);
        return cerclePoint.subtract(_axisRay.getPOO());
    }

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
