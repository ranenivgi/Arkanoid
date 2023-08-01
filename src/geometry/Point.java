// 208210708 Ranen Ivgi
package geometry;

import collision.EqualDoubles;

/**
 * The type Geometry.Point.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Instantiates a new Geometry.Point.
     *
     * @param x the point's x
     * @param y the point's y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The distance of this point to the other point.
     *
     * @param other the other point
     * @return the distance
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
    }

    /**
     * Returns true is the points are equal, false otherwise.
     *
     * @param other the other point
     * @return true or false
     */
    public boolean equals(Point other) {
        return EqualDoubles.doubleEquals(this.x, other.getX()) && EqualDoubles.doubleEquals(this.y, other.getY());
    }

    /**
     * Gets the point's x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the point's y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Round double to the closest int.
     *
     * @param num the number
     * @return the int
     */
    public int roundByHalf(double num) {
        return (int) (Math.round(num * 2) / 2.0);
    }

    /**
     * Gets the point's cast int x.
     *
     * @return the int x
     */
    public int getIntX() {
        return roundByHalf(this.x);
    }

    /**
     * Gets the point's cast int y.
     *
     * @return the int y
     */
    public int getIntY() {
        return roundByHalf(this.y);
    }
}