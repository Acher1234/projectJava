package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class CubeBox extends Geometry
{
    protected List<rectangle> rectangleList;
    protected double maxX,maxY,maxZ,minX,minY,minZ;
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
        maxX = points.get(0).getCoordX().get();
        maxY = points.get(0).getCoordY().get();
        maxZ = points.get(0).getCoordZ().get();
        minX = points.get(0).getCoordX().get();
        minY = points.get(0).getCoordY().get();
        minZ = points.get(0).getCoordZ().get();
        for (int i = 1; i < points.size(); i++)
        {
            maxX = points.get(i).getCoordX().get() > maxX ? points.get(i).getCoordX().get() : maxX;
            maxY = points.get(i).getCoordY().get() > maxY ? points.get(i).getCoordY().get() : maxY;
            maxZ = points.get(i).getCoordZ().get() > maxZ ? points.get(i).getCoordZ().get() : maxZ;
            minX = points.get(i).getCoordX().get() < minX ? points.get(i).getCoordX().get() : minX;
            minY = points.get(i).getCoordY().get() < minY ? points.get(i).getCoordY().get() : minY;
            minZ = points.get(i).getCoordZ().get() < minZ ? points.get(i).getCoordZ().get() : minZ;
        }
        this.rectangleList = new ArrayList<rectangle>();
        rectangleList.add(new rectangle(emission,material,points.get(0),points.get(1),points.get(2),points.get(3)));
        rectangleList.add(new rectangle(emission,material,points.get(4),points.get(5),points.get(6),points.get(7)));
        rectangleList.add(new rectangle(emission,material,points.get(0),points.get(1),points.get(6),points.get(7)));
        rectangleList.add(new rectangle(emission,material,points.get(4),points.get(5),points.get(2),points.get(3)));
    }

    //need to bee in max x,y,z and after min x,y,z
    public CubeBox(Color emission, Material material,double... MaxMin)
    {
        this._emmission = emission;
        this._material = material;
        if (MaxMin.length != 6)
        {
            throw  new IllegalArgumentException("bad cube");
        }
        double[] doublelist = MaxMin;
        maxX = doublelist[0];
        maxY = doublelist[1];
        maxZ = doublelist[2];
        minX = doublelist[3];
        minY = doublelist[4];
        minZ = doublelist[5];
        List<Point3D> points = new ArrayList<Point3D>();
        points.add(new Point3D(maxX,maxY,maxZ));
        points.add(new Point3D(minX,maxY,maxZ));
        points.add(new Point3D(minX,maxY,minZ));
        points.add(new Point3D(maxX,maxY,minZ));
        points.add(new Point3D(maxX,minY,minZ));
        points.add(new Point3D(minX,minY,minZ));
        points.add(new Point3D(minX,minY,maxZ));
        points.add(new Point3D(maxX,minY,maxZ));
        this.rectangleList = new ArrayList<rectangle>();
        rectangleList.add(new rectangle(emission,material,points.get(0),points.get(1),points.get(2),points.get(3)));
        rectangleList.add(new rectangle(emission,material,points.get(4),points.get(5),points.get(6),points.get(7)));
        rectangleList.add(new rectangle(emission,material,points.get(0),points.get(1),points.get(6),points.get(7)));
        rectangleList.add(new rectangle(emission,material,points.get(4),points.get(5),points.get(2),points.get(3)));
    }

    public CubeBox(Point3D... BasedCube)
    {
        this(new Color(255,255,255),new Material(0,0,0,0,0),BasedCube);
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
    public double getMaxX() {
        return maxX;
    }

    @Override
    public double getMaxY() {
        return maxY;
    }

    @Override
    public double getMaxZ() {
        return maxZ;
    }

    @Override
    public double getMinX() {
        return minX;
    }

    @Override
    public double getMinY() {
        return minY;
    }

    @Override
    public double getMinZ() {
        return minZ;
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
