package geometries;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Math help.
 */
public class MathHelp  {
    /**
     * Second degree list.
     *
     * @param a the a
     * @param b the b
     * @param c the c
     * @return the list
     */
    public static List<Double>  SecondDegree(double a, double b, double c)
    {
        ArrayList<Double> result = null;
        double delta =( (b*b) - 4*a*c);
        if(delta > 0)
        {
            result = new ArrayList<Double>();
            result.add(((-b - Math.sqrt(delta))/(2*a)));
            result.add(((-b + Math.sqrt(delta))/(2*a)));
            return result;
        }
        else if(delta == 0)
        {
            result = new ArrayList<Double>();
            result.add((-b/(2*a)));
            return result;
        }
        return result;
    }
}
