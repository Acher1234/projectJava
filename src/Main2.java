import elements.*;
import geometries.*;
import primitives.*;
import static java.lang.System.out;
import static primitives.Util.*;

import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import unittests.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Main.
 */
public final class Main2 {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        boolean SoftShadow=true,precision=false;
        Scene scene = new Scene("Scene final");
        scene.setCamera(new Camera(new Point3D(500, 1200, 100), new Vector(0, -1, 0), new Vector(0, 0, 1),precision));
        scene.setDistance(900);
        scene.setBackground(new Color(222,184,135));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        List<Contenair> contenairsGeometrie = new ArrayList<Contenair>();
        //contenair of the floor
        contenairsGeometrie.add(new CubeContenair(new Point3D(1110,0,12),new Point3D(-110,0,12),
                new Point3D(-110,0,-25),new Point3D(1110,0,-25),
                new Point3D(1110,310,-25),new Point3D(-110,310,-25),
                new Point3D(-110,310,12),new Point3D(1110,310,12)));
        contenairsGeometrie.get(0).setGeometry(new rectangle(new Color(40,10,0), new Material(1, 0.5, 50),
                new Point3D(-100, 0, 10), new Point3D(1100, 0, 10), new Point3D(1100, 300, -22),new Point3D(-100, 300, -22)));

        //contenair of the mirror
        /*contenairsGeometrie.add(new CubeContenair(new Point3D(1110,-3,530),new Point3D(-110,-3,530),
                new Point3D(-110,-3,9),new Point3D(1110,-3,9),
                new Point3D(1110,3,9),new Point3D(-110,3,9),
                new Point3D(-110,3,530),new Point3D(1110,3,530)));
        contenairsGeometrie.get(1).setGeometry(new rectangle(new Color(50,50,50), new Material(0, 0, 30,0,1),
                new Point3D(1100, 0, 500), new Point3D(-100,0,500), new Point3D(-100, 0, 10),new Point3D(1100, 0, 10)));*/

        //pyramide and sphere inside
        /*contenairsGeometrie.add(new CubeContenair(new Point3D(750,100,-20),new Point3D(400,100,-20),
                new Point3D(400,100,-170),new Point3D(750,100,-170),
                new Point3D(750,500,-170),new Point3D(400,500,-170),
                new Point3D(400,500,-20),new Point3D(750,500,-20)));
        contenairsGeometrie.get(2).setGeometry(new PyramideBasedRectangle(new Color(20,20,20),new Color(20,20,20),new Color(20,20,20),
                        new Color(20,20,20),new Color(70,70,70)
                        ,new Material(0,0,0,0,0),new Material(0,0,0,0,0.2),
                        new Material(0,0,0,1,0.2),new Material(0,0,0,0,0.2),new Material(0,0,0,0,1),
                        new Point3D(750,500,-170), new Point3D(750,100,-170),new Point3D(400,100,-170),new Point3D(400,500,-170),new Point3D(600,300,-20)),
                new Sphere(new Color(0,0,100),new Material(0.5,1,20,0.5,0),60,new Point3D(600, 300, -110)),
                new Sphere(new Color(100,20,20),new Material(0.5,1,20,0,0.5),30,new Point3D(600, 300, -110)));*/
        //left personn
        contenairsGeometrie.add(new CubeContenair(new Point3D(990,90,130),new Point3D(810,90,130),
                new Point3D(810,90,-12),new Point3D(990,90,-12),
                new Point3D(990,145,-12),new Point3D(810,145,-12),
                new Point3D(810,145,130),new Point3D(990,145,130)));
        contenairsGeometrie.get(1).setGeometry(new Sphere(new Color(100,100,100),new Material(0,0,30,0,0),20,new Point3D(900,100,110)),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),20,new Ray(new Point3D(900,100,60),new Vector(0,0,1)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(940,100,70),new Vector(1,0,-0.4)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(860,100,70),new Vector(-1,0,-0.4)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(910,100,20),new Vector(0,0.2,1)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(890,100,20),new Vector(0,0,1)),30));
        //right personne  and his ball
        contenairsGeometrie.add(new CubeContenair(new Point3D(200,70,130),new Point3D(50,70,130),
                new Point3D(50,70,-12),new Point3D(200,70,-12),
                new Point3D(200,220,-12),new Point3D(50,220,-12),
                new Point3D(50,220,130),new Point3D(200,220,130)));
        contenairsGeometrie.get(2).setGeometry(new Sphere(new Color(100,100,100),new Material(0,0,30,0,0),20,new Point3D(120,200,10)),
                new Sphere(new Color(100,100,100),new Material(0,0,30,0,0),20,new Point3D(100,100,110)),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),20,new Ray(new Point3D(100,100,60),new Vector(0,0,1)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(140,100,70),new Vector(0.2,0,-0.4)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(60,100,70),new Vector(-0.2,0,-0.4)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(110,100,20),new Vector(0,0,1)),30),
                new Cylinder(new Color(100,100,100),new Material(0,0,30,0,0),5,new Ray(new Point3D(90,100,20),new Vector(0,0.5,1)),30));
        //lumiere
        /*contenairsGeometrie.add(new CubeContenair(new Point3D(590,-80,490),new Point3D(410,-80,490),
                new Point3D(410,-80,310),new Point3D(590,-80,310),
                new Point3D(590,100,310),new Point3D(410,100,310),
                new Point3D(410,100,490),new Point3D(590,100,490)));
        contenairsGeometrie.get(5).setGeometry(new Sphere(new Color(100,100,100),new Material(0,0,30,1,0),80,new Point3D(500,10,400)));*/


        scene.addGeometries(contenairsGeometrie);
        scene.addGeometries(
                //rideau
                new rectangle(new Color(255,0,0), new Material(0, 0, 30,0,0),
                        new Point3D(1300, 3, 600), new Point3D(1300, 3, 480), new Point3D(-500, 3, 480),new Point3D(-500, 3, 600)),
                new Sphere(new Color(255,0,0),new Material(0,0,50,0,0),250,new Point3D(1150, 3, 550)),
                new Sphere(new Color(255,0,0),new Material(0,0,50,0,0),250,new Point3D(-230, 3, 550)),
                new Cylinder(new Color(255,0,0),new Material(0,0,30,0,0),35,new Ray(new Point3D(1135,3,135),new Vector(0,0,0.1)),180),
                new Cylinder(new Color(255,0,0),new Material(0,0,30,0,0),35,new Ray(new Point3D(-130,3,135),new Vector(0,0,0.11)),180)
               //Lumiere
        );

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
