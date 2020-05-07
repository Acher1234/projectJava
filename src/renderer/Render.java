package renderer;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Intersectable;
import geometries.Triangle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Render
{
    Scene _scene;
    ImageWriter _imagewriter;


    //---------Methodes-------

    public static Render getRenderFromXML(String PathFile)
    {
        Render newRender = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            File fileXML = new File(PathFile);
            Document xml = builder.parse(fileXML);
            Element RootNode = xml.getDocumentElement();
            String Background = RootNode.getAttribute("background-color");
            int []backGroundRGB = StringToInt(Background,3);
            String Distance = RootNode.getAttribute("screen-distance");
            int intDistance = Integer.parseInt(Distance);
            List<Geometry> ListOfGeometries = new ArrayList<Geometry>();
//-----------------Image Node ---------
            Element SceneElement = (Element) RootNode.getElementsByTagName("image").item(0);
            String screenWidht = SceneElement.getAttribute("screen-width");
            int screenWidhtInt = Integer.parseInt(screenWidht);
            String screenHeight = SceneElement.getAttribute("screen-height");
            int screenHeightInt = Integer.parseInt(screenHeight);
            String NxString = SceneElement.getAttribute("Nx");
            int Nxint = Integer.parseInt(screenWidht);
            String NyString = SceneElement.getAttribute("Ny");
            int Nyint = Integer.parseInt(NyString);
//--------------------camera-----------------
            Element CameraElement = (Element) RootNode.getElementsByTagName("camera").item(0);
            String tempString = CameraElement.getAttribute("P0");
            int[] tempNumber= StringToInt(tempString,3);
            Point3D P0 = new Point3D(tempNumber[0],tempNumber[1],tempNumber[2]);
            tempString = CameraElement.getAttribute("Vto");
            tempNumber = StringToInt(tempString,3);
            Vector VTO = new Vector(tempNumber[0],tempNumber[1],tempNumber[2]);
            tempString = CameraElement.getAttribute("Vup");
            tempNumber = StringToInt(tempString,3);
            Vector VUP = new Vector(tempNumber[0],tempNumber[1],tempNumber[2]);
            Camera SceneCam = new Camera(P0,VTO,VUP);
//--------------------Abiant light--------------
            int[] RGBAmbiantLight = StringToInt(((Element)RootNode.getElementsByTagName("ambient-light").item(0)).getAttribute("color"),3);
//--------------------Geometrie---------------
            Element Geometry = (Element) RootNode.getElementsByTagName("geometries").item(0);
            //Triangle Recuperation
            Element TriangleTemp;
            List<Point3D> tempPoint = new ArrayList<Point3D>();
            int[] tempInt;
            NodeList test= Geometry.getElementsByTagName("triangle");
            for (int i = 0; i < test.getLength(); i++)
            {
                tempPoint.clear();
                TriangleTemp = (Element)test.item(i);
                tempString = TriangleTemp.getAttribute("p0");
                tempInt = StringToInt(tempString,3);
                tempPoint.add(new Point3D(tempInt[0],tempInt[1],tempInt[2]));
                tempString = TriangleTemp.getAttribute("p1");
                tempInt = StringToInt(tempString,3);
                tempPoint.add(new Point3D(tempInt[0],tempInt[1],tempInt[2]));
                tempString = TriangleTemp.getAttribute("p2");
                tempInt = StringToInt(tempString,3);
                tempPoint.add(new Point3D(tempInt[0],tempInt[1],tempInt[2]));
                ListOfGeometries.add(new Triangle(tempPoint.get(0),tempPoint.get(1),tempPoint.get(2)));
            }
            //Sphere Recuperation
            Element SphereTemp;
            double Radius;
            test= Geometry.getElementsByTagName("sphere");
            for (int i = 0; i < test.getLength(); i++)
            {
                tempPoint.clear();
                SphereTemp = (Element)test.item(i);
                tempString = SphereTemp.getAttribute("center");
                tempInt = StringToInt(tempString,3);
                tempPoint.add(new Point3D(tempInt[0],tempInt[1],tempInt[2]));
                Radius = Double.parseDouble(SphereTemp.getAttribute("radius"));
                ListOfGeometries.add(new Sphere(Radius,tempPoint.get(0)));
            }
            Scene scene = new Scene("XML scene");
            scene.setAmbientLight(new AmbientLight(new primitives.Color(RGBAmbiantLight[0],RGBAmbiantLight[1],RGBAmbiantLight[2]),1));
            scene.setBackground(new primitives.Color(backGroundRGB[0],backGroundRGB[1],backGroundRGB[2]));
            scene.setCamera(SceneCam);
            scene.setDistance(intDistance);
            for (Geometry temp:ListOfGeometries)
            {
                scene.addGeometries(temp);
            }
            ImageWriter image = new ImageWriter("XML Scene",screenWidhtInt,screenHeightInt,Nxint,Nyint);
            newRender = new Render(image,scene);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return newRender;
    }
    private static int[] StringToInt(String sequence,int number)
    {
        String[] splited = sequence.split(" ");
        if(splited.length != number)
        {
            throw  new IllegalArgumentException();
        }
        int[] returnTab = new int[number];
        for (int i = 0;i < number;i++ )
        {
            returnTab[i] = Integer.parseInt(splited[i]);
        }
        return returnTab;
    }
    public Render(ImageWriter Image, Scene scene)
    {
        _imagewriter = Image;
        _scene = scene;

    }

    private List<Point3D> getSceneRayIntersections(Ray ray)
    {
        List<Point3D> returnList = new ArrayList<Point3D>();
        List<Point3D> tempList;
        Intersectable tempInter;
        for (Geometry temp:_scene.get_geometries())
        {
            tempList = temp.findIntersection(ray);
            if(tempList != null)
            {
                for (Point3D tempPoint:tempList)
                {
                    returnList.add(tempPoint);
                }
            }
        }
        return returnList;
    }

    public Color caclColor(Point3D p)
    {

        return _scene.get_ambientLight().get_intensity().getColor() ;
    }
    public Point3D getClosestPoint(List<Point3D> points)
    {
        double distance = Double.MAX_VALUE;
        Point3D PO = _scene.get_camera().getOrigins();
        Point3D minDistancePoint = null;

        for (Point3D temp : points) {
            if (PO.Distance(temp) < distance)
            {
                minDistancePoint = new Point3D(temp);
                distance = PO.Distance(temp);
            }
        }
        return minDistancePoint;
    }
    public void printGrid(int interval,java.awt.Color color)
    {
        for(int i=0;i<_imagewriter.getNx();i++)
        {
            for (int j = 0; j < _imagewriter.getNy(); j++)
            {
                if (i % interval == 0)
                {
                    _imagewriter.writePixel(i, j, color);
                }
                else if (j % interval == 0)
                {
                    _imagewriter.writePixel(i, j, color);
                }
            }
        }
    }

    public void renderImage()
    {
        Ray ray;
        List<Point3D> intersectionsPoint;
        for(int j = 0;j < _imagewriter.getNx(); j++)
        {
            for(int i = 0;i < _imagewriter.getNy(); i++)
            {
                ray = _scene.get_camera().constructRayThroughPixel(_imagewriter.getNx(),
                        _imagewriter.getNy(),j,i,_scene.get_distance(),_imagewriter.getWidth(),_imagewriter.getHeight());
                intersectionsPoint = getSceneRayIntersections(ray);
                if(intersectionsPoint.isEmpty())
                {
                    _imagewriter.writePixel(j,i,_scene.get_backgroung().getColor());
                }
                else
                {
                    Point3D closestPoint = getClosestPoint(intersectionsPoint);
                    _imagewriter.writePixel(j,i,this.caclColor(closestPoint));
                }
            }
        }
    }

    public void writeToImage()
    {
        _imagewriter.writeToImage();
    }
}
