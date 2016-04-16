package f2.MySpaceWar;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

public class GameEngine implements GameReporter,KeyListener {
    GamePanel gp;

    private SpaceShip ship;
    private long score = 0;
    private Timer timer;

    public GameEngine(GamePanel gp, SpaceShip ship) {
        this.gp = gp;
        this.ship = ship;
        gp.sprites.add(ship);

        timer = new Timer(50, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                process();
            }
        });
        timer.setRepeats(true);
    }
    public void start(){
        timer.start();
    }

    public long getScore(){
        return score;
    }

    private void process(){


        gp.updateGameUI(this);
        Rectangle2D.Double vr = ship.getRectangle();
        Rectangle2D.Double er;
        }

    void controlVehicle(KeyEvent e){
        switch(e.getKeyCode()){
        case KeyEvent.VK_LEFT:
            ship.move(-1);
            break;
        case KeyEvent.VK_RIGHT:
            ship.move(1);
            break;
        /*case KeyEvent.VK_UP:
            v.move();
            break;
        case KeyEvent.VK_DOWN:
            v.move();
            break;
        */
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        controlVehicle(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
        //Nothing
    }

    @Override
    public void keyTyped(KeyEvent e){
        //Nothing
    }
}
