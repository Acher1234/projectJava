package unittests;

import elements.AmbientLight;
import elements.Camera;
import geometries.CubeBox;
import geometries.PyramideBasedRectangle;
import geometries.Sphere;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import static org.junit.Assert.assertEquals;

/**
 * The type Merge box.
 */
public class MergeBox
{
    /**
     * Merge box test.
     */
    @Test
    public void MergeBoxTest()
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
        scene.addGeometriesWithhisContenair(Sphere1,new Sphere(new Color(0,0,100),new Material(0.5,1,20,0.5,0),60,new Point3D(600, 300, -110)),
                new Sphere(new Color(100,20,20),new Material(0.5,1,20,0,0.5),30,new Point3D(600, 300, -110)),new CubeBox(new Color(255,255,255),new Material(0,0,0,0,0),Sphere1.getMaxX()+10,Sphere1.getMaxY()+10,Sphere1.getMaxZ()+10,Sphere1.getMinX()-10,Sphere1.getMinY()-10,Sphere1.getMinZ()-10));

        assertEquals(scene.get_geometries().size(),1);

    }

    /**
     * Merge box test 2.
     */
    @Test
    public void MergeBoxTest2()
    {
        Scene scene = new Scene("Scene final");
        scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1),false));
        scene.setDistance(900);
        scene.setBackground(new Color(222,184,135));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        scene.addGeometriesWithhisContenair(new Sphere(new Color(0,0,100),new Material(0.5,1,20,0.5,0),60,new Point3D(100, 300, -110)),new Sphere(new Color(0,0,100),new Material(0.5,1,20,0.5,0),60,new Point3D(600, 300, -110)),
                new Sphere(new Color(100,20,20),new Material(0.5,1,20,0,0.5),30,new Point3D(600, 300, -110)));


        assertEquals(scene.get_geometries().size(),2);
    }
}
