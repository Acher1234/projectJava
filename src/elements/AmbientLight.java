package elements;

import primitives.Color;

public class AmbientLight extends Light {

    double k;

    //----------------Mathode

    /**
     * Instantiates a new AmbientLight.
     * @param baseColor
     * @param kIntensity
     */
    public AmbientLight(Color baseColor, double kIntensity) {
        super(new Color(baseColor.getColor().getRed() * kIntensity, baseColor.getColor().getGreen() * kIntensity, baseColor.getColor().getBlue() * kIntensity));
        k = kIntensity;
    }


    /**
     * gets Intensity
     * @return
     */
    @Override
    public Color get_intensity() {
        return _intensity;
    }

    /**
     * gets K.
     * @return
     */
    public double getK() {
        return k;
    }
}


