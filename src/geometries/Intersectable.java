package geometries;
import primitives.*;
import primitives.Vector;
import java.util.*;

/**
 * intersectable interface
 */
public interface Intersectable {
    /**
     * list of intersections (points 3D) with ray
     * @param ray
     * @return
     */
    public List<Point3D>findIntersection(Ray ray);

}
