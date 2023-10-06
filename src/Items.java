/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class Items extends AnimatedObject{
     Player p;
    public Items(int x, int y) {
        super(x, y);
    }
    
    public void Boulder(){
    
        Animation walkingRightAnimation = new Animation(p.createAnimationFrames("rockBreaking", "png", 4, 0, 0), this);
        setAnimationFor("walkingRight", walkingRightAnimation);
    }
    
}
