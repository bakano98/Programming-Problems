public class IntegerPair {
    Integer x; // x/horizontal coordinate
    Integer y; // y/vertical coordinate

    public IntegerPair(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    // nothing to compare to, but might need later
    /*
     * public Integer compareTo(IntegerPair b) { this.x.compareTo(b.getX()); }
     */
}
