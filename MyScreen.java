package firstTry;

import javax.swing.JFrame;
public class MyScreen extends JFrame {
	
	public MyScreen() {
		
		this.setSize(600,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
		
	public static void main(String[]args) {
		MyScreen screen = new MyScreen() ;
		MyCanvas canvas = new MyCanvas() ;
		
		screen.getContentPane().add(canvas);
		
	}

}