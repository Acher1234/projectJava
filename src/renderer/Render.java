package renderer;

import elements.LightSource;
import geometries.*;
import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Render
{
    private static final double DELTA = 0.1;
    Scene _scene;
    ImageWriter _imagewriter;
    private static final int MAX_CALC_COLOR_LEVEL = 5;
    private static final double MIN_CALC_COLOR_K = 0.0000001;


    //---------Methodes-------


    public Render(ImageWriter Image, Scene scene)
    {
        _imagewriter = Image;
        _scene = scene;

    }

    public List<Intersectable.GeoPoint> getSceneRayIntersections(Ray ray)
    {
        List<Intersectable.GeoPoint> returnList = new ArrayList<Intersectable.GeoPoint>();
        List<Intersectable.GeoPoint> tempList;
        for (Geometry temp:_scene.get_geometries())
        {
            tempList = temp.findIntersection(ray);
            if(tempList != null)
            {
                for (Intersectable.GeoPoint tempPoint:tempList)
                {
                    returnList.add(tempPoint);
                }
            }
        }
        return returnList.isEmpty() ? null : returnList;
    }

    protected boolean sign(double e)
    {
        return e >=0 ? true : false;
    }

    public primitives.Color calcColor(Intersectable.GeoPoint p,Ray inRay,int level,double k)
    {

        if (level == MAX_CALC_COLOR_LEVEL || p == null) return new primitives.Color(0, 0, 0);
        double dotProductNormalAndL,dotProductVAndR;
        Vector symetrieOfL,PointToCamera,normalToPoint;
        primitives.Color tempColor,Diffuse,Specular;
        primitives.Color TotalLight = p.geometry.get_emmission();
        List<LightSource> tempLights=_scene.get_lights();
        double KD = p.geometry.get_material().get_kD();
        double KS = p.geometry.get_material().get_kS();
        double nShininess = p.geometry.get_material().get_nShininess();
        TotalLight = getColorSpecandiffuse(p, k, TotalLight, tempLights, KD, KS, nShininess);
        //here diffuse

        //find good normal
        Vector Normal = p.geometry.getNormal(p.point);
        // reflected ray work
        Ray ReflectedRay = constructReflectedRay(Normal,p.point,inRay);
        Intersectable.GeoPoint ReflectedNewPoint = findCLosestIntersection(ReflectedRay);
        primitives.Color reflectedLight = null;
        double kkr = p.geometry.get_material().get_kR() * k;
        if(kkr > MIN_CALC_COLOR_K)
        {
            if(ReflectedNewPoint == null )
            {
                reflectedLight = new primitives.Color(0,0,0);
            }
            else
            {
                primitives.Color reflectedColor = calcColor(ReflectedNewPoint,ReflectedRay,level+1,kkr);
                reflectedLight= reflectedColor.scale(p.geometry.get_material().get_kR());
            }
            TotalLight = TotalLight.add(reflectedLight);
        }
        //refraction Ray
        Ray refractedRay = constructRefractedRay(Normal,p.point,inRay);
        Intersectable.GeoPoint refractedNewPoint = findCLosestIntersection(refractedRay);
        primitives.Color refractedLight = null;
        double kkt = p.geometry.get_material().get_kT();
        if( kkt  * k > MIN_CALC_COLOR_K)
        {
            if (refractedNewPoint == null) {
                refractedLight = new primitives.Color(0, 0, 0);
            } else {
                primitives.Color refractedColor = calcColor(refractedNewPoint, refractedRay, level + 1,kkt );
                refractedLight = refractedColor.scale(p.geometry.get_material().get_kT());
            }
            TotalLight = TotalLight.add(refractedLight);
        }
        return TotalLight;
    }

    private primitives.Color getColorSpecandiffuse(Intersectable.GeoPoint p, double k, primitives.Color totalLight, List<LightSource> tempLights, double KD, double KS, double nShininess) {
        primitives.Color Diffuse;
        primitives.Color Specular;
        primitives.Color tempColor;
        Vector normalToPoint;
        double dotProductNormalAndL;
        Vector symetrieOfL;
        Vector PointToCamera;
        double dotProductVAndR;
        for (LightSource temp:tempLights)
        {
            //test if the point is visibleby the camera if not you can skip
            if(sign(p.geometry.getNormal(p.point).dotProduct(temp.getmultipleL(p.point).get(0))) ==  sign(p.geometry.getNormal(p.point).dotProduct(_scene.get_camera().getVto())))
            {

                Diffuse = new primitives.Color(0, 0, 0);//to estimate the diffuse light
                Specular = new primitives.Color(0, 0, 0);//to estimate the specular light
                double ktr = transparency(temp.getmultipleL(p.point),p.geometry.getNormal(p.point),p,temp);//ici
                if(ktr*k > MIN_CALC_COLOR_K)
                {
                    tempColor = temp.getIntensity(p.point).scale(ktr);//for the shadow transparency
                    normalToPoint = p.geometry.getNormal(p.point);//calcul diffuse light
                    //diffuseLight
                    try {
                        dotProductNormalAndL = abs(temp.getL(p.point).dotProduct(normalToPoint));
                        Diffuse = Diffuse.add(tempColor.scale(dotProductNormalAndL * KD));
                    }catch (IllegalArgumentException e )
                    {
                        Diffuse = new primitives.Color(0,0,0);
                    }
                    //specular light
                    try {
                        symetrieOfL = (temp.getL(p.point)).subtract(normalToPoint.scale(2 * normalToPoint.dotProduct(temp.getL(p.point))));//calcul specular light
                        PointToCamera = p.point.subtract(_scene.get_camera().getOrigins()).normalized();
                        dotProductVAndR = abs(symetrieOfL.dotProduct(PointToCamera));
                        Specular = Specular.add(tempColor.scale(KS).scale(pow(dotProductVAndR, nShininess)));
                        totalLight = totalLight.add(Diffuse).add(Specular);
                    }catch (IllegalArgumentException e)
                    {
                        Specular = new primitives.Color(0,0,0);
                    }
                }
            }
        }
        return totalLight;
    }

    public primitives.Color calcColor(Intersectable.GeoPoint gp, Ray ray)
    {
        return calcColor(gp, ray, 0,1).add(_scene.get_ambientLight().get_intensity());
    }

    private Intersectable.GeoPoint findCLosestIntersection(Ray ray)
    {
        List<Intersectable.GeoPoint> Listtemp =  getSceneRayIntersections(ray) ;
        if(Listtemp == null)
        {
            return null;
        }
        return getClosestPointFromPoint(getSceneRayIntersections(ray),ray.getPOO());
    }

    public Intersectable.GeoPoint getClosestPoint(List<Intersectable.GeoPoint> points)
    {
        return getClosestPointFromPoint(points,_scene.get_camera().getOrigins());
    }

    public Intersectable.GeoPoint getClosestPointFromPoint(List<Intersectable.GeoPoint> points,Point3D Origin)
    {
        double distance = Double.MAX_VALUE;
        Point3D PO = Origin;
        Point3D minDistancePoint = null;
        Geometry returnGeo =null;

        for (Intersectable.GeoPoint temp : points) {
            if (PO.Distance(temp.point) < distance && PO.Distance(temp.point) != 0)
            {
                returnGeo = temp.geometry;
                minDistancePoint = new Point3D(temp.point);
                distance = PO.Distance(temp.point);
            }
        }
        if(points == null || returnGeo == null )return null;
        return new Intersectable.GeoPoint(returnGeo,minDistancePoint);
    }
    public void printGrid(int interval, Color color)
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

        List<Ray> rayList;
        double scalableColor;
        double numberOfpixel = 0;
        for(int j = 0;j < _imagewriter.getNx(); j++)
        {
            System.out.println(numberOfpixel/(_imagewriter.getNx()*_imagewriter.getNx())*100);
            for(int i = 0;i < _imagewriter.getNx(); i++)
            {
                numberOfpixel++;
                rayList = _scene.get_camera().constructRayThroughPixel(_imagewriter.getNx(),
                        _imagewriter.getNy(),j,i,_scene.get_distance(),_imagewriter.getWidth(),_imagewriter.getHeight());
                List<Intersectable.GeoPoint> intersectionsPoint;
                scalableColor =  rayList.size();
                primitives.Color returnColor = new primitives.Color(0,0,0);
                for (Ray ray:rayList)
                {
                    intersectionsPoint = getSceneRayIntersections(ray);
                    if(intersectionsPoint == null)
                    {
                        returnColor = returnColor.add(_scene.get_background());
                    }
                    else
                    {
                        Intersectable.GeoPoint closestPoint = getClosestPoint(intersectionsPoint);
                        returnColor = returnColor.add(calcColor(closestPoint,ray));
                    }
                }
                returnColor = returnColor.reduce(scalableColor);
                _imagewriter.writePixel(j,i,returnColor.getColor());
            }
        }
    }

    private double transparency(List<Vector> lightVectorArray, Vector normal, Intersectable.GeoPoint gp,LightSource light)
    {
        int numberofPoint = lightVectorArray.size();
        double kkrTotal = 0;
        for (Vector lightVector:lightVectorArray)
        {
            Vector inverse= lightVector.scale(-1);
            Ray Droite;
            Vector EPS = normal.scale(normal.dotProduct(inverse) > 0 ? DELTA : -DELTA);
            if(gp.geometry instanceof FlatGeometry)
            {
                Droite = new Ray(inverse, gp.point);
            }
            else
            {
                Droite = new Ray(inverse, gp.point.Add(EPS));
            }
            List<Intersectable.GeoPoint> tempList;
            double distance = light.getDistance(gp.point);
            double kkr = 1;
            for (Geometry temp:_scene.get_geometries())
            {
                tempList = temp.findIntersection(Droite,distance);
                if(tempList != null)
                {
                    for (Intersectable.GeoPoint tempGeopoint : tempList)
                    {
                        if(!tempGeopoint.point.equals(gp.point))
                            kkr*= tempGeopoint.geometry.get_material().get_kT();
                    }
                }
            }
            kkrTotal+=kkr;
        }
        return kkrTotal/numberofPoint;
    }

    public  Ray constructRefractedRay(Vector Normal,Point3D point,Ray inRay)
    {
        //double angleI = inRay.getDirection().normalized().dotProduct(Normal.normalized());
        //double angleR = inRay.getDirection().normalized().dotProduct(Normal.normalized());
        return new Ray(point,inRay.getDirection());//because all n == n
    }
    public  Ray constructReflectedRay(Vector Normal,Point3D point,Ray inRay)
    {
        Vector temp;
        try {
                temp = Normal.scale((2 * Normal.dotProduct(inRay.getDirection())));
        }catch (IllegalArgumentException e)
        {
             temp = inRay.getDirection().scale(-1);
        }
        Vector Reflacted = inRay.getDirection().subtract(temp);
        return new Ray(point,Reflacted);
    }

    public void writeToImage()
    {
        _imagewriter.writeToImage();
    }
}
