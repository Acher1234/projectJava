package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import geometries.*;

import java.util.List;

public class reelSpotLight extends PointLight
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
    public reelSpotLight(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ,double rayon) {
        super(_intensity, _Position, _kC, _kL, _kQ);
        sphere = new Sphere(rayon,_Position);
    }


    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }
    @Override
    public List<Vector> getmultipleL(Point3D p)
    {
        Vector Basis = _Position.subtract(p).normalized();
        Vector AleatoryPoint 
    }

    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity(p);
    }

    @Override
    public double getDistance(Point3D p) {
        return super.getDistance(p);
    }
}
