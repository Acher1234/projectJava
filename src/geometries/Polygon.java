package geometries;

import primitives.Point3D;
import primitives.SpecialException;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Polygon.
 */
public class Polygon implements Geometry
{
    /**
     * The Verticle.
     */
//**********************VARIABLE*******************
    protected List<Point3D> _verticle = new ArrayList<Point3D>();
    /**
     * The Plane.
     */
    protected Plane _plane;
    //********************METHODE*********************


    /**
     * Instantiates a new Polygon.
     *
     * @param _verticle the verticle
     * @throws SpecialException the special exception
     */
    public Polygon(Point3D... _verticle) throws SpecialException {
        if(_verticle.length < 3)
            throw new IllegalArgumentException();
        for (Point3D point : _verticle)
        {
            this._verticle.add(point);
        }
        this._plane = new Plane(_verticle[0],_verticle[1],_verticle[2]);
    }

    /**
     * Instantiates a new Polygon.
     */
    protected Polygon()
    {
    }

    @Override
    public Vector getNormal(Point3D temp)
    {
        return null;
    }
}
