package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    String _name;
    Color _backgroung;
    AmbientLight _ambientLight;
    List<Geometries> _geometries;
    Camera _camera;
    double _distance;

    public Scene(String _name) {
        this._name = _name;
        this._geometries =new ArrayList<Geometries>();

    }
    public void addGeometries(Intersectable... geometries)
    {
        for (Intersectable temp :geometries)
        {
            if(temp instanceof Geometries)
            {
                _geometries.add((Geometries) temp);
            }
            else
            {
                throw  new IllegalArgumentException("bad intersectable not implement geometries");
            }
        }
    }

    //------------------GET/SET---------------

    public String get_name() {
        return _name;
    }

    public Color get_backgroung() {
        return _backgroung;
    }

    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }

    public List<Geometries> get_geometries() {
        return _geometries;
    }

    public Camera get_camera() {
        return _camera;
    }

    public double get_distance() {
        return _distance;
    }

    public void set_backgroung(Color _backgroung) {
        this._backgroung = _backgroung;
    }

    public void set_ambientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public void set_camera(Camera _camera) {
        this._camera = _camera;
    }

    public void set_distance(double _distance) {
        this._distance = _distance;
    }
}
