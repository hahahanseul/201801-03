/*
 *　クラス名：UnitTest_ScheduleControlDBAccess
 *　概要　　：クラスScheduleControlDBAccessの単体テスト用ドライバ
 *　作成者名： キム・ハンスル
 *　作成日　：　2018/05/22
 *　修正者名：
 *　修正日　：
*/
package driver;

import model.ScheduleControlDBAccess;
import stub.TaskBean;

public class UnitTest_ScheduleControlDBAccess {

	public static void main(String[] args) {
		ScheduleControlDBAccess dao = new ScheduleControlDBAccess();

/*
		//findAllHolidays()のテスト
		ArrayList<HolidayBean> holidayList = dao.findAllHolidays("2017", "05");
		for (HolidayBean bean : holidayList) {
			System.out.println(bean);
		}



		System.out.println("======================================================================");



		//findAllKinds()のテスト【項番43～67】
		ArrayList<KindBean> kindList = dao.findAllKinds();
		for (KindBean bean : kindList) {
			System.out.println(bean);
		}


		System.out.println("======================================================================");


		//findAllTasks()のテスト【項番68～102】
		ArrayList<TaskBean> taskList = dao.findAllTasks("2018", "05");
		for (TaskBean bean : taskList) {
			System.out.println(bean);
		}
*/
		//insert()のテスト【項番106～137】   나는 102부터임 目安：32項目
		TaskBean iBean = new TaskBean("2018", "06", "02", "09", "00", "12", "00", "K99", "[その他]", "朝の食事会");
		int iResult = dao.insert(iBean);
		if (iResult ==1) {
		      System.out.println("タスクを追加しました。");
		}else {
		      System.out.println("タスク追加失敗しました！！！！");
		}
/*
		//update()のテスト【項番138～169】     目安：32項目
		TaskBean uBean = new TaskBean("2018", "06", "02", "12", "00", "15", "00", "K99", "[その他]", "お昼の食事");
		int uResult = dao.update(uBean);
		if (uResult == 1) {
			System.out.println("タスクを修正しました。");
		} else {
			System.out.println("タスク修正失敗しました！！！！");
		}
*/
/*		//delete()のテスト 【項番170～189】 目安：２０項目
		int dResult = dao.delete("2018", "06", "02");
		if (dResult == 1) {
			System.out.println("タスクを削除しました。");
		} else {
			System.out.println("タスク削除失敗しました！！！！");
		}*/
	}

}
