package driver;

import model.TaskBean;
/**
 * @author Hanseul Kim
 * */
public class UnitTest_TaskBean {
	public static void main(String[] args) {
		String tYear;
		String tMonth;
		String tDay;
		String tFromHour;
		String tFromMinute;
		String tToHour;
		String tToMinute;
		String tKindId;
		String tKindName;
		String tMemo;

		TaskBean tBean = new TaskBean("2018", "04", "27", "09", "00", "18", "00", "K05", "研修", "Java応用①");

		tBean.setYear("2017");
		tYear = tBean.getYear();
		System.out.println("getYear()戻り値：" + tYear);

		tBean.setMonth("06");
		tMonth = tBean.getMonth();
		System.out.println("getMonth()戻り値：" + tMonth);

		tBean.setDay("01");
		tDay = tBean.getDay();
		System.out.println("getDay()戻り値：" + tDay);

		tBean.setFromHour("10");
		tFromHour = tBean.getFromHour();
		System.out.println("getFromHour()戻り値：" + tFromHour);

		tBean.setFromMinute("00");
		tFromMinute = tBean.getFromMinute();
		System.out.println("getFromMinute()戻り値：" + tFromMinute);

		tBean.setToHour("12");
		tToHour = tBean.getToHour();
		System.out.println("getToHour()戻り値：" + tToHour);

		tBean.setToMinute("00");
		tToMinute = tBean.getToMinute();
		System.out.println("getMonth()戻り値：" + tToMinute);

		tBean.setKindId("K99");
		tKindId = tBean.getKindId();
		System.out.println("getKindId()戻り値：" + tKindId);

		tBean.setKindName("その他");
		tKindName = tBean.getKindName();
		System.out.println("getKindName()戻り値：" + tKindName);

		tBean.setMemo("配属式");
		tMemo = tBean.getMemo();
		System.out.println("getMemo()戻り値：" + tMemo);

	}
}
