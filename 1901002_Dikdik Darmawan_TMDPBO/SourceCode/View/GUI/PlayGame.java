package View.GUI;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import Presenter.Game.*;
import Presenter.*;
public class PlayGame extends Window implements Runnable {
	// Image gambar;
 //    private Image background;    
 //    private AudioClip soundTrack;
    private WallTingkatan tingkatan1;
    private WallTingkatan tingkatan2;
    private WallTingkatan tingkatan3;
    private Image background;
    private Dimension size;
    private Player player;
    private Floor floor;
    private Handler handler;
    private KeyListerner key;
    private int timeT1,timeT2,timeT3;
    private Threads threads;
    private Random rd = new Random();
    private PointGame point = new PointGame();
    private int downTimeTarget = 0;
    private int downTime = 0;
    private int downTimeTarget1 = 0;
    private int downTime1 = 0;
    private int downTimeTarget2 = 0;
    private int downTime2 = 0;
    private Sound soundBackground;
    ProsesData pData;
    private String username;
    public PlayGame(String username){
        setBackground(Color.BLACK);
        setTitle("Game Di Mulai");
        size = new Dimension();
        background = new ImageIcon(this.getClass().getResource("Background.png")).getImage();
        size.width = background.getWidth(null);
        size.height = background.getHeight(null);
        threads = new Threads();
        player = new Player(ID.Player,50,50,50,50);
        tingkatan1 = new WallTingkatan(ID.Tingkat1,200,50,WIDTH,HEIGHT,rd.nextBoolean(),player,randomInt(5,1),point);
        tingkatan2 = new WallTingkatan(ID.Tingkat2,200,50,WIDTH,HEIGHT,rd.nextBoolean(),player,randomInt(5,1),point);
        tingkatan3 = new WallTingkatan(ID.Tingkat3,200,50,WIDTH,HEIGHT,rd.nextBoolean(),player,randomInt(5,1),point);
        floor = new Floor(ID.Floor,0,HEIGHT-50,WIDTH, 50, 1,player);
        timeT1 = timeT2 = timeT3 = 0;
        setPreferredSize(size);
        key = new KeyListerner(WIDTH,HEIGHT);
        soundBackground = new Sound("music_game.wav");
        soundBackground.loop();
        try{
            pData = new ProsesData();
        }catch(Exception e){
            System.out.print(e);
        }
        this.username = username;
    }

    public void run(){
        threads.add(new Thread(tingkatan1));
        threads.add(new Thread(tingkatan2));
        threads.add(new Thread(tingkatan3));
        threads.add(new Thread(player));
        threads.add(new Thread(floor));
        threads.start();
        while (!key.exitInput()) {
            key();
            swap();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.err.println("Error: Thread Interrupted.");
            }
        }
        closeWindows();
        dispose();
        soundBackground.stop();
        try{
            pData.sendData(this.username,point.getSuccess(),point.getFail());
            new Home();
        }catch(Exception e){
            System.err.println(e);            
        }
    }

    public void paint(Graphics g) {
        Image dbImg = createImage(getWidth(), getHeight());
        Graphics dbg = dbImg.getGraphics();
        draw(dbg);
        g.drawImage(dbImg, 0, 0, this);          
    }  

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, WIDTH,HEIGHT,this);       
        repaint();
        g2d.setColor(Color.WHITE);
        g2d.drawString("SUCCESS : "+ point.getSuccess(), 20, 60);
        g2d.drawString("FAIL : "+point.getFail(), 20, 80);
        player.draw(g2d);
        tingkatan1.draw(g2d);
        tingkatan2.draw(g2d);
        tingkatan3.draw(g2d);
        floor.draw(g2d);
    }

    public void key(){
        key.processInput(player);
        addKeyListener(key);
    }

    public void swap(){

            WallTingkatan tamp;
            if(tingkatan1.readySwap && this.downTimeTarget == 0){
                this.downTimeTarget = 30;
                this.downTime = 0;
                tingkatan1.clearHandler();
                tingkatan1.stop();
                player.gravitasiA = true;
            }

            if(downTime == downTimeTarget && downTimeTarget != 0){
                tingkatan1 = tingkatan2;
                tingkatan1.id = ID.Tingkat1;
                tingkatan1.allDown();
                tingkatan2 = tingkatan3;
                tingkatan2.id = ID.Tingkat2;
                tingkatan2.allDown();
                tingkatan3 = new WallTingkatan(ID.Tingkat3,200,50,WIDTH,HEIGHT,rd.nextBoolean(),player,randomInt(5,1),point);
                threads = new Threads();
                threads.add(new Thread(tingkatan3));
                threads.start();
                downTimeTarget = 0;
            }else if(downTimeTarget != 0){
                downTime++;
            }
    }

    public int randomInt(int max,int min){
        int range = max - min + 1;
        return rd.nextInt(range) + min;
    }
} 