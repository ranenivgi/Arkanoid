// 208210708 Ranen Ivgi
package sprites;

import collision.Collidable;
import collision.CollisionInfo;
import collision.Line;
import game.Constants;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The type sprites.Ball.
 */
public class Ball implements Sprite {
    private final int r;
    private GameEnvironment game;
    private Color color;
    private Point center;
    private Velocity velocity;

    /**
     * Instantiates a new sprites.Ball.
     *
     * @param center the ball's center point
     * @param r      the ball's radius
     * @param color  the ball's color
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Instantiates a new sprites.Ball.
     *
     * @param x     the ball's center x point
     * @param y     the ball's center y point
     * @param r     the ball's radius
     * @param color the ball's color
     * @param game  the game
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment game) {
        this(new Point(x, y), r, color);
    }

    /**
     * Gets the ball center x point.
     *
     * @return the x
     */
    public int getX() {
        return this.center.getIntX();
    }

    /**
     * Gets the ball center y point.
     *
     * @return the y
     */
    public int getY() {
        return this.center.getIntY();
    }

    /**
     * Gets the ball's radius.
     *
     * @return the radius
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the ball's color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the ball's color.
     *
     * @param col the color
     */
    public void setColor(Color col) {
        this.color = col;
    }

    /**
     * Draw's the ball on a given draw surface with black outlines.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // fill the circle
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        // draw outlines of the circle in black
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Sets game environment.
     *
     * @param game the game
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.game = game;
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets the ball's velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    /**
     * sets the ball's velocity by its size between 5-55 speed and random angle between to 360 degrees.
     * the result is that smaller balls move faster than bigger balls.
     */
    public void velocityBySize() {
        final int minBallSpeed = 5, maxBallSpeed = 50;
        final int degrees = 360;
        Random rand = new Random();
        if (this.getSize() >= maxBallSpeed) {
            this.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(degrees + 1), minBallSpeed));
        } else {
            this.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(degrees + 1),
                    -this.getSize() + (minBallSpeed + maxBallSpeed)));

        }
    }

    /**
     * Gets the ball's velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the ball's velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Checks if the ball is out of the screen bounds and fix its position to the closest point in the screen bounds.
     */
    public void fixCenter() {
        int endWidth = Constants.SCREEN_WIDTH - Constants.BORDERS;
        int endHeight = Constants.SCREEN_HEIGHT;
        int extra = 5;
        int start = Constants.BORDERS;

        // fix right bound
        if (this.center.getX() > endWidth) {
            this.center = new Point(endWidth - extra, this.center.getY());
        }
        // fix left bound
        if (this.center.getX() < start) {
            this.center = new Point(start + extra, this.center.getY());
        }
        // fix lower bound
        if (this.center.getY() > endHeight) {
            this.center = new Point(this.center.getX(), endHeight - extra);
        }
        // fix upper bound
        if (this.center.getY() < start) {
            this.center = new Point(this.center.getX(), start + extra);
        }
    }

    /**
     * Moves the ball one step by the velocity chosen, if the ball meets the bounds it changes its direction.
     */
    public void moveOneStep() {
        fixCenter();
        Point temp;
        // gets the ball trajectory, the next step is our trajectory.
        Line trajectory = new Line(this.center, getVelocity().applyToPoint(this.center));
        CollisionInfo colInfo = game.getClosestCollision(trajectory);
        // if there is a collision, we set the velocity by the hit position.
        if (colInfo != null) {
            setVelocity(colInfo.collisionObject().hit(this, colInfo.collisionPoint(), this.velocity));
        }
        temp = this.getVelocity().applyToPoint(this.center);
        fixCenter();
        // check if the next step is out of borders and fixes the ball respectively
        if (game.isColliding(temp) != null) {
            setVelocity(-this.getVelocity().getDx(), -this.getVelocity().getDy());
            outOfBlock(game.isColliding(temp));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
        fixCenter();
    }

    /**
     * Checks if the ball is inside the paddle and if so takes it outside to the closest point.
     *
     * @param collidable the collidable object
     */
    public void outOfBlock(Collidable collidable) {
        // checks the paddle only
        if (this.center.getY() >= Constants.SCREEN_HEIGHT - Constants.BORDERS - Constants.PADDLE_HEIGHT
                && this.center.getX() > Constants.BORDERS
                && this.center.getX() < Constants.SCREEN_WIDTH - Constants.BORDERS) {
            // finds the closest point outside the block and moves the ball towards it.
            if (this.center.distance(collidable.getCollisionRectangle().getUpperLeft())
                    < this.center.distance(collidable.getCollisionRectangle().getUpperRight())) {
                this.center = new Point(collidable.getCollisionRectangle().getUpperLeft().getX(), this.center.getY());
                if (this.center.getX() < Constants.PADDLE_WIDTH + Constants.BORDERS) {
                    this.center = new Point(this.center.getX(), this.center.getY() - Constants.PADDLE_HEIGHT);
                }
            } else if (this.center.distance(collidable.getCollisionRectangle().getUpperLeft())
                    > this.center.distance(collidable.getCollisionRectangle().getUpperRight())) {
                this.center = new Point(collidable.getCollisionRectangle().getUpperRight().getX(), this.center.getY());
                if (this.center.getX() > Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.BORDERS) {
                    this.center = new Point(this.center.getX(), this.center.getY() - Constants.PADDLE_HEIGHT);
                }
            }
        }
    }

    /**
     * Add the ball to the game.
     *
     * @param gameLevel the game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Remove the ball from the game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
