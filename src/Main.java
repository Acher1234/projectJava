import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import elements.reelSpotLight;
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
        try{
            Scene scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1)));
            scene.setDistance(180);
            scene.setBackground(new Color(222,184,135));
            scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));


            scene.addGeometries(
                    //2 spheres pour le haut de la scene en mode rideau
                    new Sphere(new Color(255,0,0), new Material(0.5, 0.5, 30), //
                            100, new Point3D(-160, -60, 150)),
                    new Sphere(new Color(255,0,0), new Material(0.5, 0.5, 30), //
                            100, new Point3D(160, -60, 150)),
                    //2 triangle pour faire le rectangle aus dessus de la scene
                    new Triangle(new Color(255,0,0), new Material(0.5, 0.5, 30),
                            new Point3D(-120, -100, 50), new Point3D(120, -100, 50), new Point3D(-120, -40, 50)),
                    new Triangle(new Color(255,0,0), new Material(0.5, 0.5, 30),
                            new Point3D(120, -100, 50), new Point3D(-120, -40, 50), new Point3D(120, -40, 50)));


            scene.addLights(
                    new reelSpotLight(new Color(255, 255, 255), //
                            new Point3D(500,0,400), 10, 0.00001, 0.000000001,81)
                    /*
                    new RayonLight(new Color(200, 0, 0), //
                            new Point3D(200,100,500), new Vector(0,0,-1), 1, 0.00001,0.000000001,60)

                     */
            );

            /*
            scene.addLights(new reelSpotLight(new Color(400, 240, 0), //
                    new Point3D(-100, 100, -200), 1, 1E-5, 1.5E-7,5));

            scene.addLights(new SpotLight(new Color(400, 240, 0), //
                    new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

             */



            ImageWriter imageWriter = new ImageWriter("SceneFinal", 200, 140, 400, 280);
            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
