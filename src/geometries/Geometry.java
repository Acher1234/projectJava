package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;


/**
 * The interface Geometry.
 */
public abstract class Geometry implements Intersectable
{

    protected Color _emmission;

    protected Material _material;
//----------constructtor--------------

    /**
     * Instantiates Geometry
     * @param _emmission
     */
    public Geometry(Color _emmission) {

        _material = new Material(0,0,0);
        this._emmission = _emmission;
    }

    /**
     * Instantiates Geometry
     */
    public Geometry() {
        _material = new Material(0,0,0);
        this._emmission = Color.BLACK;
    }

    /**
     * Gets normal.
     *
     * @param temp the temp
     * @return the normal
     */
    public abstract Vector getNormal(Point3D temp);

    public abstract boolean isInInside(Point3D temp);
//---------GET/SET

    /**
     * returns material
     * @return
     */
    public Material get_material() {
        return _material;
    }

    /**
     * returns emission
     * @return
     */
    public Color get_emmission() {
        return _emmission;
    }

    //Methode getCoordone
    abstract public double getMaxX();
    abstract public double getMaxY();
    abstract public double getMaxZ();
    abstract public double getMinX();
    abstract public double getMinY();
    abstract public double getMinZ();

    public boolean canMergeBox(Geometry temp)
    {
        if((getMaxX() >= temp.getMaxX() && getMinX() <= temp.getMaxX()) || (getMaxX() >= temp.getMinX() && getMinX() <= temp.getMinX()))
            if((getMaxY() >= temp.getMaxY() && getMinY() <= temp.getMaxY()) || (getMaxY() >= temp.getMinY() && getMinY() <= temp.getMinY()))
                if((getMaxZ() >= temp.getMaxZ() && getMinZ() <= temp.getMaxZ()) || (getMaxZ() >= temp.getMinZ() && getMinZ() <= temp.getMinZ()))
                    return true;
        return false;
    }
}
