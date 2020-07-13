package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import elements.Camera;
import primitives.*;
import geometries.*;

import java.util.ArrayList;
import java.util.List;

public class cameraRayIntegrationTest {

    /**
     * test for the camera with some spheres
     * to see if the finds intersections works good with the camera
     * and their rays constructed through pixel
     */
    @Test
    public void TestFunction()
    {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        ArrayList<Ray> rayTest= new ArrayList<Ray>();
        ArrayList<Point3D> pointTest= new ArrayList<Point3D>();
        List<Point3D> pointTemp ;
        for (int i = 0;i < 3;i++)
        {
            for (int j=0;j<3;j++)
            {
                rayTest.add(camera.constructRayThroughPixel(3,3,j,i,1,3,3));
            }
        }

        //Sphere Test
        Sphere sphereTest = new Sphere(1,new Point3D(0,0,3));
        for (Ray temp:rayTest)
        {
            pointTemp = sphereTest.findIntersection(temp);
            if(pointTemp == null)
            {
                continue;
            }
            for (Point3D p3D:pointTemp)
            {
                pointTest.add(p3D);
            }
        }
        assertEquals(2,pointTest.size(),0);
        pointTest.clear();

        //Sphere test2
        camera = new Camera(new Point3D(0,0,-0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        rayTest.clear();
        for (int i = 0;i < 3;i++)
        {
            for (int j=0;j<3;j++)
            {
                rayTest.add(camera.constructRayThroughPixel(3,3,j,i,1,3,3));
            }
        }
        sphereTest = new Sphere(2.5,new Point3D(0,0,2.5));
        for (Ray temp:rayTest)
        {
            pointTemp = sphereTest.findIntersection(temp);
            if(pointTemp == null)
            {
                continue;
            }
            for (Point3D p3D:pointTemp)
            {
                pointTest.add(p3D);
            }
        }
        assertEquals(18,pointTest.size(),0);
        pointTest.clear();

        //sphere Test 3
        sphereTest = new Sphere(2,new Point3D(0,0,2));
        for (Ray temp:rayTest)
        {
            pointTemp = sphereTest.findIntersection(temp);
            if(pointTemp == null)
            {
                continue;
            }
            for (Point3D p3D:pointTemp)
            {
                pointTest.add(p3D);
            }
        }
        assertEquals(10,pointTest.size(),0);
    }





}
