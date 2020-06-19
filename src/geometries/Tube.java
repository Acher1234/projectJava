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
    public Tube(Color emission,double _radius,Ray axisRay) {
        super(emission,_radius);
        _axisRay = axisRay;
    }
    public Tube(Color emission,Material material,double _radius,Ray axisRay) {
        super(emission,_radius);
        _axisRay = axisRay;
        this._material = material;
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

    /**
     * gets normal.
     * @param temp the temp
     * @return
     */
    @Override
    public Vector getNormal(Point3D temp)
    {
        double t = _axisRay.getDirection().dotProduct(temp.subtract(_axisRay.getPOO()));
        Point3D o = _axisRay.getPOO().Add(_axisRay.getDirection().scale(t));
        return temp.subtract(o).normalized();
    }

    /**
     * Gets axis ray.
     *
     * @return the axis ray
     */
    public Ray get_axisRay() {
        return _axisRay;
    }

    /**
     * returns Intersections.
     * @param ray the ray
     * @return
     */
    @Override
    public List<Intersectable.GeoPoint> findIntersection(Ray ray) {
        List<Intersectable.GeoPoint> resultPoint = new ArrayList<Intersectable.GeoPoint>();
        Vector vVAVA;
        Vector minus,deltaP;
        double VVA = ray.getDirection().dotProduct(_axisRay.getDirection());
        if(VVA == 0)
        {

        }
        vVAVA =_axisRay.getDirection().scale(VVA);
        try {
            minus = ray.getDirection().subtract(vVAVA);
        }
        catch (Exception e)
        {
            return null;
        }
        try
        {
            deltaP = ray.getPOO().subtract(_axisRay.getPOO());
        }
        catch (Exception e)
        {
            if(VVA == 0)
            {
                resultPoint.add(new Intersectable.GeoPoint(this,ray.getPoint(_radius)));
                return resultPoint;
            }
            resultPoint.add(new Intersectable.GeoPoint(this,ray.getPoint(_radius*_radius / minus.lengthSquared())));
            return resultPoint;
        }
        double a = minus.lengthSquared();
        double PVAaxys = deltaP.dotProduct(_axisRay.getDirection());
        Vector dpminus = null,dpVAVA;
        if(PVAaxys == 0)
        {
            dpminus = deltaP;
        }
        else {
            dpVAVA = _axisRay.getDirection().scale(PVAaxys);
            try
            {
                dpminus = deltaP.subtract(dpVAVA);
            }
            catch (Exception e)
            {
                resultPoint.add(new Intersectable.GeoPoint(this,ray.getPoint(Math.sqrt(_radius*_radius/a))));
            }
        }
        double b = 2 * minus.dotProduct( dpminus );
        double c = dpminus.lengthSquared()- _radius*_radius;
        List<Double> result = MathHelp.SecondDegree(a,b,c);
        resultPoint.add(new Intersectable.GeoPoint(this,ray.getPoint(result.get(0))));
        resultPoint.add(new Intersectable.GeoPoint(this,ray.getPoint(result.get(1))));
        return resultPoint;
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
}
