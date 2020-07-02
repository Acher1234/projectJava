package elements;

import geometries.Cylinder;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class tubeAreaLight extends  PointLight
{
    Cylinder cylinderHight;
    Cylinder cylinderDown;
    /**
     * Instantiates a new PointLight.
     *
     * @param _intensity
     * @param _Position
     * @param _kC
     * @param _kL
     * @param _kQ
     */
    public tubeAreaLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ, double rayon, double height, Vector DirectionOfTube) {
        super(_intensity, _Position, _kC, _kL, _kQ);
        Ray cylRay = new Ray(_Position,DirectionOfTube);
        cylinderHight = new Cylinder(rayon,cylRay,height/2);
        cylinderDown = new Cylinder(rayon,new Ray(cylRay.getDirection().scale(-1),cylRay.getPOO()),height/2);
    }

    public tubeAreaLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ, double rayon, double height, Vector DirectionOfTube,boolean softShadow) {
        super(_intensity, _Position, _kC, _kL, _kQ,softShadow);
        Ray cylRay = new Ray(_Position,DirectionOfTube);
        cylinderHight = new Cylinder(rayon,cylRay,height/2);
        cylinderDown = new Cylinder(rayon,new Ray(cylRay.getDirection().scale(-1),cylRay.getPOO()),height/2);
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
        for (int i = -(nombrePointsGenerated/2) ;i <nombrePointsGenerated/2 ; i++)
        {
            Basis = Vector.GeneratedAleatoryVector(this.cylinderDown.get_radius());
            Vector AvanceInTube = cylinderHight.get_axisRay().getDirection().scale((Math.random()+0.000001)*cylinderHight.get_height());
            Vector DownInTube = cylinderDown.get_axisRay().getDirection().scale((Math.random()+0.000001)*cylinderDown.get_height());
            ReturnList.add(p.subtract(_Position.Add(AvanceInTube).Add(DownInTube).Add(Basis)).normalized());
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
