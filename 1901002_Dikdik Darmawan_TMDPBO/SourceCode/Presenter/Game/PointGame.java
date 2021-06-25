package Presenter.Game;

public class PointGame{
	private int success,fail;

	public PointGame(){
		success = 0;
		fail = 0;
	}

	public void tambahSuccess(){
		success++;
	}
	
	public void tambahFail(){
		fail++;
	}

	public String getSuccess(){
		return Integer.toString(success);
	}
	
	public String getFail(){
		return Integer.toString(fail);
	}

}