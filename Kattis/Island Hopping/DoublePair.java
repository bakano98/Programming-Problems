public class DoublePair {
    double x; // x/horizontal coordinate
    double y; // y/vertical coordinate

    public DoublePair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }

    // nothing to compare to, but might need later
    /*
    public double compareTo(IntegerPair b) {
        this.x.compareTo(b.getX());
    }
    */
}
