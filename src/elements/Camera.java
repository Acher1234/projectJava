package elements;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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
    public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight)
    {
        Point3D Pc = this.Origins.Add((this.Vto.scale(screenDistance)));
        Point3D Result = new Point3D(Pc);
        Vector J = new Vector(),I = new Vector();
        double ToScaleJ = (j - ((double)(nX - 1)) / 2) * (screenWidth / nX);
        double ToScaleI = (i - ((double)(nY - 1)) / 2) * (screenHeight / nY);
        try {
            J = (this.Vright.scale(ToScaleJ));
        }catch (Exception e)
        {

        }
        try{
            I = (this.Vup.scale(-ToScaleI));
        }catch (Exception e)
        {

        }
        if(ToScaleJ != 0)
        {
           Result =  Result.Add(J);
        }
        if(ToScaleI != 0)
        {
            Result = Result.Add(I);
        }
        //System.out.println(new Ray(Result.subtract(this.Origins),Result));
        return new Ray(Result.subtract(this.Origins),this.Origins);
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
