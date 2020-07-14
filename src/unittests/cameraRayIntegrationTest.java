package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import elements.Camera;
import primitives.*;
import geometries.*;

import java.util.ArrayList;
import java.util.List;

public class cameraRayIntegrationTest {

    @Test
    public void TestsphereFunction()
    {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        ArrayList<Ray> rayTest= new ArrayList<Ray>();
        ArrayList<Point3D> pointTest= new ArrayList<Point3D>();
        List<Intersectable.GeoPoint> pointTemp ;
        for (int i = 0;i < 3;i++)
        {
            for (int j=0;j<3;j++)
            {
                rayTest.add(camera.constructRayThroughPixel(3,3,j,i,1,3,3).get(0));
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
            for (Intersectable.GeoPoint p3D:pointTemp)
            {
                pointTest.add(p3D.point);
            }
        }
        assertEquals(2,pointTest.size(),0);
        pointTest.clear();

        //sphere test4
        sphereTest = new Sphere(4,new Point3D(0,0,0));
        for (Ray temp:rayTest)
        {
            pointTemp = sphereTest.findIntersection(temp);
            if(pointTemp == null)
            {
                continue;
            }
            for (Intersectable.GeoPoint p3D:pointTemp)
            {
                pointTest.add(p3D.point);
            }
        }
        assertEquals(9,pointTest.size(),0);
        pointTest.clear();
        //sphere test5
        sphereTest = new Sphere(0.5,new Point3D(0,0,-1));
        for (Ray temp:rayTest)
        {
            pointTemp = sphereTest.findIntersection(temp);
            if(pointTemp == null)
            {
                continue;
            }
            for (Intersectable.GeoPoint p3D:pointTemp)
            {
                pointTest.add(p3D.point);
            }
        }
        assertEquals(0,pointTest.size(),0);
        pointTest.clear();
        //Sphere test2
        camera = new Camera(new Point3D(0,0,-0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        rayTest.clear();
        for (int i = 0;i < 3;i++)
        {
            for (int j=0;j<3;j++)
            {
                rayTest.add(camera.constructRayThroughPixel(3,3,j,i,1,3,3).get(0));
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
            for (Intersectable.GeoPoint p3D:pointTemp)
            {
                pointTest.add(p3D.point);
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
            for (Intersectable.GeoPoint p3D:pointTemp)
            {
                pointTest.add(p3D.point);
            }
        }
        assertEquals(10,pointTest.size(),0);
        pointTest.clear();
    }
    @Test
    public void TestplaneFunction()
    {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        ArrayList<Ray> rayTest= new ArrayList<Ray>();
        ArrayList<Point3D> pointTest= new ArrayList<Point3D>();
        List<Intersectable.GeoPoint> pointTemp ;
        for (int i = 0;i < 3;i++)
        {
            for (int j=0;j<3;j++)
            {
                rayTest.add(camera.constructRayThroughPixel(3,3,j,i,1,3,3).get(0));
            }
        }
        Plane planetest = new Plane(new Point3D(0,0,7), camera.getVto());
        for (Ray temp: rayTest)
        {
            pointTemp = planetest.findIntersection(temp);
            if(pointTemp == null) continue;
            for(Intersectable.GeoPoint p3D: pointTemp)
            {
                pointTest.add(p3D.point);
            }
        }
        assertEquals(9,pointTest.size(),0);
        pointTest.clear();
        //test plane 2
        planetest = new Plane(new Point3D(0,0,7),new Vector(0,-1,-2));
        for (Ray temp: rayTest)
        {
            pointTemp = planetest.findIntersection(temp);
            if(pointTemp == null) continue;
            for(Intersectable.GeoPoint p3D: pointTemp)
            {
                pointTest.add(p3D.point);
            }
        }
        assertEquals(9,pointTest.size(),0);
        pointTest.clear();
        //test plane 3
        planetest = new Plane(new Point3D(0,0,7),new Vector(1,0,1));
        for (Ray temp: rayTest)
        {
            pointTemp = planetest.findIntersection(temp);
            if(pointTemp == null) continue;
            for(Intersectable.GeoPoint p3D: pointTemp)
            {
                pointTest.add(p3D.point);
            }
        }
        assertEquals(6,pointTest.size(),0);
        pointTest.clear();

    }
    @Test
    public void TriangleTest()
    {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        ArrayList<Ray> rayTest= new ArrayList<Ray>();
        ArrayList<Point3D> pointTest= new ArrayList<Point3D>();
        List<Intersectable.GeoPoint> pointTemp ;
        for (int i = 0;i < 3;i++)
        {
            for (int j=0;j<3;j++)
            {
                rayTest.add(camera.constructRayThroughPixel(3,3,j,i,1,3,3).get(0));
            }
        }
        //triangle test 1
        Triangle triangleTest = new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
        for (Ray temp: rayTest)
        {
            pointTemp = triangleTest.findIntersection(temp);
            if(pointTemp == null) continue;
            for(Intersectable.GeoPoint p3D: pointTemp)
            {
                pointTest.add(p3D.point);
            }
        }
        assertEquals(1,pointTest.size(),0);
        pointTest.clear();
        //triangle test 2
        triangleTest = new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
        for (Ray temp: rayTest)
        {
            pointTemp = triangleTest.findIntersection(temp);
            if(pointTemp == null) continue;
            for(Intersectable.GeoPoint p3D: pointTemp)
            {
                pointTest.add(p3D.point);
            }
        }
        assertEquals(2,pointTest.size(),0);
        pointTest.clear();

    }


}
