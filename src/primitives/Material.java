package primitives;

/**
 * The type Material.
 */
public class Material {
    /**
     * The K d.
     */
    protected double _kD;
    /**
     * The K s.
     */
    protected double _kS;
    /**
     * The K t.
     */
    protected double _kT;
    /**
     * The K r.
     */
    protected double _kR;

    /**
     * The N shininess.
     */
    int _nShininess;
//---------------Construcutor---------


    /**
     * Instantiates a new Material.
     *
     * @param _kD         the k d
     * @param _kS         the k s
     * @param _nShininess the n shininess
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        _kT = 0;
        _kR = 0;
        this._nShininess = _nShininess;
    }

    /**
     * Instantiates a new Material.
     *
     * @param _kD         the k d
     * @param _kS         the k s
     * @param _nShininess the n shininess
     * @param _kT         the k t
     * @param _kR         the k r
     */
    public Material(double _kD, double _kS, int _nShininess ,double _kT, double _kR) {
        this._kD = _kD;
        this._kS = _kS;
        this._kT = _kT;
        this._kR = _kR;
        this._nShininess = _nShininess;
    }
    //----GET/SET--------------------------

    /**
     * gets Kd.
     *
     * @return k d
     */
    public double get_kD() {
        return _kD;
    }

    /**
     * gets Ks.
     *
     * @return k s
     */
    public double get_kS() {
        return _kS;
    }

    /**
     * gets nShinisess
     *
     * @return n shininess
     */
    public int get_nShininess() {
        return _nShininess;
    }

    /**
     * Gets k t.
     *
     * @return the k t
     */
    public double get_kT() {
        return _kT;
    }

    /**
     * Gets k r.
     *
     * @return the k r
     */
    public double get_kR() {
        return _kR;
    }
}
