package model;

import java.io.Serializable;

/**
 * @author Hanseul Kim
 * */

public class HolidayBean implements Serializable {
	private String year, month, day, name;

	public HolidayBean(String year, String month, String day, String name) {
		setYear(year);
		setMonth(month);
		setDay(day);
		setName(name);
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
