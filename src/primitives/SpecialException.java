package primitives;

/**
 * The type Special exception.
 */
public class SpecialException extends Exception
{
    /**
     * Instantiates a new Special exception.
     *
     * @param message the message
     */
    public SpecialException(String message)
    {
        super(message);
    }

    /**
     * Special exception vector 0 special exception.
     *
     * @return the special exception
     */
    public static SpecialException SpecialExceptionVector0()
    {
        return new SpecialException("creation of vector 0");
    }
}
