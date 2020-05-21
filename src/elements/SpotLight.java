package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight
{
    private Vector _direction;

    public SpotLight(Color _intensity, Point3D _Position,Vector _direction, double _kC, double _kL, double _kQ) {
        super(_intensity, _Position, _kC, _kL, _kQ);
        this._direction = _direction.normalized();
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction.normalized();
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
}
