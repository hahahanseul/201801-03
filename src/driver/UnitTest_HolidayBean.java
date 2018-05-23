package driver;

import model.HolidayBean;

/**
 * @author Hanseul Kim
 * */
public class UnitTest_HolidayBean {
	public static void main(String[] args) {
		String tYear;
		String tMonth;
		String tDay;
		String tName;

		HolidayBean hBean = new HolidayBean("2018", "01", "01", "元日");
		hBean.setYear("2019");
		tYear = hBean.getYear();
		System.out.println("getYear()戻り値：" + tYear);

		hBean.setMonth("12");
		tMonth = hBean.getMonth();
		System.out.println("getMonth()戻り値：" + tMonth);

		hBean.setDay("23");
		tDay = hBean.getDay();
		System.out.println("getDay()戻り値：" + tDay);

		hBean.setName("天皇誕生日");
		tName = hBean.getName();
		System.out.println("getName()戻り値：" + tName);

	}
}
