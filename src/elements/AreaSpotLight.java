package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.*;
import geometries.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Area spot light.
 */
public class AreaSpotLight extends SpotLight
{

    /**
     * The Sphere.
     */
    protected Sphere sphere;

    /**
     * Instantiates a new Area spot light.
     *
     * @param _intensity the intensity
     * @param _Position  the position
     * @param _direction the direction
     * @param _kC        the k c
     * @param _kL        the k l
     * @param _kQ        the k q
     * @param radius     the radius
     * @param softShadow the soft shadow
     */
    public AreaSpotLight(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ,double radius,boolean softShadow) {
        super(_intensity, _Position, _direction, _kC, _kL, _kQ, softShadow);
        sphere = new Sphere(radius,_Position);
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction.normalized();
    }

    @Override
    public List<Vector> getmultipleL(Point3D p) {
        List<Vector> ToReturn = new ArrayList<Vector>();
        ToReturn.add(this.getL(p));
        for (int i = 0; i < nombrePointsGenerated; i++)
        {
            Vector Basis = Vector.GeneratedAleatoryVector(this.sphere.get_radius());
            ToReturn.add(p.subtract(_Position.Add(Basis)));
        }
        return ToReturn;
    }


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
