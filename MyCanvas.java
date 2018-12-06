package firstTry;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;
import sun.audio.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MyCanvas extends Canvas implements KeyListener {
	AudioStream as;
	Image img;
	Image img2;
	Image img3;
	Goodguy link = new Goodguy (10,10,100,110,"file/mascots.png");
	Badguy villain = new Badguy (10,10, 100,200, "file/villain.png");
	LinkedList badguys = new LinkedList();
	LinkedList knives = new LinkedList();
	int seconds = 0;
	boolean GoogGuyKilled = false;
	
	public MyCanvas() {
		img = Toolkit.getDefaultToolkit().createImage("file/backround.png");
		img2 = Toolkit.getDefaultToolkit().createImage("file/win.png");
		img3 = Toolkit.getDefaultToolkit().createImage("file/Loss.jpg");
		this.setSize(600,400);
		this.addKeyListener(this); 
		playIt("file/Music.wav");

		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		
		for(int i = 0; i < 2; i++) { // <100 sets how many bad guys are made
			int rx = rand.nextInt(winwidth);
			int ry = rand.nextInt(winheight);
			Badguy bg = new Badguy(rx,ry,50,50,"file/villian.png");
			Rectangle r = new Rectangle(rx,ry,30,30);
		
			if (r.contains(villain.getxCoord(),villain.getyCoord())) {
				System.out.println("Badguy on top of link");
				continue;
			}
			badguys.add(bg);
			
			Knife k = new Knife (rx,ry,15,15,"file/puck.png"); //note changed from a knife to a puck to lazy to rename
			knives.add(k);
		}
		
        long delay  = 1000L;
        long period = 1000L;
    	Timer timer = new Timer("Timer");

        timer.scheduleAtFixedRate(repeatedTask, delay, period);		
	}
   
	TimerTask repeatedTask = new TimerTask() {
        public void run() {
        	//System.out.println("timer");
        	
    		for(int i = 0; i < badguys.size(); i++) { // sets the i = 0, the second line says repeat the action until condition is met, i plus one 
       			Badguy bg = (Badguy) badguys.get(i);
       			Knife k = (Knife) knives.get(i);
       			
       			Random rand = new Random();
    			int rx = rand.nextInt(600);
    			int ry = rand.nextInt(400);
    			
    			bg.setxCoord(rx);
    			bg.setyCoord(ry);
    			
    			if(link.getxCoord() < k.getxCoord())
    				k.setxCoord(k.getxCoord() - 20);
    			else 
    				k.setxCoord(k.getxCoord() + 20);
    			
    			if(link.getyCoord() < k.getyCoord())
    				k.setyCoord(k.getyCoord() - 20);
    			else 
    				k.setyCoord(k.getyCoord() + 20);
    			
    			if(link.getxCoord() <= k.getxCoord() && k.getxCoord() <= link.getxCoord() + link.getWidth()
    				&& link.getyCoord() <= k.getyCoord() && k.getyCoord() <= link.getyCoord() + link.getHeight())
    			{
    				GoogGuyKilled = true;
    				System.out.println("hit!!!");
    			}
    		}
        
    		seconds++;
            repaint();

    		if(badguys.size() == 0){
                this.cancel();
    		}
    			
    		if(GoogGuyKilled == true) {
                this.cancel();
    		}			
        }
	};
        
	private LinkedList LinkedList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			
			as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	@Override 
	public void paint(Graphics g) {
		
		if(badguys.size() == 0){
			AudioPlayer.player.stop(as);
			playIt("file/winsound.wav");
			g.drawImage(img2, 0, 0, this);
			g.drawString("Time alive: " + Integer.toString(seconds),50,15);
			return;
		}
		
		g.drawImage(img, 0, 0, this);
		g.drawImage(link.getImg(),link.getxCoord(), link.getyCoord(), link.getWidth(), link.getHeight(), this);
		
		for(int i = 0; i < badguys.size(); i++) {
			System.out.println(i);
			Badguy bg = (Badguy) badguys.get(i);
			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord() , bg.getWidth(), bg.getHeight(), this);			

			Knife k = (Knife) knives.get(i);
			g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord() , k.getWidth(), k.getHeight(), this);			
		}
					
		if(GoogGuyKilled == true) {
			g.drawImage(img3, 0, 0, this);
			AudioPlayer.player.stop(as);
			playIt("file/losssound.wav");
			g.setColor(Color.WHITE);
		}			
		
		g.drawString("Time alive: " + Integer.toString(seconds),10,10);
	}
	
	@Override 
	public void keyTyped(KeyEvent e) {
		//System.out.println(e)
	}
	
	@Override 
	public void keyPressed(KeyEvent e) {
		System.out.println(e);
		link.moveIt(e.getKeyCode(), this.getWidth(),this.getHeight());
		
		for(int i = 0; i < badguys.size(); i++) {
			Badguy bg = (Badguy) badguys.get(i);
			Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
			if (r.contains(link.getxCoord(),link.getyCoord())) {
				System.out.println("Badguy hit by link");
				badguys.remove(i);
			}
			repaint();
		}
		
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
