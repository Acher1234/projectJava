package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

    private Vector _direction;

    public DirectionalLight(Color _intensity,Vector  direction) {
        super(_intensity);
        _direction = direction;
    }

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
