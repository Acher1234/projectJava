package primitives;

public class Material {
    /**
     * parameters
     */
    protected double _kD;
    protected double _kS;
    protected double _kT;
    protected double _kR;

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
        _kT = 0;
        _kR = 0;
        this._nShininess = _nShininess;
    }

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

    public double get_kT() {
        return _kT;
    }

    public double get_kR() {
        return _kR;
    }
}
