package elements;

import primitives.Color;

/**
 * The type Light.
 */
public class Light {
    /**
     * The Intensity.
     */
    protected Color _intensity;
    /**
     * The Soft shadow.
     */
    protected  boolean SoftShadow;
    /**
     * The Nombre points generated.
     */
    int nombrePointsGenerated;
    /**
     * The Nombre point.
     */
    static int nombrePoint = 25;

    /**
     * Instantiates a new Light.
     *
     * @param _intensity the intensity
     */
    public Light(Color _intensity) {
        this._intensity = _intensity;
        SoftShadow = false;
        nombrePointsGenerated = SoftShadow ? nombrePoint : 0;
    }

    /**
     * Instantiates a new Light.
     *
     * @param _intensity the intensity
     * @param softShadow the soft shadow
     */
    public Light(Color _intensity,boolean softShadow) {
        this._intensity = _intensity;
        softShadow = softShadow;
        nombrePointsGenerated = softShadow ? nombrePoint : 0;
    }


    /**
     * Gets intensity.
     *
     * @return the intensity
     */
    public Color get_intensity() {
        return _intensity;
    }
}
