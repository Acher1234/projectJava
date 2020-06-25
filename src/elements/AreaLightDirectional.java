package elements;

import primitives.Color;
import geometries.*;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class AreaLightDirectional extends SpotLight {
    protected Sphere sphere;

    /**
     * Instantiates a new SpotLight.
     * @param _intensity
     * @param _Position
     * @param _direction
     * @param _kC
     * @param _kL
     * @param _kQ
     */
    public AreaLightDirectional(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ,double Rayon) {
        super(_intensity, _Position, _direction,_kC, _kL, _kQ);
        sphere = new Sphere(new Color(_intensity),new Material(0,0,0,0,0),Rayon,_Position);
    }

    /**
     * gets L
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction.normalized();
    }

    public List<Vector> getmultipleL(Point3D p)
    {
        List<Vector> ReturnList = new ArrayList<Vector>();
        Vector Basis = _Position.subtract(p).normalized();
        ReturnList.add(_Position.Add(Basis).subtract(p));
        for (int i =0;i < nombrePointsGenerated ; i++)
        {
            Basis = Vector.GeneratedAleatoryVector(this.sphere.get_radius());
            ReturnList.add(p.subtract(_Position.Add(Basis)).normalized());
        }
        return ReturnList;
    }

    /**
     * gets intenity
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        double distance = p.Distance(_Position);
        double toDivide = _kC +_kL*distance + _kQ*distance*distance;
        if(_direction.dotProduct(p.subtract(_Position).normalized()) > 0)
        {
            Color toReturn = _intensity.scale((_direction.dotProduct(p.subtract(_Position).normalized())));
            return toReturn.scale(1/toDivide);
        }
        return new Color(0,0,0);
    }

    @Override
    public double getDistance(Point3D p) {
        return p.subtract(_Position).length();
    }
}
