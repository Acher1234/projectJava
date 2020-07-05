package elements;

import primitives.Color;

/**
 * The type Ambient light.
 */
public class AmbientLight extends Light {

    /**
     * The K.
     */
    double k;

    //----------------Mathode

    /**
     * Instantiates a new Ambient light.
     *
     * @param baseColor  the base color
     * @param kIntensity the k intensity
     */
    public AmbientLight(Color baseColor, double kIntensity) {
        super(new Color(baseColor.getColor().getRed() * kIntensity, baseColor.getColor().getGreen() * kIntensity, baseColor.getColor().getBlue() * kIntensity));
        k = kIntensity;
    }


    @Override
    public Color get_intensity() {
        return _intensity;
    }

    /**
     * Gets k.
     *
     * @return the k
     */
    public double getK() {
        return k;
    }
}


