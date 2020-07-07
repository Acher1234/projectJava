package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Cube contenair.
 */
public class CubeContenair extends Geometry implements Contenair
{
    /**
     * The Geometries.
     */
    public List<Geometry> geometries;
    /**
     * The Contenair coordinate.
     */
    double maxX,maxY,maxZ,minX,minY,minZ;


    /**
     * Instantiates a new Cube contenair.
     *
     * @param BasedCube the based cube
     *     //need to be P1 P2
     *     //           P3 P4
     *     //           P5 P6
     *     //           P7 P8
     */

    public CubeContenair(Point3D... BasedCube)
    {
        this.geometries = new ArrayList<Geometry>();
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
    }

    /**
     * Instantiates a new Cube contenair.
     *
     * @param BasedCube the based cube need to bee in max x,y,z and after min x,y,z
     */

    public CubeContenair(double... BasedCube)
    {
        maxX = BasedCube[0];
        maxY = BasedCube[1];
        maxZ = BasedCube[2];
        minX = BasedCube[3];
        minY = BasedCube[4];
        minZ = BasedCube[5];
        this.geometries = new ArrayList<Geometry>();
    }

    /**
     *
     * @param geometries the geometries
     */
    @Override
    public void setGeometry(Geometry... geometries)
    {
        for (Geometry g:geometries)
        {
            this.geometries.add(g);
        }
    }

    /**
     *
     */
    @Override
    public int getSizeGeometry() {
        return geometries.size();
    }

    /**
     *
     * @param temp the temp
     * @return
     */
    @Override
    public Vector getNormal(Point3D temp) {
        return null;
    }

    /**
     *
     * @param temp the temp
     * @return
     */
    @Override
    public boolean isInInside(Point3D temp)
    {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxX() {
        return this.maxX;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxY() {
        return this.maxY;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxZ() {
        return this.maxZ;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinX() {
        return this.minX;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinY() {
        return this.minY;
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinZ() {
        return this.minZ;
    }

    /**
     *
     * @param ray the ray
     * @return
     */
    @Override
    public List<GeoPoint> findIntersection(Ray ray)
    {
        if(!Checkifistouched(ray))
        {
            return null;
        }
        List<GeoPoint> ReturnList = new ArrayList<GeoPoint>();
        for (Geometry temp :geometries)
        {
            if(temp.findIntersection(ray) != null)
                ReturnList.addAll(temp.findIntersection(ray));
        }
        if(ReturnList.size() == 0)
        {
            return null;
        }
        return ReturnList;
    }

    /**
     *
     * @param ray the ray
     * @param max the max
     * @return
     */
    @Override
    public List<GeoPoint> findIntersection(Ray ray, double max)
    {
        if(!Checkifistouched(ray))
        {
            return null;
        }
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

    /**
     *check if a ray touch the cube
     * @param ray the ray
     * @return
     */
    @Override
    public boolean Checkifistouched(Ray ray)
    {
        Point3D temp= ray.getPOO();
        Vector VectorOfRay = ray.getDirection();
        if((temp.getCoordX().get() > this.maxX && VectorOfRay.getHead().getCoordX().get() >= 0) || (temp.getCoordX().get() < this.minX && VectorOfRay.getHead().getCoordX().get() <= 0))
        {
            return false;
        }
        if((temp.getCoordY().get() > this.maxY && VectorOfRay.getHead().getCoordY().get() >= 0) || (temp.getCoordY().get() < this.minY && VectorOfRay.getHead().getCoordY().get() <= 0))
        {
            return false;
        }
        if((temp.getCoordZ().get() > this.maxZ && VectorOfRay.getHead().getCoordZ().get() >= 0)||(temp.getCoordZ().get() < this.minZ && VectorOfRay.getHead().getCoordZ().get() <= 0))
        {
            return false;
        }
        return true;
    }
}
