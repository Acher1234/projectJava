package geometries;

import primitives.*;

import java.util.List;

public class Square extends rectangle
{
    public Square(Color emission, Material material, Point3D point1, Point3D point2, Point3D point3, Point3D point4) {
        super(emission, material, point1, point2, point3, point4);
        double v1 = point1.Distance(point2);
        double v2 = point2.Distance(point3);
        double v3 = point3.Distance(point4);
        double v4 = point4.Distance(point1);
        if(!(v1 == v2 && v1 == v3 && v1 == v4))
        {
          throw  new IllegalArgumentException("bad size for a square");
        }
    }

    @Override
    public Vector getNormal(Point3D temp) {
        return super.getNormal(temp);
    }

    @Override
    public List<GeoPoint> findIntersection(Ray ray) {
        return super.findIntersection(ray);
    }

    @Override
    public boolean isInInside(Point3D temp) {
        return super.isInInside(temp);
    }
}
