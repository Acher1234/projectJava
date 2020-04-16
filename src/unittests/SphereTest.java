package unittests;

import org.junit.Test;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

public class SphereTest {

    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point3D centerTest = new Point3D(1,0,1);
        Sphere Stest = new Sphere(1.0,new Point3D(0,0,0));
        Vector result = Stest.getNormal(centerTest);
        Vector test = new Vector(1,0,1).normalized();
        assertEquals(result.getHead().getCoordX(),test.getHead().getCoordX());
        assertEquals(result.getHead().getCoordY(),test.getHead().getCoordY());
        assertEquals(result.getHead().getCoordZ(),test.getHead().getCoordZ());
    }
}