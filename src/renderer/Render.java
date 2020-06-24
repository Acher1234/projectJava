package renderer;

import elements.Camera;
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
    private static final int MAX_CALC_COLOR_LEVEL = 3;
    private static final double MIN_CALC_COLOR_K = 0.001;

    //thread
    private int _threads = 1;
    private final int SPARE_THREADS = 2;
    private boolean _print = false;

    //---------Methodes-------

    /* public static Render getRenderFromXML(String PathFile)
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
    }*/

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
            if(sign(p.geometry.getNormal(p.point).dotProduct(temp.getL(p.point))) ==  sign(p.geometry.getNormal(p.point).dotProduct(_scene.get_camera().getVto())))
            {

                Diffuse = new primitives.Color(0, 0, 0);//to estimate the diffuse light
                Specular = new primitives.Color(0, 0, 0);//to estimate the specular light
                double ktr = transparency(temp.getmultipleL(p.point),p.geometry.getNormal(p.point),p,temp);//ici
                if(ktr*k > MIN_CALC_COLOR_K)
                {
                    tempColor = temp.getIntensity(p.point).scale(ktr);//for the shadow transparency
                    //diffuseLight
                    normalToPoint = p.geometry.getNormal(p.point);//calcul diffuse light
                    dotProductNormalAndL = abs(temp.getL(p.point).dotProduct(normalToPoint));
                    Diffuse = Diffuse.add(tempColor.scale(dotProductNormalAndL*KD));
                    //specular light
                    symetrieOfL = (temp.getL(p.point)).subtract(normalToPoint.scale(2 * normalToPoint.dotProduct(temp.getL(p.point))));//calcul specular light
                    PointToCamera = p.point.subtract(_scene.get_camera().getOrigins()).normalized();
                    dotProductVAndR = abs(symetrieOfL.dotProduct(PointToCamera));
                    Specular = Specular.add(tempColor.scale(KS).scale(pow(dotProductVAndR, nShininess)));
                    totalLight = totalLight.add(Diffuse).add(Specular);
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

    /*
    public void renderImage()
    {
        List<Ray> rayList;
        double scalableColor;
        for(int j = 0;j < _imagewriter.getNx(); j++)
        {
            for(int i = 0;i < _imagewriter.getNy(); i++)
            {
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

     */

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
        Vector temp = Normal.scale((2*Normal.dotProduct(inRay.getDirection())));
        Vector Reflacted = inRay.getDirection().subtract(temp);
        return new Ray(point,Reflacted);
    }

    /**
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     * @author Dan
     *
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         *
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print) System.out.printf("\r %02d%%", _percents);
        }

        /**
         * Default constructor for secondary Pixel objects
         */
        public Pixel() {
        }

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (percents > 0)
                if (Render.this._print) System.out.printf("\r %02d%%", percents);
            if (percents >= 0)
                return true;
            if (Render.this._print) System.out.printf("\r %02d%%", 100);
            return false;
        }
    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public void renderImage() {
        final int nX = _imagewriter.getNx();
        final int nY = _imagewriter.getNy();
        final double dist = _scene.get_distance();
        final double width = _imagewriter.getWidth();
        final double height = _imagewriter.getHeight();
        final Camera camera = _scene.get_camera();

        final Pixel thePixel = new Pixel(nY, nX);

        // Generate threads
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {
                    List<Ray> rays = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row,
                            dist, width, height);
                    for (Ray r: rays) {
                        Intersectable.GeoPoint closestPoint = findCLosestIntersection(r);
                        _imagewriter.writePixel(pixel.col, pixel.row, calcColor(closestPoint,r).getColor());
                    }
                }
            });
        }



        // Start threads
        for (Thread thread : threads) thread.start();

        // Wait for all threads to finish
        for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
        if (_print) System.out.printf("\r100%%\n");
    }



    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of coress less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
        if (threads != 0)
            _threads = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            if (cores <= 2)
                _threads = 1;
            else
                _threads = cores;
        }
        return this;
    }

    /**
     * Set debug printing on
     *
     * @return the Render object itself
     */
    public Render setDebugPrint() {
        _print = true;
        return this;
    }

    /*
    // Example for setting multithreading an debug print in test files:
        ......
		ImageWriter imageWriter = new ImageWriter("teapot", 200, 200, 800, 800);
		Render render = new Render(imageWriter, scene) //
				.setMultithreading(3) //
				.setDebugPrint();

		render.renderImage();
		// render.printGrid(50, java.awt.Color.YELLOW);
		render.writeToImage();
    */

    public void writeToImage()
    {
        _imagewriter.writeToImage();
    }
}
