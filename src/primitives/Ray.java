package primitives;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Ray.
 */
public class Ray
{
    /**
     * The Direction.
     */
    protected Vector _direction;
    /**
     * the origin point
     */
    protected Point3D _POO;

//********************METHODES****************

    /**
     * Point presence boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public Boolean PointPresence(Point3D point)
    {
        double TX = point.coordX.get() / _direction.head.coordX.get();
        double TY = point.coordY.get() / _direction.head.coordY.get();
        double TZ = point.coordZ.get() / _direction.head.coordZ.get();
        return (TX  == TY  && TY == TZ);

    }

    /**
     * Gets point.
     *
     * @param t the t
     * @return the point
     */
    public  Point3D getPoint(double t)
    {
        try
        {
        return  _POO.Add(_direction.scale(t));
        }
        catch (IllegalArgumentException e)
        {
            return _POO;
        }
    }

    /**
     * Gets t.
     *
     * @param temp the temp
     * @return the t
     */
    public  double getT(Point3D temp)
    {
        return  temp.subtract(_POO).length();
    }

    @Override
    public boolean equals(Object obj) {
        Vector Test= ((Ray)obj)._direction;
        Point3D testPoint = ((Ray)obj)._POO;
        return (Test.equals(this._direction) && testPoint.equals(this._POO));
    }
    /**
     * Instantiates a new Ray.
     *
     * @param direct the vector direction
     * @param origin set the origin point
     */
//*******************CONSTRUCTOR*******************************
    public Ray(Vector direct,Point3D origin )
    {
        _direction = new Vector(direct).normalized();
        this._POO = new Point3D(origin);
    }

    @Override
    public String toString() {
        return "Point Origins:" + _POO.toString() + " Vector direction: " + _direction.toString();
    }

    public Ray(Point3D origin, Vector direct)
    {
        _direction = new Vector(direct).normalized();
        this._POO = new Point3D(origin);
    }
    /**
     * Instantiates a new Ray.
     */
    public Ray()  {
        _POO = new Point3D(0.0,0.0,0.0);
        _direction = new Vector(1.0,1.0,1.0).normalized();
    }

    /**
     * Instantiates a new Ray.
     *
     * @param ray the ray
     */
    public Ray(Ray ray){
        _POO = new Point3D(ray._POO);
        _direction = new Vector(ray._direction).normalized();
    }

    /**
     * Sets poo.
     *
     * @param _POO the poo
     */
//*************SET/GET**************//
    public void setPOO(Point3D _POO)
    {
        this._POO = _POO;
    }

    /**
     * Sets direction.
     *
     * @param _direction the direction
     */
    public void setDirection(Vector _direction)
    {
        this._direction = _direction;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Vector getDirection()
    {
        return _direction.normalized();
    }

    /**
     * Gets poo.
     *
     * @return the poo
     */
    public Point3D getPOO()
    {
        return _POO;
    }



    public List<Ray> getRays(Point3D coord, double radius)
    {
            List<Ray> Rays = new ArrayList<>();
            Rays.add(this);

            double coordX = coord.getCoordX().get();
            double coordY = coord.getCoordY().get();
            double coordZ = coord.getCoordZ().get();

             Random random = new Random();

             for (int i = 0; i < 26; i++)
             {
                 double x = random.nextInt((int)(coordX + radius -  (coordX - radius) + 1)) + (coordX - radius);
                 double y = random.nextInt((int)(coordY + radius -  (coordY - radius) + 1)) + (coordY - radius);
                 double z = random.nextInt((int)(coordZ + radius -  (coordZ - radius) + 1)) + (coordZ - radius);
                 Point3D point = new Point3D(x, y, z);
                 Vector temp = point.subtract(this._POO);
                 Ray ray = new Ray(this._POO, temp);
                 Rays.add(ray);
             }
         return Rays;
    }







}
