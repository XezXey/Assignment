package f2.MySpaceWar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    private BufferedImage bi;
    Graphics2D big;
    ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    public GamePanel(){
        bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
        big = (Graphics2D) bi.getGraphics();
        big.setBackground(Color.BLACK);
    }

    public void updateGameUI(GameReporter reporter){
        big.clearRect(0, 0, 400, 600);

        big.setColor(Color.WHITE);
        
        for(Sprite s : sprites){
            s.draw(big);
        }
    
    //repaint();
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bi, null, 0, 0);
    }
}
