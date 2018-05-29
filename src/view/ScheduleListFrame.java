/*
 *　クラス名：ScheduleListFrame
 *　概要　　：「スケジュール表」画面を管理する。
 *　作成者名：模範解答
 *　作成日　：2017年5月26日
 *　修正者名：
 *　修正日　：
*/
//パッケージの定義
package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//パッケージのインポート
import ctrl.ScheduleControl;
import model.HolidayBean;
import model.KindBean;
import model.TaskBean;
//ScheduleListFrameクラスの定義
public class ScheduleListFrame extends JFrame
{
        //インスタンス変数の定義
        private ScheduleEditDialog dialog;           //「スケジュール編集」画面
        private int year;                                           //年
        private int month;                                          //月
        private DayPanel dayPanelAry[];                      //「日にち」パネルの配列
        private Font fontL;                                         //フォント大
        private Font fontM;                                         //フォント中
        private Font fontS;                                         //フォント小

        //スタティックメソッドの定義
        //「スケジュール表」画面を表示する。
        public static void main(String args[])
        {
               ScheduleListFrame frame;
               ArrayList<KindBean> kindList = ScheduleControl.getKindList();
               frame = new ScheduleListFrame(kindList);
               frame.setVisible(true);
        }
        //コンストラクタの定義
        public ScheduleListFrame(ArrayList<KindBean> kindList)
        {
               dialog = new ScheduleEditDialog(kindList);
               Calendar cal = Calendar.getInstance();
               year = cal.get(Calendar.YEAR);
               month = cal.get(Calendar.MONTH) + 1;
               fontL = new Font("ＭＳ ゴシック", Font.PLAIN, 30);
               fontM = new Font("ＭＳ ゴシック", Font.PLAIN, 24);
               fontS = new Font("ＭＳ ゴシック", Font.PLAIN, 16);
               setTitle("スケジュール表");
               setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               getContentPane().setLayout(new BorderLayout());
               getContentPane().add(new NorthPanel(), BorderLayout.NORTH);
               getContentPane().add(new SouthPanel(), BorderLayout.SOUTH);
               setCalendar();
        }
        //メソッドの定義
        //ウインドウサイズを調整する。
        public void addNotify()
        {
               super.addNotify();
               Insets ins = getInsets();
               setSize(700 + ins.left + ins.right, 690 + ins.top + ins.bottom);
        }
        //カレンダーを更新する。
        private void setCalendar()
        {
               Calendar cal = Calendar.getInstance();
               cal.set(year, month - 1, 1);
               int lastDay = cal.getActualMaximum(Calendar.DATE);
               int firstDayIdx = cal.get(Calendar.DAY_OF_WEEK) - 1;
               int lastDayIdx = firstDayIdx + lastDay - 1;
               int idx;
               for(idx = 0; idx < dayPanelAry.length; idx++)
               {
                       if(idx >= firstDayIdx && idx <= lastDayIdx)
                       {
                              dayPanelAry[idx].setDay(idx - firstDayIdx + 1);
                       }
                       else
                       {
                              dayPanelAry[idx].setDay(0);
                       }
                       dayPanelAry[idx].setTaskBean(null);
               }
               String yearStr = String.format("%04d", year);
               String monthStr = String.format("%02d", month);
               ArrayList<HolidayBean> holidayList
                       = ScheduleControl.getHolidayList(yearStr, monthStr);
               for(HolidayBean holidayBean : holidayList)
               {
                       int day = Integer.parseInt(holidayBean.getDay());
                       dayPanelAry[firstDayIdx + day - 1].setHolidayBean(holidayBean);
               }
               ArrayList<TaskBean> taskList
                       = ScheduleControl.getTaskList(yearStr, monthStr);
               for(TaskBean taskBean : taskList)
               {
                       int day = Integer.parseInt(taskBean.getDay());
                       dayPanelAry[firstDayIdx + day - 1].setTaskBean(taskBean);
               }
        }

        public JButton setBtnStyle(JButton btn) {
               btn.setFont(fontL);
               btn.setContentAreaFilled(false);
               btn.setFocusPainted(false);
               return btn;
        }
        //NorthPanelクラスの定義（内部クラス）
        private class NorthPanel extends JPanel implements ActionListener
        {
               //インスタンス変数の定義
               private JButton prevBtn;                     //｢昨月」ボタン
               private JLabel ymLabel;                              //｢年月」ラベル
               private JButton nextBtn;                     //｢翌月」ボタン
               private JButton yPrevBtn;
               private JButton yNextBtn;

               //コンストラクタの定義
               public NorthPanel()
               {
                       setLayout(new BorderLayout());

                       yPrevBtn=new JButton("<<");
                       yPrevBtn = setBtnStyle(yPrevBtn);
                       yPrevBtn.setToolTipText("昨年");
                       yPrevBtn.addActionListener(this);
                       prevBtn = new JButton("<");
                       prevBtn = setBtnStyle(prevBtn);
                       prevBtn.setToolTipText("昨月");
                       prevBtn.addActionListener(this);
                       ymLabel = new JLabel(year + "年" + month + "月", JLabel.CENTER);
                       ymLabel.setFont(fontL);
                       nextBtn = new JButton(">");
                       nextBtn = setBtnStyle(nextBtn);
                       nextBtn.setToolTipText("翌月");
                       nextBtn.addActionListener(this);
                       yNextBtn = new JButton(">>");
                       yNextBtn = setBtnStyle(yNextBtn);
                       yNextBtn.setToolTipText("翌年");
                       yNextBtn.addActionListener(this);


                       JPanel tmpPanel = new JPanel(new FlowLayout());
                       tmpPanel.add(yPrevBtn);
                       tmpPanel.add(prevBtn);
                       tmpPanel.add(ymLabel);
                       tmpPanel.add(nextBtn);
                       tmpPanel.add(yNextBtn);
                       add(tmpPanel, BorderLayout.NORTH);
               }
               //メソッドの定義
               //｢昨月」ボタン押下，及び「翌月」ボタン押下。
               public void actionPerformed(ActionEvent e)
               {
                       Calendar cal = Calendar.getInstance();
                       cal.set(year, month - 1, 1);
                       if(e.getSource() == prevBtn)
                       {
                              cal.add(Calendar.MONTH, -1);
                       }
                       else if(e.getSource() == nextBtn)
                       {
                              cal.add(Calendar.MONTH, 1);
                       }
                       else if(e.getSource() == yPrevBtn)
                       {
                              cal.add(Calendar.YEAR, -1);
                       }
                       else if(e.getSource() == yNextBtn)
                       {
                              cal.add(Calendar.YEAR, 1);
                       }
                       year = cal.get(Calendar.YEAR);
                       month = cal.get(Calendar.MONTH) + 1;
                       ymLabel.setText(year + "年" + month + "月");
                       setCalendar();
               }
        }
        //SouthPanelクラスの定義（内部クラス）
        private class SouthPanel extends JPanel
        {
               //コンストラクタの定義
               public SouthPanel()
               {
                       setLayout(new BorderLayout());
                       String weekStrAry[] = {"日", "月", "火", "水", "木", "金", "土"};
                       JPanel tmpPanel1 = new JPanel(new GridLayout(1, 7));
                       for(String weekStr : weekStrAry)
                       {
                              JLabel weekLbl = new JLabel(weekStr, JLabel.CENTER);
                              weekLbl.setFont(fontM);
                              tmpPanel1.add(weekLbl);
                       }
                       dayPanelAry = new DayPanel[42];
                       JPanel tmpPanel2 = new JPanel(new GridLayout(6, 7));
                       int idx;
                       for(idx = 0; idx < dayPanelAry.length; idx++)
                       {
                              DayPanel dayPanel = new DayPanel();
                              dayPanelAry[idx] = dayPanel;
                              tmpPanel2.add(dayPanel);
                       }
                       add(tmpPanel1, BorderLayout.NORTH);
                       add(tmpPanel2, BorderLayout.SOUTH);
               }
        }
        //DayPanelクラスの定義（内部クラス）
        private class DayPanel extends JPanel implements ActionListener
        {
               //インスタンス変数の定義
               private int day;                                     //日
               private TaskBean taskBean;                   //タスク
               private JButton dayBtn;                              //｢日にち」ボタン
               private JTextArea holidayArea;               //｢祝日」テキストエリア
               private JTextArea taskArea;                  //｢タスク」テキストエリア
               //コンストラクタの定義
               public DayPanel()
               {
                       day = 0;
                       taskBean = null;
                       setLayout(new BorderLayout());
                       dayBtn = new JButton();
                       dayBtn.setPreferredSize(new Dimension(100, 20));
                       dayBtn.setFont(fontS);
                       dayBtn.addActionListener(this);
                       holidayArea = new JTextArea();
                       holidayArea.setPreferredSize(new Dimension(100, 20));
                       holidayArea.setOpaque(false);
                       holidayArea.setFont(fontS);
                       holidayArea.setEditable(false);
                       taskArea = new JTextArea();
                       taskArea.setPreferredSize(new Dimension(100, 60));
                       taskArea.setOpaque(false);
                       taskArea.setFont(fontS);
                       taskArea.setEditable(false);
                       JPanel tmpPanel = new JPanel(new BorderLayout());
                       tmpPanel.setOpaque(false);
                       tmpPanel.add(holidayArea, BorderLayout.NORTH);
                       tmpPanel.add(taskArea, BorderLayout.SOUTH);
                       setBorder(BorderFactory.createLineBorder(Color.BLACK));
                       setBackground(new Color(238, 238, 238));
                       add(dayBtn, BorderLayout.NORTH);
                       add(tmpPanel, BorderLayout.SOUTH);
               }
               //メソッドの定義
               //｢日にち」ボタン押下。
               public void actionPerformed(ActionEvent e)
               {
                       dialog.setYearMonthDay(year, month, day);
                       if(taskBean != null)
                       {
                              dialog.setTaskBean(taskBean);
                       }
                       dialog.setVisible(true);
                       int status = dialog.isStatus();
                       if(status == ScheduleEditDialog.REGISTER)
                       {
            	   			  TaskBean taskBean = dialog.getTaskBean();
                              ScheduleControl.registerTaskBean(taskBean);
                              this.taskBean = taskBean;
                              taskArea.setText(
                                      taskBean.getFromHour() + ":" + taskBean.getFromMinute()
                                      + "-" + taskBean.getToHour() + ":" + taskBean.getToMinute()
                                      + "\n" + taskBean.getKindName() + "\n" + taskBean.getMemo());
                       }
                       else if(status == ScheduleEditDialog.REMOVE)
                       {
                              String yearStr = String.format("%04d", year);
                              String monthStr = String.format("%02d", month);
                              String dayStr = String.format("%02d", day);
                              ScheduleControl.removeTaskBean(yearStr, monthStr, dayStr);
                              taskBean = null;
                              taskArea.setText("");
                       }
                       setCalendar();
               }
               //日にちを設定する。
               public void setDay(int day)
               {
                       this.day = day;
                       if(day != 0)
                       {
                              dayBtn.setText(day + "日");
                              dayBtn.setEnabled(true);
                              Calendar cal = Calendar.getInstance();
                              cal.set(year, month - 1, day);
                              Date date = new Date();
                              SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
                              String thisYear = sdf.format(date).substring(0, 4);
                              String thisMonth = sdf.format(date).substring(4, 6);
                              String today = sdf.format(date).substring(6);
                              int week = cal.get(Calendar.DAY_OF_WEEK);
                              if(week == Calendar.SUNDAY)
                              {
                                      setBackground(Color.PINK);
                              }
                              else if(week == Calendar.SATURDAY)
                              {
                                      setBackground(Color.CYAN);
                              }
                              else if(year == Integer.parseInt(thisYear) && month == Integer.parseInt(thisMonth) &&day == Integer.parseInt(today))
                              {
                                      setBackground(Color.BLUE);
                              }
                              else
                              {
                                      setBackground(Color.WHITE);
                              }
                       }
                       else
                       {
                              dayBtn.setText("");
                              dayBtn.setEnabled(false);
                              setBackground(new Color(238, 238, 238));
                       }
                       holidayArea.setText("");
                       taskArea.setText("");
               }
               //祝日を設定する。
               public void setHolidayBean(HolidayBean holidayBean)
               {
                       holidayArea.setText(holidayBean.getName());
                       setBackground(Color.PINK);
               }
               //タスクを設定する。
               public void setTaskBean(TaskBean taskBean)
               {

                       this.taskBean = taskBean;
                       if(taskBean != null)
                       {
                              taskArea.setText(
                                      taskBean.getFromHour() + ":" + taskBean.getFromMinute()
                                      + "-" + taskBean.getToHour() + ":" + taskBean.getToMinute()
                                      + "\n" + taskBean.getKindName() + "\n" + taskBean.getMemo());
                              if(taskBean.getImportant() == 1 ) {
                           	   setBackground(Color.RED);
                              }
                       }

               }
        }
}