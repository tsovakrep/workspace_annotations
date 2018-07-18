package example;

import java.sql.*;

import com.mysql.jdbc.Driver;

public class Main {

	public static void main(String[] args) throws Exception {
		new Driver();
		// Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("", "", "");
		PreparedStatement ps = conn.prepareStatement("");
		ResultSet rs = ps.executeQuery();
		rs.close();
		CallableStatement cs;
	}
}