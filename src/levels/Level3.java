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
import java.util.List;

/**
 * The type Level 3.
 */
public class Level3 extends GeneralLevel implements LevelInformation {
    /**
     * The Paddle width.
     */
    public static final int PADDLE_WIDTH = 110;
    /**
     * The Paddle speed.
     */
    public static final int PADDLE_SPEED = 7;
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
        return "Turquoise";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Color(0, 102, 90), "Turquoise");
    }

    @Override
    public List<Block> blocks() {
        final int blockBorders = 100;
        final int[] blockHeight = {180, 230, 280, 330, 380, 430};
        // create the blocks
        for (int i = blockHeight[0]; i < Constants.SCREEN_WIDTH - Constants.BORDERS; i += Constants.BLOCKS_WIDTH) {
            Block b = new Block(new Rectangle(new Point(i, blockBorders + Constants.BLOCKS_HEIGHT),
                    Constants.BLOCKS_WIDTH, Constants.BLOCKS_HEIGHT));
            b.setColor(new Color(170, 105, 255));
            blockList.add(b);
        }
        for (int i = blockHeight[1]; i < Constants.SCREEN_WIDTH - Constants.BORDERS; i += Constants.BLOCKS_WIDTH) {
            Block b = new Block(new Rectangle(new Point(i, blockBorders + 2 * Constants.BLOCKS_HEIGHT),
                    Constants.BLOCKS_WIDTH, Constants.BLOCKS_HEIGHT));
            b.setColor(new Color(255, 102, 102));
            blockList.add(b);
        }
        for (int i = blockHeight[2]; i < Constants.SCREEN_WIDTH - Constants.BORDERS; i += Constants.BLOCKS_WIDTH) {
            Block b = new Block(new Rectangle(new Point(i, blockBorders + 3 * Constants.BLOCKS_HEIGHT),
                    Constants.BLOCKS_WIDTH, Constants.BLOCKS_HEIGHT));
            b.setColor(new Color(119, 178, 255));
            blockList.add(b);
        }
        for (int i = blockHeight[3]; i < Constants.SCREEN_WIDTH - Constants.BORDERS; i += Constants.BLOCKS_WIDTH) {
            Block b = new Block(new Rectangle(new Point(i, blockBorders + 4 * Constants.BLOCKS_HEIGHT),
                    Constants.BLOCKS_WIDTH, Constants.BLOCKS_HEIGHT));
            b.setColor(new Color(51, 255, 153));
            blockList.add(b);
        }
        for (int i = blockHeight[4]; i < Constants.SCREEN_WIDTH - Constants.BORDERS; i += Constants.BLOCKS_WIDTH) {
            Block b = new Block(new Rectangle(new Point(i, blockBorders + 5 * Constants.BLOCKS_HEIGHT),
                    Constants.BLOCKS_WIDTH, Constants.BLOCKS_HEIGHT));
            b.setColor(new Color(255, 181, 31));
            blockList.add(b);
        }
        for (int i = blockHeight[5]; i < Constants.SCREEN_WIDTH - Constants.BORDERS; i += Constants.BLOCKS_WIDTH) {
            Block b = new Block(new Rectangle(new Point(i, blockBorders + 6 * Constants.BLOCKS_HEIGHT),
                    Constants.BLOCKS_WIDTH, Constants.BLOCKS_HEIGHT));
            b.setColor(new Color(255, 255, 102));
            blockList.add(b);
        }
        return blockList;
    }
}
