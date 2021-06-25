package Presenter;
import Model.Database.*;
import Model.Users.*;
import java.util.ArrayList;

public class ProsesData{
	private DBCRUD dbCRUD;
	private ArrayList<User> users;
	
	public ProsesData() throws Exception{
		try{
			dbCRUD = new DBCRUD();
			users = new ArrayList<User>();
		}catch (Exception e) {
			throw e;
		}
	}

	public void pindahData() throws Exception{
		try{
			dbCRUD.Query("select * from pengguna");
			while(dbCRUD.getResult().next()){
				String id = dbCRUD.getResult().getString(1);
				String username = dbCRUD.getResult().getString(2);
				String fail = dbCRUD.getResult().getString(3);
				String success = dbCRUD.getResult().getString(4);
				User user = new User(username,fail,success);
				users.add(user);
			}
			dbCRUD.closeResult();
			dbCRUD.closeConnection();
		}catch (Exception e) {
			throw e;
		}
	}

	public void sendData(String username,String success,String fail) throws Exception{
		try{
			dbCRUD.QueryUpdate("insert into `pengguna` (`username`, `fail`, `success`) value ('"+username+"', '"+fail+"', '"+success+"');");
			dbCRUD.closeResult();
			dbCRUD.closeConnection();
		}catch (Exception e) {
			throw e;
		}
	}


	public String getUsername(int i){
		return users.get(i).getUsername();
	}
	public String getFail(int i){
		return users.get(i).getFail();
	}
	public String getSuccess(int i){
		return users.get(i).getSuccess();
	}
	public int getSize(){
		return users.size();
	}

	public String[][] getData() throws Exception{
        String data[][] = new String[100][100];
        try{
			this.pindahData();
			for(int i=0;i<this.getSize();i++){
                data[i][0] = this.getUsername(i); 
                data[i][1] = this.getFail(i); 
                data[i][2] = this.getSuccess(i); 
			}
		}catch(Exception e){
			throw e;
		}
        return data;
    }


}
	
