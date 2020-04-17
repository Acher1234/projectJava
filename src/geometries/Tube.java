package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.sqrt;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * The type Tube.
 */
public class Tube extends RadialGeometry {
    /**
     * The Axis ray.
     */
    Ray _axisRay;

    /**
     * Instantiates a new Tube.
     *
     * @param _radius the radius
     * @param axisRay the axis ray
     */
    public Tube(double _radius,Ray axisRay) {
        super(_radius);
        _axisRay = axisRay;
    }

    /**
     * Instantiates a new Tube.
     *
     * @param temp    the temp
     * @param axisRay the axis ray
     */
    public Tube(RadialGeometry temp,Ray axisRay) {
        super(temp);
        _axisRay = axisRay;
    }

    //*************METHODE***************
    @Override
    public Vector getNormal(Point3D temp)
    {
        return temp.subtract(_axisRay.getPOO()).normalized();
    }

    /**
     * Gets axis ray.
     *
     * @return the axis ray
     */
    public Ray get_axisRay() {
        return _axisRay;
    }
    @Override
    public
    List<Point3D> findIntersection(Ray ray)
    {
        Vector vecteur = ray.getDirection();
        Vector centerToPoint = _axisRay.getPOO().subtract(ray.getPOO());
        double tm = alignZero(centerToPoint.dotProduct(ray.getDirection()));// dot product beetween the U and the ray vecteur
        if(isZero(tm) || tm < 0)
        {
            if(_radius == centerToPoint.length())
            {
                List<Point3D> List = new ArrayList<Point3D>();
                List.add(ray.getPOO());
            }
            return null;// if == 0 or <0 so it will be so i can't touch the struture
        }
        double d = sqrt(centerToPoint.lengthSquared() - (tm*tm));
        double th = sqrt((_radius*_radius)-(d*d));
        List<Point3D> List = new ArrayList<Point3D>();
        if(centerToPoint.length() < _radius)//there is only one point
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
        List.add(ray.getPoint(alignZero(tm-th)));
        List.add(ray.getPoint(alignZero(tm-th)));
        return List;
    }
}
