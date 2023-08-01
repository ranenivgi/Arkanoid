// 208210708 Ranen Ivgi
package levels;

import game.Constants;
import geometry.Point;
import geometry.Rectangle;
import sprites.Background;
import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1.
 */
public class Level1 extends GeneralLevel implements LevelInformation {
    private final Velocity firstBall = Velocity.fromAngleAndSpeed(0, 2);
    /**
     * The Paddle width.
     */
    public static final int PADDLE_WIDTH = 150;
    /**
     * The Paddle speed.
     */
    public static final int PADDLE_SPEED = 5;
    private final List<Velocity> velocityList = new ArrayList<>();
    private final List<Block> blockList = new ArrayList<>();

    @Override
    public List<Velocity> initialBallVelocities() {
        this.velocityList.add(firstBall);
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Color(43, 43, 43), "Direct Hit");
    }

    @Override
    public List<Block> blocks() {
        final Point firstBlock = new Point(385, 205);
        Block b = new Block(new Rectangle(firstBlock, Constants.BLOCKS_HEIGHT, Constants.BLOCKS_HEIGHT));
        b.setColor(new Color(255, 51, 51));
        blockList.add(b);
        return blockList;
    }
}
