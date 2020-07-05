package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Spot light.
 */
public class SpotLight extends PointLight
{
    /**
     * The Direction.
     */
    protected Vector _direction;

    /**
     * Instantiates a new Spot light.
     *
     * @param _intensity the intensity
     * @param _Position  the position
     * @param _direction the direction
     * @param _kC        the k c
     * @param _kL        the k l
     * @param _kQ        the k q
     */
    public SpotLight(Color _intensity, Point3D _Position,Vector _direction, double _kC, double _kL, double _kQ) {
        super(_intensity, _Position, _kC, _kL, _kQ);
        this._direction = _direction.normalized();
    }


    /**
     * Instantiates a new Spot light.
     *
     * @param _intensity the intensity
     * @param _Position  the position
     * @param _direction the direction
     * @param _kC        the k c
     * @param _kL        the k l
     * @param _kQ        the k q
     * @param softShadow the soft shadow
     */
    public SpotLight(Color _intensity, Point3D _Position,Vector _direction, double _kC, double _kL, double _kQ,boolean softShadow) {
        super(_intensity, _Position, _kC, _kL, _kQ,softShadow);
        this._direction = _direction.normalized();
    }

    /**
     *
     * @param p the p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction.normalized();
    }

    /**
     *
     * @param p the p
     * @return
     */
    @Override
    public List<Vector> getmultipleL(Point3D p) {
        List<Vector> ToReturn = new ArrayList<Vector>();
        ToReturn.add(this.getL(p));
        return ToReturn;

    }

    /**
     *
     * @param p the p
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

    /**
     *
     * @param p the p
     * @return
     */
    @Override
    public double getDistance(Point3D p) {
        return p.subtract(_Position).length();
    }
}
