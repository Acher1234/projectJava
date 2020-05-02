package geometries;
import primitives.*;
import primitives.Vector;
import java.util.*;

/**
 * The interface Intersectable.
 */
public interface Intersectable {
    /**
     * Find intersection list.
     *
     * @param ray the ray
     * @return the list
     */
    public List<Point3D>findIntersection(Ray ray);

}
