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

    /**
     * The Emmission.
     */
    protected Color _emmission;

    /**
     * The Material.
     */
    protected Material _material;
//----------constructtor--------------

    /**
     * Instantiates Geometry
     *
     * @param _emmission the emmission
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

    /**
     * Is in inside boolean.
     *
     * @param temp the temp
     * @return the boolean
     */
    public abstract boolean isInInside(Point3D temp);
//---------GET/SET

    /**
     * returns material
     *
     * @return material
     */
    public Material get_material() {
        return _material;
    }

    /**
     * returns emission
     *
     * @return emmission
     */
    public Color get_emmission() {
        return _emmission;
    }

    /**
     * Gets max x.
     *
     * @return the max x
     */
//Methode getCoordone
    abstract public double getMaxX();

    /**
     * Gets max y.
     *
     * @return the max y
     */
    abstract public double getMaxY();

    /**
     * Gets max z.
     *
     * @return the max z
     */
    abstract public double getMaxZ();

    /**
     * Gets min x.
     *
     * @return the min x
     */
    abstract public double getMinX();

    /**
     * Gets min y.
     *
     * @return the min y
     */
    abstract public double getMinY();

    /**
     * Gets min z.
     *
     * @return the min z
     */
    abstract public double getMinZ();

    /**
     * Can merge box boolean.
     *
     * @param temp the temp
     * @return the boolean
     */
    public boolean canMergeBox(Geometry temp)
    {
        if((getMaxX() >= temp.getMaxX() && getMinX() <= temp.getMaxX()) || (getMaxX() >= temp.getMinX() && getMinX() <= temp.getMinX()))
            if((getMaxY() >= temp.getMaxY() && getMinY() <= temp.getMaxY()) || (getMaxY() >= temp.getMinY() && getMinY() <= temp.getMinY()))
                if((getMaxZ() >= temp.getMaxZ() && getMinZ() <= temp.getMaxZ()) || (getMaxZ() >= temp.getMinZ() && getMinZ() <= temp.getMinZ()))
                    return true;
        return false;
    }
}
