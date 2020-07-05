package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

public class RayonLight extends SpotLight
{
    protected double RayonLight;
    /**
     * Instantiates a new SpotLight.
     *
     * @param _intensity
     * @param _Position
     * @param _direction
     * @param _kC
     * @param _kL
     * @param _kQ
     */
    public RayonLight(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ,double RayonLight) {
        super(_intensity, _Position, _direction, _kC, _kL, _kQ);
        this.RayonLight = RayonLight;
    }


    public RayonLight(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ,double RayonLight,boolean softShadow) {
        super(_intensity, _Position, _direction, _kC, _kL, _kQ,softShadow);
        this.RayonLight = RayonLight;
    }


    @Override
    public Vector getL(Point3D p) {
        return _direction.normalized();
    }

    /**
     * gets intenity
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        double t = _direction.dotProduct(p.subtract(_Position));
        Point3D o;
        if(alignZero(t) == 0)
        {
            o = _Position;
        }
        else
        {
            o = _Position.Add(_direction.scale(t));
        }
        if(o.subtract(p).length() < this.RayonLight)
        {
            double distance = p.Distance(_Position);
            double toDivide = _kC +_kL*distance + _kQ*distance*distance;
            if(_direction.dotProduct(p.subtract(_Position).normalized()) > 0)
            {
                Color toReturn = _intensity.scale((_direction.dotProduct(p.subtract(_Position).normalized())));
                return toReturn.scale(1/toDivide);
            }
        }
        return new Color(0,0,0);
    }

    @Override
    public List<Vector> getmultipleL(Point3D p) {
        List<Vector> ToReturn = new ArrayList<Vector>();
        ToReturn.add(getL(p));
        return ToReturn;

    }

    @Override
    public double getDistance(Point3D p) {
        return p.subtract(_Position).length();
    }

}
