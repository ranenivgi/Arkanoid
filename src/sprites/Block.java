// 208210708 Ranen Ivgi
package sprites;

import collision.Collidable;
import collision.EqualDoubles;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type sprites.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle block;
    private final List<HitListener> hitListeners;
    private Color color;

    /**
     * Instantiates a new sprites.Block.
     *
     * @param rectangle the rectangle
     */
    public Block(Rectangle rectangle) {
        this.block = rectangle;
        this.color = new Color(60, 63, 65);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Gets the block.
     *
     * @return the block
     */
    public Rectangle getBlock() {
        return this.block;
    }

    /**
     * Sets the color of the block.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean changeX = (EqualDoubles.doubleEquals(collisionPoint.getX(), block.getUpperLeft().getX())
                || EqualDoubles.doubleEquals(collisionPoint.getX(), block.getUpperRight().getX()))
                && (EqualDoubles.doubleEquals(collisionPoint.getY(), block.getUpperLeft().getY())
                || collisionPoint.getY() > block.getUpperLeft().getY())
                && (EqualDoubles.doubleEquals(collisionPoint.getY(), block.getBottomLeft().getY())
                || collisionPoint.getY() < block.getBottomLeft().getY());
        boolean changeY = (EqualDoubles.doubleEquals(collisionPoint.getY(), block.getUpperLeft().getY())
                || EqualDoubles.doubleEquals(collisionPoint.getY(), block.getBottomLeft().getY()))
                && (EqualDoubles.doubleEquals(collisionPoint.getX(), block.getUpperRight().getX())
                || collisionPoint.getX() < block.getUpperRight().getX())
                && (EqualDoubles.doubleEquals(collisionPoint.getX(), block.getUpperLeft().getX())
                || collisionPoint.getX() > block.getUpperLeft().getX());
        this.notifyHit(hitter);
        // check about corners. the return makes sure it doesn't change twice.
        if (changeX && changeY) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (changeX) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (changeY) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // fill the block
        d.setColor(this.color);
        d.fillRectangle(this.block.getUpperLeft().getIntX(), this.block.getUpperLeft().getIntY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(this.block.getUpperLeft().getIntX(), this.block.getUpperLeft().getIntY(),
                (int) block.getWidth(), (int) block.getHeight());
    }

    /**
     * can use to change the behavior of the block when time passed.
     */
    @Override
    public void timePassed() {
        // for the future
    }

    /**
     * Add the block to the game.
     *
     * @param gameLevel the game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * notify about a hit to all the listeners.
     *
     * @param hitter the hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
