package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;
import renderer.*;
import scene.Scene;


/**
 * The type Super sampling tes.
 */
public class SuperSamplingTes {

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void WithandWithoutSuperSampling() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("without Super Sampling", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        Scene scene2 = new Scene("Test scene");
        scene2.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0),true));
        scene2.setDistance(1000);
        scene2.setBackground(Color.BLACK);
        scene2.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene2.addGeometries(
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene2.addLights(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));

        ImageWriter imageWriter2 = new ImageWriter("with Super Sampling", 200, 200, 500, 500);
        Render render2 = new Render(imageWriter2, scene2);

        render2.renderImage();
        render2.writeToImage();
    }
}
