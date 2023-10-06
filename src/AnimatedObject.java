
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gangs_000
 */
class AnimatedObject {

    private int x, y;
    private final Map<String, Animation> animations = new HashMap<>();

    public AnimatedObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Animation getAnimationFor(String name) {
        return animations.get(name);
    }

    public void setAnimationFor(String name, Animation animation) {
        animations.put(name, animation);
    }

    public void changePosition(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }
}