// 208210708 Ranen Ivgi
package game;

import sprites.Ball;
import sprites.Block;
import sprites.HitListener;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(Constants.SCORE_FOR_HIT);
        beingHit.removeHitListener(this);
    }
}