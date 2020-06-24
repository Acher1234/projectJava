package elements;

import geometries.Cylinder;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;

public class tubeAreaLight extends  PointLight
{
    Cylinder cylinder;
    /**
     * Instantiates a new PointLight.
     *
     * @param _intensity
     * @param _Position
     * @param _kC
     * @param _kL
     * @param _kQ
     */
    public tubeAreaLight(Color _intensity, Point3D _Position, double _kC, double _kL, double _kQ, double rayon, double height, Ray cylRay) {
        super(_intensity, _Position, _kC, _kL, _kQ);
        cylinder = new Cylinder(rayon,cylRay,height);
    }
}
