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

    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;
        //Constructor


        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }
        //Methodes
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            if (!super.equals(object)) return false;
            GeoPoint geoPoint = (GeoPoint) object;
            return java.util.Objects.equals(geometry, geoPoint.geometry) &&
                    java.util.Objects.equals(point, geoPoint.point);
        }
    }

}


