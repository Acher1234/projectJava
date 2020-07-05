package unittests;

import geometries.Intersectable;
import geometries.Triangle;
import geometries.rectangle;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Rectangle test.
 */
class rectangleTest {
    /**
     * T est triangleisinpoint.
     */
    @Test
    void TEstTriangleisinpoint()
    {
        List<Point3D> test = new ArrayList<Point3D>();
        test.add(new Point3D(0,0,0));
        test.add(new Point3D(0,-2,0));
        test.add(new Point3D(2,0,0));
        Triangle r = new Triangle(test.get(0),test.get(1),test.get(2));
        boolean x = r.isInInside(new Point3D(0.35,-1.05,0));
        boolean y = r.isInInside(new Point3D(0.91,-0.39,0));
        boolean z = r.isInInside(new Point3D(-0.75,-0.68,0));
        boolean w = r.isInInside(new Point3D(-0.75,-0.68,0));
    }

    /**
     * Create rectangle.
     */
    @Test
    void CreateRectangle()
    {
        List<Point3D> test = new ArrayList<Point3D>();
        test.add(new Point3D(0,0,0));
        test.add(new Point3D(0,-3,0));
        test.add(new Point3D(2,-3,0));
        test.add(new Point3D(2,0,0));
        rectangle r = new rectangle(new Color(255,255,255),new Material(0,0,0,0,0),test.get(0),test.get(1),test.get(2),test.get(3));
    }

    /**
     * Createfalse rectangle.
     */
    @Test
    void createfalseRectangle()
    {
        List<Point3D> test2 = new ArrayList<Point3D>();
        test2.add(new Point3D(0,0,1));
        test2.add(new Point3D(0,-3,0));
        test2.add(new Point3D(2,-3,0));
        test2.add(new Point3D(2,0,0));
        rectangle r = new rectangle(new Color(255,255,255),new Material(0,0,0,0,0),test2.get(0),test2.get(1),test2.get(2),test2.get(3));
    }

    /**
     * Createfalsesize rectangle.
     */
    @Test
    void createfalsesizeRectangle()
    {
        List<Point3D> test2 = new ArrayList<Point3D>();
        test2.add(new Point3D(0,0,0));
        test2.add(new Point3D(-5,0,0));
        test2.add(new Point3D(-3,3,0));
        test2.add(new Point3D(0,3,0));
        rectangle r = new rectangle(new Color(255,255,255),new Material(0,0,0,0,0),test2.get(0),test2.get(1),test2.get(2),test2.get(3));
    }


    /**
     * Find intersection.
     */
    @Test
    void findIntersection()
    {
        List<Point3D> test = new ArrayList<Point3D>();
        test.add(new Point3D(0,-3,0));
        test.add(new Point3D(0,0,0));
        test.add(new Point3D(3,0,0));
        test.add(new Point3D(3,-3,0));
        rectangle r = new rectangle(new Color(255,255,255),new Material(0,0,0,0,0),test.get(0),test.get(1),test.get(2),test.get(3));
        Ray ray= new Ray(new Vector(-0.81,-0.74,-2),new Point3D(0,0,2));
        List<Intersectable.GeoPoint>i =r.findIntersection(ray);
        ray= new Ray(new Vector(2.13,-5.02,-2),new Point3D(0,0,2));
        i =r.findIntersection(ray);
        ray= new Ray(new Vector(3.84,-1.53,-2),new Point3D(0,0,2));
        i =r.findIntersection(ray);
        ray= new Ray(new Vector(1.11,-1.16,-2),new Point3D(0,0,2));
        i =r.findIntersection(ray);
        double a = 0;

    }
}