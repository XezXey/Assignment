package f2.MySpaceWar;

import javax.swing.JButton;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;
import java.awt.*;

public class GameEngine implements GameReporter,KeyListener {
    GamePanel gp;

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private SpaceShip ship;
    private long score = 0;
    private Timer timer;
    private int x;
    private double difficulty = 0.03;

    public GameEngine(GamePanel gp, SpaceShip ship) {
        this.gp = gp;
        this.ship = ship;
        gp.sprites.add(ship);

        /*GridLayout layout = new GridLayout(2,1);
        JButton hard = new JButton("hard");
        gp.setLayout(layout);
        hard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                difficulty += 0.01;
                System.out.println("Difficulty : " + difficulty);
            }
        });
        gp.add(hard, BorderLayout.PAGE_END);
        */
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


    public void generateEnemy(){
        Enemy e = new Enemy((int)(Math.random()* 390),30);
        gp.sprites.add(e);
        enemies.add(e);
    }
    public long getScore(){
        return score;
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
            }
        }

        gp.updateGameUI(this);
        Rectangle2D.Double vr = ship.getRectangle();
        Rectangle2D.Double er;

        for(Enemy e : enemies){
            er = e.getRectangle();
        }
        }

    void controlVehicle(KeyEvent e){
        switch(e.getKeyCode()){
        case KeyEvent.VK_LEFT:
            ship.move(-1);
            break;
        case KeyEvent.VK_RIGHT:
            ship.move(1);
            break;
        case KeyEvent.VK_H:
            if(difficulty <= 0.1 ){
                difficulty += 0.01;
                System.out.println("Difficulty : " + difficulty);
            }
            /*while(ee_iter.hasNext()){
                    Enemy ee = ee_iter.next();
                    ee.harder();
            }*/
            break;
        case KeyEvent.VK_L:
            if(difficulty >= 0.02 ){
                difficulty -= 0.01;
                System.out.println("Difficulty : " + difficulty);
            }
            /*while(ee_iter.hasNext()){
                Enemy ee = ee_iter.next();
                ee.easier();
            }*/
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
