package unittests;

import geometries.*;
import org.junit.Test;
import primitives.*;

import static org.junit.Assert.*;

public class CylinderTest {

    /**
     * Test for Get Normal.
     *
     */
    @Test
    public void getNormal()
    {
        Cylinder test = new Cylinder(2.0,new Ray(new Vector(1,1,0),new Point3D(0,0,0)),2.0);
        Vector Normaltest = test.getNormal(new Point3D(1.0,0.0,0.0));
        Vector Expected = new Vector(0,0,-1);
        assertEquals(Expected.getHead().getCoordX(),Normaltest.getHead().getCoordX());
        assertEquals(Expected.getHead().getCoordY(),Normaltest.getHead().getCoordY());
        assertEquals(Expected.getHead().getCoordZ(),Normaltest.getHead().getCoordZ());


        //----------------------VBA 2 -----
        Point3D center =  new Point3D(1,1,2);
        test = new Cylinder(1.0,new Ray(new Vector(1,1,0),new Point3D(0,0,0)),2.0);
        Vector test1 = test.getNormal(new Point3D(1,0,2));
        Vector Result1 = center.subtract(new Point3D(1,1,0)).normalized();
        assertEquals(Result1.getHead().getCoordX(),test1.getHead().getCoordX());
        assertEquals(Result1.getHead().getCoordY(),test1.getHead().getCoordY());
        assertEquals(Result1.getHead().getCoordZ(),test1.getHead().getCoordZ());

    }
}