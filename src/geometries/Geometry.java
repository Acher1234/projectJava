package geometries;

import primitives.*;


/**
 * The interface Geometry.
 */
public interface Geometry
{
    /**
     * Gets normal.
     *
     * @param temp the temp
     * @return the normal
     */
    public abstract Vector getNormal(Point3D temp);
}
