package Presenter.Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
public class Wall extends ObjectParent implements Runnable{
    int noImage;
    boolean move;
    boolean exit;
	public Wall(ID id,int x,int y,int width, int height, int noimage,boolean move){
		super(id, x, y, width, height);
        this.noImage = noimage;
        this.move = move;
        if(move){
            this.x = -200;
        }else{
            this.x += 200;
        }
	}

    public void run(){
        while (!exit) {
            if(move){
                x += 2;
            }else{
                x -= 2;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.err.println("Error: Thread Interrupted.");
            }
        }
    }

    public void draw(Graphics g)
    {
        Image image = new ImageIcon(this.getClass().getResource("Floor/floor ("+noImage+").png")).getImage();
        g.drawImage(image, x, y,width,height,null);
    }
    
    public void stop()
    {
        exit = true;
    }
    
}