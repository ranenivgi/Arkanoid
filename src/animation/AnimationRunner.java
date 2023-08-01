// 208210708 Ranen Ivgi
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.Constants;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private final Sleeper sleeper;
    private final GUI gui;
    private final int framesPerSecond;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui the gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = Constants.FPS;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs an animation in a loop until it should stop running.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}