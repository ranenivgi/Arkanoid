// 208210708 Ranen Ivgi
package geometry;

import collision.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Geometry.Rectangle.
 */
public class Rectangle {
    private final Point upperLeft;
    private final Point bottomLeft;
    private final Point upperRight;
    private final Point bottomRight;

    private final double width;
    private final double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line
     * @return the list
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        Line topLine = new Line(upperLeft, upperRight);
        Line bottomLine = new Line(bottomLeft, bottomRight);
        Line leftLine = new Line(upperLeft, bottomLeft);
        Line rightLine = new Line(upperRight, bottomRight);
        // adds all kind of possible intersection points to the list.
        if (topLine.intersectionWith(line) != null && topLine.isIntersecting(line)) {
            points.add(topLine.intersectionWith(line));
        }
        if (bottomLine.intersectionWith(line) != null && bottomLine.isIntersecting(line)) {
            points.add(bottomLine.intersectionWith(line));
        }
        if (rightLine.intersectionWith(line) != null && rightLine.isIntersecting(line)) {
            points.add(rightLine.intersectionWith(line));
        }
        if (leftLine.intersectionWith(line) != null && leftLine.isIntersecting(line)) {
            points.add(leftLine.intersectionWith(line));
        }
        return points;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }


    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Gets bottom left.
     *
     * @return the bottom left
     */
    public Point getBottomLeft() {
        return bottomLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * Gets bottom right.
     *
     * @return the bottom right
     */
    public Point getBottomRight() {
        return bottomRight;
    }
}