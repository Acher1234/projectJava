package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class CubeContenair extends Geometry implements Contenair
{
    public List<Geometry> geometries;
    public CubeBox contenair;


    //need to be P1 P2
    //           P3 P4
    //           P5 P6
    //           P7 P8
    public CubeContenair(Point3D... BasedCube)
    {
        contenair = new CubeBox(new Color(0,0,0),new Material(0,0,0,0,0),BasedCube);

    }

    @Override
    public void setGeometry(Geometry... geometries)
    {
        for (Geometry g:geometries)
        {
            this.geometries.add(g);
        }
    }

    @Override
    public Vector getNormal(Point3D temp) {
        return null;
    }

    @Override
    public boolean isInInside(Point3D temp)
    {
        return false;
    }

    @Override
    public List<GeoPoint> findIntersection(Ray ray)
    {
        List<GeoPoint> ReturnList = new ArrayList<GeoPoint>();
        if(!Checkifistouched(ray))
        {
            return null;
        }
        for (Geometry temp :geometries)
        {
            ReturnList.addAll(temp.findIntersection(ray));
        }
        if(ReturnList.size() == 0)
        {
            return null;
        }
        return ReturnList;
    }

    @Override
    public List<GeoPoint> findIntersection(Ray ray, double max) {
        return null;
    }

    @Override
    public boolean Checkifistouched(Ray ray)
    {
        Point3D temp= ray.getPOO();
        Vector VectorOfRay = ray.getDirection();
        if(temp.getCoordX().get() > this.contenair.maxX && VectorOfRay.getHead().getCoordX().get() >= 0)
        {
            return false;
        }
        if(temp.getCoordX().get() < this.contenair.minX && VectorOfRay.getHead().getCoordX().get() <= 0)
        {
            return false;
        }
        if(temp.getCoordY().get() > this.contenair.maxY && VectorOfRay.getHead().getCoordY().get() >= 0)
        {
            return false;
        }
        if(temp.getCoordY().get() < this.contenair.minY && VectorOfRay.getHead().getCoordY().get() <= 0)
        {
            return false;
        }
        if(temp.getCoordZ().get() > this.contenair.maxZ && VectorOfRay.getHead().getCoordZ().get() >= 0)
        {
            return false;
        }
        if(temp.getCoordZ().get() < this.contenair.minZ && VectorOfRay.getHead().getCoordZ().get() <= 0)
        {
            return false;
        }
        return true;
    }
}
