// 208210708 Ranen Ivgi
package animation;

import biuoop.DrawSurface;
import game.Constants;
import game.Counter;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds,
 * and on top of them it will show a countdown from countFrom back to 1,
 * where each number will appear on the screen for (numOfSeconds / countFrom) seconds,
 * before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private final Counter count;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.count = new Counter();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        this.count.increase();
        int number = this.countFrom - ((int) (this.count.getValue()
                / (Constants.FPS / (this.countFrom / this.numOfSeconds))));
        if (this.count.getValue() >= this.countFrom * (Constants.FPS / (this.countFrom / this.numOfSeconds))) {
            this.stop = true;
            return;
        }
        d.setColor(new Color(60, 63, 65));
        d.fillRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.BORDERS + Constants.SCORE_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.BORDERS + Constants.SCORE_HEIGHT);
        d.setColor(Color.WHITE);
        d.drawText(Constants.SCREEN_WIDTH / 4 + 20, Constants.SCORE_HEIGHT + 10,
                "The game will start in: " + number, 37);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}