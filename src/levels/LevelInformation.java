// 208210708 Ranen Ivgi
package levels;

import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;

import java.util.List;

/**
 * The interface Level information. contains information about levels.
 */
public interface LevelInformation {
    /**
     * Number of balls in the level.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed.
     *
     * @return the speed
     */
    int paddleSpeed();

    /**
     * Paddle width.
     *
     * @return the width
     */
    int paddleWidth();

    /**
     * The level name is displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the blocks list
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks
     */
    int numberOfBlocksToRemove();
}
