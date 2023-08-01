// 208210708 Ranen Ivgi
package game;

import sprites.Ball;
import sprites.Block;
import sprites.HitListener;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel      the game to remove from
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit the hit block
     * @param hitter   the hitter ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease();
        hitter.removeFromGame(this.gameLevel);
    }
}
