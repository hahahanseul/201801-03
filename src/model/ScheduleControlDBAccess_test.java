package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleControlDBAccess_test {
	private Connection createConnection() {
		Connection con = null;
		System.out.println("createConnection con:"+con);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:65534/schedule?useSSL=false","user1","pass1");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		// 項番6 System.out.println("createConnection 最終のcon:"+con);
		return con;
	}
	private void closeConnection(Connection con) {
		// 項番7 System.out.println("closeConnectino con:"+con);
		try{
			if(con != null)
			{
				con.close();
				System.out.println("closeConnection close sareta?");
			}
		}
		catch(SQLException e)
		{
			System.out.println("DB切断時にエラーが発生しました。");
			e.printStackTrace();
		}
	}
	public ArrayList<stub.HolidayBean> findAllHolidays(String year, String month){
		System.out.println("findAllHolidays param year:"+year);
		System.out.println("findAllHolidays param month:"+month);
		Connection con = createConnection();
		System.out.println("findAllHolidays con:"+con);
		PreparedStatement stmt = null;
		System.out.println("findAllHolidays PreparedStatement:"+stmt);
		ResultSet rs = null;
		System.out.println("findAllHolidays ResultSet:"+rs);
		ArrayList<stub.HolidayBean> list = new ArrayList<>();
		System.out.println("findAllHolidays ArrayList:"+list);
		try {
			String sql = "select 年月日, 祝日名 from 祝日 where 年月日 between ? and ?";
			stmt = con.prepareStatement(sql);
			System.out.println("findAllHolidays PreparedStatement 2:"+stmt);
			String fromYMD = year+month+"01";
			System.out.println("findAllHolidays fromYMD:"+fromYMD);
			String toYMD = year+month+"31";
			stmt.setString(1, fromYMD);
			stmt.setString(2, toYMD);
			rs=stmt.executeQuery();
			System.out.println("findAllHolidays executeQuery rs:"+rs );
			while(rs.next() == true) {
				String day = rs.getString("年月日").substring(6);
				String name = rs.getString("祝日名");
				stub.HolidayBean bean = new stub.HolidayBean(year, month, day, name);
				list.add(bean);
			}
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。（祝日検索）" );
			e.printStackTrace();
		}finally {
			try {
				System.out.println("finally~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				if(rs != null ) {
					rs.close();
					System.out.println("rs close");
				}
				if(stmt != null) {
					stmt.close();
					System.out.println("stmt close");
				}
			}catch(SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		System.out.println("りすとりすとりすとりすとりすと"+list);
		return list;
	}
	public ArrayList<stub.KindBean> findAllKinds(){
		Connection con = createConnection();
		System.out.println("findAllKinds con:"+con);
		PreparedStatement stmt = null;
		System.out.println("findAllKinds PreparedStatement:"+stmt);
		ResultSet rs = null;
		System.out.println("findAllKinds ResultSet:"+rs);
		ArrayList<stub.KindBean> list = new ArrayList<>();
		System.out.println("findAllKinds ArrayList:"+list);
		try {
			String sql = "select 種別ID, 種別名 from 種別";
			stmt = con.prepareStatement(sql);
			System.out.println("findAllKinds PreparedStatement 2:"+stmt);
			rs=stmt.executeQuery();
			while(rs.next() == true) {
				String id = rs.getString("種別ID");
				String name = rs.getString("種別名");
				stub.KindBean bean = new stub.KindBean(id, name);
				list.add(bean);
			}
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。（種別検索）" );
			e.printStackTrace();
		}
		closeConnection(con);
		return list;
	}
	public ArrayList<stub.TaskBean> findAllTasks(String year, String month){
		System.out.println("findAllTasks param year:"+year);
		System.out.println("findAllTasks param month:"+month);
		Connection con = createConnection();
		System.out.println("findAllTasks con:"+con);
		PreparedStatement stmt = null;
		System.out.println("findAllTasks PreparedStatement:"+stmt);
		ResultSet rs = null;
		System.out.println("findAllTasks ResultSet:"+rs);
		ArrayList<stub.TaskBean> list = new ArrayList<>();
		System.out.println("findAllTasks ArrayList:"+list);
		try {
			String sql = "select t.年月日, t.開始時刻, t.終了時刻, t.種別ID, s.種別名, t.メモ from タスク t inner join 種別 s on t.種別ID = s.種別ID where t.年月日 between ? and ?";
			stmt = con.prepareStatement(sql);
			System.out.println("findAllTasks PreparedStatement 2:"+stmt);
			String fromYMD = year+month+"01";
			String toYMD = year+month+"31";
			stmt.setString(1, fromYMD);
			stmt.setString(2, toYMD);
			rs=stmt.executeQuery();
			while(rs.next() == true) {
				// String year, month, day, fromHour, fromMinute, toHour, toMinute, kindId, kindName, memo;
				String day = rs.getString("年月日").substring(6);

				String fromHour = rs.getString("開始時刻").substring(0, 2);
				String fromMinute = rs.getString("開始時刻").substring(2, 4);
				String toHour = rs.getString("終了時刻").substring(0, 2);
				String toMinute = rs.getString("終了時刻").substring(2, 4);
				String kindId = rs.getString("種別ID");
				String kindName = rs.getString("種別名");
				String memo = rs.getString("メモ");
				stub.TaskBean bean = new stub.TaskBean(year, month, day, fromHour, fromMinute, toHour,
						toMinute, kindId, kindName, memo);
				list.add(bean);
			}
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。（タスク検索）" );
			e.printStackTrace();
		}
		closeConnection(con);
		return list;
	}
	public int insert(stub.TaskBean taskBean) {return 0;}
	public int update(stub.TaskBean taskBean) {return 0;}
	public int delete(String year, String month, String day) {return 0;}

}
