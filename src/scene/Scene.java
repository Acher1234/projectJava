package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    String _name;
    Color _background;
    AmbientLight _ambientLight;
    List<Geometry> _geometries;
    Camera _camera;
    double _distance;
    List<LightSource> _lights = new LinkedList<LightSource>();

    /**
     * Instantiates a new addLights.
     * @param lights
     */
    public void addLights(LightSource... lights)
    {
        for (LightSource  temp:lights)
        {
            _lights.add(temp);
        }

    }

    /**
     * Instantiates a new Scene.
     * @param _name
     */
    public Scene(String _name) {
        this._name = _name;
        this._geometries =new ArrayList<Geometry>();

    }

    /**
     * Instantiates a new addGeometries.
     * @param geometries
     */
    public void addGeometries(Intersectable... geometries)
    {
        for (Intersectable temp :geometries)
        {
            _geometries.add((Geometry) temp);
        }
    }

    //------------------GET/SET---------------

    /**
     * gets Name.
     * @return
     */
    public String get_name() {
        return _name;
    }

    /**
     * gets Background
     * @return
     */
    public Color get_background() {
        return _background;
    }

    /**
     * gets AmbientLight
     * @return
     */
    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }


    /**
     * gets Geometries
     * @return
     */
    public List<Geometry> get_geometries() {
        return _geometries;
    }

    /**
     * gets camera
     * @return
     */
    public Camera get_camera() {
        return _camera;
    }

    /**
     * gets distance
     * @return
     */
    public double get_distance() {
        return _distance;
    }

    /**
     * sets background
     * @param _backgroung
     */
    public void setBackground(Color _backgroung) {
        this._background = _backgroung;
    }

    /**
     * sets ambientLight
     * @param _ambientLight
     */
    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     * sets Camera
     * @param _camera
     */
    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    /**
     * sets Distance
     * @param _distance
     */
    public void setDistance(double _distance) {
        this._distance = _distance;
    }

    /**
     * gets lights
     * @return
     */
    public List<LightSource> get_lights() {
        return _lights;
    }
}
