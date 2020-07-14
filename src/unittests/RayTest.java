package unittests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * The type Ray test.
 */
public class RayTest {

    /**
     * Gets point.
     */
    @Test
    public void getPoint() {
    }

    /**
     * Gets t.
     */
    @Test
    public void getT()
    {
        Ray test = new Ray(new Vector(1,0,0),new Point3D(0,0,0));
        Point3D result = test.getPoint(3);
        assertEquals(result,new Point3D(3,0,0));
        double resultDouble = test.getT(result);
        assertEquals(resultDouble,3,0);

    }
}