package unittests;

import geometries.*;
import org.junit.Test;
import primitives.*;

import static org.junit.Assert.*;

public class CylinderTest {

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
}