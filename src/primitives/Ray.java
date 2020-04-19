package primitives;

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
    public  Point3D getPoint(double t)
    {
        return  _POO.Add(_direction.scale(-t));
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
        _direction = new Vector(direct);
        this._POO = new Point3D(origin);
    }
    public Ray()  {
        _POO = new Point3D(0.0,0.0,0.0);
        _direction = new Vector(1.0,1.0,1.0);
    }
    public Ray(Ray ray){
        _POO = new Point3D(ray._POO);
        _direction = new Vector(ray._direction);
    }
    //*************SET/GET**************//
    public void setPOO(Point3D _POO)
    {
        this._POO = _POO;
    }
    public void setDirection(Vector _direction)
    {
        this._direction = _direction;
    }
    public Vector getDirection()
    {
        return _direction;
    }
    public Point3D getPOO()
    {
        return _POO;
    }
}
