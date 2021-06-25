package Presenter.Game;
import java.util.LinkedList;

public class Threads {
	 public LinkedList<Thread> thread = new LinkedList<Thread>();

	 public void add(Thread obj){
	 	this.thread.add(obj);
	 }

	 public void start(){
	 	for (int i=0;i<this.thread.size();i++) {
	 		this.thread.get(i).start(); 		
	 	}
	 }

	public void remove(Thread obj){
        this.thread.remove(obj);
    }
}