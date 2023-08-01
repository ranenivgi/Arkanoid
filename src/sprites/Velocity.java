// 208210708 Ranen Ivgi
package sprites;

import geometry.Point;

/**
 * The type sprites.Velocity.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Instantiates a new sprites.Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Changes the velocity from angle and speed to dx and dy.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle - 90);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Gets speed (a^2 +b^2 = c^2).
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt((dy * dy) + (dx * dx));
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the point
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}