package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class PyramideBasedRectangle extends Geometry
{
    List<Geometry> listofGeometry;
    //need to be in this order p1 p2
    //                         P4 p3
    //make in first p3,p4:p1,p2:p1,p4:p2,p3
    public PyramideBasedRectangle(Color emission, Material material,Point3D p1, Point3D p2, Point3D p3, Point3D p4,Point3D p5)
    {
        Triangle t1 = new Triangle(emission,material,p3,p4,p5);
        Triangle t2 = new Triangle(emission,material,p1,p2,p5);
        Triangle t3 = new Triangle(emission,material,p4,p1,p5);
        Triangle t4 = new Triangle(emission,material,p2,p3,p5);
        rectangle based = new rectangle(emission,material,p1,p2,p3,p4);
        listofGeometry = new ArrayList<Geometry>();
        listofGeometry.add(t1);
        listofGeometry.add(t2);
        listofGeometry.add(t3);
        listofGeometry.add(t4);
        listofGeometry.add(based);

    }
    public PyramideBasedRectangle(Color emission1,Color emission2,Color emission3,Color emission4,Color emission5, Material material1,Material material2,Material material3,Material material4,Material material5,Point3D p1, Point3D p2, Point3D p3, Point3D p4,Point3D p5)
    {
        Triangle t1 = new Triangle(emission1,material1,p3,p4,p5);
        Triangle t2 = new Triangle(emission2,material2,p1,p2,p5);
        Triangle t3 = new Triangle(emission3,material3,p4,p1,p5);
        Triangle t4 = new Triangle(emission4,material4,p2,p3,p5);
        rectangle based = new rectangle(emission5,material5,p1,p2,p3,p4);
        listofGeometry = new ArrayList<Geometry>();
        listofGeometry.add(t1);
        listofGeometry.add(t2);
        listofGeometry.add(t3);
        listofGeometry.add(t4);
        listofGeometry.add(based);

    }

    @Override
    public Vector getNormal(Point3D temp)
    {
        for (Geometry i:listofGeometry)
        {
            if(i.isInInside(temp))
            {
                return i.getNormal(temp);
            }
        }
        return null;
    }

    @Override
    public boolean isInInside(Point3D temp)
    {
        for (Geometry i:listofGeometry)
        {
            if(i.isInInside(temp))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<GeoPoint> findIntersection(Ray ray)
    {
        List<GeoPoint> TotalReturnList = new ArrayList<GeoPoint>();
        for (Geometry i:listofGeometry)
        {
            List<GeoPoint> ReturnList = i.findIntersection(ray);
            if(ReturnList != null)
            {
                for (GeoPoint temp:ReturnList)
                {
                    TotalReturnList.add(temp);
                }
            }
        }
        return TotalReturnList.isEmpty() ? null:TotalReturnList;
    }

    @Override
    public List<GeoPoint> findIntersection(Ray ray, double max)
    {
        return null;
    }
}
