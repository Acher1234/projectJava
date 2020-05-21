package elements;

import primitives.Color;

public class AmbientLight extends Light {
    double k;

    //----------------Mathode
    public AmbientLight(Color baseColor, double kIntensity) {
        super(new Color(baseColor.getColor().getRed() * kIntensity, baseColor.getColor().getGreen() * kIntensity, baseColor.getColor().getBlue() * kIntensity));
        k = kIntensity;
    }

    @Override
    public Color get_intensity() {
        return _intensity;
    }

    public double getK() {
        return k;
    }
}


