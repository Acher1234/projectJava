package elements;

import primitives.Color;

public class Light {
    protected Color _intensity;
    protected  boolean SoftShadow;
    int nombrePointsGenerated;
    static int nombrePoint = 25;

    /**
     * Instantiates a new Light.
     * @param _intensity
     */
    public Light(Color _intensity) {
        this._intensity = _intensity;
        SoftShadow = false;
        nombrePointsGenerated = SoftShadow ? nombrePoint : 0;
    }

    public Light(Color _intensity,boolean softShadow) {
        this._intensity = _intensity;
        softShadow = softShadow;
        nombrePointsGenerated = softShadow ? nombrePoint : 0;
    }


    /**
     * gets intensity.
     * @return
     */
    public Color get_intensity() {
        return _intensity;
    }
}
