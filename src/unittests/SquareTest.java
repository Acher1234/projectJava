package unittests;

import geometries.Intersectable;
import geometries.Square;
import geometries.rectangle;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest
{
    @Test
    void CreateSquare()
    {
        List<Point3D> test = new ArrayList<Point3D>();
        test.add(new Point3D(0,3,0));
        test.add(new Point3D(0,0,0));
        test.add(new Point3D(-3,0,0));
        test.add(new Point3D(-3,3,0));
        Square r = new Square(new Color(255,255,255),new Material(0,0,0,0,0),test.get(0),test.get(1),test.get(2),test.get(3));
    }
    @Test
    void CreateFalseSquare()
    {
        List<Point3D> test = new ArrayList<Point3D>();
        test.add(new Point3D(0,0,0));
        test.add(new Point3D(0,-3,0));
        test.add(new Point3D(2,-3,0));
        test.add(new Point3D(2,0,0));
        Square r = new Square(new Color(255,255,255),new Material(0,0,0,0,0),test.get(0),test.get(1),test.get(2),test.get(3));
    }
    @Test
    void CreateFalseSizeSquare()
    {
        List<Point3D> test = new ArrayList<Point3D>();
        test.add(new Point3D(0,3,0));
        test.add(new Point3D(0,0,0));
        test.add(new Point3D(-3,0,0));
        test.add(new Point3D(-3,3,0));
        Square r = new Square(new Color(255,255,255),new Material(0,0,0,0,0),test.get(0),test.get(1),test.get(2),test.get(3));
    }


    @Test
    void findIntersection()
    {
        List<Point3D> test = new ArrayList<Point3D>();
        test.add(new Point3D(0,3,0));
        test.add(new Point3D(0,0,0));
        test.add(new Point3D(-3,0,0));
        test.add(new Point3D(-3,3,0));
        Square r = new Square(new Color(255,255,255),new Material(0,0,0,0,0),test.get(0),test.get(1),test.get(2),test.get(3));
        Ray ray= new Ray(new Point3D(0,0,2),new Vector(-0.92,2.33,-2));
        List<Intersectable.GeoPoint> i =r.findIntersection(ray);
        ray= new Ray(new Point3D(0,0,2),new Vector(-3.83,0.6,-2));
        i =r.findIntersection(ray);
        double a = 0;

    }
}