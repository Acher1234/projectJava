package unittests;

import primitives.*;
import org.junit.jupiter.api.DynamicTest;

import static java.lang.StrictMath.sqrt;
import static org.junit.Assert.*;

/**
 * The type Vector tests.for pimitives.Vector
 */
public class VectorTests {

    /**
     * Cross product test {@primitives.ja}
     */
    @org.junit.Test
    public void crossProduct()
    {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(3.5, -5, 10);
        Vector v2 = new Vector(2.5, 7, 0.5);
        Coordinate X = v1.crossProduct(v2).getHead().getCoordX();
        Coordinate Y = v1.crossProduct(v2).getHead().getCoordY();
        Coordinate Z = v1.crossProduct(v2).getHead().getCoordZ();
        assertEquals(-72.5, X.get(),0);
        assertEquals(23.25, Y.get(),0);
        assertEquals(37, Z.get(),0);
    }

    /**
     * Scale.
     */
    @org.junit.Test
    public void scale(){
        Vector test2 = null;
        // ============ Equivalence Partitions Tests ==============
        Vector test = new Vector(1.0, 1.0, 1.0);
        test2 = test.scale(2);
        assertEquals(2.0,test2.getHead().getCoordX().get(),0);
        assertEquals(2.0,test2.getHead().getCoordY().get(),0);
        assertEquals(2.0,test2.getHead().getCoordZ().get(),0);

    }

    /**
     * Test.
     */
    @org.junit.Test
    public void test(){
        Vector test2 = new Vector(1.0, 1.0, 1.0);
        // ============ Equivalence Partitions Tests ==============
        Vector test = new Vector(1.0, 1.0, 1.0);
        assertEquals(test,test2);
    }

    /**
     * Dot product.
     */
    @org.junit.Test
    public void dotProduct()  {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(1.0, 2.0, 4.0);
        assertEquals(17.0, v1.dotProduct(v2),0);
    }


    /**
     * Lenght squared.
     */
    @org.junit.Test
    public void lenghtSquared() {
        // ============ Equivalence Partitions Tests ==============
        Vector V1 = new Vector(1.0,1.0,1.0);
        double test = V1.lengthSquared();
        assertEquals(3.0,test,0);
        V1 = new Vector(-1.0,0,0);//test negative
        test = V1.lengthSquared();
        assertEquals(1.0,test,0);
    }


    /**
     * Normalized.
     */
    @org.junit.Test
    public void normalized() {
        // ============ Equivalence Partitions Tests ==============
        Vector V1 = new Vector(1.0,1.0,1.0);
        double test = V1.normalized().length();
        assertEquals(1,test,0);
        V1 = new Vector(-2.0,4.0,-3.0);
        test = V1.normalized().length();
        assertEquals(1.0,test,0);
    }

}