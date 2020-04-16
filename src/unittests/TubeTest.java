package unittests;

import org.junit.Test;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;

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


}