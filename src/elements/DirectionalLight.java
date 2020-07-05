package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Directional light.
 */
public class DirectionalLight extends Light implements LightSource  {

    private Vector _direction;

    @Override
    public double getDistance(Point3D p) {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Instantiates a new Directional light.
     *
     * @param _intensity the intensity
     * @param direction  the direction
     */
    public DirectionalLight(Color _intensity,Vector  direction) {
        super(_intensity);
        _direction = direction;
    }

    /**
     * Instantiates a new Directional light.
     *
     * @param _intensity the intensity
     * @param direction  the direction
     * @param softShadow the soft shadow
     */
    public DirectionalLight(Color _intensity,Vector  direction,boolean softShadow) {
        super(_intensity,softShadow);
        _direction = direction;
    }

    @Override
    public List<Vector> getmultipleL(Point3D p) {
        List<Vector> ToReturn = new ArrayList<Vector>();
        ToReturn.add(getL(p));
        return ToReturn;

    }

    /**
     * Gets l.
     *
     * @return the l
     */
    public Vector getL() {
        return _direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }
}
