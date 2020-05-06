package renderer;

import geometries.Geometries;
import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class render
{
    Scene _scene;
    ImageWriter _imagewriter;


    //---------Methodes-------

    public render getRenderFromXML()
    {

    }
    public render()
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
                    Point3D closestPoint = getCloses(intersectionsPoint);
                    _imagewriter.writePixel(j,i,this.caclColor(closestPoint));
                }
            }
        }
    }

    private List<Point3D> getSceneRayIntersections(Ray ray)
    {
        List<Point3D> returnList = new ArrayList<Point3D>();
        List<Point3D> tempList = new ArrayList<Point3D>();
        Intersectable tempInter;
        for (Geometries temp:_scene.get_geometries())
        {
            tempInter = (Intersectable)temp;
            tempList = tempInter.findIntersection(ray);
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
    public Point3D getCloses(List<Point3D> points)
    {

    }
    public void printGrid(int interval,java.awt.Color color)
    {
        for(int i=0;i<_imagewriter.getNx();i++)
        {
            for (int j = 0; j < _imagewriter.getNy(); j++)
            {
                if (i % interval == 0)
                {
                    _imagewriter.writePixel(i, j, new java.awt.Color(255, 0, 0));
                }
                else if (j % interval == 0)
                {
                    _imagewriter.writePixel(i, j, new Color(255, 0, 0));
                }
            }
        }
    }
}
