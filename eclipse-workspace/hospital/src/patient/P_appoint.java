/*
Ordinary patient appointment interface;
Parameter: pid(String);
Written by zjy;
*/
//在本页面只选择科室
package patient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.Date;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.JComboBox;


import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
import java.util.GregorianCalendar;


public class P_appoint extends JFrame implements ActionListener{
    //基本组件
    JPanel jp1, jp2;
    JLabel jl1;
    JButton jb1, jb2;
    JTable jt;
    JScrollPane jsp;
    JTextField jtf;

    public static int row_number;
    String pid;
    String dept;
    int deptID;
    String doc;
    int docID;

    //连接数据库要用的东西
    Connection ct=null;
    PreparedStatement ps= null;
    ResultSet rs=null;
    PreparedStatement ps1= null;
    ResultSet rs1=null;
    PreparedStatement ps2= null;
    ResultSet rs2=null;
    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/hospital";
    String user="root";
    String password="A20001112a";
    String sql_insert;



    public P_appoint(String pid_) {
        pid=pid_;

        jp1=new JPanel();
        jl1=new JLabel("选择科室");
        jb1=new JButton("确认");
        jb1.addActionListener(this);
        jp1.add(jl1);
        jp1.add(jb1);

        DeptTable table_init = new DeptTable();
        jt = new JTable(table_init);
        jsp = new JScrollPane(jt);

        this.add(jsp);
        this.add(jp1,"South");

        this.setSize(500,700);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("普通预约-选择科室");
        this.setVisible(true);
    }

    public static void main(String[] args) {new P_appoint("1");}



    @Override

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            row_number=this.jt.getSelectedRow();
            if(row_number==-1){
                //提示
                JOptionPane.showMessageDialog(this, "请选择科室");
                return ;//谁调用就返回到哪
            }
            else {
                String deptid=this.jt.getValueAt(row_number, 0).toString();
                new choose_doctor(pid, deptid);
                this.dispose();
            }

        }
    }

}