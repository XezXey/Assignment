package f2.MySpaceWar;

import javax.swing.JButton;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameEngine implements GameReporter,KeyListener{
    GamePanel gp;

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private SpaceShip ship;
    private long score = 0;
    private Timer timer;
    private int x;
    private double harderFactor = 500000;
    private double difficulty = 0.01;
    private boolean checkDead = true;

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
        //gp.add(new JLabel(Double.toString(score)));
        timer.start();
    }


    public void generateEnemy(){
        Enemy e = new Enemy((int)(Math.random()* 390),30);
        gp.sprites.add(e);
        enemies.add(e);
    }

    private void process(){

        if(Math.random()/10 < difficulty){
            generateEnemy();
        }
        Iterator<Enemy> e_iter = enemies.iterator();
        while(e_iter.hasNext()){
            Enemy e = e_iter.next();
            e.proceed();
            if(!e.isAlive()){
                e_iter.remove();
                gp.sprites.remove(e);
                score += 100;
                if(((double)score/harderFactor) >= difficulty){
                    difficulty+=0.005;
                    System.out.println("Harder!!!(Diff is" + difficulty + ")" );
        }
            }
        }

        gp.updateGameUI(this);
        Rectangle2D.Double vr = ship.getRectangle();
        Rectangle2D.Double er;

        for(Enemy e : enemies){
            er = e.getRectangle();
            if(er.intersects(vr)){
                die();
                return;
            }
        }
    }

    public void die(){
            //checkDead = true;
            timer.stop();
            JOptionPane.showMessageDialog(gp,"High Score : " + score +"\nPress OK to NewGame");
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
            score = 0;

            Iterator<Enemy> e_iter = enemies.iterator();
            while(e_iter.hasNext()){
                Enemy e = e_iter.next();
                    e_iter.remove();
                    gp.sprites.remove(e);
            }
            timer.start();
        
        }

    void controlVehicle(KeyEvent e){
        switch(e.getKeyCode()){
        case KeyEvent.VK_LEFT:
            ship.move(-1, 0);
            break;
        case KeyEvent.VK_RIGHT:
            ship.move(1, 0);
            break;
        case KeyEvent.VK_H:
            if(difficulty <= 0.1 ){
                difficulty += 0.01;
                System.out.println("Difficulty : " + difficulty);
            }
            break;
        case KeyEvent.VK_L:
            if(difficulty >= 0.02 ){
                difficulty -= 0.01;
                System.out.println("Difficulty : " + difficulty);
            }
            break;
        case KeyEvent.VK_UP:
            ship.move(0, -1);
            break;
        case KeyEvent.VK_DOWN:
            ship.move(0, 1);
            break;
        }
    }


    public long getScore(){
        return score;
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
