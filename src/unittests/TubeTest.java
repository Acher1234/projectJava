package unittests;

import org.junit.Test;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Tube test.
 */
public class TubeTest {

    /**
     * Gets normal.
     */
    @Test
    public void getNormal()
    {
        Point3D center =  new Point3D(0,1,0);
        Tube test = new Tube(1.0,new Ray(new Vector(1,0,0),new Point3D(0,0,0)));
        Vector test1 = test.getNormal(new Point3D(0,1,0));
        Vector Result1 = center.subtract(test.get_axisRay().getPOO()).normalized();
        assertEquals(Result1.getHead().getCoordX(),test1.getHead().getCoordX());
        assertEquals(Result1.getHead().getCoordY(),test1.getHead().getCoordY());
        assertEquals(Result1.getHead().getCoordZ(),test1.getHead().getCoordZ());
    }


    /**
     * Test find intersection.
     */
    @Test
    public void testFindIntersection()
    {
        List<Intersectable.GeoPoint> result;
        Tube test = new Tube(2,new Ray(new Vector(0,0,1),new Point3D(0,0,0)));
        result = test.findIntersection(new Ray(new Vector(4.91,0.41,2.6),new Point3D(-3,0,0)));
        //assertEquals(result.get(0),);
        test = new Tube(1,new Ray(new Vector(0,0,1),new Point3D(0,0,0)));
        result = test.findIntersection(new Ray(new Vector(-3,0,1),new Point3D(3,0,0)));
        assertEquals(result.get(0).point,new Point3D(1,0,0.67));
        assertEquals(result.get(1).point,new Point3D(-1,0,1.33));
        result = test.findIntersection(new Ray(new Vector(-3,0,3),new Point3D(3,0,0)));
        assertEquals(result.get(0).point,new Point3D(1,0,2));
        assertEquals(result.get(1).point,new Point3D(-1,0,4));
    }
}