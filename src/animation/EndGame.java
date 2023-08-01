// 208210708 Ranen Ivgi
package animation;

import biuoop.DrawSurface;
import game.Constants;
import game.Counter;

import java.awt.Color;

/**
 * The type End game.
 */
public class EndGame implements Animation {
    private final boolean ended;
    private final Counter score;

    /**
     * Instantiates a new End game.
     *
     * @param ended the ended
     * @param score the score
     */
    public EndGame(boolean ended, Counter score) {
        this.ended = ended;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(43, 43, 43));
        d.fillRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        d.setColor(Color.WHITE);
        if (!this.ended) {
            d.drawText(Constants.SCREEN_WIDTH / 4 + 70, Constants.SCREEN_HEIGHT / 3, "You Win!", 70);
            d.drawText(Constants.SCREEN_WIDTH / 2 - 110, Constants.SCREEN_HEIGHT / 2 + 40,
                    "Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(Constants.SCREEN_WIDTH / 4 + 30, Constants.SCREEN_HEIGHT / 3, "Game Over!", 70);
            d.drawText(Constants.SCREEN_WIDTH / 2 - 110, Constants.SCREEN_HEIGHT / 2 + 40,
                    "Your score is " + this.score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
