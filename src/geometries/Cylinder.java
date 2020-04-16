package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * The type Cylinder.
 */
public class Cylinder extends Tube
{
    /**
     * The Height.
     */
//************************Variable*************
    double _height;

    /**
     * Instantiates a new Cylinder.
     *
     * @param _radius the radius
     * @param axisRay the axis ray
     * @param _height the height
     */
    public Cylinder(double _radius, Ray axisRay,double _height) {
        super(_radius, axisRay);
        this._height = _height;
    }

    /**
     * Instantiates a new Cylinder.
     *
     * @param temp    the temp
     * @param axisRay the axis ray
     * @param _heigh  the heigh
     */
    public Cylinder(RadialGeometry temp, Ray axisRay,double _heigh) {
        super(temp, axisRay);
        this._height = _height;
    }

    //************************METHODE****************
    @Override
    public Vector getNormal(Point3D temp)
    {
        Point3D point1 =_axisRay.getPOO();
        Point3D point2 = _axisRay.getPOO().Add(_axisRay.getDirection());
        double CoordX = new Vector(point2).getHead().getCoordX().get();//use for vectoriel Rotation on Z
        double CoordY = new Vector(point2).getHead().getCoordY().get();
        double CoordZ = new Vector(point2).getHead().getCoordZ().get();
        CoordX = CoordX*cos(90) - CoordY*sin(90);
        CoordY = CoordX*sin(90) + CoordY*cos(90);
        Point3D point3 =  _axisRay.getPOO().Add(new Vector(CoordX,CoordY,CoordZ));
        Vector BaseplanNormal = new Plane(point1,point2,point3).getNormal();//recup the nomal plane of the cercle
        // i get the basis vector to calcul the direct beetween the point and the base cercle
        BaseplanNormal = BaseplanNormal.normalized();
        return BaseplanNormal;
    }
}
