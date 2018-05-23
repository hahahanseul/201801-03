package driver;
import model.KindBean;

/**
 * @author Hanseul Kim
 * */
public class UnitTest_KindBean {
	public static void main(String[] args) {
		String tId;
		String tName;
		KindBean kBean = new KindBean("K05", "研修");

		kBean.setId("K99");
		tId = kBean.getId();
		System.out.println("getId()戻り値:" + tId);

		kBean.setName("その他");
		tName = kBean.getName();
		System.out.println("getName()戻り値:" + tName);

	}
}
