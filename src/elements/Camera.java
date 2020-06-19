package elements;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        int numberOnXAndY = 3;
        int scaleloop = (numberOnXAndY/2);
        int NearIndex = 1;
        List<Ray> returnList= new ArrayList<Ray>();
        double sizeBetweenPixelWidht = screenWidth / nX /2;
        double sizeBetweenPixelHeight = screenHeight / nY /2;
        for (double loop = 0;loop<numberOnXAndY;loop++ )
        {
            Point3D Pc = this.Origins.Add((this.Vto.scale(screenDistance)));
            Point3D ResultOnX = new Point3D(Pc);
            Point3D ResultOnY = new Point3D(Pc);
            double ToScaleJOnX = ((j + ((loop-scaleloop)*(screenWidth / nX)/(numberOnXAndY+NearIndex)) - (((double)(nX - 1)) / 2) * (screenWidth / nX)));
            double ToScaleIOnX = (i - (((double)(nY - 1)) / 2)  * (screenHeight / nY));
            double ToScaleJOnY = (j - (((double)(nX - 1)) / 2) * (screenWidth / nX));
            double ToScaleIOnY = ((i + ((loop-scaleloop)*(screenHeight / nY)/(numberOnXAndY+NearIndex)))- (((double)(nY - 1)) / 2)  * (screenHeight / nY));
            if(ToScaleJOnX != 0)
            {
                ResultOnX =  ResultOnX.Add((this.Vright.scale(ToScaleJOnX)));
            }
            if(ToScaleIOnX != 0)
            {
                ResultOnX = ResultOnX.Add(this.Vup.scale(-ToScaleIOnX));
            }
            if(ToScaleJOnY != 0)
            {
                ResultOnY =  ResultOnY.Add((this.Vright.scale(ToScaleJOnY)));
            }
            if(ToScaleIOnY != 0)
            {
                ResultOnY = ResultOnY.Add(this.Vup.scale(-ToScaleIOnY));
            }
            returnList.add(new Ray(ResultOnX.subtract(this.Origins),this.Origins));
            returnList.add(new Ray(ResultOnY.subtract(this.Origins),this.Origins));
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
