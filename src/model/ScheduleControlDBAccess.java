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

	public ArrayList<HolidayBean> findAllHolidays(String year, String month) {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<HolidayBean> list = new ArrayList<>();
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
				HolidayBean bean = new HolidayBean(year, month, day, name);
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

	public ArrayList<KindBean> findAllKinds() {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<KindBean> list = new ArrayList<>();
		try {
			String sql = "select 種別ID, 種別名 from 種別";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next() == true) {
				String id = rs.getString("種別ID");
				String name = rs.getString("種別名");
				KindBean bean = new KindBean(id, name);
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

	public ArrayList<TaskBean> findAllTasks(String year, String month) {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<TaskBean> list = new ArrayList<>();
		try {
			String sql = "select t.年月日, t.開始時刻, t.終了時刻, t.種別ID, s.種別名, t.メモ, t.重要 from タスク t inner join 種別 s on t.種別ID = s.種別ID where t.年月日 between ? and ?";
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
				int important = rs.getInt("重要");
				TaskBean bean = new TaskBean(year, month, day, fromHour, fromMinute, toHour,
						toMinute, kindId, kindName, memo, important);
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

	public int insert(TaskBean taskBean) {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		int result = 0;
		try {
			stmt = con.prepareStatement("insert into タスク values (?,?,?,?,?,?);");
			stmt.setString(1, taskBean.getYear() + taskBean.getMonth() + taskBean.getDay());
			stmt.setString(2, taskBean.getFromHour() + taskBean.getFromMinute());
			stmt.setString(3, taskBean.getToHour() + taskBean.getToMinute());
			stmt.setString(4, taskBean.getKindId());
			stmt.setString(5, taskBean.getMemo());
			stmt.setInt(6, taskBean.getImportant());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。（insert）");
			//e.printStackTrace();
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

	public int update(TaskBean taskBean) {
		Connection con = createConnection();
		PreparedStatement stmt = null;
		int result = 0;
		try {
			stmt = con.prepareStatement("update タスク  set  開始時刻 = ?, 終了時刻= ?, 種別ID = ?, メモ = ?, 重要 = ? WHERE 年月日 = ?");
			stmt.setString(1, taskBean.getFromHour() + taskBean.getFromMinute());
			stmt.setString(2, taskBean.getToHour() + taskBean.getToMinute());
			stmt.setString(3, taskBean.getKindId());
			stmt.setString(4, taskBean.getMemo());
			stmt.setInt(5, taskBean.getImportant());
			stmt.setString(6, taskBean.getYear() + taskBean.getMonth() + taskBean.getDay());
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
			stmt = con.prepareStatement("delete from タスク  WHERE 年月日 = ?");
			stmt.setString(1, year + month + day);
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
