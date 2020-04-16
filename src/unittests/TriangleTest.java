package unittests;

import org.junit.Test;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

public class TriangleTest {

    @Test
    public void getNormal() {
        new PolygonTests().testGetNormal();
    }
}