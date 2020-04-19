package primitives;
import java.lang.Math;

import static java.lang.Math.sqrt;
import static primitives.Util.isZero;

/**
 * The type Vector.
 */
public class Vector
{
    /**
     * The Point.
     */
    protected Point3D head;

    public Vector() {
        head = new Point3D(1,1,1);
    }

    //*************************METHODES*************************
    public int compareTo(Vector vector) // this function compares between 2 vectors
    {
        return this.head.compareTo(vector.head);
    }

    /**
     * Return size double.
     *
     * @param X the x
     * @param Y the y
     * @param Z the z
     * @return the double
     */
    static double ReturnSize(double X,double Y,double Z)
    {
        double NewX =  Math.pow(X,2);
        double NewY = Math.pow(Y,2);
        double NewZ = Math.pow(Z,2);
        return  sqrt(NewX+NewY+NewZ);
    }

    /**
     * Add vector.
     *
     * @param temp the temp
     * @return the vector
     */
    public Vector add(Vector temp)
    {
        return new Vector(head.Add(temp));
    }

    /**
     * Sub vector.
     *
     * @param temp the temp
     * @return the vector
     */
    public Vector subtract(Vector temp)
    {
        return new Vector(head.subtract(temp.head));
    }

    /**
     * Cross product vector.
     *
     * @param temp the temp
     * @return the vector
     */
    public Vector crossProduct(Vector temp) {
        double tempX = (this.head.coordY.get() * temp.head.coordZ.get()) - (this.head.coordZ.get()*temp.head.coordY.get());
        double tempY = (this.head.coordZ.get() * temp.head.coordX.get()) - (this.head.coordX.get()*temp.head.coordZ.get());
        double tempZ = (this.head.coordX.get() * temp.head.coordY.get()) - (this.head.coordY.get()*temp.head.coordX.get());
        if(tempX == tempY && tempY== tempZ && tempZ == 0)
        {
            return null;
        }
        return new Vector(tempX,tempY,tempZ);
    }

    /**
     * Scale vector.
     *
     * @param Scale the scale
     * @return the vector
     */
    public Vector scale(double Scale)
    {
        return new Vector(head.coordX.get()*Scale,head.coordY.get()*Scale,head.coordZ.get()*Scale);
    }

    /**
     * Dot product double.
     *
     * @param temp the temp
     * @return the double
     */
    public double dotProduct(Vector temp)
    {
        return (head.coordX.get()*temp.head.coordX.get() + head.coordY.get()*temp.head.coordY.get() +head.coordZ.get()*temp.head.coordZ.get());
    }

    /**
     * Lenght squared double.
     *
     * @return the double
     */
    public double lengthSquared()
    {
        return head.DistanceSquare(new Point3D(0,0,0));
    }

    /**
     * Normalize vector.
     *
     * @return the vector
     */
    public Vector normalize()
    {
        double lenghtActual = this.length();
        this.head.coordX = new Coordinate(head.coordX.get()/lenghtActual);
        this.head.coordY = new Coordinate(head.coordY.get()/lenghtActual);
        this.head.coordZ = new Coordinate(head.coordZ.get()/lenghtActual);
        return this;
    }

    /**
     * Normalized vector.
     *
     * @return the vector
     */
    public Vector normalized()
    {
        return new Vector(this).normalize();
    }

    /**
     * Lenght double.
     *
     * @return the double
     */
    public double length()
    {
        return sqrt(this.lengthSquared());
    }
    @Override
    public boolean equals(Object obj) {
        Vector test = (Vector)obj;
        if(getHead().coordX.get() != test.getHead().coordX.get())
            return false;
        if(getHead().coordY.get() != test.getHead().coordY.get())
            return false;
        if(getHead().coordZ.get() != test.getHead().coordZ.get())
            return false;
        return true;


    }


    /**
     * Zero test.
     *
     */
    protected void ZeroTest()
    {
        if ((head.coordX.get() == head.coordY.get()) && (head.coordY.get() == head.coordZ.get()) && (isZero(head.coordZ.get())))
            throw new IllegalArgumentException("Illegal Vector 0" + head.toString());

    }

    /**
     * Instantiates a new Vector.
     *
     * @param coordX the coord x
     * @param coordY the coord y
     * @param coordZ the coord z
     */
//*******************CONSTRUCTOR*******************************
    public Vector(double coordX, double coordY, double coordZ) {
        head = new Point3D(coordX, coordY, coordZ);
        ZeroTest();
    }

    /**
     * Instantiates a new Vector.
     *
     * @param coordX the coord x
     * @param coordY the coord y
     * @param coordZ the coord z
     */
    public Vector(Coordinate coordX, Coordinate coordY, Coordinate coordZ)  {
        head = new Point3D(coordX.get(), coordY.get(), coordZ.get());
        ZeroTest();
    }

    /**
     * Instantiates a new Vector.
     *
     * @param vector the vector
     */
    public Vector(Vector vector){
        head = new Point3D(vector.head);
        ZeroTest();
    }

    /**
     * Instantiates a new Vector.
     *
     * @param point the point
     */
    public Vector(Point3D point)  {
        head = new Point3D(point);
        ZeroTest();
        //SetSizeDegrees();
    }

    public Vector(Point3D point1,Point3D point2) {
        head = new Point3D((point1.subtract(point2)).getHead());
        ZeroTest();
    }
    //***************GET/SET********************************

    /**
     * Gets point head.
     *
     * @return the point
     */
    public Point3D getHead()
    {
        return head;
    }

    /**
     * Sets x.
     *
     * @param coordX the coord x
     */
    public void setcoordX(double coordX)
    {
        head.setcoordX(coordX);
        ZeroTest();
    }


    /**
     * Sets y.
     *
     * @param coordY the coord y
     */
    public void setcoordY(double coordY)
    {
        head.setcoordY(coordY);
        ZeroTest();
    }

    /**
     * Sets z.
     *
     * @param coordZ the coord z
     */
    public void setcoordZ(double coordZ)
    {
        head.setcoordZ(coordZ);
        ZeroTest();

    }

    /**
     * Sets z.
     *
     * @param point the point
     */
    public void setcoordZ(Point3D point)
    {
        this.head = point;
        ZeroTest();

    }

}
