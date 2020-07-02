package geometries;

import elements.Light;
import primitives.Ray;

import java.util.List;

public interface Contenair extends Intersectable
{
    public List<Geometry> interiorGeometry = null;
    public List<Geometry> ContenairGeometry = null;

    public boolean Checkifistouched(Ray ray);
    public void setGeometry(Geometry... geometries);
}
