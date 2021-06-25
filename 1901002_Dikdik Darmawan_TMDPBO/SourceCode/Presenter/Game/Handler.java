package Presenter.Game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    public LinkedList<ObjectParent> object = new LinkedList<ObjectParent>();
    public Thread threads;
    public void draw(Graphics g){
        for(int i=0;i<object.size(); i++){
            ObjectParent tempObject = object.get(i);
            tempObject.draw(g);
        }
    }
    
    public void addObject(ObjectParent obj){
        this.object.add(obj);
        threads = new Thread(obj);
        threads.start();
    }
    
    public void removeObject(ObjectParent obj){
        this.object.remove(obj);
    }
}
