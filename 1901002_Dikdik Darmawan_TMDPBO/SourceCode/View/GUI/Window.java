package View.GUI;

import javax.swing.*; 

public class Window extends JFrame{
	int WIDTH = 1000;
	int HEIGHT = 600;
	public Window(){
		setTitle("HOME");
		setSize(WIDTH,HEIGHT);//400 width and 500 height  
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}

	protected void showVisible(){
		setVisible(true);
	}

	protected void hiddenVisible(){
		setVisible(false);
	}
	
	protected void closeWindows(){
		dispose();
	}

}
	

