/*
 *　クラス名：TaskBean（スタブ）
 *　概要　　：クラスScheduleControlDBAccessの単体テスト用スタブ
 *　作成者名： キム・ハンスル
 *　作成日　： 2018/05/22
 *　修正者名：
 *　修正日　：
*/

//パッケージの定義
package stub;

//パッケージのインポート
import java.io.Serializable;

public class TaskBean implements Serializable {
	private String year, month, day, fromHour, fromMinute, toHour, toMinute, kindId, kindName, memo;

	public TaskBean(String year, String month, String day, String fromHour, String fromMinute, String toHour,
			String toMinute, String kindId, String kindName, String memo) {
	/*	System.out.println("Task引数：" + year + ":" + month + ":" + day + ":" + fromHour + ":" + ":" + fromMinute + ":"
				+ toHour + ":" + toMinute + ":" + kindId + ":" + kindName + ":" + memo);*/
		this.year = year;
		this.month = month;
		this.day = day;
		this.fromHour = fromHour;
		this.fromMinute = fromMinute;
		this.toHour = toHour;
		this.toMinute = toMinute;
		this.kindId = kindId;
		this.kindName = kindName;
		this.memo = memo;
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

	@Override
	public String toString() {
		return "TaskBean [year=" + year + ", month=" + month + ", day=" + day + ", fromHour=" + fromHour
				+ ", fromMinute=" + fromMinute + ", toHour=" + toHour + ", toMinute=" + toMinute + ", kindId=" + kindId
				+ ", kindName=" + kindName + ", memo=" + memo + "]";
	}

}
