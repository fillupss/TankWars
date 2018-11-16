package tankwars.objects;

import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Obstacles extends GameObject {

    protected float health;

    public Obstacles(BufferedImage img, float x, float y, String id, Controller c){
        super(img,x,y,id,c);
    }

    public abstract void tick();
    public abstract void draw(Graphics g);

    public float getHealth(){
        return health;
    }

    public void setHealth(float health){
        this.health = health;
    }

    public void isDead(){
        // if the obstacle is 0 health by collision, then remove this object via the controller
        if(health <= 0)
            cont.removeObject(this);

    }

}

