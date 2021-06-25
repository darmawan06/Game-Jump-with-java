package Model.Users;

public class User{
	private String username;
	private String fail;
	private String success;
	public User(String name,String fail,String success){
		this.username = name;
		this.fail = fail;
		this.success = success;

	}

	public String getUsername(){
		return this.username;
	}
	
	public String getFail(){
		return this.fail;
	}

	public String getSuccess(){
		return this.success;
	}
}