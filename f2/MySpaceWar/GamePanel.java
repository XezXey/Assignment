package f2.MySpaceWar;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;

public class GamePanel extends JPanel{
    private BufferedImage bi;
    Graphics2D big;
    private Image img;
    ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    public GamePanel(){
        try{
            img = ImageIO.read(new File("Galaxy.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }
        //Image img = Toolkit.getDefualtToolkit().createImage("Galaxy.jpg");
        bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
        big = (Graphics2D) bi.getGraphics();
        //big.setBackground(Color.BLACK);
    }

    public void updateGameUI(GameReporter reporter){
        big.clearRect(0, 0, 400, 600);
        big.drawImage(img, 0, 0, Color.BLACK, null);

        big.setColor(Color.WHITE);
        big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
        for(Sprite s : sprites){
            s.draw(big);
        }
    
    repaint();
    }
    
    @Override
    public void paint(Graphics g){
        //Graphics2D g2d = (Graphics2D) g;
        g.drawImage(bi, 0, 0, null);
    }
}
