package kodcu.aidatTakipJavaFXMaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	
	static String MySqlClassName="com.mysql.cj.jdbc.Driver";
	static String MySqlEmptyUrl="jdbc:mysql://localhost:3306/";
	static String MySqlUrl="jdbc:mysql://localhost:3306/AidatTakipSistemi";
	static String MySqlUname="root";
	static String MySqlPass= "Savassanati01";
	
	static String MariaDBClassName="org.mariadb.jdbc.Driver";
	static String MariaDBEmptyUrl="jdbc:mariadb://localhost:3306/";
	static String MariaDBUrl="jdbc:mariadb://localhost:3306/AidatTakipSistemi";
	static String MariaDBUname="root";
	static String MariaDBPass= "Savassanati01";
	
	static String SqliteDBClassName="org.sqlite.JDBC";
	static String SqliteDBEmptyUrl="jdbc:sqlite";
	static String SqliteDBUrl="jdbc:sqlite:AidatTakipSistemi.sqlite";
	
	static String className=SqliteDBClassName;
	static String emptyUrl=SqliteDBEmptyUrl;
	static String url=SqliteDBUrl;
//	String uname=MySqlUname;
//	String pass=MySqlPass;
	//DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	static Connection con=null;
	static Connection con1=null;
	public static Connection getCon() throws ClassNotFoundException, SQLException {
		Class.forName(className);
		Connection con = DriverManager.getConnection(url);
//		Connection con = DriverManager.getConnection(url,uname,pass);
		return con;
	}
	
	public void createDatabase() throws SQLException, ClassNotFoundException 
	{
		String query1="Create Database if not exists AidatTakipSistemi";
				//"If(db_id(N'DonerciOtomasyonu') IS NULL) CREATE DATABASE [DonerciOtomasyonu]";
		
		try {
			Class.forName(className);
			Connection con1 = DriverManager.getConnection(url);
//			Connection con1 = DriverManager.getConnection(emptyUrl,uname,pass);
			
			PreparedStatement st1= con1.prepareStatement(query1);
			st1.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if(con1!=null)
			{
				con1.close();
			}
			
				
			
		}
		
	}
	public void createAidatPayerTable() throws SQLException
	{

		
		
		String query1="Create table AidatPayerTable" +
                "(" +
				"PayerId integer primary key,"+
                "PayerName text,"+
                 "PayerNo integer unique not null,"+
                 "PayerPhone text,"+
                "PayerAddress text"+");";
		
		
		
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			
			st1.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		
	}
	public void createAidatTable() throws SQLException
	{

		
		
		String query1="Create table AidatTable" +
                "(" +
				"AidatId integer primary key,"+
                "AidatMonth integer not null,"+
                "AidatYear integer not null,"+
                "AidatAmount integer not null,"+
                "AidatStatus integer not null,"+
                 "PayerNo integer not null,"+
                "foreign key (PayerNo) references AidatPayerTable(PayerNo)"+");";
		
		
		
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			
			st1.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		
	}
	public int addAidatPayerToAidatPayerTable(AidatPayer p) throws SQLException
	{
		
		
		String query1="Insert into AidatPayerTable (PayerName,PayerNo,PayerPhone,PayerAddress)"
				+ "values (?,?,?,?)";
		int result=-1;
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			st1.setString(1, p.getPayerName());
			st1.setInt(2, (int)p.getPayerNo());
			st1.setString(3, p.getPayerPhone());
			st1.setString(4, p.getPayerAddress());
			result=st1.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
		}
		if(result>0)result=1;
		return result;
		
	}
	public int addAidatToAidatTable(Aidat p) throws SQLException
	{
		
		
		
		
		String query1="Insert into AidatTable (AidatMonth,AidatYear,AidatAmount,AidatStatus,PayerNo)"
				+ "values (?,?,?,?,?)";
		int result=-1;
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			
			st1.setInt(1, (int)p.getAidatMonth());
			st1.setInt(2, (int)p.getAidatYear());
			st1.setInt(3, (int)p.getAidatAmount());
			st1.setInt(4, (int)p.getAidatStatus());
			st1.setInt(5, (int)p.getPayerNo());
			result=st1.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-ge0tnerated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
		}
		if(result>0)result=1;
		return result;
		
	}
	public int deleteFromAidatPayerTable(long payerNo) throws SQLException 
	{
		String query1="Delete From AidatPayerTable where PayerNo=?";
		
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			st1.setInt(1, (int)payerNo);
			return st1.executeUpdate();
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		return 2;
		
	}
	public int deleteAidatFromAidatTable(long aidatMonth, long aidatYear) throws SQLException 
	{
		String query1="DELETE FROM AidatTable WHERE AidatMonth=? and AidatYear=?";
		
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			st1.setInt(1, (int)aidatMonth);
			st1.setInt(2, (int)aidatYear);
			return st1.executeUpdate();
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		return -1;
		
	}
	public int deleteAidatsOfAnAidatPayerFromAidatTable(long payerNo) throws SQLException 
	{
		String query1="DELETE FROM AidatTable WHERE PayerNo=?";
		
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			st1.setInt(1, (int)payerNo);
			return st1.executeUpdate();
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		return -1;
		
	}
	public int clearAidatPayerTable() throws SQLException 
	{
		String query1="Delete From AidatPayerTable";
		int result=-1;
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			result= st1.executeUpdate();
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		
		return result;
		
	}
	public int clearAidatTable() throws SQLException 
	{
		String query1="Delete From AidatTable";
		int result=-1;
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			result= st1.executeUpdate();
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		
		return result;
		
	}
	public int clearDB() throws SQLException 
	{
		int a=0;
		int b=0;
		a=this.clearAidatTable();
		b=this.clearAidatPayerTable();
		if(a>0&&b>0) return 1;
		else return 2;
		
	}
	public List<AidatPayer> getAllAidatPayers() throws SQLException
	{
		String query="Select * From AidatPayerTable";
		List<AidatPayer> aidatPayersInDb=new ArrayList<AidatPayer>();
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st= con.prepareStatement(query);
			
			
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				AidatPayer p=new AidatPayer();
				p.setPayerId((long)rs.getInt("PayerId"));
				p.setPayerNo((long)rs.getInt("PayerNo"));
				p.setPayerName(rs.getString("PayerName"));
				p.setPayerPhone(rs.getString("PayerPhone"));
				p.setPayerAddress(rs.getString("PayerAddress"));
				aidatPayersInDb.add(p);
				
				
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		return aidatPayersInDb;
	}
	public List<Aidat> getAllAidats() throws SQLException
	{
		String query="Select * From AidatTable";
		List<Aidat> aidatsInDb=new ArrayList<Aidat>();
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st= con.prepareStatement(query);
			
			
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				Aidat p=new Aidat();
				p.setAidatId((long)rs.getInt("AidatId"));
				p.setAidatMonth((long)rs.getInt("AidatMonth"));
				p.setAidatYear((long)rs.getInt("AidatYear"));
				p.setAidatAmount((long)rs.getInt("AidatAmount"));
				p.setAidatStatus((long)rs.getInt("AidatStatus"));
				p.setPayerNo((long)rs.getInt("PayerNo"));
				aidatsInDb.add(p);
				
				
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		return aidatsInDb;
	}
	public List<Aidat> getAidatsByPayerNo(long payerNo) throws SQLException
	{
		String query="Select * From AidatTable where PayerNo=?";
		List<Aidat> aidatsInDb=new ArrayList<Aidat>();
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, (int)payerNo);
			
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				Aidat p=new Aidat();
				p.setAidatId((long)rs.getInt("AidatId"));
				p.setAidatMonth((long)rs.getInt("AidatMonth"));
				p.setAidatYear((long)rs.getInt("AidatYear"));
				p.setAidatAmount((long)rs.getInt("AidatAmount"));
				p.setAidatStatus((long)rs.getInt("AidatStatus"));
				p.setPayerNo((long)rs.getInt("PayerNo"));
				aidatsInDb.add(p);
				
				
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		return aidatsInDb;
	}
	public AidatPayer getAidatPayerByPayerNo(long payerNo) throws SQLException
	{
		String query="Select * From AidatPayerTable where PayerNo=?";
		AidatPayer p=new AidatPayer();
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st= con.prepareStatement(query);
			st.setInt(1, (int)payerNo);
			
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				
				p.setPayerId((long)rs.getInt("PayerId"));
				p.setPayerNo((long)rs.getInt("PayerNo"));
				p.setPayerName(rs.getString("PayerName"));
				p.setPayerPhone(rs.getString("PayerPhone"));
				p.setPayerAddress(rs.getString("PayerAddress"));
				
				
				
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		return p;
	}
	public Aidat getAidatByAidatId(long aidatId) throws SQLException
	{
		String query="Select * From AidatTable where AidatId=?";
		Aidat p=new Aidat();
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st= con.prepareStatement(query);
			st.setInt(1, (int)aidatId);
			
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				p.setAidatId((long)rs.getInt("AidatId"));
				p.setAidatMonth((long)rs.getInt("AidatMonth"));
				p.setAidatYear((long)rs.getInt("AidatYear"));
				p.setAidatAmount((long)rs.getInt("AidatAmount"));
				p.setAidatStatus((long)rs.getInt("AidatStatus"));
				p.setPayerNo((long)rs.getInt("PayerNo"));
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
			
				
			
		}
		return p;
	}
	public int updateAidatPayer(long payerNo,String newName,String newPhone, String newAddress) throws SQLException
	{

		
		String query1="Update AidatPayerTable Set PayerName=?, PayerPhone=?, PayerAddress=? where PayerNo=?";
		int result=-1;
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			st1.setString(1, newName);
			st1.setString(2, newPhone);
			st1.setString(3, newAddress);
			st1.setInt(4, (int)payerNo);
			result=st1.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
		}
		return result;
		
	}
	public int updateAidatAmount(long aidatMonth,long aidatYear,long newamount) throws SQLException
	{

//		p.setAidatId((long)rs.getInt("AidatId"));
//		p.setAidatMonth((long)rs.getInt("AidatMonth"));
//		p.setAidatYear((long)rs.getInt("AidatYear"));
//		p.setAidatAmount((long)rs.getInt("AidatAmount"));
//		p.setAidatStatus((long)rs.getInt("AidatStatus"));
//		p.setPayerNo((long)rs.getInt("PayerNo"));
		
		String query1="Update AidatTable Set AidatAmount=? where AidatMonth=? and AidatYear=?";
		int result=-1;
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			st1.setInt(1, (int)newamount);
			st1.setInt(2, (int)aidatMonth);
			st1.setInt(3, (int)aidatYear);
			result=st1.executeUpdate();
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
		
		}
		if(result>0)result=1;
		return result;
		
	}
	public int updateAidatStatus(long aidatMonth,long aidatYear,long payerNo,long newaidatstatus) throws SQLException
	{

//		p.setAidatId((long)rs.getInt("AidatId"));
//		p.setAidatMonth((long)rs.getInt("AidatMonth"));
//		p.setAidatYear((long)rs.getInt("AidatYear"));
//		p.setAidatAmount((long)rs.getInt("AidatAmount"));
//		p.setAidatStatus((long)rs.getInt("AidatStatus"));
//		p.setPayerNo((long)rs.getInt("PayerNo"));
		
		String query1="Update AidatTable Set AidatStatus=? where AidatMonth=? and AidatYear=? and PayerNo=?";
		int result=-1;
		try {
//			Class.forName(className);
//			con = DriverManager.getConnection(url);
//			con = DriverManager.getConnection(url,uname,pass);
			con=getCon();
			PreparedStatement st1= con.prepareStatement(query1);
			st1.setInt(1, (int)newaidatstatus);
			st1.setInt(2, (int)aidatMonth);
			st1.setInt(3, (int)aidatYear);
			st1.setInt(4, (int)payerNo);
			result=st1.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			if(con!=null)
			{
				con.close();
			}
		}
		if(result>0)result=1;
		return result;
		
	}
	
	
}
