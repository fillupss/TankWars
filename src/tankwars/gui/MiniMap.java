package tankwars.gui;

import tankwars.GameWorld;
import tankwars.brain.Controller;
import tankwars.objects.*;

import java.awt.*;

public class MiniMap {

    final static int WIDTH = GameWorld.WIDTH / 4;
    final static int HEIGHT = GameWorld.HEIGHT / 4;
    final static int X = GameWorld.WIDTH - WIDTH;
    final static int Y = 0;
    private Controller handler;

    public MiniMap(Controller c){
        this.handler = c;
    }

    public void draw(Graphics g){
        g.setColor(new Color(222,184,135));
        g.fillRect(X,Y, this.WIDTH, this.HEIGHT);

        // go through each object in the controller and then scale their position based on the proportion
        // of Map -> MiniMap size and place them depending on where the MiniMap is located
        for(int i = 0; i < handler.getArray().size(); i++){
            GameObject tempObj = handler.getArray().get(i);
            if(tempObj.getId().contains("Player") && !tempObj.getId().contains("Bullet")){
                g.setColor(Color.green);
                g.fillOval((int)tempObj.getX()/4 + this.X , (int)tempObj.getY()/4 + this.Y,6,6);
            }
            else if(tempObj.getId().contains("EnemyBullet")){
                g.setColor(Color.black);
                g.fillRect((int)tempObj.getX()/4 + this.X , (int)tempObj.getY()/4 + this.Y,4,4);
            }
            else if(tempObj.getId().contains("PlayerBullet")){
                g.setColor(Color.orange);
                g.fillRect((int)tempObj.getX()/4 + this.X , (int)tempObj.getY()/4 + this.Y,4,4);
            }
            else if(tempObj.getId().equals("Enemy")){
                g.setColor(Color.red);
                g.fillOval((int)tempObj.getX()/4 + this.X , (int)tempObj.getY()/4 + this.Y,6,6);
            }
            else if(tempObj.getId().equals("Wall")){
                g.setColor(Color.gray);
                g.fillRect((int)tempObj.getX()/4 + this.X , (int)tempObj.getY()/4 + this.Y,8,8);
            }
            else if(tempObj.getId().equals("PowerUp")){
                g.setColor(Color.CYAN);
                g.fillRect((int)tempObj.getX()/4 + this.X , (int)tempObj.getY()/4 + this.Y,8,8);
            }
        }

        // make the boarder more thick
        Graphics2D g2 = (Graphics2D)g;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color(139,69,19));
        g2.drawRect(X,Y, this.WIDTH, this.HEIGHT);
        g2.setStroke(oldStroke);
    }
}

