package Model.Database;

import java.sql.*;
public class DBCRUD extends ConfigDB{
	public DBCRUD() throws Exception,SQLException{
		super();
	}
	public void Query(String query) throws Exception,SQLException{ // menampilkan data dari database
	    try {
	        stm = con.createStatement();
	        rs = stm.executeQuery(query);
	    }
	    catch (SQLException e) {
	    	throw e;
	    }
	}

	public void QueryUpdate(String query) throws Exception,SQLException{ // menampilkan data dari database
	    try {
	        stm = con.createStatement();
	        stm.executeUpdate(query);
	    }
	    catch (SQLException e) {
	    	throw e;
	    }
	}
}								