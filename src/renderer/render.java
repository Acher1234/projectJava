package renderer;

import primitives.Point3D;
import scene.Scene;

import java.awt.*;
import java.util.List;

public class render
{
    Scene _scene;
    ImageWriter _imagewriter;


    //---------Methodes-------

    public render()
    {

    }
    public void caclColor(Point3D p)
    {

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
