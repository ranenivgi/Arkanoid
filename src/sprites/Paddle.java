// 208210708 Ranen Ivgi
package sprites;

import collision.Collidable;
import game.Constants;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type sprites.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private final KeyboardSensor keyboard;
    private Rectangle paddle;
    private final int speed;
    private final int width;
    private final Point paddleStart;

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard the keyboard
     * @param speed    the speed
     * @param width    the width
     */
    public Paddle(KeyboardSensor keyboard, int speed, int width) {
        this.keyboard = keyboard;
        this.speed = speed;
        this.width = width;
        this.paddleStart = new Point((double) Constants.SCREEN_WIDTH / 2 - (double) width / 2,
                570 - (double) Constants.PADDLE_HEIGHT / 2);
        this.paddle = new Rectangle(this.paddleStart, width, Constants.PADDLE_HEIGHT);
    }

    /**
     * move the paddle to left.
     */
    public void moveLeft() {
        final double epsilon = 0.2;
        if (paddle.getUpperLeft().getX() > Constants.BORDERS) {
            this.paddle = new Rectangle(new Point(paddle.getUpperLeft().getX() - speed,
                    paddle.getUpperLeft().getY()), this.width, Constants.PADDLE_HEIGHT);
        }
        if (paddle.getUpperLeft().getX() <= Constants.BORDERS) {
            this.paddle = new Rectangle(new Point(Constants.BORDERS + epsilon,
                    paddle.getUpperLeft().getY()), this.width, Constants.PADDLE_HEIGHT);
        }
    }

    /**
     * move the paddle to right.
     */
    public void moveRight() {
        final double epsilon = 0.2;
        if (paddle.getUpperRight().getX() < Constants.SCREEN_WIDTH - Constants.BORDERS) {
            this.paddle = new Rectangle(new Point(paddle.getUpperLeft().getX() + speed,
                    paddle.getUpperLeft().getY()), this.width, Constants.PADDLE_HEIGHT);
        }
        if (paddle.getUpperRight().getX() >= Constants.SCREEN_WIDTH - Constants.BORDERS) {
            this.paddle = new Rectangle(new Point(Constants.SCREEN_WIDTH - Constants.BORDERS - width - epsilon,
                    paddle.getUpperLeft().getY()), this.width, Constants.PADDLE_HEIGHT);
        }
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(255, 108, 0));
        d.fillRectangle(paddle.getUpperLeft().getIntX(), paddle.getUpperLeft().getIntY(),
                (int) paddle.getWidth(), (int) paddle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(paddle.getUpperLeft().getIntX(), paddle.getUpperLeft().getIntY(),
                (int) paddle.getWidth(), (int) paddle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double checkX = (double) width / 5;
        double speed = currentVelocity.getSpeed();
        final double[] regions = new double[5];
        final int[] angles = {30, 60, 300, 330};
        // the paddle is divided to five equally-spaced regions.
        for (int i = 0; i < regions.length; i++) {
            regions[i] = checkX;
            checkX += (double) width / 5;
        }
        if (collisionPoint.getX() >= this.paddle.getUpperLeft().getX()
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + regions[0]) {
            return Velocity.fromAngleAndSpeed(angles[2], speed);
        }
        if (collisionPoint.getX() > this.paddle.getUpperLeft().getX() + regions[0]
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + regions[1]) {
            return Velocity.fromAngleAndSpeed(angles[3], speed);
        }
        if (collisionPoint.getX() > this.paddle.getUpperLeft().getX() + regions[1]
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + regions[2]) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (collisionPoint.getX() > this.paddle.getUpperLeft().getX() + regions[2]
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + regions[3]) {
            return Velocity.fromAngleAndSpeed(angles[0], speed);
        }
        if (collisionPoint.getX() > this.paddle.getUpperLeft().getX() + regions[3]
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + regions[4]) {
            return Velocity.fromAngleAndSpeed(angles[1], speed);
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Sets paddle position to the start.
     */
    public void setPaddle() {
        this.paddle = new Rectangle(this.paddleStart, width, Constants.PADDLE_HEIGHT);
    }
}