// 208210708 Ranen Ivgi
package collision;

import geometry.Point;
import geometry.Rectangle;

import java.util.List;

/**
 * The type collision.Line.
 */
public class Line {
    private final Point p1;
    private final Point p2;

    /**
     * Instantiates a new collision.Line.
     *
     * @param start the start point
     * @param end   the end point
     */
    public Line(Point start, Point end) {
        this.p1 = new Point(start.getX(), start.getY());
        this.p2 = new Point(end.getX(), end.getY());
    }

    /**
     * Instantiates a new collision.Line.
     *
     * @param x1 the x1
     * @param y1 the y1
     * @param x2 the x2
     * @param y2 the y2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Calculates the length of the line.
     *
     * @return the length
     */
    public double length() {
        return p1.distance(p2);
    }

    /**
     * Calculates the middle point of the line.
     *
     * @return the point
     */
    public Point middle() {
        return new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the point
     */
    public Point start() {
        return this.p1;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the point
     */
    public Point end() {
        return this.p2;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line
     * @return true if they intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // Both segments are vertical
        if (EqualDoubles.doubleEquals(this.start().getX(), this.end().getX())
                && EqualDoubles.doubleEquals(other.start().getX(), other.end().getX())) {
            return checkBothVertical(this, other);
        }
        // Only first line is vertical. checks if second line cross it.
        if (EqualDoubles.doubleEquals(this.start().getX(), this.end().getX())) {
            return checkOneVertical(other, this);
        }
        // Only second line is vertical. checks if first line cross it.
        if (EqualDoubles.doubleEquals(other.start().getX(), other.end().getX())) {
            return checkOneVertical(this, other);
        }
        // find first line's slope and b (in the equation y = mx + b)
        double firstSlope = (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX());
        double bFirst = this.start().getY() - firstSlope * this.start().getX();

        // find second line's slope and b (in the equation y = mx + b)
        double secondSlope = (other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX());
        double bSecond = other.start().getY() - secondSlope * other.start().getX();

        // check if the lines a parallel and return true if they intersect
        if (EqualDoubles.doubleEquals(firstSlope, secondSlope)) {
            // parallel lines
            return EqualDoubles.doubleEquals(bFirst, bSecond);
        }

        // not parallel not vertical lines intersect at x, check if x is part of both segments
        double x = (bSecond - bFirst) / (firstSlope - secondSlope);
        return (x >= Math.min(this.start().getX(), this.end().getX())
                || EqualDoubles.doubleEquals(x, Math.min(this.start().getX(), this.end().getX())))
                && (x <= Math.max(this.start().getX(), this.end().getX())
                || EqualDoubles.doubleEquals(x, Math.max(this.start().getX(), this.end().getX())))
                && (x >= Math.min(other.start().getX(), other.end().getX())
                || EqualDoubles.doubleEquals(x, Math.min(other.start().getX(), other.end().getX())))
                && (x <= Math.max(other.start().getX(), other.end().getX())
                || EqualDoubles.doubleEquals(x, Math.max(other.start().getX(), other.end().getX())));
    }

    /**
     * Check intersection when both vertical.
     *
     * @param first  the first line
     * @param second the second line
     * @return true if they intersect, false otherwise
     */
    boolean checkBothVertical(Line first, Line second) {
        // segments are completely different
        if (!EqualDoubles.doubleEquals(first.start().getX(), second.start().getX())) {
            return false;
        }
        // segments are on the same line
        if (Math.min(first.start().getY(), first.end().getY())
                < Math.min(second.start().getY(), second.end().getY())) {
            return Math.max(first.start().getY(), first.end().getY())
                    >= Math.min(second.start().getY(), second.end().getY());
        } else {
            return Math.max(second.start().getY(), second.end().getY())
                    >= Math.min(first.start().getY(), first.end().getY());
        }
    }

    /**
     * Check intersection when only one is vertical.
     * Checks if they cross (y=ax+b)
     *
     * @param first  the first line
     * @param second the second line
     * @return true if they intersect, false otherwise
     */
    boolean checkOneVertical(Line first, Line second) {
        double slopeFirst = (first.end().getY() - first.start().getY()) / (first.end().getX() - first.start().getX());
        double bFirst = first.start().getY() - slopeFirst * first.start().getX();
        double y = slopeFirst * second.start().getX() + bFirst;

        // check if the crossing point is between other line's max and min Y values
        boolean isCrossingY = y >= Math.min(second.start().getY(), second.end().getY())
                && y <= Math.max(second.start().getY(), second.end().getY());

        // check if the crossing point is between other line's max and min X values
        boolean isCrossingX = second.start().getX() >= Math.min(first.start().getX(), first.end().getX())
                && second.start().getX() <= Math.max(first.start().getX(), first.end().getX());

        // return true if the lines cross
        return isCrossingY && isCrossingX;
    }

    /**
     * Returns the intersection point of the lines, if they don't intersect returns null.
     *
     * @param other the other line
     * @return the point (or null)
     */
    public Point intersectionWith(Line other) {
        // checks if the lines are equal
        if (this.equals(other)) {
            return null;
        }
        double b1 = findBPoint(this);
        double b2 = findBPoint(other);
        // checks if the slope of the first line does not exist.
        if (EqualDoubles.doubleEquals(this.start().getX(), this.end().getX())
                && !EqualDoubles.doubleEquals(other.start().getX(), other.end().getX())) {
            double y1 = (findSLope(other) * this.start().getX()) + b2;
            return new Point(this.start().getX(), y1);
        }

        // checks if the slope of the second line does not exist.
        if (EqualDoubles.doubleEquals(other.start().getX(), other.end().getX())
                && !EqualDoubles.doubleEquals(this.start().getX(), this.end().getX())) {
            double y2 = (findSLope(this) * other.end().getX()) + b1;
            return new Point(other.start().getX(), y2);
        }

        // checks if the lines are parallel (the slopes are equal) and they intersect
        if (EqualDoubles.doubleEquals(findSLope(this), findSLope(other))) {
            if (this.start().equals(other.end())) {
                return this.start();
            } else if (this.end().equals(other.start())) {
                return this.end();
            } else if (this.start().equals(this.end())) {
                if (other.start().getX() <= this.start().getX() && this.start().getX() <= other.end().getX()) {
                    return this.start();
                }
            } else if (other.start().equals(other.end())) {
                if (this.start().getX() <= other.start().getX() && other.start().getX() <= this.end().getX()) {
                    return other.start();
                }
            }
            return null;
        }

        // find the point of the intersection.
        // when y1 = m1x +b1, y2 = m2x +b2 => x = (b2 - b1) / (m1 - m2).
        double x = (b2 - b1) / (findSLope(this) - findSLope(other));
        double y = (findSLope(this) * x) + b1;
        return new Point(x, y);
    }

    /**
     * Find the slope of a line (using the equation m = y2-y1 / x2-x1).
     *
     * @param line the line to find its slope
     * @return the slope
     */
    public double findSLope(Line line) {
        if (!EqualDoubles.doubleEquals(line.start().getX(), line.end().getX())) {
            return ((line.start().getY() - line.end().getY()) / (line.start().getX() - line.end().getX()));
        }
        return 0;
    }

    /**
     * Find the b value of the line equation( y = mx + b).
     *
     * @param line the line to check
     * @return the value
     */
    public double findBPoint(Line line) {
        return line.start().getY() - (findSLope(line) * (line.start().getX()));
    }

    /**
     * Return true is the lines are equal, false otherwise.
     *
     * @param other the other line
     * @return true if they are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.p1.equals(other.start()) && this.p2.equals(other.end()))
                || (this.p1.equals(other.end()) && this.p2.equals(other.start()));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the rectangle
     * @return the closest point to the start of the line
     */
//
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        // if the line doesn't intersect with the rectangle
        if (points.isEmpty()) {
            return null;
        }
        // return the closest intersection point to the line
        Point closest = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i) == null) {
                continue;
            }
            if (points.get(i).distance(this.start()) < closest.distance(this.start())) {
                closest = points.get(i);
            }
        }
        return closest;
    }
}