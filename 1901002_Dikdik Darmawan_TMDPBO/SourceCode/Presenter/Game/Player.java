package Presenter.Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
public class Player implements Runnable{
    public ID id;
    protected int x, y, width,height;
    public boolean gravitasiA = true;
    public int jumpHeight = 150;
    public int targetJumpHeight = 0;
    public boolean inFloor = false;
	public Player(ID id,int x,int y,int width, int height){
		this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
	}

    public void run(){
        while (true) {
            action();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.err.println("Error: Thread Interrupted.");
            }
        }
    }

    public void draw(Graphics g)
    {
        Image image = new ImageIcon(this.getClass().getResource("spider.png")).getImage();
        g.drawImage(image, x, y,width,height,null);
    }

    private void action(){
        if(gravitasiA){
           y += 10;
           targetJumpHeight =0;
        }else if(!gravitasiA && targetJumpHeight != 0){
            if(targetJumpHeight < y){
                y -= 10;
            }else{
                gravitasiA = true;
            }
        }
    }
}