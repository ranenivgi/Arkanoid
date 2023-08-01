// 208210708 Ranen Ivgi
package game;

import animation.Animation;
import animation.PauseScreen;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import collision.Collidable;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import levels.LevelInformation;
import sprites.Ball;
import sprites.SpriteCollection;
import sprites.Block;
import sprites.Paddle;
import sprites.Sprite;
import sprites.ScoreIndicator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    private final AnimationRunner runner;
    private boolean running;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter blocksCounter;
    private final Counter ballsCounter;
    private final Counter scoreCounter;
    private final KeyboardSensor keyboard;
    private final LevelInformation level;
    private List<Ball> ballsArray;
    private final Counter lives;
    private final Paddle paddle;

    /**
     * Instantiates a new game.Game.
     *
     * @param lev          the level
     * @param ks           the keyboard sensor
     * @param ar           the animation runner
     * @param scoreCounter the score counter
     * @param lives        the lives counter
     */
    public GameLevel(LevelInformation lev, KeyboardSensor ks, AnimationRunner ar,
                     Counter scoreCounter, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.scoreCounter = scoreCounter;
        this.runner = ar;
        this.keyboard = ks;
        this.level = lev;
        this.lives = lives;
        this.paddle = new Paddle(this.keyboard, this.level.paddleSpeed(), this.level.paddleWidth());
    }

    /**
     * Add collidable to the game environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidiable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Add sprite to the sprite environment.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks, Balls and Paddle and add them to the game.
     */
    public void initialize() {
        // add background
        addSprite(this.level.getBackground());
        // create paddle
        paddle.addToGame(this);
        // add balls, blocks and score
        setBalls(this.level.numberOfBalls());
        initializeBlocks(level.blocks());
        initializeBorders();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter, this, this.lives);
        addSprite(scoreIndicator);
    }

    /**
     * Sets new balls start position.
     *
     * @param ballsNumber the balls number
     */
    public void setBalls(int ballsNumber) {
        int w, h;
        int halfBalls;
        if (ballsNumber % 2 == 0) {
            halfBalls = ballsNumber / 2;
            w = 350;
            h = 450;
        } else {
            halfBalls = ballsNumber / 2 + 1;
            w = 400;
            h = 430;
        }
        this.ballsArray = new ArrayList<>();
        //setting the number of the balls that in the game
        for (int i = 0; i < halfBalls; i++, w -= 50, h += 20) {
            Ball b = new Ball(new Point(w, h), 5, Color.WHITE);
            this.ballsArray.add(b);
            this.ballsArray.get(i).setVelocity(level.initialBallVelocities().get(i));
            this.ballsArray.get(i).addToGame(this);
            this.ballsArray.get(i).setGameEnvironment(this.environment);
            this.ballsCounter.increase();
        }
        w = 450;
        h = 450;
        for (int i = halfBalls; i < ballsNumber; i++, w += 50, h += 20) {
            Ball b = new Ball(new Point(w, h), 5, Color.WHITE);
            this.ballsArray.add(b);
            this.ballsArray.get(i).setVelocity(level.initialBallVelocities().get(i));
            this.ballsArray.get(i).addToGame(this);
            this.ballsArray.get(i).setGameEnvironment(this.environment);
            this.ballsCounter.increase();
        }
    }

    /**
     * Initialize the blocks.
     *
     * @param blocks the blocks
     */
    public void initializeBlocks(List<Block> blocks) {
        // create the blocks listeners
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        // add the blocks to the listeners and the game
        for (Block b : blocks) {
            b.addToGame(this);
            this.blocksCounter.increase();
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * Initialize the screen borders.
     */
    public void initializeBorders() {
        final int start = 0;
        final int extra = 20;
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        Block topScreen = new Block(new Rectangle(new Point(Constants.BORDERS, Constants.SCORE_HEIGHT),
                Constants.SCREEN_WIDTH - extra, Constants.BORDERS));
        topScreen.addToGame(this);
        Block bottomScreen = new Block(new Rectangle(new Point(Constants.BORDERS,
                Constants.SCREEN_HEIGHT), Constants.SCREEN_WIDTH - extra, Constants.BORDERS));
        bottomScreen.addToGame(this);
        bottomScreen.addHitListener(ballRemover);
        Block rightScreen = new Block(new Rectangle(new Point(Constants.SCREEN_WIDTH - Constants.BORDERS,
                start), Constants.BORDERS, Constants.SCREEN_HEIGHT));
        rightScreen.addToGame(this);
        Block leftScreen = new Block(new Rectangle(new Point(start, start),
                Constants.BORDERS, Constants.SCREEN_HEIGHT));
        leftScreen.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
            this.runner.run(new CountdownAnimation(1, 2, this.sprites));
        }
        this.sprites.notifyAllTimePassed();
        if (this.ballsCounter.getValue() == 0 && this.lives.getValue() > 1) {
            this.lives.decrease();
            setBalls(this.ballsArray.size());
            this.paddle.setPaddle();
            this.sprites.drawAllOn(d);
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        }
    }

    @Override
    public boolean shouldStop() {
        if (this.blocksCounter.getValue() <= 0 || this.ballsCounter.getValue() <= 0) {
            // add 100 points for killing all the blocks
            if (this.blocksCounter.getValue() <= 0) {
                this.scoreCounter.increase(Constants.SCORE_FOR_END);
            }
            this.running = false;
        }
        return !this.running;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public LevelInformation getLevel() {
        return this.level;
    }
}