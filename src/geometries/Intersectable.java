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
    public List<GeoPoint>findIntersection(Ray ray);

    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;
        //Constructor


        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }
        //Methodes
        public boolean equals(GeoPoint other) {
            if(other.geometry.equals(geometry) && other.point.equals(point))
            {
                return true;
            }
            return false;
        }
    }

}


