// 208210708 Ranen Ivgi
package collision;

import geometry.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable object;

    /**
     * Instantiates a new Collision info.
     *
     * @param p the point of collision
     * @param c the object that was collided with
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.object = c;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.object;
    }
}