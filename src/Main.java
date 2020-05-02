import geometries.Cylinder;
import geometries.Sphere;
import primitives.*;
import static java.lang.System.out;
import static primitives.Util.*;
import unittests.*;


/**
 * The type Main.
 */
public final class Main {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        try{
            new CylinderTest().getNormal();
            new PolygonTests().testGetNormal();
            new SphereTest().getNormal();
            new TubeTest().getNormal();
            new TriangleTest().getNormal();
            VectorTests test = new VectorTests();
            test.crossProduct();
            test.dotProduct();
            test.lenghtSquared();
            test.normalized();
            test.scale();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
