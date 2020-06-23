package elements;

import geometries.Sphere;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public interface LightSource {
    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);
    double getDistance(Point3D p);

    double get_radius();
    Point3D get_Position();
    //public Sphere getSphere();
    //Sphere getSphere(Point3D p, double r);
}
