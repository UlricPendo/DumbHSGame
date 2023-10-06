
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
//import java.util.Date;

public class MainPan extends JPanel implements ActionListener {

    private Monster1 m1;
    private int nMonster1X = 1000;
    static boolean drawn = false;
    final private Player p;
    final private Timer timer;
    int x;
    int y;
    private Image background, RedPlatform1, RedPlatform2, rock1, RockBreaking_f1, RockBreaking_f2,
            RockBreaking_f3, Tree1, Tree2, Tree3, Grass, backGround2, BigTree, PetDuckyL, PetDuckyR;
    Image arnRockBreaking[] = new Image[5];
    // loading in the background image and set (timer/ speed) of the movment of the background 

    public MainPan() {
        //super();
        p = new Player();
        addKeyListener(new ActionListener());
        setFocusable(true);
        ImageIcon i1 = new ImageIcon("peter's background.png");
        ImageIcon i2 = new ImageIcon("Platform_red.png");
        ImageIcon i3 = new ImageIcon("Platform_red.png");
        ImageIcon i4 = new ImageIcon("rock.png");
        ImageIcon i5 = new ImageIcon("RockBreaking_f1.png");
        ImageIcon i6 = new ImageIcon("RockBreaking_f2.png");
        ImageIcon i7 = new ImageIcon("RockBreaking_f3.png");
        ImageIcon i8 = new ImageIcon("RockBreaking_f4.png");
        ImageIcon i9 = new ImageIcon("CurruptTree#1.png");
        ImageIcon i10 = new ImageIcon("CurruptTree#2.png");
        ImageIcon i11 = new ImageIcon("CurruptTree#3.png");
        ImageIcon i12 = new ImageIcon("CurruptGrassBlock.png");
        ImageIcon i13 = new ImageIcon("currupt background.png");
        ImageIcon i14 = new ImageIcon("BigTree.png");
        ImageIcon i15 = new ImageIcon("PetDuckyL.png");
        ImageIcon i16 = new ImageIcon("PetDuckyR.png");

        background = i1.getImage();
        backGround2 = i13.getImage();
        RedPlatform1 = i2.getImage();
        RedPlatform2 = i3.getImage();
        rock1 = i4.getImage();
        Tree1 = i9.getImage();
        Tree2 = i10.getImage();
        Tree3 = i11.getImage();
        Grass = i12.getImage();
        BigTree = i14.getImage();
        PetDuckyL = i15.getImage();
        PetDuckyR = i16.getImage();
        timer = new Timer(80, this);
        timer.start();

        arnRockBreaking[0] = i5.getImage();
        arnRockBreaking[1] = i6.getImage();
        arnRockBreaking[2] = i7.getImage();
        arnRockBreaking[3] = i8.getImage();

    }

    public void actionPerformed(ActionEvent arg0) {
        p.move();
        repaint();
        Monster1Movement();
    }

    public void paint(Graphics g) {

        // draw the background image to the stage
        //super.paint(g);
        // System.out.println(new Date());
        x = p.getX();
        y = p.getY();
        Graphics2D g2d = (Graphics2D) g;

        //System.out.println("Drawing background at: " + (350 - p.getX()));
        g2d.drawImage(backGround2, 350 - x, 0, null);
        // draws the grass
        int GrassPlacement = 360;
        for (int GrassCounter = 0; GrassCounter < 400; GrassCounter++) {
            g2d.drawImage(Grass, GrassPlacement - x, 312, null);
            GrassPlacement = GrassPlacement + 20;
        }
        // draws a large tree
        g2d.drawImage(BigTree, 4000 - x, - 35, null);

        // draws a bunch of tree#2
        int tree2Placement = 1450;
        for (int tree2Counter = 0; tree2Counter < 5; tree2Counter++) {
            g2d.drawImage(Tree2, tree2Placement - x, 45, null);
            tree2Placement = tree2Placement + 300;
        }
        // draws a bunch of Tree#3
        int tree3Placement = 1350;
        for (int tree3Counter = 0; tree3Counter < 5; tree3Counter++) {
            g2d.drawImage(Tree3, tree3Placement - x, 45, null);
            tree3Placement = tree3Placement + 300;
        }
        // draws red playform 1 and 2
        g2d.drawImage(RedPlatform1, 750 - x, 220, null);
        g2d.drawImage(RedPlatform2, 950 - x, 220, null);
        //draws the player
        g2d.drawImage(p.getImage(), 350, y, null);
        // draw a bunch of tree#1's
        int tree1Placement = 1300;
        for (int treeCounter = 0; treeCounter < 5; treeCounter++) {
            g2d.drawImage(Tree1, tree1Placement - x, 30, null);
            tree1Placement = tree1Placement + 300;
        }
        // draws monster#1
        if ((x + 351) > nMonster1X) {
            g2d.drawImage(PetDuckyR, nMonster1X - x, y+45, null);
        } else {
            g2d.drawImage(PetDuckyL, nMonster1X - x, y+45, null);
        }
        // draws the rock
        if (p.isRock1destroyed == false) {
            g2d.drawImage(arnRockBreaking[p.nRock1HitCounter], 1150 - x, 242, null);
            if (p.isRock1destroyed == true) {
                g2d.drawImage(arnRockBreaking[3], 1150 - x, 240, null);
            }//sevan is a lazy fucker
        }
    }

    public void Monster1Movement() {
        if ((x + 315) > nMonster1X) {
            nMonster1X += 5;
            System.out.println(nMonster1X);
            System.out.println("chasing1");
            System.out.println((x + 315) + " playerx");
        } else if ((x + 400) < nMonster1X) {
            nMonster1X -= 5;
            System.out.println("chasing2");
            System.out.println(nMonster1X + " monsterx");
            System.out.println((x + 400) + " playerx");
        }
    }

    private class ActionListener extends KeyAdapter {
        // set key pressed and release actions 

        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }
    }
}
// roflcopter
