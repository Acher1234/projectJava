package elements;

import geometries.*;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static primitives.Util.alignZero;

/**
 * The type Area rayon light.
 */
public class AreaRayonLight extends RayonLight
{
    /**
     * The Sphere.
     */
    protected Sphere sphere;

    /**
     * Instantiates a new Area rayon light.
     *
     * @param _intensity  the intensity
     * @param _Position   the position
     * @param _direction  the direction
     * @param _kC         the k c
     * @param _kL         the k l
     * @param _kQ         the k q
     * @param RayonLight  the rayon light
     * @param RayonSphere the rayon sphere
     */
    public AreaRayonLight(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ, double RayonLight,double RayonSphere) {
        super(_intensity, _Position, _direction, _kC, _kL, _kQ, RayonLight);
        sphere = new Sphere(RayonSphere,_Position);
    }

    /**
     * Instantiates a new Area rayon light.
     *
     * @param _intensity  the intensity
     * @param _Position   the position
     * @param _direction  the direction
     * @param _kC         the k c
     * @param _kL         the k l
     * @param _kQ         the k q
     * @param RayonLight  the rayon light
     * @param RayonSphere the rayon sphere
     * @param softShadow  the soft shadow
     */
    public AreaRayonLight(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ,double RayonLight,double RayonSphere,boolean softShadow) {
        super(_intensity, _Position, _direction, _kC, _kL, _kQ, RayonLight,softShadow);
        sphere = new Sphere(RayonSphere,_Position);
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction.normalized();
    }

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
