package f2.MySpaceWar;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

public class GameEngine implements GameReporter {
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
        //Rectangle2D.Double vr = ship.getRectangle();
    }
}


