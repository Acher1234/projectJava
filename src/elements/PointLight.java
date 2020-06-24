package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class PointLight extends Light implements LightSource
{
    protected Point3D _Position;
    protected double _kC,_kL,_kQ;

    /**
     * Instantiates a new PointLight.
     * @param _intensity
     * @param _Position
     * @param _kC
     * @param _kL
     * @param _kQ
     */
    public PointLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ) {
        super(_intensity);
        this._Position = _Position;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }

    /**
     * gets intensity.
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        double distance = p.Distance(_Position);
        double toDivide = _kC +_kL*distance + _kQ*distance*distance;
        return _intensity.scale(1/toDivide);
    }

    /**
     * gets L.
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_Position).normalized();
    }

    @Override
    public double getDistance(Point3D p) {
        return p.subtract(_Position).length();
    }

    @Override
    public List<Vector> getmultipleL(Point3D p) {
        List<Vector> ToReturn = new ArrayList<Vector>();
        ToReturn.add(getL(p));
        return ToReturn;

    }
}
