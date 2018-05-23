/*
 *　クラス名：KindBean（スタブ）
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

public class KindBean implements Serializable
{
	private String id, name;

	//コンストラクタの定義
	public KindBean(String id, String name) {
		//System.out.println("Kind引数：" + id + ":" + name );
		this.id=id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "KindBean [id=" + id + ", name=" + name + "]";
	}

}
