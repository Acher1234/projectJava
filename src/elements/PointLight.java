package elements;

import geometries.Sphere;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;


public class PointLight extends Light implements LightSource
{
    protected Point3D _Position;
    protected double _kC,_kL,_kQ;
    protected double _radius;


    public PointLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ, double _radius) {
        super(_intensity);
        this._Position = _Position;
        this._radius = _radius;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }

    /**
     * Instantiates a new PointLight.
     * @param _intensity
     * @param _Position
     * @param _kC
     * @param _kL
     * @param _kQ
     */
    public PointLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ) {
        super(_intensity);
        this._Position = _Position;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }


    /**
     * gets intensity.
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        double distance = p.Distance(_Position);
        double toDivide = _kC +_kL*distance + _kQ*distance*distance;
        return _intensity.scale(1/toDivide);
    }

    /**
     * gets L.
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return _Position.subtract(p).normalized();
    }

    @Override
    public double getDistance(Point3D p) {
        return p.subtract(_Position).length();
    }

    @Override
    public double get_radius(){return _radius; }

   /* @Override
    public Sphere getSphere(Point3D p, double r)
    {
        Sphere SLight = new Sphere(r,p);
        return SLight;
    }
      @Override
    public Sphere getSphere()
    {
        Sphere SLight = new Sphere(_radius,_Position);
        return SLight;
    }

    */

   @Override
   public Point3D get_Position() { return _Position; }



}


