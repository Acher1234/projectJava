package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

public interface LightSource {
    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);
    double getDistance(Point3D p);
    public List<Vector> getmultipleL(Point3D p);

}
