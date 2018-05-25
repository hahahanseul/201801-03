/*
 *　クラス名：IntegrationTest_ScheduleControlDBAccess
 *　概要　　：結合番号１の結合テスト用ドライバ
 *　作成者名：　キム・ハンスル
 *　作成日　：　2018/05/25
 *　修正者名：
 *　修正日　：
*/

//パッケージの定義
package driver;

import java.util.ArrayList;

import model.HolidayBean;
import model.KindBean;
import model.ScheduleControlDBAccess;
import model.TaskBean;

public class IntegrationTest_ScheduleControlDBAccess {

	public static void main(String[] args) {
		//DAOを作成する
		ScheduleControlDBAccess dao = new ScheduleControlDBAccess();

		//ScheduleControlDBAccess → HolidayBeanのテスト【項番1~10】
		ArrayList<HolidayBean> holidayList = dao.findAllHolidays("2017", "05");
		for (HolidayBean bean : holidayList) {
			System.out.println("年........"+bean.getYear());
			System.out.println("月........"+bean.getMonth());
			System.out.println("日........"+bean.getDay());
			System.out.println("祝日名...."+bean.getName());
			System.out.println("-------------------------------------");
		}

		System.out.println("======================================================================");

		//ScheduleControlDBAccess → KindBeanのテスト【項番11~16】
 		ArrayList<KindBean> kindList = dao.findAllKinds();
		for (KindBean bean : kindList) {
			System.out.println("種別ID........"+bean.getId());
			System.out.println("種別名........"+bean.getName());
			System.out.println("-------------------------------------");
		}

		System.out.println("======================================================================");

		//ScheduleControlDBAccess → TaskBeanのテスト【項番17~39】
		ArrayList<TaskBean> taskList = dao.findAllTasks("2018", "05");
		for (TaskBean bean : taskList) {
			System.out.println("年........"+bean.getYear());
			System.out.println("月........"+bean.getMonth());
			System.out.println("日........"+bean.getDay());
			System.out.println("開始時...."+bean.getFromHour());
			System.out.println("開始分...."+bean.getFromMinute());
			System.out.println("終了時...."+bean.getToHour());
			System.out.println("終了分...."+bean.getToMinute());
			System.out.println("種別ID...."+bean.getKindId());
			System.out.println("種別名...."+bean.getKindName());
			System.out.println("メモ......."+bean.getMemo());
			System.out.println("-------------------------------------");
		}


		TaskBean iBean = new TaskBean("2018", "06", "02", "09", "00", "12", "00", "K99", "[その他]", "朝の食事会");
		int iResult = dao.insert(iBean);
		if (iResult == 1) {
			System.out.println("タスクを追加しました。");
		} else {
			System.out.println("タスク追加失敗しました！！！！");
		}


		TaskBean uBean = new TaskBean("2018", "06", "02", "12", "00", "15", "00", "K99", "[その他]", "お昼の食事");
		int uResult = dao.update(uBean);
		if (uResult == 1) {
			System.out.println("タスクを修正しました。");
		} else {
			System.out.println("タスク修正失敗しました！！！！");
		}

		int dResult = dao.delete("2018", "06", "02");
		if (dResult == 1) {
			System.out.println("タスクを削除しました。");
		} else {
			System.out.println("タスク削除失敗しました！！！！");
		}
	}

}
