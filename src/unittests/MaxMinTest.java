package unittests;

import elements.AmbientLight;
import elements.Camera;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;
import geometries.*;

public class MaxMinTest
{
    @Test
    void test1()
    {
        Scene scene = new Scene("Scene final");
        scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1),false));
        scene.setDistance(900);
        scene.setBackground(new Color(222,184,135));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        scene.addGeometries(new Sphere(new Color(255,255,255),new Material(0,0,0,0,0),20,new Point3D(0,0,0)));
    }
}
