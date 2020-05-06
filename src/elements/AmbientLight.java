package elements;

import primitives.Color;

public class AmbientLight {
    Color _intensity;
    double k;

    //----------------Mathode
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
