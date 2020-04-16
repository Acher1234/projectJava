package unittests;

import org.junit.Test;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

import java.util.List;

public class TubeTest {

    @Test
    public void getNormal()
    {
        Point3D center =  new Point3D(0,1,0);
        Tube test = new Tube(1.0,new Ray(new Vector(1,0,0),new Point3D(0,0,0)));
        Vector test1 = test.getNormal(new Point3D(0,1,0));
        Vector Result1 = center.subtract(test.get_axisRay().getPOO()).normalized();
        assertEquals(Result1.getHead().getCoordX(),test1.getHead().getCoordX());
        assertEquals(Result1.getHead().getCoordY(),test1.getHead().getCoordY());
        assertEquals(Result1.getHead().getCoordZ(),test1.getHead().getCoordZ());
    }
    @Test
    public void findIntersection(Ray ray)
    {
        List<Point3D> ListTest;
        Point3D center = new Point3D(0,0,0);
        Tube tubeTest = new Tube(1.0,new Ray(new Vector(1,1,0),center));

        //---------------------BVA 1---------------Not center point start with a point of cercle
        Point3D Point = tubeTest.findIntersection(new Ray(new Vector(-1,-1,0),new Point3D(-1,-1,0))).get(0);
        assertEquals(Point,new Point3D(-1,-1,0));
        ListTest = tubeTest.findIntersection(new Ray(new Vector(2,2,0),new Point3D(-1,-1,0)));
        Point = ListTest.get(0);
        assertEquals(Point,new Point3D(-1,-1,0));
        Point = ListTest.get(1);
        assertEquals(Point,new Point3D(1,1,0));

        tubeTest = new Tube(1.0,new Ray(new Vector(1,0,0),center));
        //---------------------BVA 2---------------Center Point
        Point = tubeTest.findIntersection(new Ray(new Vector(-1,-1,0),new Point3D(-1,-1,0))).get(0);
        assertEquals(Point,new Point3D(-1,-1,0));

    }


}