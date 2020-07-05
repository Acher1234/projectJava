package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Point light.
 */
public class PointLight extends Light implements LightSource
{
    /**
     * The Position.
     */
    protected Point3D _Position;
    /**
     * The K c.
     */
    protected double _kC,
    /**
     * The K l.
     */
    _kL,
    /**
     * The K q.
     */
    _kQ;

    /**
     * Instantiates a new Point light.
     *
     * @param _intensity the intensity
     * @param _Position  the position
     * @param _kC        the k c
     * @param _kL        the k l
     * @param _kQ        the k q
     */
    public PointLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ) {
        super(_intensity);
        this._Position = _Position;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }

    /**
     * Instantiates a new Point light.
     *
     * @param _intensity the intensity
     * @param _Position  the position
     * @param _kC        the k c
     * @param _kL        the k l
     * @param _kQ        the k q
     * @param softShadow the soft shadow
     */
    public PointLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ,boolean softShadow) {
        super(_intensity,softShadow);
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
