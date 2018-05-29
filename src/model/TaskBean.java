package model;

import java.io.Serializable;

/**
 * @author Hanseul Kim
 * */
public class TaskBean implements Serializable {
	private String year, month, day, fromHour, fromMinute, toHour, toMinute, kindId, kindName, memo;
	private int important;
	public TaskBean(String year, String month, String day, String fromHour, String fromMinute, String toHour,
			String toMinute, String kindId, String kindName, String memo,int important) {
		setYear(year);
		setMonth(month);
		setDay(day);
		setFromHour(fromHour);
		setFromMinute(fromMinute);
		setToHour(toHour);
		setToMinute(toMinute);
		setKindId(kindId);
		setKindName(kindName);
		setMemo(memo);
		setImportant(important);
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

	public String getFromHour() {
		return fromHour;
	}

	public void setFromHour(String fromHour) {
		this.fromHour = fromHour;
	}

	public String getFromMinute() {
		return fromMinute;
	}

	public void setFromMinute(String fromMinute) {
		this.fromMinute = fromMinute;
	}

	public String getToHour() {
		return toHour;
	}

	public void setToHour(String toHour) {
		this.toHour = toHour;
	}

	public String getToMinute() {
		return toMinute;
	}

	public void setToMinute(String toMinute) {
		this.toMinute = toMinute;
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getImportant() {
		return important;
	}

	public void setImportant(int important) {
		this.important = important;
	}
}
