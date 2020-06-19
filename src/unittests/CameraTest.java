package unittests;

import elements.Camera;
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
        for (int i = 0;i < 200;i++)
        {
            for (int j=0;j<300;j++)
            {
                recuptest = camera.constructRayThroughPixel(300,200,j,i,1,600,400);
                recuptest.remove(3);
                for (Ray test:recuptest)
                {
                 rayTest.add(test);
                }
            }
        }
        assertEquals(45,rayTest.size());
    }
}