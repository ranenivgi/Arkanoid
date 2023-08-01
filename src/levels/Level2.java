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
import java.util.LinkedList;
import java.util.List;

/**
 * The type Level 2.
 */
public class Level2 extends GeneralLevel implements LevelInformation {
    /**
     * The Paddle width.
     */
    public static final int PADDLE_WIDTH = 550;
    /**
     * The Paddle speed.
     */
    public static final int PADDLE_SPEED = 15;
    private final List<Velocity> velocityList = new ArrayList<>();
    private final List<Block> blockList = new ArrayList<>();

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v;
        for (int i = 0; i < 10; i++) {
            if (i % 2 != 0) {
                v = new Velocity(5, -5);
            } else {
                v = new Velocity(-5, -5);
            }
            this.velocityList.add(v);
        }
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Color(102, 178, 255), "Wide Easy");
    }

    @Override
    public List<Block> blocks() {
        List<Color> colors = new LinkedList<>();
        colors.add(new Color(170, 105, 255));
        colors.add(new Color(255, 102, 102));
        colors.add(new Color(51, 255, 51));
        colors.add(new Color(51, 255, 153));
        colors.add(new Color(255, 181, 31));
        colors.add(new Color(255, 255, 102));
        colors.add(new Color(51, 255, 255));
        colors.add(new Color(192, 192, 192));
        colors.add(new Color(255, 51, 51));
        colors.add(new Color(255, 178, 102));

        for (int j = 0, l = 0; j < 20; l++) {
            for (int i = 0; i < 2; i++, j++) {
                Block block = new Block(new Rectangle(new Point(j * 38 + 20,
                        (double) Constants.SCREEN_HEIGHT / 2 - 70), 38,
                        Constants.BLOCKS_HEIGHT));
                block.setColor(colors.get(l));
                blockList.add(block);
            }
        }
        return blockList;
    }

}
