package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Hanseul Kim
 * */
public class ScheduleControlDBAccess {
	private Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:65534/schedule?useSSL=false", "user1", "pass1");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		return con;
	}

	private void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("DB切断時にエラーが発生しました。");
			e.printStackTrace();
		}
	}

	public ArrayList<stub.HolidayBean> findAllHolidays(String year, String month) {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<stub.HolidayBean> list = new ArrayList<>();
		try {
			String sql = "select 年月日, 祝日名 from 祝日 where 年月日 between ? and ?";
			stmt = con.prepareStatement(sql);
			String fromYMD = year + month + "01";
			String toYMD = year + month + "31";
			stmt.setString(1, fromYMD);
			stmt.setString(2, toYMD);
			rs = stmt.executeQuery();
			while (rs.next() == true) {
				String day = rs.getString("年月日").substring(6);
				String name = rs.getString("祝日名");
				stub.HolidayBean bean = new stub.HolidayBean(year, month, day, name);
				list.add(bean);
			}
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。（祝日検索）");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return list;
	}

	public ArrayList<stub.KindBean> findAllKinds() {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<stub.KindBean> list = new ArrayList<>();
		try {
			String sql = "select 種別ID, 種別名 from 種別";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next() == true) {
				String id = rs.getString("種別ID");
				String name = rs.getString("種別名");
				stub.KindBean bean = new stub.KindBean(id, name);
				list.add(bean);
			}
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。（種別検索）");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return list;
	}

	public ArrayList<stub.TaskBean> findAllTasks(String year, String month) {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<stub.TaskBean> list = new ArrayList<>();
		try {
			String sql = "select t.年月日, t.開始時刻, t.終了時刻, t.種別ID, s.種別名, t.メモ from タスク t inner join 種別 s on t.種別ID = s.種別ID where t.年月日 between ? and ?";
			stmt = con.prepareStatement(sql);
			String fromYMD = year + month + "01";
			String toYMD = year + month + "31";
			stmt.setString(1, fromYMD);
			stmt.setString(2, toYMD);
			rs = stmt.executeQuery();
			while (rs.next() == true) {
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
			System.out.println("DBアクセス時にエラーが発生しました。（タスク検索）");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);

		return list;
	}
	public int insert(stub.TaskBean taskBean) {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		int result = 0;
		try {
			String sql = "insert into タスク values (?,?,?,?,?);";
			stmt = con.prepareStatement(sql);
			String year = taskBean.getYear();
			String month = taskBean.getMonth();
			String day = taskBean.getDay();
			String fromHour = taskBean.getFromHour();
			String fromMinute = taskBean.getFromMinute();
			String toHour = taskBean.getToHour();
			String toMinute = taskBean.getToMinute();
			String kindId = taskBean.getKindId();
			String memo = taskBean.getMemo();
			String tYMD = year+month+day;
			String fromHM = fromHour + fromMinute;
			String toHM = toHour + toMinute;
				//ymd  ,  fromhm, tohm, kindid, memo
			stmt.setString(1,tYMD );
			stmt.setString(2, fromHM);
			stmt.setString(3, toHM);
			stmt.setString(4, kindId);
			stmt.setString(5,memo);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。（insert）");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return result;
	}
	public int update(stub.TaskBean taskBean) {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		int result = 0;
		try {
			String sql = "update タスク  set  開始時刻 = ?, 終了時刻= ?, 種別ID = ?, メモ = ? WHERE 年月日 = ?";
			stmt = con.prepareStatement(sql);
			String year = taskBean.getYear();
			String month = taskBean.getMonth();
			String day = taskBean.getDay();
			String fromHour = taskBean.getFromHour();
			String fromMinute = taskBean.getFromMinute();
			String toHour = taskBean.getToHour();
			String toMinute = taskBean.getToMinute();
			String kindId = taskBean.getKindId();
			String memo = taskBean.getMemo();
			String tYMD = year+month+day;
			String fromHM = fromHour + fromMinute;
			String toHM = toHour + toMinute;
			stmt.setString(1, fromHM);
			stmt.setString(2, toHM);
			stmt.setString(3, kindId);
			stmt.setString(4,memo);
			stmt.setString(5, tYMD );
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。（update）");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return result;
	}
	public int delete(String year, String month, String day) {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		int result = 0;
		try {
			String sql = "delete from タスク  WHERE 年月日 = ?";
			stmt = con.prepareStatement(sql);
			String tYMD = year+month+day;
			stmt.setString(1, tYMD);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。（delete）");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return result;
	}

}
