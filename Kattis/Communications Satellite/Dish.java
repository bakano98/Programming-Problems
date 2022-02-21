public class Dish {
    int x;
    int y;
    int r;
    
    public Dish(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getR() { return r; }

    public double distanceTo(Dish b) {
        return Math.hypot(this.getX() - b.getX(), this.getY() - b.getY()) - this.getR() - b.getR();
    }
}
