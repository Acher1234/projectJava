package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class ThreadRunnable implements Runnable
{
    Render render;
    ImageWriter _imagewriter;
    Scene _scene;
    int j,i;

    public ThreadRunnable(Render render, ImageWriter _imagewriter, Scene _scene, int j, int i) {
        this.render = render;
        this._imagewriter = _imagewriter;
        this._scene = _scene;
        this.j = j;
        this.i = i;
    }

    @Override
    public void run()
    {
        List<Ray> rayList = _scene.get_camera().constructRayThroughPixel(_imagewriter.getNx(),
                _imagewriter.getNy(),j,i,_scene.get_distance(),_imagewriter.getWidth(),_imagewriter.getHeight());
        List<Intersectable.GeoPoint> intersectionsPoint;
        int scalableColor =  rayList.size();
        primitives.Color returnColor = new primitives.Color(0,0,0);
        for (Ray ray:rayList)
        {

            intersectionsPoint = render.getSceneRayIntersections(ray);
            if(intersectionsPoint == null)
            {
                returnColor = returnColor.add(_scene.get_background());
            }
            else
            {
                Intersectable.GeoPoint closestPoint = render.getClosestPoint(intersectionsPoint);
                returnColor = returnColor.add(render.calcColor(closestPoint,ray));
            }
        }
        returnColor = returnColor.reduce(scalableColor);
        _imagewriter.writePixel(j,i,returnColor.getColor());
    }
}
