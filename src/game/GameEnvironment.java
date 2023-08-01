// 208210708 Ranen Ivgi
package game;

import collision.Collidable;
import collision.CollisionInfo;
import collision.EqualDoubles;
import collision.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type game.Game environment.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Instantiates a new game.Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Remove the collidable item.
     *
     * @param c the item
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection,
     * we return null. Else, we return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> colInfo = new ArrayList<>();
        Point colPoint;
        List<Collidable> tempCol = new ArrayList<>(this.collidables);
        // make a list of colInfo of the closest intersection to the start of the line.
        for (Collidable collidable : tempCol) {
            colPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (colPoint != null) {
                colInfo.add(new CollisionInfo(colPoint, collidable));
            }
        }
        // if its empty we return null
        if (colInfo.isEmpty()) {
            return null;
        }
        // if not empty we return the item whom his distance to the point is the closest.
        CollisionInfo closest = colInfo.get(0);
        for (int j = 1; j < colInfo.size(); j++) {
            if (trajectory.start().distance(colInfo.get(j).collisionPoint())
                    < trajectory.start().distance(closest.collisionPoint())) {
                closest = colInfo.get(j);
            }
        }
        return closest;
    }

    /**
     * Checks if there is any block colliding with the ball. In other words,
     * checks if the ball is inside the collidable item, if so we return the item, if not we return null.
     *
     * @param point the point
     * @return the collidable item
     */
    public Collidable isColliding(Point point) {
        List<Collidable> temp = new ArrayList<>(this.collidables);
        for (Collidable collidable : temp) {
            boolean collideX = (EqualDoubles.doubleEquals(point.getX(),
                    collidable.getCollisionRectangle().getUpperLeft().getX())
                    || EqualDoubles.doubleEquals(point.getX(),
                    collidable.getCollisionRectangle().getUpperRight().getX()))
                    && (EqualDoubles.doubleEquals(point.getY(),
                    collidable.getCollisionRectangle().getUpperLeft().getY())
                    || point.getY() > collidable.getCollisionRectangle().getUpperLeft().getY())
                    && (EqualDoubles.doubleEquals(point.getY(),
                    collidable.getCollisionRectangle().getBottomLeft().getY())
                    || point.getY() < collidable.getCollisionRectangle().getBottomLeft().getY());
            boolean collideY = (EqualDoubles.doubleEquals(point.getY(),
                    collidable.getCollisionRectangle().getUpperLeft().getY())
                    || EqualDoubles.doubleEquals(point.getY(),
                    collidable.getCollisionRectangle().getBottomLeft().getY()))
                    && (EqualDoubles.doubleEquals(point.getX(),
                    collidable.getCollisionRectangle().getUpperRight().getX())
                    || point.getX() < collidable.getCollisionRectangle().getUpperRight().getX())
                    && (EqualDoubles.doubleEquals(point.getX(),
                    collidable.getCollisionRectangle().getUpperLeft().getX())
                    || point.getX() > collidable.getCollisionRectangle().getUpperLeft().getX());
            boolean isInside = ((point.getX() < collidable.getCollisionRectangle().getUpperRight().getX())
                    && (point.getX() > collidable.getCollisionRectangle().getUpperLeft().getX())
                    && (point.getY() < collidable.getCollisionRectangle().getBottomLeft().getY())
                    && (point.getY() > collidable.getCollisionRectangle().getUpperLeft().getY()));
            if (collideX || collideY || isInside) {
                return collidable;
            }
        }
        return null;
    }
}