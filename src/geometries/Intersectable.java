package geometries;
import primitives.*;
import primitives.Vector;
import java.util.*;

public interface Intersectable {
    public List<Point3D>findIntersection(Ray ray);

}
