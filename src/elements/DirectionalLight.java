package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

    private Vector _direction;

    @Override
    public double getDistance(Point3D p) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public double get_radius() {
        return 0;
    }

    @Override
    public Point3D get_Position() {
        return null;
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
