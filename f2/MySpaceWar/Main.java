package f2.MySpaceWar;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        
        JFrame space = new JFrame("Space War");
        space.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        space.setSize(400,650);
        space.getContentPane().setLayout(new BorderLayout());
        SpaceShip ship = new SpaceShip(180, 550, 20, 20);
        GamePanel gp = new GamePanel();
        GameEngine engine = new GameEngine(gp, ship);
        space.getContentPane().add(gp, BorderLayout.CENTER);
        space.addKeyListener(engine);
        engine.start();
        space.setVisible(true);
        System.out.println("END");
    }
}
