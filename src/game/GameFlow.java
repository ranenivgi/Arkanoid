// 208210708 Ranen Ivgi
package game;

import animation.AnimationRunner;
import animation.EndGame;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;
    private final Counter score;
    private boolean ended;
    private final Counter lives;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the ar
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, GUI gui) {
        this.animationRunner = ar;
        this.gui = gui;
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.score = new Counter();
        this.ended = false;
        this.lives = new Counter(Constants.TOTAL_LIVES);
    }

    /**
     * Runs the levels entered until the game is over.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            // creates the game based on the array entered
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score, this.lives);
            level.initialize();
            level.run();
            // checks the lives' status, if it reaches under 1 the game will be stopped
            if (this.lives.getValue() == 1) {
                this.ended = true;
                break;
            }
        }
        // end screen
        this.animationRunner.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(), "space",
                new EndGame(this.ended, this.score)));
        // finish the game
        this.gui.close();
    }
}