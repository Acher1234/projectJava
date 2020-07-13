package elements;

import primitives.Color;

/**
 * class Ambiant light
 */
public class AmbientLight {
    /**
     * parameters
     * the color intensity
     * and the double k 
     */
    Color _intensity;
    double k;

    //----------------Constructor
    public AmbientLight(Color baseColor,double kIntensity)
    {
        k = kIntensity;
        _intensity = baseColor;
    }


    //-------GET/SET----------
    public Color get_intensity() {
        return _intensity;
    }
}
