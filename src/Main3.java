import elements.*;
import geometries.Cylinder;
import geometries.PyramideBasedRectangle;
import geometries.Sphere;
import geometries.rectangle;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;


/**
 * The type Main.
 */
public final class Main3 {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        boolean SoftShadow=false,precision=false;
        Scene scene = new Scene("Scene final");
        scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1),precision));
        scene.setDistance(900);
        scene.setBackground(new Color(222,184,135));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));


        scene.addGeometriesWithhisContenair(new rectangle(new Color(40,10,0), new Material(1, 0.5, 50),
                new Point3D(-100, 0, 10), new Point3D(1100, 0, 10), new Point3D(1100, 300, -22),new Point3D(-100, 300, -22)),
                new rectangle(new Color(255,0,0), new Material(0, 0, 30,0,0),
                        new Point3D(1300, 3, 600), new Point3D(1300, 3, 480), new Point3D(-500, 3, 480),new Point3D(-500, 3, 600)),
                //red windows
                new Sphere(new Color(255,0,0),new Material(0,0,50,0,0),250,new Point3D(1150, 3, 550)),
                new Sphere(new Color(255,0,0),new Material(0,0,50,0,0),250,new Point3D(-230, 3, 550)),
                new Cylinder(new Color(255,0,0),new Material(0,0,30,0,0),35,new Ray(new Point3D(1135,3,135),new Vector(0,0,0.1)),180),
                new Cylinder(new Color(255,0,0),new Material(0,0,30,0,0),35,new Ray(new Point3D(-130,3,135),new Vector(0,0,0.11)),180),
                //light
                new Sphere(new Color(100,100,100),new Material(0,0,30,1,0),80,new Point3D(500,10,400)),
                //right personne  and his ball
                new Sphere(new Color(100,100,100),new Material(0,0,30,0,0),20,new Point3D(120,200,10)),
                new Sphere(new Color(100,100,100),new Material(0,0,30,0,0),20,new Point3D(100,100,110)),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),20,new Ray(new Point3D(100,100,60),new Vector(0,0,1)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(120,100,70),new Vector(-0.4,0.1,0.4)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(60,100,70),new Vector(-0.2,0,-0.4)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(110,100,20),new Vector(0,0,1)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(90,100,20),new Vector(0,0.5,1)),30),
                //left Personne
                new Sphere(new Color(100,100,100),new Material(0,0,30,0,0),20,new Point3D(900,100,110)),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),20,new Ray(new Point3D(900,100,60),new Vector(0,0,1)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(940,100,70),new Vector(1,0,-0.4)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(860,100,70),new Vector(-1,0,-0.4)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(910,100,20),new Vector(0,0.2,1)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(890,100,20),new Vector(0,0,1)),30),
                //Pyramide and ball
                new PyramideBasedRectangle(new Color(20,20,20),new Color(20,20,20),new Color(20,20,20),
                        new Color(20,20,20),new Color(70,70,70)
                        ,new Material(0,0,0,0,0),new Material(0,0,0,0,0.2),
                        new Material(0,0,0,1,0.2),new Material(0,0,0,0,0.2),new Material(0,0,0,0,1),
                        new Point3D(750,500,-170), new Point3D(750,100,-170),new Point3D(400,100,-170),new Point3D(400,500,-170),new Point3D(600,300,-20)),
                new Sphere(new Color(0,0,100),new Material(0.5,1,20,0.5,0),60,new Point3D(600, 300, -110)),
                new Sphere(new Color(100,20,20),new Material(0.5,1,20,0,0.5),30,new Point3D(600, 300, -110)),
                //mirror
                new rectangle(new Color(50,50,50), new Material(0, 0, 30,0,1),
                        new Point3D(1100, 0, 500), new Point3D(-100,0,500), new Point3D(-100, 0, 10),new Point3D(1100, 0, 10)));


        scene.addLights(
                new AreaPointLight(new Color(70, 70, 70), //
                        new Point3D(500,30,400), 1.1, 0.000000000000001, 0.00000000000000001,80,SoftShadow),
                new AreaSpotLight(new Color(100,100,100), //
                        new Point3D(600, 1100, -110), new Vector(0,-1,0), 1, 0.00000000001,0.000000001,30,SoftShadow),
                new AreaRayonLight(new Color(255, 255, 255), //
                        new Point3D(640,100,1100), new Vector(0.3,0,-1), 1, 0.00001,0.000000001,100,10,SoftShadow),
                new AreaRayonLight(new Color(0, 255, 0), //
                        new Point3D(460,150,1100), new Vector(0.3,0,-1), 1, 0.00001,0.000000001,100,10,SoftShadow),
                new AreaRayonLight(new Color(0, 0, 255), //
                        new Point3D(260,200,1100), new Vector(0.3,0,-1), 1, 0.00001,0.000000001,100,10,SoftShadow),
                new AreaRayonLight(new Color(255, 255, 0), //
                        new Point3D(740,200,1100), new Vector(-0.3,0,-1), 1, 0.00001,0.000000001,100,10,SoftShadow),
                new AreaRayonLight(new Color(238, 130, 238), //
                        new Point3D(540,150,1100), new Vector(-0.3,0,-1), 1, 0.00001,0.000000001,100,10,SoftShadow),
                new AreaRayonLight(new Color(238, 0, 0), //
                        new Point3D(340,100,1100), new Vector(-0.3,0,-1), 1, 0.00001,0.000000001,100,10,SoftShadow)
        );

        ImageWriter imageWriter = new ImageWriter("SceneFinal", 1000, 700, 2000, 1400);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

}
