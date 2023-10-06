
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Player extends AnimatedObject {
// verables
// beta tester peter,austin
// artist peter

    private Image img;
    private Animation anim;
    int PlayerX, rock1X, rock2X, nRock1HitCounter;
    long time;
    private final int SPEED = 6, GRAV = 1;
    boolean isJumping, release_R, release_L, isFalling, isRock1destroyed, isRunning;
    private int Xmin, Xmax, Ymin, Ymax;
    private MainPan b;
    // images for the left running action
    // images for the standing left and right actions  
    ImageIcon i13 = new ImageIcon("Standing_R.png");
    ImageIcon i14 = new ImageIcon("Standing_L.png");
    ImageIcon i63 = new ImageIcon("crouchingRight.png");
    public String currentAction;
    private boolean keyPressed = false;
    private boolean goingRight = true;

// player image and place on the stage and other verable definations 
    public Player() {
        super(350, 238);

        setupAnimations();
        rock1X = 800;
        rock2X = 1200;
        Xmin = 350;
        Xmax = 450;
        PlayerX = 350;
        nRock1HitCounter = 0;
        isJumping = false;
        isFalling = false;
        release_R = false;
        release_L = false;
        isRunning = false;
        isRock1destroyed = false;

    }

    private void setupAnimations() {
        // images for the right walking action

        Animation walkingRightAnimation = new Animation(createAnimationFrames("walking_R", "png", 6, SPEED, 0), this);
        setAnimationFor("walkingRight", walkingRightAnimation);

        Animation walkingLeftAnimation = new Animation(createAnimationFrames("walking_L", "png", 6, -SPEED, 0), this);
        setAnimationFor("walkingLeft", walkingLeftAnimation);

        Animation runningRightAnimation = new Animation(createAnimationFrames("running_R", "png", 6, 2 * SPEED, 0), this);
        setAnimationFor("runningRight", runningRightAnimation);

        Animation runningLeftAnimation = new Animation(createAnimationFrames("Running_L", "png", 6, -2 * SPEED, 0), this);
        setAnimationFor("runningLeft", runningLeftAnimation);

        Animation crouchingRightAnimation = new Animation(createAnimationFrames("crouchingRight_R", "png", 1, 0, 0), this);
        setAnimationFor("crouchingRight", crouchingRightAnimation);

        Animation crouchingLeftAnimation = new Animation(createAnimationFrames("crouchingLeft_L", "png", 1, 0, 0), this);
        setAnimationFor("crouchingLeft", crouchingLeftAnimation);

        Animation jumpingRightAnimation = new Animation(createAnimationFrames("Jumping_R", "png", 5, 0, -90 / 5), this);
        jumpingRightAnimation.stopAtEnd();
        setAnimationFor("jumpingRight", jumpingRightAnimation);

        Animation punchingRightAnimation = new Animation(createAnimationFrames("Amaterasu Dragon_R", "png", 5, 0, 0), this);
        punchingRightAnimation.stopAtEnd();
        setAnimationFor("punchingRight", punchingRightAnimation);

        Animation punchingLAnimation = new Animation(createAnimationFrames("Amaterasu Dragon_L", "png", 5, 0, 0), this);
        punchingLAnimation.stopAtEnd();
        setAnimationFor("punchingL", punchingLAnimation);

        Animation rasenganRightAnimation = new Animation(createAnimationFrames("Rasengan_R", "png", 27, 0, 0), this);
        for (int i = 12; i < 17; i++) {
            rasenganRightAnimation.setFrameDeltas(i, 16, 0);
        }
        rasenganRightAnimation.loop(24);
        setAnimationFor("rasenganRight", rasenganRightAnimation);

        Animation chidoriRightAnimation = new Animation(createAnimationFrames("Chidori_R", "png", 26, 0, 0), this);
        for (int i = 13; i < 17; i++) {
            chidoriRightAnimation.setFrameDeltas(i, 16, 0);
        }
        chidoriRightAnimation.stopAtEnd();
        setAnimationFor("chidoriRight", chidoriRightAnimation);

        Animation rasenganLeftAnimation = new Animation(createAnimationFrames("Rasengan_L", "png", 27, 0, 0), this);
        for (int i = 12; i < 17; i++) {
            rasenganLeftAnimation.setFrameDeltas(i, -16, 0);
        }
        rasenganLeftAnimation.loop(24);
        setAnimationFor("rasenganLeft", rasenganLeftAnimation);

        Animation chidoriLeftAnimation = new Animation(createAnimationFrames("Chidori_L", "png", 26, 0, 0), this);
        for (int i = 13; i < 17; i++) {
            chidoriLeftAnimation.setFrameDeltas(i, -16, 0);
        }
        chidoriLeftAnimation.stopAtEnd();
        setAnimationFor("chidoriLeft", chidoriLeftAnimation);

    }

    public AnimationFrame[] createAnimationFrames(String filenameBase, String imageType, int numberOfFrames, int deltaX, int deltaY) {
        AnimationFrame[] frames = new AnimationFrame[numberOfFrames];
        for (int i = 0; i < numberOfFrames; i++) {
            frames[i] = new AnimationFrame(frameImageFilename(filenameBase, i + 1, imageType), deltaX, deltaY);
        }
        return frames;
    }

    // how to move the stage to make it look like the player is moving
    public void move() {

        if (!isJumping) {
            if (!isAbovePlatform()) {
                setY(238);
            } else {
                setY(148);
            }
        }
    }

    public Image getImage() {

        if (keyPressed) {
            Animation animationForCurrentAction = getAnimationFor(currentAction);
            img = animationForCurrentAction.getCurrentFrame().getImage();
            animationForCurrentAction.advanceFrame();
        } else {
            if (goingRight) {
                img = i13.getImage();
            } else {
                img = i14.getImage();
            }
        }
        return img;
    }
    // check what key is pressed

    public void keyPressed(KeyEvent e) {
        keyPressed = true;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                currentAction = "walkingLeft";
                goingRight = false;
                break;
            case KeyEvent.VK_D:
                currentAction = "walkingRight";
                goingRight = true;
                break;
            case KeyEvent.VK_W:
                currentAction = "jumpingRight";
                goingRight = true;
                isJumping = !getAnimationFor(currentAction).isStopped();
                keyPressed = isJumping;
                break;
            case KeyEvent.VK_S:
                if (goingRight) {
                    currentAction = "crouchingRight";
                    //System.out.println("Right");
                } else {
                    currentAction = "crouchingLeft";
                    // System.out.println("Left");

                }
                break;

            case KeyEvent.VK_J:
                if (goingRight) {
                    currentAction = "punchingRight";
                    // System.out.println("Right");
                } else {
                    currentAction = "punchingL";
                    // System.out.println("Left");
                }
                if (isAttackHittingRock() == true && getAnimationFor(currentAction).getCurrentFrameIndex() == 4) {
                    System.out.println("Hit");
                    nRock1HitCounter++;
                }
                //goingRight = true;
                break;

            case KeyEvent.VK_K:
                if (goingRight) {
                    currentAction = "chidoriRight";
                    //System.out.println("Right");
                } else {
                    currentAction = "chidoriLeft";
                    //System.out.println("Left");
                }
                //currentAction = "chidoriRight";

                if (isAttackHittingRock() == true && getAnimationFor(currentAction).getCurrentFrameIndex() == 18) {
                    nRock1HitCounter++;
                }
                //goingRight = true;
                break;
            case KeyEvent.VK_L:
                if (goingRight) {
                    currentAction = "rasenganRight";
                    //System.out.println("Right");
                } else {
                    currentAction = "rasenganLeft";
                    //System.out.println("Left");
                }
                //currentAction = "rasenganRight";
                if (isAttackHittingRock() == true && getAnimationFor(currentAction).getCurrentFrameIndex() == 24) {
                    nRock1HitCounter++;
                }
                //goingRight = true;
                break;
            case KeyEvent.VK_C:
                currentAction = "runningRight";
                goingRight = true;
                break;
            case KeyEvent.VK_Z:
                currentAction = "runningLeft";
                goingRight = false;
                break;
            default:
                keyPressed = false;
                break;
        }
        if (nRock1HitCounter == 4) {
            nRock1HitCounter = 3;
        }
    }
    // check what key is released

    public void keyReleased(KeyEvent e) {
        if (currentAction != null) {
            getAnimationFor(currentAction).reset();
        }
        keyPressed = false;

        int code = e.getKeyCode();
        // code to make character (Stage) to the left

        //if the W key is released the player will no longer be jumping
        if (code == KeyEvent.VK_W) {
            isJumping = false;
        }

    }

    public boolean isAbovePlatform() {
        int redPlatform1X = 750 - getX();
        int redPlatform2X = 950 - getX();
        if (PlayerX > (redPlatform1X - 28) && PlayerX < (redPlatform1X + 50) && getY() <= 148) {
            System.out.println("RED1");
            return true;
        } else if (PlayerX > (redPlatform2X - 28) && PlayerX < (redPlatform2X + 50) && getY() <= 148) {
            System.out.println("RED2");
            return true;
        } else {
            return false;
        }
    }

    public boolean isOnPlatform() {
        int redPlatform1X = 750 - getX();
        int redPlatform2X = 950 - getX();
        if (PlayerX > (redPlatform1X - 28) && PlayerX < (redPlatform1X + 50) && (getY() < 170) && (getY() > 146) && isJumping == false) {
            System.out.println("RED1");
            return true;
        } else if (PlayerX > (redPlatform2X - 28) && PlayerX < (redPlatform2X + 50) && (getY() < 170) && (getY() > 146) && isJumping == false) {
            System.out.println("RED2");
            return true;
        } else {
            return false;
        }
    }

    public boolean isAtRock() {
        if (PlayerX > (rock1X - 70) && PlayerX < (rock1X + 42)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAttackHittingRock() {
        int nFrontOfAttack;
        if ("chidoriRight".equals(currentAction)) {
            nFrontOfAttack = getX() + 20;
            if (nFrontOfAttack > (rock1X - 70) && nFrontOfAttack < (rock1X + 42)) {
                return true;
            }
        }
        if ("chidoriLeft".equals(currentAction)) {
            nFrontOfAttack = getX() - 20;
            if (nFrontOfAttack > (rock1X - 70) && nFrontOfAttack < (rock1X + 42)) {
                return true;
            }
        }
        if ("rasenganRight".equals(currentAction)) {
            nFrontOfAttack = getX() + 30;
            if (nFrontOfAttack > (rock1X - 70) && nFrontOfAttack < (rock1X + 42)) {
                return true;
            }
        }
        if ("rasenganLeft".equals(currentAction)) {
            nFrontOfAttack = getX() - 30;
            if (nFrontOfAttack > (rock1X - 70) && nFrontOfAttack < (rock1X + 42)) {
                return true;
            }
        }
        if ("punchingRight".equals(currentAction)) {
            nFrontOfAttack = getX() + 20;//anim.getCurrentFrameIndex()));
            if (nFrontOfAttack > (rock1X - 70) && nFrontOfAttack < (rock1X + 42)) {
                return true;
            }
            //anim.getCurrentFrameIndex()
        }
        return false;
    }

    private String frameImageFilename(String filenameBase, int i, String imageType) {
        // "walking_R", 1, "png" => "walking_R_f1.png"
        return filenameBase + "_f" + i + "." + imageType;
    }
}
