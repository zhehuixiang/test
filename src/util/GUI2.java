package util;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.*;
 
/**
* 下拉框，列表框，窗口滚动
* Created by admin on 2017/7/9.
*/
public class GUI2 extends JFrame{
// 定义需要用到的组件
private JList jList;
private JComboBox jComboBox;
private JScrollPane jScrollPane;
private JLabel jLabel, jLabel2;
private JPanel jPanel, jPanel2;
public static void main(String[] args){
GUI2 scroll = new GUI2();
}
public GUI2(){
jPanel = new JPanel();
jPanel2 = new JPanel();
jLabel = new JLabel("所在省");
jLabel2 = new JLabel("所在市");
String [] province = {"广西", "广东", "湖南"};
// 下拉框
jComboBox = new JComboBox(province);
String [] city = {"南宁", "柳州", "广州","深圳", "长沙"};
String[] number={"1","2","3,","4","5"};
// 列表框
jList = new JList(city);
jList.setVisibleRowCount(3);
// 窗口滚动
this.setLayout(new GridLayout(2, 1));
JList jList2=new JList(number);
jList2.setVisibleRowCount(3);
this.setLayout(new GridLayout(4,1));
//JPanel jPanell = new JPanel(new BorderLayout());
//jPanell.add(jList,BorderLayout.WEST);
//jPanell.add(jList2,BorderLayout.EAST);
jScrollPane = new JScrollPane();
jScrollPane.getViewport().add(jList, BorderLayout.WEST);
jScrollPane.getViewport().add(jList2, BorderLayout.EAST);
jScrollPane.setAutoscrolls(true);

jPanel.add(jLabel);
jPanel.add(jComboBox);
 
jPanel2.add(jLabel2);
jPanel2.add(jScrollPane);
 
this.add(jPanel);
this.add(jPanel2);
this.setTitle("Java");
this.setLocation(500, 250);
this.setSize(350, 200);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setVisible(true);
}
}