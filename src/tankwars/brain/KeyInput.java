package tankwars.brain;

import tankwars.brain.Controller;
import tankwars.objects.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    // going to be used to access objects that are player
    private Controller hand;

    // up, down, left, right, CCW, CW, Shoot
    boolean[] keyPressedP1 = new boolean[7];
    boolean[] keyPressedP2 = new boolean[7];

    public KeyInput(Controller handler){
        this.hand = handler;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }

        for(int i = 0; i < this.hand.handler.size(); i++){
            GameObject temp = hand.handler.get(i);
            if(temp.getId().equals("Player1")){
                PlayerTank temp1 = (PlayerTank) temp;
                if(keyCode == KeyEvent.VK_W){
                    temp1.moveUp();
                    keyPressedP1[0] = true;
                }
                if(keyCode == KeyEvent.VK_S){
                    temp1.moveDown();
                    keyPressedP1[1] = true;
                }
                if(keyCode == KeyEvent.VK_A){
                    temp1.moveLeft();
                    keyPressedP1[2] = true;
                }
                if(keyCode == KeyEvent.VK_D){
                    temp1.moveRight();
                    keyPressedP1[3] = true;
                }
                if(keyCode == KeyEvent.VK_Q){
                    temp1.rotateCW();
                    keyPressedP1[4] = true;
                }
                if(keyCode == KeyEvent.VK_E){
                    temp1.rotateCCW();
                    keyPressedP1[5] = true;
                }
                if(keyCode == KeyEvent.VK_SPACE){
                    if(!keyPressedP1[6]){
                        temp1.shoot();
                        keyPressedP1[6] = true;
                    }
                }
            }
            if(temp.getId().equals("Player2")){
                PlayerTank temp1 = (PlayerTank) temp;
                if(keyCode == KeyEvent.VK_UP){
                    temp1.moveUp();
                    keyPressedP2[0] = true;
                }
                if(keyCode == KeyEvent.VK_DOWN){
                    temp1.moveDown();
                    keyPressedP2[1] = true;
                }
                if(keyCode == KeyEvent.VK_LEFT){
                    temp1.moveLeft();
                    keyPressedP2[2] = true;
                }
                if(keyCode == KeyEvent.VK_RIGHT){
                    temp1.moveRight();
                    keyPressedP2[3] = true;
                }
                if(keyCode == KeyEvent.VK_BACK_SLASH){
                    temp1.rotateCCW();
                    keyPressedP2[4] = true;
                }
                if(keyCode == KeyEvent.VK_SHIFT){
                    temp1.rotateCW();
                    keyPressedP2[5] = true;
                }
                if(keyCode == KeyEvent.VK_ENTER){
                    if(!keyPressedP2[6]){
                        temp1.shoot();
                        keyPressedP2[6] = true;
                    }
                }
            }
        }

    }

    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        for(int i = 0; i < this.hand.handler.size(); i++){
            GameObject temp = hand.handler.get(i);
            if(temp.getId().equals("Player1")){
                PlayerTank temp1 = (PlayerTank) temp;
                if(keyCode == KeyEvent.VK_W){
                    keyPressedP1[0] = false;
                }
                if(keyCode == KeyEvent.VK_S){
                    keyPressedP1[1] = false;
                }
                if(keyCode == KeyEvent.VK_A){
                    keyPressedP1[2] = false;
                }
                if(keyCode == KeyEvent.VK_D){
                    keyPressedP1[3] = false;
                }
                if(keyCode == KeyEvent.VK_Q){
                    keyPressedP1[4] = false;
                }
                if(keyCode == KeyEvent.VK_E){
                    keyPressedP1[5] = false;
                }
                if(keyCode == KeyEvent.VK_SPACE){
                    keyPressedP1[6] = false;
                }
                if(!keyPressedP1[0] && !keyPressedP1[1]){
                    temp1.stopY();
                }
                if(!keyPressedP1[2] && !keyPressedP1[3]){
                    temp1.stopX();
                }
                if(!keyPressedP1[4] && !keyPressedP1[5]){
                    temp1.stopRotating();
                }
            }
            if(temp.getId().equals("Player2")){
                PlayerTank temp1 = (PlayerTank) temp;
                if(keyCode == KeyEvent.VK_UP){
                    keyPressedP2[0] = false;
                }
                if(keyCode == KeyEvent.VK_DOWN){
                    keyPressedP2[1] = false;
                }
                if(keyCode == KeyEvent.VK_LEFT){
                    keyPressedP2[2] = false;
                }
                if(keyCode == KeyEvent.VK_RIGHT){
                    keyPressedP2[3] = false;
                }
                if(keyCode == KeyEvent.VK_BACK_SLASH){
                    keyPressedP2[4] = false;
                }
                if(keyCode == KeyEvent.VK_SHIFT){
                    keyPressedP2[5] = false;
                }
                if(keyCode == KeyEvent.VK_ENTER){
                    keyPressedP2[6] = false;
                }
                if(!keyPressedP2[0] && !keyPressedP2[1]){
                    temp1.stopY();
                }
                if(!keyPressedP2[2] && !keyPressedP2[3]){
                    temp1.stopX();
                }
                if(!keyPressedP2[4] && !keyPressedP2[5]){
                    temp1.stopRotating();
                }
            }
        }
    }
}
