/*
 *　クラス名：IntegrationTest_ScheduleControl
 *　概要　　：結合番号２の結合テスト用ドライバ
 *　作成者名：
 *　作成日　：
 *　修正者名：
 *　修正日　：
*/

//パッケージの定義
package driver;

//パッケージのインポート
import java.util.ArrayList;

import ctrl.ScheduleControl;
import model.HolidayBean;
import model.KindBean;
import model.TaskBean;

public class IntegrationTest_ScheduleControl
{
	public static void main(String[] args){
		//ScheduleControl → ScheduleControlDBAccessのテスト【項番１～３】
		//ScheduleControl → HolidayBeanのテスト【項番９】
		ArrayList<HolidayBean> holidayList = ScheduleControl.getHolidayList("2017", "05");
		System.out.println("【ScheduleControl → ScheduleControlDBAccessの結果①】");
		for(HolidayBean bean : holidayList)
		{
			System.out.println("＜HolidayBeanインスタンスの情報＞");
			System.out.println("　　・年　：" + bean.getYear());
			System.out.println("　　・月　：" + bean.getMonth());
			System.out.println("　　・日　：" + bean.getDay());
			System.out.println("　　・名称：" + bean.getName());
		}

		//ScheduleControl → ScheduleControlDBAccessのテスト【項番４】
		//ScheduleControl → KindBeanのテスト【項番10】
		ArrayList<KindBean> kindList = ScheduleControl.getKindList();
		System.out.println("【ScheduleControl → ScheduleControlDBAccessの結果②】");
		for(KindBean bean : kindList)
		{
			System.out.println("＜KindBeanインスタンスの情報＞");
			System.out.println("　　・ID　：" + bean.getId());
			System.out.println("　　・名称：" + bean.getName());
		}

		//ScheduleControl → ScheduleControlDBAccessのテスト【項番５】
		//ScheduleControl → KindBeanのテスト【項番11】
		ArrayList<TaskBean> taskList = ScheduleControl.getTaskList("2018", "05");
		System.out.println("【ScheduleControl → ScheduleControlDBAccessの結果③】");
		for(TaskBean bean : taskList)
		{
			System.out.println("＜TaskBeanインスタンスの情報＞");
			System.out.println("　　・年　　：" + bean.getYear());
			System.out.println("　　・月　　：" + bean.getMonth());
			System.out.println("　　・日　　：" + bean.getDay());
			System.out.println("　　・開始時：" + bean.getFromHour());
			System.out.println("　　・開始分：" + bean.getFromMinute());
			System.out.println("　　・終了時：" + bean.getToHour());
			System.out.println("　　・終了分：" + bean.getToMinute());
			System.out.println("　　・種別ID：" + bean.getKindId());
			System.out.println("　　・種別名：" + bean.getKindName());
			System.out.println("　　・メモ　：" + bean.getMemo());
		}
		//ScheduleControl → ScheduleControlDBAccessのテスト【項番６】
		TaskBean tBean1 = new TaskBean("2018", "06", "27", "09", "00", "18", "00",
														"K05", "[研修]", "結合テスト");
		int insertRet =  ScheduleControl.registerTaskBean(tBean1);
		System.out.println("【ScheduleControl → ScheduleControlDBAccessの結果④】");
		System.out.println("insert()返却値：" + insertRet);

		//ScheduleControl → ScheduleControlDBAccessのテスト【項番７】
		TaskBean tBean2 = new TaskBean("2018", "06", "26", "10", "30", "12", "30",
														"K99", "[その他]", "個人面接");
		int updateRet = ScheduleControl.registerTaskBean(tBean2);
		System.out.println("【ScheduleControl → ScheduleControlDBAccessの結果⑤】");
		System.out.println("update()返却値：" + updateRet);

		//ScheduleControl → ScheduleControlDBAccessのテスト【項番８】
		int deleteRet = ScheduleControl.removeTaskBean("2018", "06", "26");
		System.out.println("【ScheduleControl → ScheduleControlDBAccessの結果⑥】");
		System.out.println("delete()返却値：" + deleteRet);
	}
}
