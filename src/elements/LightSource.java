package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

/**
 * The interface Light source.
 */
public interface LightSource {
    /**
     * Gets intensity.
     *
     * @param p the p
     * @return the intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * Gets l.
     *
     * @param p the p
     * @return the l
     */
    public Vector getL(Point3D p);

    /**
     * Gets distance.
     *
     * @param p the p
     * @return the distance
     */
    double getDistance(Point3D p);

    /**
     * Gets l.
     *
     * @param p the p
     * @return the l
     */
    public List<Vector> getmultipleL(Point3D p);

}
