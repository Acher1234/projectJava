package geometries;

import primitives.*;

import java.util.List;


/**
 * The interface Geometry.
 */
public interface Geometry extends Intersectable
{
    /**
     * Gets normal.
     *
     * @param temp the temp
     * @return the normal
     */
    public abstract Vector getNormal(Point3D temp);

}
