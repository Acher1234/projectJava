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
        scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistance(180);
        scene.setBackground(new Color(222,184,135));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(

                //2 spheres pour le haut de la scene en mode rideau
                //sphere droite
                new Sphere(new Color(130,0,0), new Material(0, 0, 30), //
                        360, new Point3D(-300, 0, 475)),
                //sphere gauche
                new Sphere(new Color(130,0,0), new Material(0, 0, 30), //
                        360, new Point3D(1300, 0, 475)),

                //2 triangle pour faire le rectangle aus dessus de la scene
                //triangle droite
                new Triangle(new Color(130,0,0), new Material(0, 0, 30),
                        new Point3D(-100, 150, 550), new Point3D(1400, 150, 550), new Point3D(-100, 150, 425)),
                //triangle gauche
                new Triangle(new Color(130,0,0), new Material(0, 0, 30),
                        new Point3D(1400, 150, 550), new Point3D(-100, 150, 425), new Point3D(1400, 150, 425)),


                //lets go la pyramide (= 3 triangles)


                /*
                //1. pyramide de cote
                //coord tout a droite cest le sommet apres bah les pieds
                //patron de gauche transparancé
                new Triangle(new Color(225,225,225), new Material(0, 0, 30,1,0),
                        new Point3D(200, 305, -165), new Point3D(125, 385, -200), new Point3D(125, 385, 200)),
                //triangle de droite mirroiré
                new Triangle(new Color(100,100,100), new Material(0, 0, 30,0,1),
                        new Point3D(50, 305, -165), new Point3D(125, 385, -200), new Point3D(125, 385, 200)),
                //triangle de derrière mirroiré
                new Triangle(new Color(100,100,100), new Material(0, 0, 30,0,1),
                        new Point3D(200, 305, -165), new Point3D(50, 305, -165), new Point3D(125, 385, 200)),

                //sphere dans pyramide
                new Sphere(new Color(100,100,0), new Material(0, 0, 30,0,0), //
                        30, new Point3D(125, 340,-50)),

                 */



                //2. pyramide de face (pour toi^^)
                //coord tout a droite cest le sommet apres bah les pieds
                //patron de face transparancé
                new Triangle(new Color(225,225,225), new Material(0, 0, 30,1,0),
                        new Point3D(225, 385, -200), new Point3D(75, 385, -200), new Point3D(150, 385, 200)),
                //triangle de droite mirroiré
                new Triangle(new Color(100,100,100), new Material(0, 0, 30,0,1),
                        new Point3D(125, 305, -180), new Point3D(75, 385, -200), new Point3D(150, 385, 200)),
                //triangle de gauche mirroiré
                new Triangle(new Color(100,100,100), new Material(0, 0, 30,0,1),
                        new Point3D(125, 305, -180), new Point3D(225, 385, -200), new Point3D(150, 385, 200)),

                //sphere dans pyramide
                new Sphere(new Color(100,100,0), new Material(1, 0, 30,0,0), //
                        30, new Point3D(125, 340,-50)),






                //plancher de la scene
                new Triangle(new Color(40,20,0), new Material(1, 0.5, 50),
                        new Point3D(-100, 0, 10), new Point3D(1100, 0, 10), new Point3D(1100, 300, -22)),
                new Triangle(new Color(40,20,0), new Material(1, 0.5, 50),
                        new Point3D(-100, 0, 10), new Point3D(1100, 300, -22), new Point3D(-100, 300, -22)),
                //miroir Derriere
                new Triangle(new Color(50,50,50), new Material(0, 0, 30,0,1),
                        new Point3D(-100, 0, 10), new Point3D(1100, 0, 10), new Point3D(1100, -10, 500)),
                new Triangle(new Color(50,50,50), new Material(0, 0, 30,0,1),
                        new Point3D(1100, -10, 500), new Point3D(-100, 0, 10), new Point3D(-100, -10, 500)),
                //Arrier Noir
                new Triangle(new Color(0,0,0), new Material(0, 0, 30,0,1),
                        new Point3D(-400, -100, 10), new Point3D(1500, -100, 10), new Point3D(1500, -100, 700)),
                new Triangle(new Color(0,0,0), new Material(0, 0, 30,0,1),
                        new Point3D(1500, -100, 700), new Point3D(-400, -100, 10), new Point3D(-400, -100, 700)),
                //Objet de scene
                new Sphere(new Color(100,100,100),new Material(0.5,0.5,30,0.5,0),20,new Point3D(300,60,30)),
                new Cylinder(new Color(100,100,100),new Material(0.5,1,30,0.5,0),20,new Ray(new Point3D(280,60,30),new Vector(1,0,0)),20),
                //Lumiere
                new Sphere(new Color(255,255,255),new Material(0.5,0.5,30,0,0.2),80,new Point3D(500,0,400))


        );

        scene.addLights(new SpotLight(new Color(400, 240, 0), //
                new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

        scene.addLights(
                new reelSpotLight(new Color(255, 255, 255), //
                        new Point3D(500,0,400), 10, 0.00001, 0.000000001,81),
                new RayonLight(new Color(200, 0, 0), //
                        new Point3D(200,100,500), new Vector(0,0,-1), 1, 0.00001,0.000000001,60)
        );

                ImageWriter imageWriter = new ImageWriter("SceneFinal", 200, 140, 400, 280);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

}
