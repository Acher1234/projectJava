package elements;

import geometries.*;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static primitives.Util.alignZero;

public class AreaRayonLight extends RayonLight
{
    protected Sphere sphere;
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
    public AreaRayonLight(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ, double RayonLight,double RayonSphere) {
        super(_intensity, _Position, _direction, _kC, _kL, _kQ, RayonLight);
        sphere = new Sphere(RayonSphere,_Position);
    }

    public AreaRayonLight(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ,double RayonLight,double RayonSphere,boolean softShadow) {
        super(_intensity, _Position, _direction, _kC, _kL, _kQ, RayonLight,softShadow);
        sphere = new Sphere(RayonSphere,_Position);
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
        Random r = new Random();
        List<Vector> ToReturn = new ArrayList<Vector>();
        ToReturn.add(getL(p));
        for(int i = 0;i < nombrePointsGenerated;i++)
        {
            Vector Basis = Vector.GeneratedAleatoryVector(this.sphere.get_radius());
            ToReturn.add(p.subtract(_Position.Add(Basis)));
        }
        return ToReturn;

    }

    @Override
    public double getDistance(Point3D p) {
        return p.subtract(_Position).length();
    }
}
