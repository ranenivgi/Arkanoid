// 208210708 Ranen Ivgi
package levels;

import sprites.Block;
import sprites.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type General level, the base level whose being inherent from.
 */
public abstract class GeneralLevel implements LevelInformation {
    private final List<Velocity> velocityList = new ArrayList<>();
    private final List<Block> blockList = new ArrayList<>();

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v;
        for (int i = 0; i < 3; i++) {
            if (i % 2 != 0) {
                v = new Velocity(5, -5);
            } else {
                v = new Velocity(-5, -5);
            }
            this.velocityList.add(v);
        }
        return this.velocityList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blockList.size();
    }
}
