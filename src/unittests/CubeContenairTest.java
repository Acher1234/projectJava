package unittests;

import geometries.Contenair;
import geometries.CubeContenair;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Cube contenair test.
 */
class CubeContenairTest {

    /**
     * Checkifistouched.
     */
    @Test
    void checkifistouched()
    {
        Point3D a = new Point3D(0,0,0);
        Point3D b = new Point3D(1,0,0);
        Point3D c = new Point3D(1,1,0);
        Point3D d = new Point3D(0,1,0);
        Point3D f = new Point3D(1,0,1);
        Point3D g = new Point3D(1,1,1);
        Point3D h = new Point3D(0,1,1);
        Point3D e = new Point3D(0,0,1);
        CubeContenair x = new CubeContenair(h,g,c,d,a,b,f,e);
        Ray ra = new Ray(new Point3D(-1.68,-2.59,0),new Vector(-1.59,5.44,0));
        Ray rb = new Ray(new Point3D(1.22,-1.96,0),new Vector(-1.22,-1.1,0));
        Ray rc = new Ray(new Point3D(1.22,-1.96,0),new Vector(-0.66,1.96,0.6));
        boolean Bx = x.Checkifistouched(ra);
        boolean Cx = x.Checkifistouched(rb);
        boolean Dx = x.Checkifistouched(rc);
        double da = 12;

    }
}