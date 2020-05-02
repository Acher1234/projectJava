package unittests;

import geometries.*;
import org.junit.Test;
import primitives.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * The type Cylinder test.
 */
public class CylinderTest {

    /**
     * Gets normal.
     */
    @Test
    public void getNormal()
    {
        Cylinder test = new Cylinder(2.0,new Ray(new Vector(1,1,0),new Point3D(0,0,0)),2.0);
        Vector Normaltest = test.getNormal(new Point3D(1.0,1.0,1.0));
        Vector Expected = new Point3D(0,0,1.0).subtract(new Point3D(0,0,0)).normalized();
        assertEquals(Expected.getHead().getCoordX(),Normaltest.getHead().getCoordX());
        assertEquals(Expected.getHead().getCoordY(),Normaltest.getHead().getCoordY());
        assertEquals(Expected.getHead().getCoordZ(),Normaltest.getHead().getCoordZ());

    }

    /**
     * Find intersection.
     */
    @Test
    public void findIntersection() {
        Cylinder test = new Cylinder(1,new Ray(new Vector(0,0,1),new Point3D(0,0,0)),1);
        List<Point3D> result = test.findIntersection(new Ray(new Vector(-2,0,1),new Point3D(2,0,0)));
        assertEquals(result.get(0),new Point3D(1,0,0.50));
        assertEquals(result.get(1),new Point3D(0,0,1));
    }
}