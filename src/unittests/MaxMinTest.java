package unittests;

import elements.AmbientLight;
import elements.Camera;
import geometries.*;
import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * The type Max min test.
 */
public class MaxMinTest
{
    /**
     * Test spere.
     */
    @Test
    public void testSpere()
    {
        Scene scene = new Scene("Scene final");
        scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1),false));
        scene.setDistance(900);
        scene.setBackground(new Color(222,184,135));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        Sphere Sphere1 = new Sphere(new Color(255,255,255),new Material(0,0,0,0,0),200,new Point3D(500,0,0));
        scene.addGeometries(Sphere1,new CubeBox(new Color(255,255,255),new Material(0,0,0,0,0),Sphere1.getMaxX()+1,Sphere1.getMaxY()+1,Sphere1.getMaxZ()+1,Sphere1.getMinX()+1,Sphere1.getMinY()+1,Sphere1.getMinZ()+1));


        ImageWriter imageWriter = new ImageWriter("testMaxmin", 1000, 700, 2000, 1400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Test rectangle.
     */
    @Test
    public void testRectangle()
    {
        Scene scene = new Scene("Scene final");
        scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1),false));
        scene.setDistance(900);
        scene.setBackground(new Color(222,184,135));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        rectangle Sphere1 = new rectangle(new Color(255,255,255),new Material(0,0,0,0,0),new Point3D(500,0,0),new Point3D(400,0,0),new Point3D(400,0,-100),new Point3D(500,0,-100));
        scene.addGeometries(Sphere1,new CubeBox(new Color(255,255,255),new Material(0,0,0,0,0),Sphere1.getMaxX()+1,Sphere1.getMaxY()+1,Sphere1.getMaxZ()+1,Sphere1.getMinX()-1,Sphere1.getMinY()-1,Sphere1.getMinZ()-1));


        ImageWriter imageWriter = new ImageWriter("testMaxmin", 1000, 700, 2000, 1400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Testcylinder.
     */
    @Test
    public void testcylinder()
    {
        Scene scene = new Scene("Scene final");
        scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1),false));
        scene.setDistance(900);
        scene.setBackground(new Color(222,184,135));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        Cylinder Sphere1 = new Cylinder(new Color(255,0,0),new Material(0,0,0,0,0),20,new Ray(new Point3D(500,0,0),new Vector(0,0,1)),200);
        scene.addGeometries(Sphere1,new CubeBox(new Color(255,255,255),new Material(0,0,0,0,0),Sphere1.getMaxX()+10,Sphere1.getMaxY()+10,Sphere1.getMaxZ()+10,Sphere1.getMinX()-10,Sphere1.getMinY()-10,Sphere1.getMinZ()-10));


        ImageWriter imageWriter = new ImageWriter("testMaxmin", 1000, 700, 2000, 1400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Testpyramide.
     */
    @Test
    public void testpyramide()
    {
        Scene scene = new Scene("Scene final");
        scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1),false));
        scene.setDistance(900);
        scene.setBackground(new Color(222,184,135));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        PyramideBasedRectangle Sphere1 = new PyramideBasedRectangle(new Color(20,20,20),new Color(20,20,20),new Color(20,20,20),
                new Color(20,20,20),new Color(70,70,70)
                ,new Material(0,0,0,0,0),new Material(0,0,0,0,0.2),
                new Material(0,0,0,1,0.2),new Material(0,0,0,0,0.2),new Material(0,0,0,0,1),
                new Point3D(750,500,-170), new Point3D(750,100,-170),new Point3D(400,100,-170),new Point3D(400,500,-170),new Point3D(600,300,-20));
        scene.addGeometries(Sphere1,new Sphere(new Color(0,0,100),new Material(0.5,1,20,0.5,0),60,new Point3D(600, 300, -110)),
                new Sphere(new Color(100,20,20),new Material(0.5,1,20,0,0.5),30,new Point3D(600, 300, -110)),new CubeBox(new Color(255,255,255),new Material(0,0,0,0,0),Sphere1.getMaxX()+10,Sphere1.getMaxY()+10,Sphere1.getMaxZ()+10,Sphere1.getMinX()-10,Sphere1.getMinY()-10,Sphere1.getMinZ()-10));


        ImageWriter imageWriter = new ImageWriter("testMaxmin", 1000, 700, 2000, 1400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}
