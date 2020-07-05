package elements;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import primitives.Vector.*;
import geometries.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Area point light.
 */
public class AreaPointLight extends PointLight
{
    /**
     * The Sphere.
     */
    protected Sphere sphere;

    /**
     * Instantiates a new Area point light.
     *
     * @param _intensity the intensity
     * @param _Position  the position
     * @param _kC        the k c
     * @param _kL        the k l
     * @param _kQ        the k q
     * @param rayon      the rayon
     */
    public AreaPointLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ,double rayon) {
        super(_intensity, _Position, _kC, _kL, _kQ);
        sphere = new Sphere(new Color(_intensity),new Material(0,0,0,0,0),rayon,_Position);
    }


    /**
     * Instantiates a new Area point light.
     *
     * @param _intensity the intensity
     * @param _Position  the position
     * @param _kC        the k c
     * @param _kL        the k l
     * @param _kQ        the k q
     * @param rayon      the rayon
     * @param softShadow the soft shadow
     */
    public AreaPointLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ,double rayon,boolean softShadow) {
        super(_intensity, _Position, _kC, _kL, _kQ,softShadow);
        sphere = new Sphere(new Color(_intensity),new Material(0,0,0,0,0),rayon,_Position);
    }


    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }


    public List<Vector> getmultipleL(Point3D p)
    {
        List<Vector> ReturnList = new ArrayList<Vector>();
        Vector Basis = p.subtract(_Position).normalized();
        ReturnList.add(Basis);
        for (int i =0;i < nombrePointsGenerated ; i++)
        {
            Basis = Vector.GeneratedAleatoryVector(this.sphere.get_radius());
            ReturnList.add(p.subtract(_Position.Add(Basis)));
        }
        return ReturnList;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity(p);
    }

    @Override
    public double getDistance(Point3D p) {
        return super.getDistance(p);
    }
}
