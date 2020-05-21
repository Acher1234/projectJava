package geometries;

import primitives.*;


/**
 * The interface Geometry.
 */
public abstract class Geometry implements Intersectable
{
    protected Color _emmission;

    protected Material _material;
//----------constructtor--------------
    public Geometry(Color _emmission) {

        _material = new Material(0,0,0);
        this._emmission = _emmission;
    }

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
//---------GET/SET
    public Material get_material() {
        return _material;
    }

    public Color get_emmission() {
        return _emmission;
    }
}
