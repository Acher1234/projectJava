package unittests;

import org.junit.Test;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

import java.util.List;

/**
 * The type Sphere test.
 */
public class SphereTest {

    /**
     * Gets normal.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point3D centerTest = new Point3D(1,0,1);
        Sphere Stest = new Sphere(1.0,new Point3D(0,0,0));
        Vector result = Stest.getNormal(centerTest);
        Vector test = new Vector(-1,0,-1).normalized();
        assertEquals(result.getHead().getCoordX(),test.getHead().getCoordX());
        assertEquals(result.getHead().getCoordY(),test.getHead().getCoordY());
        assertEquals(result.getHead().getCoordZ(),test.getHead().getCoordZ());
    }

    /**
     * Find intersection.
     */
    @Test
    public void findIntersection() {
        Sphere test = new Sphere(1.0, new Point3D(0, 0, 0));
        //EP TEST 1--------
        //1 test for EP
        List<Point3D> result = test.findIntersection(new Ray(new Vector(1.86, 2.44, -0.24), new Point3D(-0.34, -0.65, 0.68)));
        Point3D resultPoint = result.get(0);
        assertEquals(resultPoint, new Point3D(-0.34, -0.65, 0.68));
        resultPoint = result.get(1);
        assertEquals(resultPoint, new Point3D(0.60, 0.58, 0.56));
        //2 test for EP
        result = test.findIntersection(new Ray(new Vector(1, 0, 0), new Point3D(1, 0, 0)));
        resultPoint = result.get(0);
        assertEquals(resultPoint, new Point3D(1.0, 0, 0));
        //EP test 2
        //1 test for EP
        result = test.findIntersection(new Ray(new Vector(-3, 0, 0), new Point3D(2, 0, 0)));
        resultPoint = result.get(0);
        assertEquals(new Point3D(1, 0, 0), resultPoint);
        resultPoint = result.get(1);
        assertEquals(new Point3D(-1, 0, 0), resultPoint);
        //2 test for EP
        result = test.findIntersection(new Ray(new Vector(-3, 0, 0), new Point3D(0.5, 0, 0)));
        resultPoint = result.get(0);
        assertEquals(new Point3D(-1, 0, 0), resultPoint);
        //3 test for EP
        result = test.findIntersection(new Ray(new Vector(-1, 0, 0), new Point3D(-1.5, 0, 0)));
        assertNull(result);
        //4 test for EP
        result = test.findIntersection(new Ray(new Vector(-2.1, 0, 0), new Point3D(1, 0, 0)));
        resultPoint = result.get(0);
        assertEquals(new Point3D(1, 0, 0), resultPoint);
        resultPoint = result.get(1);
        assertEquals(new Point3D(-1, 0, 0), resultPoint);
        //5 test for EP
        result = test.findIntersection(new Ray(new Vector(0.47, 0.27, 0.84), new Point3D(0, 0, 0)));
        resultPoint = result.get(0);
        assertEquals(new Point3D(0.47, 0.27, 0.84), resultPoint);
        //6 test for EP
        result = test.findIntersection(new Ray(new Vector(1, 0, 0), new Point3D(1, 0, 0)));
        resultPoint = result.get(0);
        assertEquals(new Point3D(1, 0, 0), resultPoint);
        //EP TEST 3
        //test 1
        result = test.findIntersection(new Ray((new Vector(-0.65, -0.39, 0.95)), new Point3D(0, 0, 1.61)));
        resultPoint = result.get(0);
        assertEquals(new Point3D(-0.65, -0.39, 0.65), resultPoint);
        //test 2
        result = test.findIntersection(new Ray((new Vector(0.65, 0.39, -0.95)).scale(2), new Point3D(-0.65, -0.39, 0.65)));
        resultPoint = result.get(0);
        assertEquals(new Point3D(-0.65, -0.39, 0.65), resultPoint);
        //test3
        result = test.findIntersection(new Ray((new Vector(1, 1, 1)), new Point3D(2, 2, 2)));
        assertNull(result);
        //BVA test
        result = test.findIntersection(new Ray((new Vector(0, 0, 2)), new Point3D(2, 0, 0)));
        assertNull(result);

    }

}