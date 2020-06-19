package primitives;

public class Material {
    protected double _kD;
    protected double _kS;
    int _nShininess;
//---------------Construcutor---------

    /**
     * Instantiates a new Material.
     * @param _kD
     * @param _kS
     * @param _nShininess
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }
//----GET/SET--------------------------

    /**
     * gets Kd.
     * @return
     */
    public double get_kD() {
        return _kD;
    }

    /**
     * gets Ks.
     * @return
     */
    public double get_kS() {
        return _kS;
    }

    /**
     * gets nShinisess
     * @return
     */
    public int get_nShininess() {
        return _nShininess;
    }
}
