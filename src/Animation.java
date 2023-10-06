/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gangs_000
 */
public class Animation {

    public static final int LOOP = 1;
    public static final int STOP = 2;
    public static final int RESTART_AT = 3;

    private final AnimationFrame[] frames;
    private int currentFrameIndex = 0;
    private final AnimatedObject subject;
    private int endAction;
    private int restartFrame;
    private boolean looped = false;

    public Animation(AnimationFrame[] frames, AnimatedObject subject) {
        this.frames = frames;
        this.subject = subject;
        this.endAction = LOOP;
    }

    public void loop() {
        this.endAction = LOOP;
    }

    public void loop(int restartFrame) {
        this.endAction = RESTART_AT;
        this.restartFrame = restartFrame;
    }

    public void stopAtEnd() {
        this.endAction = STOP;
    }

    public void reset() {
        looped = false;
        currentFrameIndex = 0;
    }

    public AnimationFrame getCurrentFrame() {
        return frames[currentFrameIndex];
    }

    public void advanceFrame() {
        if (isStopped()) {
            return;
        }

        AnimationFrame currentFrame = getCurrentFrame();

        currentFrameIndex++;
        currentFrameIndex %= frames.length;

        if (currentFrameIndex == 0) { // If I've looped
            looped = true;
            if (endAction == RESTART_AT) {
                currentFrameIndex = restartFrame;
            }
        }

        subject.changePosition(currentFrame.getDeltaX(), currentFrame.getDeltaY());
    }

    public void setFrameDeltas(int frame, int deltaX, int deltaY) {
        frames[frame].setDeltaX(deltaX);
        frames[frame].setDeltaY(deltaY);
    }

    public boolean isStopped() {
        return looped & endAction == STOP;
    }

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }
}
