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
    protected Point3D head;
    /**
     * The Degrees xy.
     */
    //protected float degreesXY;
    /**
     * The Degrees xz.
     */
    //protected float degreesXZ;


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
        return  Math.sqrt(NewX+NewY+NewZ);
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
        return (head.coordX.get()*temp.head.coordX.get() + head.coordY.get()*temp.head.coordY.get() +head.coordY.get()*temp.head.coordY.get());
    }

    /**
     * Lenght squared double.
     *
     * @return the double
     */
    public double lenghtSquared()
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
        double lenghtActual = this.lenght();
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
    /*float ReturnDegreesXY(double coodX, double coodY )
    {
        return 1;
    }*/

    /**
     * Return degrees xz float.
     *
     * @param coodX the cood x
     * @param coodZ the cood z
     * @return the float
     */
    /*float ReturnDegreesXZ(double coodX, double coodZ)
    {
        return 1;
    }*/

    /**
     * Set size degrees.
     */
    /*protected void SetSizeDegrees()

        this.degreesXY = ReturnDegreesXY(head.coordX,head.coordY);
        this.degreesXZ = ReturnDegreesXZ(head.coordX,head.coordZ);
    }*/

    /**
     * Zero test.
     *
     */
    protected void ZeroTest()
    {
        try {
            if ((head.coordX.get() == head.coordY.get()) && (head.coordY.get() == head.coordZ.get()) && (head.coordZ.get() == 0))
               throw new Exception("bad Number");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            head.setcoordX(1.0);
            head.setcoordY(1.0);
            head.setcoordZ(1.0);
        }
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
        //SetSizeDegrees();
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
        //SetSizeDegrees();
    }

    /**
     * Instantiates a new Vector.
     *
     * @param vector the vector
     */
    public Vector(Vector vector){
        head = new Point3D(vector.head);
        ZeroTest();
        //SetSizeDegrees();
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
        //SetSizeDegrees();
    }
    //***************GET/SET********************************


    /*
     * Gets degrees xy.
     *
     * @return the degrees xy
     */
    /*public float getDegreesXY() {
        return degreesXY;
    }*/


    /*
     * Gets degrees xz.
     *
     * @return the degrees xz
    public float getDegreesXZ() {
        return degreesXZ;
    }*/

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
        //SetSizeDegrees();
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
        //SetSizeDegrees();
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
       // SetSizeDegrees();
    }
}
