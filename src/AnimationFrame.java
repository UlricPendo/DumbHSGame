
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gangs_000
 */
public class AnimationFrame {

    private final ImageIcon image;
    private int deltaX, deltaY;

    public Image getImage() {
        return image.getImage();
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public AnimationFrame(ImageIcon image, int deltaX, int deltaY) {
        this.image = image;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public AnimationFrame(String filename, int deltaX, int deltaY) {
        this(new ImageIcon(filename), deltaX, deltaY);
    }
}
