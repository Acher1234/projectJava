import elements.*;
import geometries.Cylinder;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import static java.lang.System.out;
import static primitives.Util.*;

import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import unittests.*;


/**
 * The type Main.
 */
public final class Main {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        Scene scene = new Scene("Scene final");
        scene.setCamera(new Camera(new Point3D(500, 900, 200), new Vector(0, -1, -0.15), new Vector(0, -0.15, 1)));
        scene.setDistance(950);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                //plancher de la scene
                new Triangle(new Color(109,7,26), new Material(0.5, 0.5, 30),
                        new Point3D(0, 0, 10), new Point3D(1000, 0, 10), new Point3D(1000, 300, 15)),
                new Triangle(new Color(109,7,26), new Material(0.5, 0.5, 30),
                        new Point3D(0, 0, 10), new Point3D(1000, 300, 15), new Point3D(0, 300, 15)),
                //miroir Derriere
                new Triangle(new Color(70,70,70), new Material(0, 0, 30,0,1),
                        new Point3D(0, 0, 10), new Point3D(1000, 0, 10), new Point3D(1000, 0, 3000)),
                new Triangle(new Color(70,70,70), new Material(0, 0, 30,0,1),
                        new Point3D(1000, 0, 3000), new Point3D(0, 0, 10), new Point3D(0, 0, 3000)),
                //
                new Sphere(new Color(100,100,100),new Material(0.5,0.5,30,0.5,0),20,new Point3D(300,60,30))

        );

        scene.addLights(
                new PointLight(new Color(400, 0, 0), //
                        new Point3D(300,60,1000), 1, 1E-5, 1.5E-7)
        );

                ImageWriter imageWriter = new ImageWriter("SceneFinal", 1000, 700, 2000, 1400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

}
