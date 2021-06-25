package Presenter.Game;
import java.awt.*;
import javax.swing.*;

public abstract class ObjectParent implements Runnable{
    public ID id;
    public int x, y, width,height;
    public ObjectParent(ID id,int x,int y,int width, int height){
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    };

    public abstract void run();
    public abstract void draw(Graphics g);
    public abstract void stop();
}