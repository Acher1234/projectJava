package unittests;

import org.junit.Test;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

public class TriangleTest {

    /**
     * Test for Get Normal.
     *
     */
    @Test
    public void getNormal() {
        new PolygonTests().testGetNormal();
    }
}