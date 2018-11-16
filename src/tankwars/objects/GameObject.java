package tankwars.objects;

import tankwars.GameWorld;
import tankwars.brain.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected BufferedImage image;
    protected float x;
    protected float y;
    protected float velX;
    protected float velY;
    protected Controller cont;
    protected GameWorld game;
    protected String id;

    public GameObject(BufferedImage img, float x, float y, String id, Controller c){
        this.image = img;
        this.x = x;
        this.y = y;
        this.id = id;
        this.cont = c;
    }

    public abstract void tick();
    public abstract void draw(Graphics g);
    public Rectangle getCollisionBounds(){
        return new Rectangle((int)this.x, (int)this.y, this.image.getWidth(), this.image.getHeight());
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public String getId(){
        return id;
    }
}

