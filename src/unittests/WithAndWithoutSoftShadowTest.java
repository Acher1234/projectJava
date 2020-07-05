package unittests;

import elements.*;
import geometries.Cylinder;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;


public class WithAndWithoutSoftShadowTest {

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void WithAndWithoutSoftShadowTestPoint() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

        scene.addLights(new AreaPointLight(new Color(400, 240, 0), //
                new Point3D(-100, 100, -200), 1, 1E-5, 1.5E-7,15,false));

        ImageWriter imageWriter = new ImageWriter("PointsphereTrianglewithoutsoftshadow", 200, 200, 400, 400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

        scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

        scene.addLights(new AreaPointLight(new Color(400, 240, 0), //
                new Point3D(-100, 100, -200), 1, 1E-5, 1.5E-7,10,true));

        imageWriter = new ImageWriter("PointsphereTrianglewithsoftshadow", 200, 200, 400, 400);
        render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void WithAndWithoutSoftShadowTestSpotLight() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

        scene.addLights(new AreaSpotLight(new Color(400, 240, 0), //
                new Point3D(-100, 100, -200),new Vector(0.14, -0.28, 0.95), 1, 1E-5, 1.5E-7,15,false));

        ImageWriter imageWriter = new ImageWriter("PointsphereTrianglewithoutsoftshadowSpot", 200, 200, 400, 400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

        scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0),true));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

        scene.addLights(new AreaSpotLight(new Color(400, 240, 0), //
                new Point3D(-100, 100, -200),new Vector(0.14, -0.28, 0.95), 1, 1E-5, 1.5E-7,15,true));

        imageWriter = new ImageWriter("PointsphereTrianglewithsoftshadowSpot", 200, 200, 400, 400);
        render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void WithAndWithoutSoftShadowTestRayonLight() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

        scene.addLights(new AreaRayonLight(new Color(400, 240, 0), //
                new Point3D(-80, 100, -200),new Vector(0.14, -0.28, 0.95), 1, 1E-5, 1.5E-7,30,30,false));

        ImageWriter imageWriter = new ImageWriter("PointsphereTrianglewithoutsoftshadowRayon", 200, 200, 400, 400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

        scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        60, new Point3D(0, 0, 200)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
                        new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

        scene.addLights(new AreaRayonLight(new Color(400, 240, 0), //
                new Point3D(-80, 100, -200),new Vector(0.14, -0.28, 0.95), 1, 1E-5, 1.5E-7,30,30,true));

        imageWriter = new ImageWriter("PointsphereTrianglewithsoftshadowRayon", 200, 200, 400, 400);
        render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}
