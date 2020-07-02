package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class CubeContenair extends Geometry implements Contenair
{
    public List<Geometry> geometries;
    public List<rectangle> rectangleList;
    public CubeBox contenair;


    //need to be P1 P2
    //           P3 P4
    //           P5 P6
    //           P7 P8
    public CubeContenair(Point3D... BasedCube)
    {

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
        if(!Checkifistouched(ray))
        {
            return null;
        }
        for (Geometry temp :geometries)
        {
            return null;
        }
        return null;
    }

    @Override
    public List<GeoPoint> findIntersection(Ray ray, double max) {
        return null;
    }

    @Override
    public boolean Checkifistouched(Ray ray)
    {
        return false;
    }
}
