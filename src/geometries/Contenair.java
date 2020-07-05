package geometries;

import elements.Light;
import primitives.Ray;

import java.util.List;

/**
 * The interface Contenair.
 */
public interface Contenair extends Intersectable
{
    /**
     * The constant interiorGeometry.
     */
    public List<Geometry> interiorGeometry = null;
    /**
     * The constant ContenairGeometry.
     */
    public List<Geometry> ContenairGeometry = null;

    /**
     * Checkifistouched boolean.
     *
     * @param ray the ray
     * @return the boolean
     */
    public boolean Checkifistouched(Ray ray);

    /**
     * Sets geometry.
     *
     * @param geometries the geometries
     */
    public void setGeometry(Geometry... geometries);
}
