package primitives;
import java.lang.Math;

/**
 * The type Vector.
 */
public class Vector
{
    /**
     * The Point.
     */
    protected Point3D point;
    /**
     * The Size.
     */
    protected double size;
    /**
     * The Degrees xy.
     */
    protected float degreesXY;
    /**
     * The Degrees xz.
     */
    protected float degreesXZ;

    /**
     * Return size double.
     *
     * @param X the x
     * @param Y the y
     * @param Z the z
     * @return the double
     */
//*************************METHODES*************************
    static double ReturnSize(double X,double Y,double Z)
    {
        double NewX =  Math.pow(X,2);
        double NewY = Math.pow(Y,2);
        double NewZ = Math.pow(Z,2);
        return  Math.sqrt(NewX+NewY+NewZ);
    }

    /**
     * Add vector.
     *
     * @param temp the temp
     * @return the vector
     * @throws SpecialException the special exception
     */
    public Vector add(Vector temp) throws SpecialException
    {
        return new Vector(point.Add(temp));
    }

    /**
     * Sub vector.
     *
     * @param temp the temp
     * @return the vector
     * @throws SpecialException the special exception
     */
    public Vector Sub(Vector temp) throws SpecialException
    {
        return point.Substract(temp.point);
    }

    /**
     * Cross product vector.
     *
     * @param temp the temp
     * @return the vector
     * @throws SpecialException the special exception
     */
    public Vector crossProduct(Vector temp) throws SpecialException {
        double tempX = (this.point.coordY * temp.point.coordZ) - (this.point.coordZ*temp.point.coordY);
        double tempY = (this.point.coordZ * temp.point.coordX) - (this.point.coordX*temp.point.coordZ);
        double tempZ = (this.point.coordX * temp.point.coordY) - (this.point.coordY*temp.point.coordX);
        return new Vector(tempX,tempY,tempZ);
    }

    /**
     * Scale vector.
     *
     * @param Scale the scale
     * @return the vector
     * @throws SpecialException the special exception
     */
    public Vector scale(double Scale)throws SpecialException
    {
        return new Vector(point.coordX*Scale,point.coordY*Scale,point.coordZ*Scale);
    }

    /**
     * Dot product double.
     *
     * @param temp the temp
     * @return the double
     */
    public double dotProduct(Vector temp)
    {
        return (point.coordX*temp.point.coordX + point.coordY*temp.point.coordY +point.coordY*temp.point.coordY);
    }

    /**
     * Lenght squared double.
     *
     * @return the double
     */
    public double lenghtSquared()
    {
        return point.DistanceSquare(new Point3D(0,0,0));
    }

    /**
     * Normalize vector.
     *
     * @return the vector
     * @throws SpecialException the special exception
     */
    public Vector normalize()throws SpecialException
    {
        double lenghtActual = this.lenght();
        this.point.coordX = point.coordX/lenghtActual;
        this.point.coordY = point.coordY/lenghtActual;
        this.point.coordZ = point.coordZ/lenghtActual;
        return this;
    }

    /**
     * Normalized vector.
     *
     * @return the vector
     * @throws SpecialException the special exception
     */
    public Vector normalized()throws SpecialException
    {
        return new Vector(this).normalize();
    }

    /**
     * Lenght double.
     *
     * @return the double
     */
    public double lenght()
    {
        return Math.sqrt(lenghtSquared());
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Return degrees xy float.
     *
     * @param coodX the cood x
     * @param coodY the cood y
     * @return the float
     */
    float ReturnDegreesXY(double coodX, double coodY )
    {
        return 1;
    }

    /**
     * Return degrees xz float.
     *
     * @param coodX the cood x
     * @param coodZ the cood z
     * @return the float
     */
    float ReturnDegreesXZ(double coodX, double coodZ)
    {
        return 1;
    }

    /**
     * Set size degrees.
     */
    protected void SetSizeDegrees()
    {
        this.size = ReturnSize(point.coordX,point.coordY,point.coordZ);
        this.degreesXY = ReturnDegreesXY(point.coordX,point.coordY);
        this.degreesXZ = ReturnDegreesXZ(point.coordX,point.coordZ);
    }

    /**
     * Zero test.
     *
     * @throws SpecialException the special exception
     */
    protected void ZeroTest() throws SpecialException
    {
        if((point.coordX == point.coordY) && (point.coordY == point.coordZ) && (point.coordZ == 0))
            throw  SpecialException.SpecialExceptionVector0();
    }

    /**
     * Instantiates a new Vector.
     *
     * @param coordX the coord x
     * @param coordY the coord y
     * @param coordZ the coord z
     * @throws SpecialException the special exception
     */
//*******************CONSTRUCTOR*******************************
    public Vector(double coordX, double coordY, double coordZ) throws SpecialException {
        point = new Point3D(coordX, coordY, coordZ);
        ZeroTest();
        SetSizeDegrees();
    }

    /**
     * Instantiates a new Vector.
     *
     * @param coordX the coord x
     * @param coordY the coord y
     * @param coordZ the coord z
     * @throws SpecialException the special exception
     */
    public Vector(Coordinate coordX, Coordinate coordY, Coordinate coordZ) throws SpecialException {
        point = new Point3D(coordX.get(), coordY.get(), coordZ.get());
        ZeroTest();
        SetSizeDegrees();
    }

    /**
     * Instantiates a new Vector.
     *
     * @param vector the vector
     * @throws SpecialException the special exception
     */
    public Vector(Vector vector) throws SpecialException {
        point = new Point3D(vector.point);
        ZeroTest();
        SetSizeDegrees();
    }

    /**
     * Instantiates a new Vector.
     *
     * @param point the point
     * @throws SpecialException the special exception
     */
    public Vector(Point3D point) throws SpecialException {
        point = new Point3D(point);
        ZeroTest();
        SetSizeDegrees();
    }
    //***************GET/SET********************************

    /**
     * Gets size.
     *
     * @return the size
     */
    public double getSize() {
        return size;
    }


    /**
     * Gets degrees xy.
     *
     * @return the degrees xy
     */
    public float getDegreesXY() {
        return degreesXY;
    }


    /**
     * Gets degrees xz.
     *
     * @return the degrees xz
     */
    public float getDegreesXZ() {
        return degreesXZ;
    }

    /**
     * Gets point.
     *
     * @return the point
     */
    public Point3D getPoint()
    {
        return point;
    }

    /**
     * Sets x.
     *
     * @param coordX the coord x
     * @throws SpecialException the special exception
     */
    public void setcoordX(double coordX) throws SpecialException
    {
        point.setcoordX(coordX);
        ZeroTest();
        SetSizeDegrees();
    }


    /**
     * Sets y.
     *
     * @param coordY the coord y
     * @throws SpecialException the special exception
     */
    public void setcoordY(double coordY) throws SpecialException
    {
        point.setcoordY(coordY);
        ZeroTest();
        SetSizeDegrees();
    }

    /**
     * Sets z.
     *
     * @param coordZ the coord z
     * @throws SpecialException the special exception
     */
    public void setcoordZ(double coordZ) throws SpecialException
    {
        point.setcoordZ(coordZ);
        ZeroTest();
        SetSizeDegrees();
    }

    /**
     * Sets z.
     *
     * @param point the point
     * @throws SpecialException the special exception
     */
    public void setcoordZ(Point3D point) throws SpecialException
    {
        this.point = point;
        ZeroTest();
        SetSizeDegrees();
    }
}
