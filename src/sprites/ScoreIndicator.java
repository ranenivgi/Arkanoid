// 208210708 Ranen Ivgi
package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

import game.Constants;
import game.Counter;
import game.GameLevel;
import geometry.Point;


/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private final Counter scoreCounter;
    private final GameLevel gameLevel;
    private final Counter lives;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreCounter the score counter
     * @param gameLevel    the game level
     * @param lives        the lives
     */
    public ScoreIndicator(Counter scoreCounter, GameLevel gameLevel, Counter lives) {
        this.scoreCounter = scoreCounter;
        this.gameLevel = gameLevel;
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        final int centerText = 50;
        final Point start = new Point(0, 0);
        d.setColor(Color.GRAY);
        d.fillRectangle(start.getIntX(), start.getIntY(), Constants.SCREEN_WIDTH, Constants.SCORE_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawRectangle(start.getIntX(), start.getIntY(), Constants.SCREEN_WIDTH, Constants.SCORE_HEIGHT);
        d.drawText(Constants.SCREEN_WIDTH / 7 - centerText, Constants.SCORE_HEIGHT - 5,
                "Lives: " + this.lives.getValue(), Constants.SCORE_HEIGHT - 5);
        d.drawText(Constants.SCREEN_WIDTH / 2 - centerText, Constants.SCORE_HEIGHT - 5,
                "Score: " + this.scoreCounter.getValue(), Constants.SCORE_HEIGHT - 5);
        d.drawText(Constants.SCREEN_WIDTH - centerText * 5, Constants.SCORE_HEIGHT - 5,
                "Level name: " + this.gameLevel.getLevel().levelName(), Constants.SCORE_HEIGHT - 5);
    }

    @Override
    public void timePassed() {
        // for the future
    }
}
