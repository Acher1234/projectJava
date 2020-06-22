package elements;

import geometries.Sphere;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import primitives.*;

public class SpotLight extends PointLight
{
    private Vector _direction;
    //private double _radius;


    /**
     * Instantiates an area Light (SphereLight)
     * @param _intensity
     * @param _Position
     * @param _direction
     * @param _kC
     * @param _kL
     * @param _kQ
     * @param _radius
     */
    public SpotLight(Color _intensity, Point3D _Position,Vector _direction, double _kC, double _kL, double _kQ, double _radius) {
        super(_intensity, _Position, _kC, _kL, _kQ);
        this._direction = _direction.normalized();
        Sphere sphere = new Sphere(_radius,_Position);
    }

    /**
     * Instantiates a new SpotLight.
     * @param _intensity
     * @param _Position
     * @param _direction
     * @param _kC
     * @param _kL
     * @param _kQ
     */
    public SpotLight(Color _intensity, Point3D _Position,Vector _direction, double _kC, double _kL, double _kQ) {
        super(_intensity, _Position, _kC, _kL, _kQ);
        this._direction = _direction.normalized();
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

    /**
     * gets direction
     * @return
     */
    public Vector get_direction() {
        return _direction;
    }


    /**
     * gets intensity
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
