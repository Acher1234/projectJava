package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Contenair;
import geometries.CubeContenair;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Scene.
 */
public class Scene {
    /**
     * The Name.
     */
    String _name;
    /**
     * The Background.
     */
    Color _background;
    /**
     * The Ambient light.
     */
    AmbientLight _ambientLight;
    /**
     * The Geometries.
     */
    List<Geometry> _geometries;
    /**
     * The Camera.
     */
    Camera _camera;
    /**
     * The Distance.
     */
    double _distance;
    /**
     * The Lights.
     */
    List<LightSource> _lights = new LinkedList<LightSource>();
    /**
     * The D box.
     */
    static double DBox = 20;

    /**
     * Instantiates a new addLights.
     *
     * @param lights the lights
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
     *
     * @param _name the name
     */
    public Scene(String _name) {
        this._name = _name;
        this._geometries =new ArrayList<Geometry>();

    }

    /**
     * Instantiates a new addGeometries.
     *
     * @param geometries the geometries
     */
    public void addGeometries(Intersectable... geometries)
    {
        for (Intersectable temp :geometries)
        {
            _geometries.add((Geometry) temp);
        }
    }

    /**
     * Add geometries.
     *
     * @param contenairs the contenairs
     */
    public void addGeometries(List<Contenair> contenairs)
    {
        for (Intersectable temp :contenairs)
        {
            _geometries.add((Geometry) temp);
        }
    }

    /**
     * Add geometries withhis contenair.
     *
     * @param geometries the geometries
     */
    public void addGeometriesWithhisContenair(Geometry... geometries)
    {
        Contenair tempContenair;
        boolean BoucleWhile = true;
        for (Geometry temp :geometries)
        {
            boolean madeInContenair = false;
            for (int i = 0; i < _geometries.size(); i++)
            {
               if(_geometries.get(i).canMergeBox(temp) && ((CubeContenair)_geometries.get(i)).geometries.size()<3)
               {
                   Geometry Toremove= _geometries.remove(i);
                   Contenair newContenair = createMergingBox(Toremove,temp);
                   for(Geometry InsideGeometry:((CubeContenair)Toremove).geometries)
                   {
                       newContenair.setGeometry(InsideGeometry);
                   }
                   newContenair.setGeometry(temp);
                   _geometries.add((Geometry) newContenair);
                   madeInContenair = true;
                   break;
               }
            }
            if(!madeInContenair)
            {
                tempContenair = new CubeContenair(temp.getMaxX()+DBox,temp.getMaxY()+DBox,temp.getMaxZ()+DBox,temp.getMinX()-DBox,temp.getMinY()-DBox,temp.getMinZ()-DBox);
                tempContenair.setGeometry(temp);
                _geometries.add((Geometry) tempContenair);
            }
        }
        while(BoucleWhile)
        {
            BoucleWhile = false;
            for (int i = 0; i < _geometries.size(); i++)
            {
                for (int j = i+1; j < _geometries.size(); j++)
                {
                    if(_geometries.get(i).canMergeBox(_geometries.get(j)))
                    {
                        BoucleWhile = true;
                        Geometry temp= _geometries.remove(i);
                        Geometry temp2= _geometries.remove(j-1);
                        Contenair ContenairTemp= createMergingBox(temp,temp2);
                        ContenairTemp.setGeometry(temp,temp2);
                        _geometries.add((Geometry) ContenairTemp);
                        break;
                    }
                }
                if(BoucleWhile)
                {
                    break;
                }
            }
        }
    }

    /**
     * Create merging box contenair.
     *
     * @param temp1 the temp 1
     * @param temp2 the temp 2
     * @return the contenair
     */
    public Contenair createMergingBox(Geometry temp1,Geometry temp2)
    {
        double MaxX,MaxY,MaxZ,MinX,MinY,MinZ,tempDouble1,tempDouble2;
        tempDouble1 = temp1.getMaxX();
        tempDouble2 = temp2.getMaxX();
        MaxX = tempDouble1 > tempDouble2 ? tempDouble1:tempDouble2;
        tempDouble1 = temp1.getMaxY();
        tempDouble2 = temp2.getMaxY();
        MaxY = tempDouble1 > tempDouble2 ? tempDouble1:tempDouble2;
        tempDouble1 = temp1.getMaxZ();
        tempDouble2 = temp2.getMaxZ();
        MaxZ = tempDouble1 > tempDouble2 ? tempDouble1:tempDouble2;
        tempDouble1 = temp1.getMinX();
        tempDouble2 = temp2.getMinX();
        MinX = tempDouble1 < tempDouble2 ? tempDouble1:tempDouble2;
        tempDouble1 = temp1.getMinY();
        tempDouble2 = temp2.getMinY();
        MinY = tempDouble1 < tempDouble2 ? tempDouble1:tempDouble2;
        tempDouble1 = temp1.getMinZ();
        tempDouble2 = temp2.getMinZ();
        MinZ = tempDouble1 < tempDouble2 ? tempDouble1:tempDouble2;
        return new CubeContenair(MaxX,MaxY,MaxZ,MinX,MinY,MinZ);
    }

    //------------------GET/SET---------------

    /**
     * gets Name.
     *
     * @return name
     */
    public String get_name() {
        return _name;
    }

    /**
     * gets Background
     *
     * @return background
     */
    public Color get_background() {
        return _background;
    }

    /**
     * gets AmbientLight
     *
     * @return ambient light
     */
    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }


    /**
     * gets Geometries
     *
     * @return geometries
     */
    public List<Geometry> get_geometries() {
        return _geometries;
    }

    /**
     * gets camera
     *
     * @return camera
     */
    public Camera get_camera() {
        return _camera;
    }

    /**
     * gets distance
     *
     * @return distance
     */
    public double get_distance() {
        return _distance;
    }

    /**
     * sets background
     *
     * @param _backgroung the backgroung
     */
    public void setBackground(Color _backgroung) {
        this._background = _backgroung;
    }

    /**
     * sets ambientLight
     *
     * @param _ambientLight the ambient light
     */
    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     * sets Camera
     *
     * @param _camera the camera
     */
    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    /**
     * sets Distance
     *
     * @param _distance the distance
     */
    public void setDistance(double _distance) {
        this._distance = _distance;
    }

    /**
     * gets lights
     *
     * @return lights
     */
    public List<LightSource> get_lights() {
        return _lights;
    }
}
