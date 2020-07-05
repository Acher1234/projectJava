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

    /**
     * Find intersection list.
     *
     * @param ray the ray
     * @param max the max
     * @return the list
     */
    public List<GeoPoint>findIntersection(Ray ray,double max);

    /**
     * The type Geo point.
     */
    public static class GeoPoint {
        /**
         * The Geometry.
         */
        public Geometry geometry;
        /**
         * The Point.
         */
        public Point3D point;



        //Constructor

        /**
         * Instantiates a GeoPoint
         *
         * @param geometry the geometry
         * @param point    the point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }
        //Methodes

        /**
         * checks if they are equals
         *
         * @param other the other
         * @return boolean
         */
        public boolean equals(GeoPoint other) {
            if(other.geometry.equals(geometry) && other.point.equals(point))
            {
                return true;
            }
            return false;
        }
    }

}


