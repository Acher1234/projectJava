package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The type Triangle.
 */
public class Triangle extends Polygon implements FlatGeometry
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

    /**
     * Instantiates a new Triangle.
     *
     * @param emission the emission
     * @param point1   the point 1
     * @param point2   the point 2
     * @param point3   the point 3
     */
    public Triangle(Color emission,Point3D point1,Point3D point2,Point3D point3){
        super(emission,point1,point2,point3);
        this._emmission = emission;
    }

    /**
     * Instantiates a new Triangle.
     *
     * @param emission the emission
     * @param material the material
     * @param point1   the point 1
     * @param point2   the point 2
     * @param point3   the point 3
     */
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
            List<Intersectable.GeoPoint> Listreturn = new ArrayList<Intersectable.GeoPoint>();
            if(Listtest == null)
            {
                return null;
            }
            for (Intersectable.GeoPoint temp:Listtest)
            {
                Listreturn.add(new Intersectable.GeoPoint(this,temp.point));
            }
            Intersectable.GeoPoint PointPossible = Listtest.get(0);
            if(PointPossible.point.equals(_vertices.get(0)) || PointPossible.point.equals(_vertices.get(1))  || PointPossible.point.equals(_vertices.get(2)) )
            {
                return Listreturn;
            }
            double number1;
            double number2;
            double number3;
            Vector v1 = _vertices.get(1).subtract(_vertices.get(0)).normalized();
            Vector v2 = _vertices.get(2).subtract(_vertices.get(1)).normalized();
            Vector v3 = _vertices.get(0).subtract(_vertices.get(2)).normalized();
            Vector n1 = v1.crossProduct(PointPossible.point.subtract(_vertices.get(0)).normalized());
            Vector n2 = v2.crossProduct(PointPossible.point.subtract(_vertices.get(1)).normalized());
            Vector n3 = v3.crossProduct(PointPossible.point.subtract(_vertices.get(2)).normalized());
            if(PointPossible.point.equals(ray.getPOO()))
            {
                return Listtest;
            }
            if(n1 == null)
            {
                number1 = 0;
            }
            else {
                number1 = this._plane._normal.dotProduct(n1.normalized());
            }
            if(n2 == null)
            {
                number2 = 0;
            }
            else
            {
                number2 = this._plane._normal.dotProduct(n2.normalize());
            }
            if(n3 == null)
            {
                number3 = 0;
            }
            else
            {
                number3 = this._plane._normal.dotProduct(n3.normalize());
            }

            if(number1>= 0 && number2 >= 0 && number3 >= 0)
            {
                return Listreturn;
            }
            return null;
    }

    /**
     *
     * @param temp the temp
     * @return
     */
    @Override
    public boolean isInInside(Point3D temp) {
        if(!_plane.isInInside(temp))
        {
            return false;
        }
        double number1;
        double number2;
        double number3;
        Vector v1 = _vertices.get(1).subtract(_vertices.get(0)).normalized();
        Vector v2 = _vertices.get(2).subtract(_vertices.get(1)).normalized();
        Vector v3 = _vertices.get(0).subtract(_vertices.get(2)).normalized();
        Vector n1 = v1.crossProduct(temp.subtract(_vertices.get(0)).normalized());
        Vector n2 = v2.crossProduct(temp.subtract(_vertices.get(1)).normalized());
        Vector n3 = v3.crossProduct(temp.subtract(_vertices.get(2)).normalized());
        if(n1 == null)
        {
            number1 = 0;
        }
        else {
            number1 = this._plane._normal.dotProduct(n1.normalized());
        }
        if(n2 == null)
        {
            number2 = 0;
        }
        else
        {
            number2 = this._plane._normal.dotProduct(n2.normalize());
        }
        if(n3 == null)
        {
            number3 = 0;
        }
        else
        {
            number3 = this._plane._normal.dotProduct(n3.normalize());
        }

        if(number1>= 0 && number2 >= 0 && number3 >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     *
     * @param ray the ray
     * @param max the max
     * @return
     */
    @Override
    public List<GeoPoint> findIntersection(Ray ray,double max) {
        boolean flag = false;
        List<Intersectable.GeoPoint> tempList = findIntersection(ray);
        List<Intersectable.GeoPoint> tempReturn  = new ArrayList<Intersectable.GeoPoint>();
        if(tempList == null)
        {
            return null;
        }
        for (Intersectable.GeoPoint tempPoint:tempList)
        {
            double t;
            Vector temp =tempPoint.point.subtract(ray.getPOO());
            if(temp.getHead() == null)
            {
                t = 0;
            }
            else
            {
                t = 0;
            }
            if(alignZero(t-max)<=0 && (t!=0))
            {
                tempReturn.add(new Intersectable.GeoPoint(this,tempPoint.point));
                flag = true;
            }
        }
        return flag ? tempReturn : null;
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
