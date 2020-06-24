package renderer;

import geometries.Intersectable;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class ThreadRayIntersections implements Runnable {

    private List<Ray> ListOfRayToTest;
    Scene _scene;
    ImageWriter _imagewriter;
    Render render;
    int j,i;
    ThreadRayIntersections(List<Ray> List, Scene scene,ImageWriter imageWriter,int j,int i,Render render)
    {
        ListOfRayToTest = List;
        _scene = scene;
        _imagewriter = imageWriter;
        this.render = render;
        this.j = j;
        this.i = i;
    }
    @Override
    public void run() {

    }
}
