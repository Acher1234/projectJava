package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class CubeBox extends Geometry
{
    public List<rectangle> rectangleList;
    //need to be P0 P1
    //           P3 P2
    //           P7 P6
    //           P4 P5
    public CubeBox(Color emission, Material material,Point3D... BasedCube)
    {
        this._emmission = emission;
        this._material = material;
        if (BasedCube.length != 8)
        {
            throw  new IllegalArgumentException("bad cube");
        }
        List<Point3D> points;
        points = List.of(BasedCube);
        this.rectangleList = new ArrayList<rectangle>();
        rectangleList.add(new rectangle(emission,material,points.get(0),points.get(1),points.get(2),points.get(3)));
        rectangleList.add(new rectangle(emission,material,points.get(4),points.get(5),points.get(6),points.get(7)));
        rectangleList.add(new rectangle(emission,material,points.get(0),points.get(1),points.get(6),points.get(7)));
        rectangleList.add(new rectangle(emission,material,points.get(4),points.get(5),points.get(2),points.get(3)));
    }

    @Override
    public Vector getNormal(Point3D temp) {
        for (int i = 0; i < rectangleList.size(); i++)
        {
            if(rectangleList.get(i).isInInside(temp))
            {
                return rectangleList.get(i).getNormal(temp);
            }
        }
        return null;
    }

    @Override
    public boolean isInInside(Point3D temp)
    {
        for (int i = 0; i < rectangleList.size(); i++)
        {
            if(rectangleList.get(i).isInInside(temp))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<GeoPoint> findIntersection(Ray ray)
    {
        List<GeoPoint> temp,ReturnResult = new ArrayList<GeoPoint>();
        for (int i = 0; i < rectangleList.size(); i++)
        {
            temp = rectangleList.get(i).findIntersection(ray);
            if(temp != null)
            {
                ReturnResult.add(new GeoPoint(this,temp.get(0).point));
            }
        }
        if(ReturnResult.size() == 0)
            return null;
        return ReturnResult;
    }

    @Override
    public List<GeoPoint> findIntersection(Ray ray, double max) {
        return null;
    }
}
