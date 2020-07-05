package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class PyramideBasedRectangle extends Geometry
{
    List<Geometry> listofGeometry;
    double Xmax,Ymax,Zmax,Xmin,Ymin,Zmin;
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
        List<Point3D> points = List.of(p1,p2,p3,p4,p5);
        Xmax = points.get(0).getCoordX().get();
        Ymax = points.get(0).getCoordY().get();
        Zmax = points.get(0).getCoordZ().get();
        Xmin = points.get(0).getCoordX().get();
        Ymin = points.get(0).getCoordY().get();
        Zmin = points.get(0).getCoordZ().get();
        for (int i = 1; i < points.size(); i++)
        {
            Xmax = points.get(i).getCoordX().get() > Xmax ? points.get(i).getCoordX().get() : Xmax;
            Ymax = points.get(i).getCoordY().get() > Ymax ? points.get(i).getCoordY().get() : Ymax;
            Zmax = points.get(i).getCoordZ().get() > Zmax ? points.get(i).getCoordZ().get() : Zmax;
            Xmin = points.get(i).getCoordX().get() < Xmin ? points.get(i).getCoordX().get() : Xmin;
            Ymin = points.get(i).getCoordY().get() < Ymin ? points.get(i).getCoordY().get() : Ymin;
            Zmin = points.get(i).getCoordZ().get() < Zmin ? points.get(i).getCoordZ().get() : Zmin;
        }

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
        List<Point3D> points = List.of(p1,p2,p3,p4,p5);
        Xmax = points.get(0).getCoordX().get();
        Ymax = points.get(0).getCoordY().get();
        Zmax = points.get(0).getCoordZ().get();
        Xmin = points.get(0).getCoordX().get();
        Ymin = points.get(0).getCoordY().get();
        Zmin = points.get(0).getCoordZ().get();
        for (int i = 1; i < points.size(); i++)
        {
            Xmax = points.get(i).getCoordX().get() > Xmax ? points.get(i).getCoordX().get() : Xmax;
            Ymax = points.get(i).getCoordY().get() > Ymax ? points.get(i).getCoordY().get() : Ymax;
            Zmax = points.get(i).getCoordZ().get() > Zmax ? points.get(i).getCoordZ().get() : Zmax;
            Xmin = points.get(i).getCoordX().get() < Xmin ? points.get(i).getCoordX().get() : Xmin;
            Ymin = points.get(i).getCoordY().get() < Ymin ? points.get(i).getCoordY().get() : Ymin;
            Zmin = points.get(i).getCoordZ().get() < Zmin ? points.get(i).getCoordZ().get() : Zmin;
        }
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
    public double getMaxX() {
        return Xmax;
    }

    @Override
    public double getMaxY() {
        return Ymax;
    }

    @Override
    public double getMaxZ() {
        return Zmax;
    }

    @Override
    public double getMinX() {
        return Xmin;
    }

    @Override
    public double getMinY() {
        return Ymin;
    }

    @Override
    public double getMinZ() {
        return Zmin;
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
        List<GeoPoint> listpossible = this.findIntersection(ray);
        List<GeoPoint> listReturn = new ArrayList<GeoPoint>();
        if(listpossible == null)
        {
            return null;
        }
        for (GeoPoint p:listpossible)
        {
            if(ray.getPOO().Distance(p.point) <= max)
            {
                listReturn.add(p);
            }
        }
        if(listReturn.size() == 0)
        {
            return null;
        }
        return listReturn;
    }
}
