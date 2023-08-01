// 208210708 Ranen Ivgi

import animation.AnimationRunner;
import biuoop.GUI;
import game.Constants;
import game.GameFlow;
import levels.LevelInformation;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * The entry point of application, runs the game.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("ass6", Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        List<LevelInformation> levels = new ArrayList<>();
        AnimationRunner ar = new AnimationRunner(gui);
        // create the level's list following the arguments entered
        for (String arg : args) {
            try {
                LevelInformation newLevel;
                switch (Integer.parseInt(arg)) {
                    case 1:
                        newLevel = new Level1();
                        levels.add(newLevel);
                        break;
                    case 2:
                        newLevel = new Level2();
                        levels.add(newLevel);
                        break;
                    case 3:
                        newLevel = new Level3();
                        levels.add(newLevel);
                        break;
                    case 4:
                        newLevel = new Level4();
                        levels.add(newLevel);
                        break;
                    default:
                        break;
                }
            } catch (Exception ignored) {
            }
        }
        // creates default levels 1-4 if no level was entered as an argument.
        if (levels.size() == 0) {
            LevelInformation level1 = new Level1();
            LevelInformation level2 = new Level2();
            LevelInformation level3 = new Level3();
            LevelInformation level4 = new Level4();
            levels.add(level1);
            levels.add(level2);
            levels.add(level3);
            levels.add(level4);
        }
        // runs the game
        GameFlow gameFlow = new GameFlow(ar, gui);
        gameFlow.runLevels(levels);
    }
}