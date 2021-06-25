package Model.Database;

import java.sql.*;

public class ConfigDB {
	protected Connection con;
	protected Statement stm;
	protected ResultSet rs;
	public ConfigDB() throws Exception,SQLException{
		if (this.con == null) {
			try{
				String DB="jdbc:mysql://localhost:3306/TMDPBO";
                String user="root"; // user database
                String pass=""; // password database
                Class.forName("com.mysql.jdbc.Driver");
                this.con = (Connection) DriverManager.getConnection(DB,user,pass);
			}catch(Exception e){
				throw e;
			}
		}
	}
	
	public ResultSet getResult() throws Exception,SQLException{
		ResultSet temp = null;
		try{
			return rs;
		}catch(Exception ex){
			return temp;
		}
	}
	
	public void closeResult() throws Exception,SQLException{
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){
				rs = null;
				throw e;
			}
		}
		if(stm != null){
			try{
				stm.close();
			}catch(SQLException e){
				stm = null;
				throw e;
			}
		}
	}

	public void closeConnection() throws Exception,SQLException{
		if(con != null){
			try{
				con.close();
			}catch (SQLException e) {
				con = null;
				throw e;
			}
		}
	}
}