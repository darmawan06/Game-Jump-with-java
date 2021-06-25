package Presenter.Game;
import java.awt.Graphics;
import java.util.Random;

public class WallTingkatan implements Runnable{
	public ID id;
	public int x,y,width,height,noImage,WIDTHWINDOWS,HEIGHTWINDOWS;
	public boolean move,exit;
	public Handler handler;
	public int timeAdd;
	public Random rd;
	public Player player;
    private Collusion collusion;
    private int deleteTime = 0;
    private int deleteTimeTarget = 0;
    private int minPointTime = 0;
    private int minPointTimeTarget = 0;

    public boolean readySwap = false;
    private PointGame point;
	
	public WallTingkatan(ID id,int width,int height,int widthWindows,int heightWindows,boolean move,Player player, int noImage,PointGame point){
		this.id = id;
		this.handler = new Handler();
		this.rd = new Random();
		this.WIDTHWINDOWS = widthWindows;
		this.HEIGHTWINDOWS = heightWindows;
		this.noImage = noImage;
		this.width = width;
		this.height = height;
		this.move = move;
		this.timeAdd = 0;
		this.point = point;
		this.collusion = new Collusion(this.point);
		this.player = player;
		initX();
	}

	public void initY(){
		if(this.id == ID.Tingkat1){
			y = HEIGHTWINDOWS-200;
		}else if(this.id == ID.Tingkat2){
			y = HEIGHTWINDOWS-350;
		}else if(this.id == ID.Tingkat3){
			y = HEIGHTWINDOWS-500;
		}
	}

	public int initX(){
		if(this.move){
			this.x = -100;
		}else{
			this.x = this.WIDTHWINDOWS+100;
		}
		return x;
	}
	public void run(){
		while (!exit) {
        	initY();
        	add();
        	if(this.collusion.check(player,handler) == 0 && this.deleteTimeTarget == 0){
                this.deleteTimeTarget = 30;
                this.deleteTime = 0;
        	}
			if(deleteTime == deleteTimeTarget && deleteTimeTarget != 0){
                deleteTimeTarget = 0;
               	player.inFloor = false;
            	player.gravitasiA = true;
            	point.tambahSuccess();
				this.readySwap = true;
            }else if(deleteTimeTarget != 0){
                deleteTime++;
            }

            if(this.collusion.check(player,handler) == 1 && this.minPointTimeTarget == 0){
                this.minPointTimeTarget = 5;
                this.minPointTime = 0;
        	}
			if(minPointTime == minPointTimeTarget && minPointTimeTarget != 0){
                minPointTimeTarget = 0;
            	point.tambahFail();
            }else if(minPointTimeTarget != 0){
                minPointTime++;
            }
            try {
                Thread.sleep(20);
            }catch (InterruptedException ex) {
                System.err.println("Error: Thread Interrupted.");
            }
        }
	}

	public void draw(Graphics g)
    {
    	handler.draw(g);
    }

    public void add(){
		if(this.timeAdd >= 50){
			this.timeAdd = 0;
		}
		if(this.timeAdd == 0){
			int max = 3;
			int min = 1;
			int range = max - min + 1;
			int hasil = rd.nextInt(range) + min;
			if( hasil % 2 != 0){
				handler.addObject(new Wall(this.id,this.x,this.y,this.width, this.height, this.noImage,this.move));    	
			}
		}
		this.timeAdd++;		
	}

	public void allDown(){
		for (int i=0;i<handler.object.size();i++) {
			handler.object.get(i).y += 150;
		}
	}
	public void clearHandler(){
		handler.object.clear();
	}

	public void stop()
    {
    	for (int i=0;i<handler.object.size();i++) {
			handler.object.get(i).stop();
		}
        exit = true;
    }
}

