package firstTry;

import java.awt.Image;
import java.awt.Toolkit;


public class Knife {
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private Image img;
	public Image getImg;
	
	public Knife() {
		setxCoord(10);
		setyCoord(10);
		setWidth(30);
		setHeight(30);
		setImg("..file/puck.png");
	}
	public Knife(int x, int y,int w, int h, String imgpath) {
		//int x = 0;
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
		
	}
	
	private void setHeight(int h) {
		// TODO Auto-generated method stub
		this.height = h;
	}
	private void setWidth(int w) {
		// TODO Auto-generated method stub
		this.width = w;
	}
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}
	
	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}
	public Image getImg() {
		// TODO Auto-generated method stub
		return this.img;
	}
}
