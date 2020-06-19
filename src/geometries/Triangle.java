package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Triangle.
 */
public class Triangle extends Polygon
{
    //**********************VARIABLE*******************

    //********************METHODE*********************


    /**
     * Instantiates a new Triangle.
     *
     * @param point1 the point 1
     * @param point2 the point 2
     * @param point3 the point 3
     */
    public Triangle(Point3D point1,Point3D point2,Point3D point3){
        super(point1,point2,point3);
    }
    public Triangle(Color emission,Point3D point1,Point3D point2,Point3D point3){
        super(emission,point1,point2,point3);
        this._emmission = emission;
    }
    public Triangle(Color emission,Material material,Point3D point1,Point3D point2,Point3D point3){
        super(emission,material,point1,point2,point3);
    }

    /**
     * gets normal
     * @param temp
     * @return
     */
    @Override
    public Vector getNormal(Point3D temp)
    {
        return super.getNormal(temp).normalized();
    }

    /**
     * returns intersections
     * @param ray
     * @return
     */
    @Override
    public List<Intersectable.GeoPoint> findIntersection(Ray ray)
    {
        List<Intersectable.GeoPoint> Listtest = _plane.findIntersection(ray);
        List<Intersectable.GeoPoint> List = new ArrayList<GeoPoint>();
        for (GeoPoint temp:Listtest)
        {
            List.add(new GeoPoint(this,temp.point));
        }
        if(List == null)
        {
            return null;
        }
        Vector v1 = ray.getPOO().subtract(_vertices.get(0));
        Vector v2 = ray.getPOO().subtract(_vertices.get(1));
        Vector v3 = ray.getPOO().subtract(_vertices.get(2));
        Vector n1 = (_vertices.get(1).subtract(_vertices.get(0))).crossProduct(v1).normalize();
        Vector n2 = (_vertices.get(2).subtract(_vertices.get(1))).crossProduct(v2).normalize();
        Vector n3 = (_vertices.get(0).subtract(_vertices.get(2))).crossProduct(v3).normalize();
        double number1 = ray.getDirection().dotProduct(n1);
        double number2 = ray.getDirection().dotProduct(n2);
        double number3 = ray.getDirection().dotProduct(n3);
        if(number1 >= 0 && number2 >= 0 && number3 >= 0)
        {
            return List;
        }
        if(number1 <= 0 && number2 <= 0 && number3 <= 0) {
            return List;
        }
        return null;
    }
}
