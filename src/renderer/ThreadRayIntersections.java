package renderer;

import geometries.Intersectable;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * The type Thread ray intersections.
 */
public class ThreadRayIntersections implements Runnable {

    private List<Ray> ListOfRayToTest;
    /**
     * The Scene.
     */
    Scene _scene;
    /**
     * The Imagewriter.
     */
    ImageWriter _imagewriter;
    /**
     * The Render.
     */
    Render render;
    /**
     * The J.
     */
    int j, /**
     * The .
     */
    i;

    /**
     * Instantiates a new Thread ray intersections.
     *
     * @param List        the list
     * @param scene       the scene
     * @param imageWriter the image writer
     * @param j           the j
     * @param i           the
     * @param render      the render
     */
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
