import elements.AmbientLight;
import elements.Camera;
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
            scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1000);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

            scene.addGeometries(new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                    new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)),
                    new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                            new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

            scene.addLights(new reelSpotLight(new Color(400, 240, 0), //
                    new Point3D(-100, 100, -200), 1, 1E-5, 1.5E-7,5));

            ImageWriter imageWriter = new ImageWriter("SceneFinal", 1000, 700, 2000, 1400);
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
