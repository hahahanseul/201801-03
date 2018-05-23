/*
 *　クラス名：UnitTest_ScheduleControlDBAccess
 *　概要　　：クラスScheduleControlDBAccessの単体テスト用ドライバ
 *　作成者名： キム・ハンスル
 *　作成日　：　2018/05/22
 *　修正者名：
 *　修正日　：
*/

//パッケージの定義
package driver;

//パッケージのインポート
import java.util.ArrayList;

import model.ScheduleControlDBAccess;
//単体テスト用のパッケージのインポート
import stub.HolidayBean;
import stub.KindBean;
import stub.TaskBean;

public class UnitTest_ScheduleControlDBAccess_sample
{

	public static void main(String[] args)
	{
		//DAOを作成する
		ScheduleControlDBAccess dao = new ScheduleControlDBAccess();
		//createConnection()のテスト【項番１～６】
		//closeConnection()のテスト【項番７～10】
		//findAllHolidays()のテスト【項番11～42】
		ArrayList<HolidayBean> holidayList = dao.findAllHolidays("2017", "05");
/*		for(HolidayBean bean : holidayList)
		{
			System.out.println(bean);
		}*/
		System.out.println("======================================================================");
		//findAllKinds()のテスト【項番43～67】
		ArrayList<KindBean> kindList = dao.findAllKinds();
	/*	for(KindBean bean : kindList) {
			System.out.println(bean);
		}
*/
		System.out.println("======================================================================");
		//findAllTasks()のテスト【項番68～105】
		ArrayList<TaskBean> taskList = dao.findAllTasks("2018","05");
/*		for(TaskBean bean : taskList) {
			System.out.println(bean);
		}*/

		//insert()のテスト【項番106～137】


		//update()のテスト【項番138～169】


		//delete()のテスト【項番170～189】


	}

}
