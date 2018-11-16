package tankwars;

import tankwars.brain.*;
import tankwars.gui.HUD;
import tankwars.gui.Map;
import tankwars.gui.MiniMap;
import tankwars.gui.Window;
import tankwars.objects.PlayerTank;

import javax.imageio.ImageIO;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameWorld extends Canvas implements Runnable {

    public final static int WIDTH = 1024;
    public final static int HEIGHT = 704;
    private Thread thread;
    private boolean running = false;
    private BufferedImage playerTank1 = null;
    private BufferedImage playerTank2 = null;
    private Controller handler;
    private HUD hud;
    private Spawner spawner;
    private PlayerTank p1,p2;
    private Map map;
    private MiniMap minimap;

    public GameWorld(){
        new Window(WIDTH, HEIGHT, "Tank Wars", this);
        handler = new Controller();
        map = new Map(handler);
        minimap = new MiniMap(handler);
        this.addKeyListener(new KeyInput(handler));
        this.start();
    }

    public void init(){
        try{
            playerTank1 = ImageIO.read(GameWorld.class.getResourceAsStream("/tank1.png"));
            playerTank2 = ImageIO.read(GameWorld.class.getResourceAsStream("/tank1.png"));

            p1 = new PlayerTank(playerTank1,0,100,"Player1", handler);
            p2 = new PlayerTank(playerTank2,0,200,"Player2", handler);
            handler.addObject(p1);
            handler.addObject(p2);
        }catch(Exception e){
            e.printStackTrace();
        }
        hud = new HUD(p1,p2, handler);
        spawner = new Spawner(hud,handler);
        MusicPlayer.playMusic();


    }

    public void start(){
        // allocate a new thread that links to the game
        thread = new Thread(this);
        // will go to the run function of this class
        thread.start();
        running = true;
    }

    public void stop(){
        try{
            // waits for thread to stop running (no more instructions to go through)
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
        System.exit(1);

    }

    public void run(){
        init();
        this.requestFocus(); // do not need to click on screen to interact with it
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                draw();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void tick(){
        handler.tick();
        spawner.tick();
        hud.tick();
    }

    public void draw(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        map.draw(g);
        handler.draw(g);
        minimap.draw(g);
        hud.draw(g);
        g.dispose();
        bs.show();
    }

    public static void main(String args[]){
        new GameWorld();
    }
}
