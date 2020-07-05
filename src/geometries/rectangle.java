package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The type Rectangle.
 */
public class rectangle extends Polygon implements FlatGeometry
{

    /**
     * Instantiates a new Rectangle.
     *
     * @param emission the emission
     * @param material the material
     * @param point1   the point 1
     * @param point2   the point 2
     * @param point3   the point 3
     * @param point4   the point 4
     */
    public rectangle(Color emission, Material material, Point3D point1, Point3D point2, Point3D point3,Point3D point4)
    {
        //point neeed to bo in hour clock sens
        super(point1,point2,point3,point4);
        if(point1.Distance(point2) != point3.Distance(point4) && point4.Distance(point1) != point2.Distance(point3))
            throw new IllegalArgumentException("bad argument for a rectangle");
        this._material = material;
        this._emmission = emission;
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
     *
     * @param temp the temp
     * @return
     */
    @Override
    public boolean isInInside(Point3D temp)
    {
        Triangle t1 = new Triangle(_vertices.get(0),_vertices.get(1),_vertices.get(2));
        if(t1.isInInside(temp))
        {
            return true;
        }
        t1 = new Triangle(_vertices.get(2),_vertices.get(3),_vertices.get(0));
        if(t1.isInInside(temp))
        {
            return true;
        }
        return false;
    }


    /**
     * returns intersections
     * @param ray
     * @return
     */
    @Override
    public List<GeoPoint> findIntersection(Ray ray)
    {
        List<GeoPoint> Possible = this._plane.findIntersection(ray);
        if(Possible == null)
        {
            return null;
        }
        GeoPoint PossibleGeopoint = new GeoPoint(this,Possible.get(0).point);
        List<GeoPoint> ReturnPossible = new ArrayList<GeoPoint>();
        ReturnPossible.add(PossibleGeopoint);
        Triangle t1 = new Triangle(_vertices.get(0),_vertices.get(1),_vertices.get(2));
        if(t1.isInInside(PossibleGeopoint.point))
        {
            return ReturnPossible;
        }
        t1 = new Triangle(_vertices.get(2),_vertices.get(3),_vertices.get(0));
        if(t1.isInInside(PossibleGeopoint.point))
        {
            return ReturnPossible;
        }
        return null;
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
     *
     * @return
     */
    @Override
    public double getMaxX() {
        return super.getMaxX();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxY() {
        return super.getMaxY();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMaxZ() {
        return super.getMaxZ();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinX() {
        return super.getMinX();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinY() {
        return super.getMinY();
    }

    /**
     *
     * @return
     */
    @Override
    public double getMinZ() {
        return super.getMinZ();
    }
}
