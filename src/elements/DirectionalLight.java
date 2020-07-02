package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class DirectionalLight extends Light implements LightSource  {

    private Vector _direction;

    @Override
    public double getDistance(Point3D p) {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Instantiates a new DirectionalLight.
     * @param _intensity
     * @param direction
     */
    public DirectionalLight(Color _intensity,Vector  direction) {
        super(_intensity);
        _direction = direction;
    }

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
     * gets L
     * @return
     */
    public Vector getL() {
        return _direction.normalized();
    }

    /**
     * gets intensity.
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    /**
     * gets L.
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }
}
