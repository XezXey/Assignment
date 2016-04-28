package f2.MySpaceWar;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

    int step = 8;

    public SpaceShip(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public void move(int dirLR, int dirUD){
        x += (step * dirLR);
        y += (step * dirUD);
        if(x < 0 && y < 0){
            x = 0;
            y = 0;
        }
        if(x > 400 - width && y > 400-height){
            x = 400 - width;
            y = 400 - height;
        }

    }
}
