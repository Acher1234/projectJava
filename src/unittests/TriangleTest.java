package unittests;

import org.junit.Test;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

import java.util.List;

/**
 * The type Triangle test.
 */
public class TriangleTest {

    /**
     * Gets normal.
     */
    @Test
    public void getNormal() {
        new PolygonTests().testGetNormal();
    }

    /**
     * Find intersection.
     */
    @Test
    public void findIntersection()
    {
        Triangle test = new Triangle(new Point3D(0,0,0),new Point3D(-2,0,0),new Point3D(0,0,2));
        Point3D testPoint = new Point3D(0,-2,0);
        List <Point3D> result;
        //----EP test
        //test1 D is in the triangle
        result = test.findIntersection(new Ray(new Vector(-0.83,2,0.58),testPoint));
        assertEquals(result.get(0),new Point3D(-0.83,0,0.58));
        //test1 D is outside
        result = test.findIntersection(new Ray(new Vector(-2,2,2),testPoint));
        assertNull(result);
        //test3 D is in a ray of the triangle
        result = test.findIntersection(new Ray(new Vector(0.5,2,0),testPoint));
        assertNull(result);

        //------BVA CASE the ray isn't in the plane
        result = test.findIntersection(new Ray(new Vector(-1.61,-0.13,0),testPoint));
        assertNull(result);
        //------BVA CASE the ray touch one ray of the triangle
        result = test.findIntersection(new Ray(new Vector(-1,2,0),testPoint));
        assertEquals(result.get(0),new Point3D(-1,0,0));
        //------BVA CASE the ray touch one point of the triangle
        result = test.findIntersection(new Ray(new Vector(-2,2,0),testPoint));
        assertEquals(result.get(0),new Point3D(-2,0,0));
    }
}