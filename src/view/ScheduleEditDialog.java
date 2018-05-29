/*
 *　クラス名：ScheduleEditDialog
 *　概要　　：「スケジュール編集」画面を管理する。
 *　作成者名：模範解答
 *　作成日　：2017年5月26日
 *　修正者名：
 *　修正日　：
*/

//パッケージの定義
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

//パッケージのインポート
import model.KindBean;
import model.TaskBean;

//ScheduleEditDialogクラスの定義
public class ScheduleEditDialog extends JDialog
{
	//スタティック変数の定義
	public static final int REGISTER = 1;	//「登録」ボタン押下状態
	public static final int CLOSE = 0;		//「閉じる」ボタン押下状態
	public static final int REMOVE = -1;	//「削除」ボタン押下状態

	//インスタンス変数の定義
	private ArrayList<KindBean> kindList;	//種別リスト
	private int status;						//ボタン押下状態
	private int year;						//年
	private int month;						//月
	private int day;						//日

	private ScheduleEditDialog dialog;		//自インスタンスの参照
	private Font fontL;						//フォント大
	private Font fontM;						//フォント中

	private JLabel ymdLabel;				//「年月日(曜)」ラベル
	private JRadioButton timeSpRadio;		//「時刻指定」ラジオボタン
	private JComboBox fromHMCombo;			//「開始時刻」コンボボックス
	private JComboBox toHMCombo;			//「終了時刻」コンボボックス
	private JRadioButton daySpRadio;		//「終日指定」ラジオボタン
	private JComboBox kindNameCombo;		//「種別名」コンボボックス
	private JTextArea memoArea;				//「メモ」テキストエリア
	private JCheckBox importantChk;

	//コンストラクタの定義
	public ScheduleEditDialog(ArrayList<KindBean> kindList)
	{
		this.kindList = kindList;
		status = CLOSE;
		year = 0;
		month = 0;
		day = 0;

		dialog = this;
		fontL = new Font("ＭＳ ゴシック", Font.PLAIN, 30);
		fontM = new Font("ＭＳ ゴシック", Font.PLAIN, 20);

		setTitle("スケジュール編集");
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setModalityType(JDialog.DEFAULT_MODALITY_TYPE);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new NorthPanel(), BorderLayout.NORTH);
		getContentPane().add(new CenterPanel(), BorderLayout.CENTER);
		getContentPane().add(new SouthPanel(), BorderLayout.SOUTH);
	}

	//メソッドの定義
	//ウインドウサイズを自動的に調整する。
	public void addNotify()
	{
		super.addNotify();
		pack();
	}

	//年月日を設定する。
	public void setYearMonthDay(int year, int month, int day)
	{
		status = CLOSE;

		this.year = year;
		String yearStr = String.format("%d", year);

		this.month = month;
		String monthStr = String.format("%d", month);

		this.day = day;
		String dayStr = String.format("%d", day);

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		String weekStrAry[] = {"日", "月", "火", "水", "木", "金", "土"};
		String weekStr = weekStrAry[cal.get(Calendar.DAY_OF_WEEK) - 1];

		ymdLabel.setText(yearStr + "年" + monthStr + "月" + dayStr + "日(" + weekStr + ")");

		timeSpRadio.setSelected(true);
		fromHMCombo.setSelectedIndex(18);
		fromHMCombo.setEnabled(true);
		toHMCombo.setSelectedIndex(36);
		toHMCombo.setEnabled(true);
		kindNameCombo.setSelectedIndex(0);
		memoArea.setText("");
	}

	//タスクを設定する。
	public void setTaskBean(TaskBean taskBean)
	{
		String fromHour = taskBean.getFromHour();
		String fromMinute = taskBean.getFromMinute();
		String toHour = taskBean.getToHour();
		String toMinute = taskBean.getToMinute();
		int important = taskBean.getImportant();
		System.out.println("set important " + important);
		if(important == 1 ) {
			importantChk.setSelected(true);
		}
		if(fromHour.equals("00") == true && fromMinute.equals("00") == true && toHour.equals("24") == true && toMinute.equals("00") == true)
		{
			daySpRadio.setSelected(true);
			fromHMCombo.setSelectedIndex(18);
			fromHMCombo.setEnabled(false);
			toHMCombo.setSelectedIndex(36);
			toHMCombo.setEnabled(false);
		}
		else
		{
			timeSpRadio.setSelected(true);
			fromHMCombo.setSelectedIndex((Integer.parseInt(fromHour) * 60 + Integer.parseInt(fromMinute)) / 30);
			fromHMCombo.setEnabled(true);
			toHMCombo.setSelectedIndex((Integer.parseInt(toHour) * 60 + Integer.parseInt(toMinute)) / 30);
			toHMCombo.setEnabled(true);
		}

		String kindID = taskBean.getKindId();
		int idx;
		for(idx = 0; idx < kindList.size(); idx++)
		{
			if(kindID.equals(kindList.get(idx).getId()) == true)
			{
				kindNameCombo.setSelectedIndex(idx);
				break;
			}
		}

		String memo = taskBean.getMemo();
		memoArea.setText(memo);
	}

	//タスクを取得する。
	public TaskBean getTaskBean()
	{
		String yearStr = String.format("%04d", year);
		String monthStr = String.format("%02d", month);
		String dayStr = String.format("%02d", day);

		String fromHour;
		String fromMinute;
		String toHour;
		String toMinute;
		int important = 0;
		if(timeSpRadio.isSelected() == true)
		{
			String fromHM = (String)(fromHMCombo.getSelectedItem());
			fromHour = fromHM.substring(0, 2);
			fromMinute = fromHM.substring(3);
			String toHM = (String)(toHMCombo.getSelectedItem());
			toHour = toHM.substring(0, 2);
			toMinute = toHM.substring(3);
		}
		else
		{
			fromHour = "00";
			fromMinute = "00";
			toHour = "24";
			toMinute = "00";
		}
		if(importantChk.isSelected() == true) {
			important = 1;
		}
		int idx = kindNameCombo.getSelectedIndex();
		String kindID = kindList.get(idx).getId();
		String kindName = kindList.get(idx).getName();

		String memo = memoArea.getText();

		TaskBean taskBean = new TaskBean(yearStr, monthStr, dayStr, fromHour, fromMinute, toHour, toMinute, kindID, kindName, memo, important);
		System.out.println("task bean"+taskBean);
		return taskBean;

	}

	//ボタン押下状態を調べる。
	public int isStatus()
	{
		return status;
	}

	//NorthPanelクラスの定義（内部クラス）
	private class NorthPanel extends JPanel implements ActionListener
	{
		//コンストラクタの定義
		public NorthPanel()
		{
			setLayout(new BorderLayout());

			ymdLabel = new JLabel("年月日(曜)", JLabel.CENTER);
			ymdLabel.setFont(fontL);

			JPanel tmpPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			tmpPanel1.add(ymdLabel);

			timeSpRadio = new JRadioButton();
			timeSpRadio.setFont(fontM);
			timeSpRadio.addActionListener(this);

			fromHMCombo = new JComboBox();
			fromHMCombo.setFont(fontM);
			int hour, minute;
			for(hour = 0; hour < 24; hour++)
			{
				for(minute = 0; minute < 60; minute = minute + 5)
				{
					fromHMCombo.addItem(String.format("%02d:%02d", hour, minute));
				}
			}
			fromHMCombo.addItem("24:00");

			JLabel fromHMLabel = new JLabel("から", JLabel.LEFT);
			fromHMLabel.setFont(fontM);

			toHMCombo = new JComboBox();
			toHMCombo.setFont(fontM);
			for(hour = 0; hour < 24; hour++)
			{
				for(minute = 0; minute < 60; minute = minute + 5)
				{
					toHMCombo.addItem(String.format("%02d:%02d", hour, minute));
				}
			}
			toHMCombo.addItem("24:00");

			JLabel toHMLabel = new JLabel("まで", JLabel.LEFT);
			toHMLabel.setFont(fontM);

			JPanel tmpPanel2= new JPanel(new FlowLayout(FlowLayout.LEFT));
			tmpPanel2.add(timeSpRadio);
			tmpPanel2.add(fromHMCombo);
			tmpPanel2.add(fromHMLabel);
			tmpPanel2.add(toHMCombo);
			tmpPanel2.add(toHMLabel);

			daySpRadio = new JRadioButton("終日");
			daySpRadio.setFont(fontM);
			daySpRadio.addActionListener(this);

			JPanel tmpPanel3= new JPanel(new FlowLayout(FlowLayout.LEFT));
			tmpPanel3.add(daySpRadio);

			JPanel tmpPanel4 = new JPanel(new BorderLayout());
			tmpPanel4.add(tmpPanel2, BorderLayout.NORTH);
			tmpPanel4.add(tmpPanel3, BorderLayout.SOUTH);

			JLabel kindNameLabel = new JLabel("種別", JLabel.LEFT);
			kindNameLabel.setFont(fontM);

			kindNameCombo = new JComboBox();
			kindNameCombo.setFont(fontM);
			for(KindBean kindBean : kindList)
			{
				kindNameCombo.addItem(kindBean.getName());
			}
			kindNameCombo.addActionListener(this);

			importantChk= new JCheckBox("重要！");
			importantChk.setFont(fontM);
			importantChk.addActionListener(this);


			JPanel tmpPanel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			tmpPanel5.add(kindNameLabel);
			tmpPanel5.add(kindNameCombo);
			tmpPanel5.add(importantChk);

			JPanel tmpPanel6 = new JPanel(new BorderLayout());
			tmpPanel6.add(tmpPanel4, BorderLayout.NORTH);
			tmpPanel6.add(tmpPanel5, BorderLayout.SOUTH);

			add(tmpPanel1, BorderLayout.NORTH);
			add(tmpPanel6, BorderLayout.SOUTH);

			ButtonGroup group = new ButtonGroup();
			group.add(timeSpRadio);
			group.add(daySpRadio);
		}

		//メソッドの定義
		//｢開始時刻」ラジオボタン押下，及び「終了時刻」ラジオボタン押下。
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == timeSpRadio)
			{
				fromHMCombo.setEnabled(true);
				toHMCombo.setEnabled(true);
			}
			else if(e.getSource() == daySpRadio)
			{
				fromHMCombo.setEnabled(false);
				toHMCombo.setEnabled(false);
			}
		}
	}

	//CenterPanelクラスの定義（内部クラス）
	private class CenterPanel extends JPanel
	{
		//コンストラクタの定義
		public CenterPanel()
		{
			setLayout(new BorderLayout());

			memoArea = new JTextArea(8, 40);
			memoArea.setFont(fontM);

			add(memoArea, BorderLayout.CENTER);
		}
	}

	//SouthPanelクラスの定義（内部クラス）
	private class SouthPanel extends JPanel implements ActionListener
	{
		//インスタンス変数の定義
		private JButton registerBtn;		//「登録」ボタン
		private JButton removeBtn;			//「削除」ボタン

		//コンストラクタの定義
		public SouthPanel()
		{
			setLayout(new FlowLayout(FlowLayout.CENTER));

			registerBtn = new JButton("登録");
			registerBtn.setFont(fontM);
			registerBtn.addActionListener(this);

			JLabel spaceLbl = new JLabel();
			spaceLbl.setPreferredSize(new Dimension(100, 20));

			removeBtn = new JButton("削除");
			removeBtn.setFont(fontM);
			removeBtn.addActionListener(this);

			add(registerBtn);
			add(spaceLbl);
			add(removeBtn);
		}

		//メソッドの定義
		//｢登録」ボタン押下，及び「削除」ボタン押下。
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == registerBtn)
			{
				int fromHMIdx = fromHMCombo.getSelectedIndex();
				int toHMIdx = toHMCombo.getSelectedIndex();
				if(timeSpRadio.isSelected() == true && fromHMIdx > toHMIdx)
				{
					JOptionPane.showConfirmDialog(dialog, "終了時刻が不正です。", "エラー", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					return;
				}
				status = REGISTER;
			}
			else if(e.getSource() == removeBtn)
			{
				status = REMOVE;
			}
			dialog.setVisible(false);
		}
	}
}
