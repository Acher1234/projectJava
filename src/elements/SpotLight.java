package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight
{
    protected Vector _direction;

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
