package elements;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import primitives.Vector.*;
import geometries.*;

import java.util.ArrayList;
import java.util.List;

public class reelSpotLight extends PointLight
{
    static int nombrePointsGenerated = 6;
    protected Sphere sphere;

    /**
     * Instantiates a new SpotLight.
     *
     * @param _intensity
     * @param _Position
     * @param _direction
     * @param _kC
     * @param _kL
     * @param _kQ
     */
    public reelSpotLight(Color _intensity, Point3D _Position, Vector _direction, double _kC, double _kL, double _kQ,double rayon) {
        super(_intensity, _Position, _kC, _kL, _kQ);
        sphere = new Sphere(new Color(_intensity),new Material(0,0,0,1,0),rayon,_Position);
    }


    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }

    public List<Vector> getmultipleL(Point3D p)
    {
        List<Vector> ReturnList = new ArrayList<Vector>();
        Vector Basis = _Position.subtract(p).normalized();
        ReturnList.add(_Position.Add(Basis).subtract(p));
        for (int i =0;i < nombrePointsGenerated ; i++)
        {
            Basis = Vector.GeneratedAleatoryVector(this.sphere.get_radius());
            ReturnList.add(_Position.Add(Basis).subtract(p).normalized());
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
