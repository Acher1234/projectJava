package elements;

import primitives.Color;

public class Light {
    protected Color _intensity;

    /**
     * Instantiates a new Light.
     * @param _intensity
     */
    public Light(Color _intensity) {
        this._intensity = _intensity;
    }

    /**
     * gets intensity.
     * @return
     */
    public Color get_intensity() {
        return _intensity;
    }
}
