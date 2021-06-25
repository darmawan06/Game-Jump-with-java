package Presenter.Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
public class Floor implements Runnable{
    int noImage;
    public Player player;
    public ID id;
    public int x, y, width,height;
	public Floor(ID id,int x,int y,int width, int height, int noimage,Player player){
        this.noImage = noimage;
        this.player = player;
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
	}

    public void run(){
        while (true) {
            checkColussion(this.player);
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

    public void checkColussion(Player player){
        int floorLeft = this.x;
        int floorRight = this.x + this.width;
        int floorTop = this.y;
        int floorBottom = this.y + this.height;

        int playerLeft = player.x;
        int playerRight = player.x + player.width;
        int playerTop = player.y;
        int playerBottom = player.y + player.height;

        if (floorLeft < playerRight && floorRight > playerLeft 
            && floorTop < playerBottom && floorBottom > playerTop) {
            if(playerTop < floorTop){
                player.y = floorTop - this.height-1;
                player.gravitasiA = false;
                player.inFloor = true;
            }
        }
    }
}