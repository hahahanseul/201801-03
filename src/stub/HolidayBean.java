/*
 *　クラス名：HolidayBean（スタブ）
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

public class HolidayBean implements Serializable
{
	//インスタンス変数の定義
	private String year;					//年
	private String month;					//月
	private String day;						//日
	private String name;					//名称

	//コンストラクタの定義
	public HolidayBean(String year, String month, String day, String name)
	{
		//System.out.println("Holiday引数：" + year + ":" + month + ":" + day + ":" + name);
		this.year = year;
		this.month = month;
		this.day = day;
		this.name = name;
	}

	//インスタンスの文字列表現
	public String toString()
	{
		return ("HolidayBean:" + year + ":" + month + ":" + day + ":" + name);
	}
}
