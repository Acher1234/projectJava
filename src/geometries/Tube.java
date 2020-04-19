package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;
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
        Vector U =_axisRay.getPOO().subtract(ray.getPOO());
        if(U.crossProduct(ray.getDirection()) == null)
        {
            System.out.println(1);
          return null;  //calcul si les 2 sont colineaite
        }
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
