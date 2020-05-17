package geometries;

import primitives.*;


/**
 * The interface Geometry.
 */
public abstract class Geometry implements Intersectable
{
    protected Color _emmission;

    public Color get_emmission() {
        return _emmission;
    }

    public Geometry(Color _emmission) {
        this._emmission = _emmission;
    }

    public Geometry() {
        this._emmission = Color.BLACK;
    }

    /**
     * Gets normal.
     *
     * @param temp the temp
     * @return the normal
     */
    public abstract Vector getNormal(Point3D temp);
}
