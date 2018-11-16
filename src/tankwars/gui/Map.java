package tankwars.gui;

import tankwars.GameWorld;
import tankwars.brain.Controller;
import tankwars.objects.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Map{

    private BufferedImage block = null;
    private BufferedImage superwall = null;
    private BufferedImage normalwall = null;
    private Controller handler;
    boolean initialMap = true;

    public Map(Controller c){
        this.handler = c;
    }

    public void draw(Graphics g){
        try{
            block = ImageIO.read(Map.class.getResourceAsStream("/GroundTile.png"));
            for(int i = 0; i < GameWorld.WIDTH; i = i + block.getWidth()){
                for(int j = 0; j < GameWorld.HEIGHT; j = j + block.getHeight()){
                    g.drawImage(block, i, j, block.getWidth(), block.getHeight(),null);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        // we use initialMap so that the walls do not respawn if broken down
        if(initialMap){
            try{
                normalwall = ImageIO.read(Map.class.getResourceAsStream("/NormalWall.png"));
                superwall = ImageIO.read(Map.class.getResourceAsStream("/SuperWall.png"));
                handler.addObject(new SuperWalls(superwall,96,96,"Wall", handler));
                handler.addObject(new SuperWalls(superwall,96,128,"Wall", handler));
                handler.addObject(new SuperWalls(superwall,96,160,"Wall", handler));
                handler.addObject(new SuperWalls(superwall,96,192,"Wall", handler));
                handler.addObject(new SuperWalls(superwall,96,224,"Wall", handler));
                handler.addObject(new NormalWalls(normalwall,96, GameWorld.HEIGHT/2,"Wall", handler));
                handler.addObject(new NormalWalls(normalwall,96, GameWorld.HEIGHT/2 + 32,"Wall", handler));
                handler.addObject(new NormalWalls(normalwall,96, GameWorld.HEIGHT/2 + 64,"Wall", handler));
                handler.addObject(new NormalWalls(normalwall,96, GameWorld.HEIGHT/2 + 96,"Wall", handler));
                handler.addObject(new NormalWalls(normalwall,96, GameWorld.HEIGHT/2 + 128,"Wall", handler));

                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 320, GameWorld.HEIGHT/2,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 320, GameWorld.HEIGHT/2 + 32,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 320, GameWorld.HEIGHT/2 + 64,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 320, GameWorld.HEIGHT/2 + 96,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 320, GameWorld.HEIGHT/2 + 128,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 320, GameWorld.HEIGHT/2 + 160,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 320, GameWorld.HEIGHT/2 + 192,
                        "Wall", handler));
                handler.addObject(new SuperWalls(superwall, GameWorld.WIDTH/2 - 288, GameWorld.HEIGHT/2,
                        "Wall", handler));
                handler.addObject(new SuperWalls(superwall, GameWorld.WIDTH/2 - 256, GameWorld.HEIGHT/2,
                        "Wall", handler));
                handler.addObject(new SuperWalls(superwall, GameWorld.WIDTH/2 - 224, GameWorld.HEIGHT/2,
                        "Wall", handler));
                handler.addObject(new SuperWalls(superwall, GameWorld.WIDTH/2 - 192, GameWorld.HEIGHT/2,
                        "Wall", handler));
                handler.addObject(new SuperWalls(superwall, GameWorld.WIDTH/2 - 160, GameWorld.HEIGHT/2,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 160, GameWorld.HEIGHT/2 + 32,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 160, GameWorld.HEIGHT/2 + 64,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 160, GameWorld.HEIGHT/2 + 96,
                        "Wall", handler));
                handler.addObject(new SuperWalls(superwall, GameWorld.WIDTH/2 - 160, GameWorld.HEIGHT/2 + 128,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 - 160, GameWorld.HEIGHT/2 + 160,
                        "Wall", handler));
                handler.addObject(new SuperWalls(superwall, GameWorld.WIDTH/2 - 160, GameWorld.HEIGHT/2 + 192,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 64, GameWorld.HEIGHT/2- 160,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 64, GameWorld.HEIGHT/2- 192,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 64, GameWorld.HEIGHT/2- 224,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 96, GameWorld.HEIGHT/2- 224,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 128, GameWorld.HEIGHT/2- 224,
                        "Wall", handler));

                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 224, GameWorld.HEIGHT/2 + 128,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 256, GameWorld.HEIGHT/2 + 128,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 288, GameWorld.HEIGHT/2 + 128,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 320, GameWorld.HEIGHT/2 + 128,
                        "Wall", handler));
                handler.addObject(new NormalWalls(normalwall, GameWorld.WIDTH/2 + 352, GameWorld.HEIGHT/2 + 128,
                        "Wall", handler));

                handler.addObject(new SuperWalls(superwall, GameWorld.WIDTH/2 - 32, GameWorld.HEIGHT/2 + 32,
                        "Wall", handler));
                handler.addObject(new SuperWalls(superwall, GameWorld.WIDTH/2 - 96, GameWorld.HEIGHT/2 - 128,
                        "Wall", handler));

                initialMap = false;
            }catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}

