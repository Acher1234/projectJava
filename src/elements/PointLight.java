package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource
{
    protected Point3D _Position;
    protected double _kC,_kL,_kQ;

    public PointLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ) {
        super(_intensity);
        this._Position = _Position;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double distance = p.Distance(_Position);
        double toDivide = _kC +_kL*distance + _kQ*distance*distance;
        return _intensity.scale(1/toDivide);
    }

    @Override
    public Vector getL(Point3D p) {
        return _Position.subtract(p).normalized();
    }
}
