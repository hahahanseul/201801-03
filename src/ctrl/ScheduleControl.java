/*
 *　クラス名：ScheduleControl
 *　概要　　：スケジュール管理ソフトを制御する。
 *　作成者名：
 *　作成日　：
 *　修正者名：
 *　修正日　：
*/

//パッケージの定義
package ctrl;

//パッケージのインポート
import java.util.ArrayList;

import model.HolidayBean;
import model.KindBean;
import model.ScheduleControlDBAccess;
import model.TaskBean;

//ScheduleControlクラスの定義
public class ScheduleControl
{
	//スタティック変数の定義
	private static ScheduleControlDBAccess dao = new ScheduleControlDBAccess();

	//祝日リストを取得する。
	public static ArrayList<HolidayBean> getHolidayList(String year, String month)
	{
		ArrayList<HolidayBean> holidayList = dao.findAllHolidays(year, month);
		return holidayList;
	}

	//種別リストを取得する。
	public static ArrayList<KindBean> getKindList()
	{
		ArrayList<KindBean> kindList = dao.findAllKinds();
		return kindList;
	}

	//タスクリストを取得する。
	public static ArrayList<TaskBean> getTaskList(String year, String month)
	{
		ArrayList<TaskBean> taskList = dao.findAllTasks(year, month);
		return taskList;
	}

	//タスクを登録する。
	public static int registerTaskBean(TaskBean taskBean)
	{
		int result;
		result = dao.insert(taskBean);
		if(result == 0)
		{
			result = dao.update(taskBean);
		}
		return result;
	}

	//タスクを削除する。
	public static int removeTaskBean(String year, String month, String day)
	{
		int result;
		result = dao.delete(year, month, day);
		return result;
	}
}
