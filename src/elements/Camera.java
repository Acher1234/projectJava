package elements;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Camera.
 */
public class Camera {
    /**
     * The Origins.
     */
//----------------Variable----------------
    protected Point3D Origins;
    /**
     * The Vup.
     */
    protected Vector Vup;
    /**
     * The Vto.
     */
    protected Vector Vto;
    /**
     * The Vright.
     */
    protected Vector Vright;

    boolean SuperSampling;

    static int numberPointsSuperSampling = 50;
    //--------------Methode--------

    /**
     * Instantiates a new Camera.
     *
     * @param origins the origins
     * @param vup     the vup
     * @param vto     the vto
     */
    public Camera(Point3D origins, Vector vto, Vector vup) {
        if(vup.dotProduct(vto) != 0)
        {
            throw new IllegalArgumentException("bad Vector for camera");
        }
        Origins = origins;
        Vup = vup.normalize();
        Vto = vto.normalize();
        Vright = vto.crossProduct(vup).normalize();
        SuperSampling = false;
    }
    public Camera(Point3D origins, Vector vto, Vector vup,boolean superSampling)
    {
        this(origins,vto,vup);
        SuperSampling = superSampling;
    }

    /**
     * Construct ray through pixel ray.
     *
     * @param nX             the n x
     * @param nY             the n y
     * @param j              the j
     * @param i              the
     * @param screenDistance the screen distance
     * @param screenWidth    the screen width
     * @param screenHeight   the screen height
     * @return the ray
     */
    public List<Ray> constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight)
    {
        Random r = new Random();
        int numberOnXAndY = (SuperSampling == true ? numberPointsSuperSampling : 0);
        List<Ray> returnList= new ArrayList<Ray>();
        double sizeBetweenPixelWidht = (screenWidth / nX) / (numberOnXAndY+1);
        double sizeBetweenPixelHeight = (screenHeight / nY) / (numberOnXAndY+1);
        Point3D Pc = this.Origins.Add((this.Vto.scale(screenDistance)));
        Point3D Result = new Point3D(Pc);
        double ToScaleJ = (j - ((double)(nX - 1)) / 2) * (screenWidth / nX);
        double ToScaleI = (i - ((double)(nY - 1)) / 2) * (screenHeight / nY);
        if(ToScaleJ != 0)
        {
            Result =  Result.Add((this.Vright.scale(ToScaleJ)));
        }
        if(ToScaleI != 0)
        {
            Result = Result.Add(this.Vup.scale(-ToScaleI));
        }
        for (int number = (-numberOnXAndY);number <= numberOnXAndY;number++)
        {
            if(number == 0)
            {
                returnList.add(new Ray(Result.subtract(Origins),Origins));
                continue;
            }
            Point3D ResultToADD = Result.Add(getVup().scale(sizeBetweenPixelHeight*(r.nextInt(numberOnXAndY+1)+1)));//add 1 to avoid 0
            ResultToADD = ResultToADD.Add(getVright().scale(sizeBetweenPixelWidht*(r.nextInt(numberOnXAndY+1)+1)));
            returnList.add(new Ray(ResultToADD.subtract(Origins),Origins));
        }
        return returnList;
    }
    //-------------GET--------


    /**
     * Gets origins.
     *
     * @return the origins
     */
    public Point3D getOrigins() {
        return Origins;
    }

    /**
     * Gets vup.
     *
     * @return the vup
     */
    public Vector getVup() {
        return Vup;
    }

    /**
     * Gets vto.
     *
     * @return the vto
     */
    public Vector getVto() {
        return Vto;
    }

    /**
     * Gets vright.
     *
     * @return the vright
     */
    public Vector getVright() {
        return Vright;
    }
}
