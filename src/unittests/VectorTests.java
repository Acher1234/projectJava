package unittests;

import primitives.*;
import org.junit.jupiter.api.DynamicTest;

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
        assertEquals(-1.0, X.get());
        assertEquals(0.0, Y.get());
        assertEquals(0.0, Z.get());
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
            assertEquals(2.0,test2.getHead().getCoordX().get());
            assertEquals(2.0,test2.getHead().getCoordY().get());
            assertEquals(2.0,test2.getHead().getCoordZ().get());

    }

    /**
     * Dot product.
     */
    @org.junit.Test
    public void dotProduct()  {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(1.0, 2.0, 4.0);
        assertEquals(17.0, v1.dotProduct(v2));
    }



    /**
     * Lenght squared.
     */
    @org.junit.Test
    public void lenghtSquared() {
        // ============ Equivalence Partitions Tests ==============
        Vector V1 = new Vector(1.0,1.0,1.0);
        double test = V1.lengthSquared();
        assertEquals(1.0,test);
         V1 = new Vector(-1.0,-1.0,-1.0);//test negative
        test = V1.lengthSquared();
        assertEquals(1.0,test);
    }


    /**
     * Normalized.
     */
    @org.junit.Test
    public void normalized() {
        // ============ Equivalence Partitions Tests ==============
        Vector V1 = new Vector(2.0,4.0,3.0);
        double test = V1.normalized().length();
        assertEquals(1.0,test);
        V1 = new Vector(-2.0,4.0,-3.0);
        test = V1.normalized().length();
        assertEquals(1.0,test);
    }

}