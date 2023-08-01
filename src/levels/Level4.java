// 208210708 Ranen Ivgi
package levels;

import game.Constants;
import geometry.Point;
import geometry.Rectangle;
import sprites.Background;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Level 4.
 */
public class Level4 extends GeneralLevel implements LevelInformation {
    /**
     * The Paddle width.
     */
    public static final int PADDLE_WIDTH = 100;
    /**
     * The Paddle speed.
     */
    public static final int PADDLE_SPEED = 10;
    private final List<Block> blockList = new ArrayList<>();

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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Color(102, 178, 255), "Final Four");
    }

    @Override
    public List<Block> blocks() {
        List<Color> colors = new LinkedList<>();
        colors.add(new Color(170, 105, 255));
        colors.add(new Color(255, 102, 102));
        colors.add(new Color(119, 178, 255));
        colors.add(new Color(51, 255, 153));
        colors.add(new Color(255, 181, 31));
        colors.add(new Color(255, 255, 102));
        colors.add(new Color(51, 255, 255));
        for (int i = 210, size = 0; i > 0; i -= 30, size++) {
            for (int j = 20; j < 780; j += 76) {
                Block b = new Block(new Rectangle(new Point(j, (double) Constants.SCREEN_HEIGHT / 2 - i), 76,
                        Constants.BLOCKS_HEIGHT));
                b.setColor(colors.get(size));
                blockList.add(b);
            }
        }
        return blockList;
    }
}
