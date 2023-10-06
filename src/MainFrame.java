
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    boolean isShowingMenu = true;

    //Name of Frame, Dementions of the frame and other verables about the frame
    public MainFrame() {
       add(new MainPan());
        
        if (isShowingMenu == true) {
            //add(new MenuPan());
        }

        setTitle("SexySideScroller Beta V 3.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 350);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
