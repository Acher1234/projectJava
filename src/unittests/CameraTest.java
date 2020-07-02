package unittests;

import elements.Camera;
import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CameraTest {

    @Test
    void constructRayThroughPixel()
    {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        ArrayList<Ray> rayTest= new ArrayList<Ray>();
        ArrayList<Point3D> pointTest= new ArrayList<Point3D>();
        List<Point3D> pointTemp ;
        List<Ray> recuptest= new ArrayList<Ray>();
        for (int i = 0;i < 3;i++)
        {
            for (int j=0;j<3;j++)
            {
                recuptest = camera.constructRayThroughPixel(3,3,j,i,1,9,9);
                for (Ray test:recuptest)
                {
                 rayTest.add(test);
                }
            }
        }
        assertEquals(45,rayTest.size());
    }
    @Test
    void PlaneTest()
    {
        Plane a = new Plane(new Point3D(1,1,1),new Vector(25,42,12));
        double result = a.findPerpendicularVector(3,5.6).dotProduct(new Vector(25,42,12));
        assertEquals(0.000,result,0.000001);
    }
}