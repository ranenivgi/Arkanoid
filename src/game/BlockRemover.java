// 208210708 Ranen Ivgi
package game;

import sprites.Ball;
import sprites.Block;
import sprites.HitListener;

/**
 * The type Block remover.
 * a BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel       the game
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit the hit block
     * @param hitter   the hitter ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decrease();
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
    }
}
