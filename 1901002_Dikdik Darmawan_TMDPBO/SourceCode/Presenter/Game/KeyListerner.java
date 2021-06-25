package Presenter.Game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class KeyListerner extends KeyboardInput{
    protected int HEIGHT;
    protected int WIDTH;
    private Sound effectSound;
    public KeyListerner(int width,int height){
        effectSound = new Sound("Jump.wav");
        WIDTH = width;
        HEIGHT = height;
    }

    public void processInput(Player player) {

        poll();
        // If moving up
        if( keyDown( KeyEvent.VK_UP ) ) {
            if(!player.gravitasiA && player.targetJumpHeight == 0){
                player.targetJumpHeight = player.y - player.jumpHeight;
                effectSound.play();             
            }
        }
        // If moving left
        if( keyDown( KeyEvent.VK_LEFT ) ) {
          // Check collision with left
            player.x -= 20;
          if( player.x < 0 )
            player.x = 0;
        }
        // If moving right
        if( keyDown( KeyEvent.VK_RIGHT ) ) {
            player.x += 20;
          // Check collision with right
          if( player.x + player.height > WIDTH - 1 )
            player.x = WIDTH - player.height - 1;
        }
    }

    public boolean exitInput(){
        poll();
        if(keyDownOnce( KeyEvent.VK_SPACE )){
            return true;
        }
        return false;
    }
}