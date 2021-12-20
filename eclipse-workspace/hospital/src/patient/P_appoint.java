/*
Ordinary patient appointment interface;
Parameter: pid(String);
Written by zjy;
*/
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


public class P_appoint extends JFrame implements ActionListener, ItemListener{
    //基本组件
    JLabel jl1,jl2,jl3,jl4;
    JButton jb1,jb2,jb3;
    JPanel jp1,jp2,jp3,jp4;
    JComboBox box1, box2, box3,box4,box5;
    int pid;
    String dept;
    int deptID;
    String doc;
    int docID;
    String date1,date2,date3;

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



    public void update_box(JComboBox jbox1, JComboBox jbox2, String sql_, String s1, String s2) {
        jbox2.removeAllItems();
        jbox1.removeAllItems();
        try {
            //1、加载驱动(把下需要的驱动程序加入内存中)
            Class.forName(driver);
            //2、得到连接(指定连接到哪个数据源)
            ct = DriverManager.getConnection(url, user, password);
            //3、创建ps

            ps1 = ct.prepareStatement(sql_);
            //预编译语句对象
            rs1 = ps1.executeQuery();//返回查询结果
            //如果有查询结果
            while (rs1.next()) {
                jbox1.addItem(rs1.getString(s1));
                jbox2.addItem(rs1.getString(s2));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //关闭资源（关闭顺序：谁后创建则先关闭)
            try{
                if(rs1!=null) rs.close();
                if(ps1!=null) ps.close();
                if(ct!=null) ct.close();
            }catch(Exception e){}
        }
    }

    public void update_date(JComboBox box, int docid,String date1){
        try {
            //1、加载驱动(把下需要的驱动程序加入内存中)
            Class.forName(driver);
            //2、得到连接(指定连接到哪个数据源)
            ct = DriverManager.getConnection(url, user, password);
            //3、创建ps

            ps = ct.prepareStatement("select * from appointment/* where doctor_id=docid and (String)date=date1*/");
            //预编译语句对象
            rs = ps.executeQuery();//返回查询结果
            //如果有查询结果
            int cnt = 0;
            while (rs.next()) {
                cnt=cnt+1;
            }
            if(cnt>=20) {
                box.removeItem(date1);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //关闭资源（关闭顺序：谁后创建则先关闭)
            try{
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(ct!=null) ct.close();
            }catch(Exception e){}
        }
    }

    //构造函数,以及一个限制科室选择的字符串，如果是发热病人，只能选发热科室
    public P_appoint(String pid_) {
        pid=Integer.parseInt(pid_);

        jl1=new JLabel("选择科室：");
        box1=new JComboBox();
        box2=new JComboBox();
        box1.addItemListener(this);
        box2.addItemListener(this);
        update_box(box1,box2,"select * from department","department_id","department_name");
        box1.setSelectedIndex(-1);
        box2.setSelectedIndex(-1);


        jl2=new JLabel("选择医生：");
        box3=new JComboBox();
        box4=new JComboBox();
        box3.addItemListener(this);
        box4.addItemListener(this);
        update_box(box3,box4,"select * from doctor","doctor_id","doctor_name");
        box3.setSelectedIndex(-1);
        box4.setSelectedIndex(-1);


        jl3=new JLabel("选择时间：");
        box5=new JComboBox();

        SimpleDateFormat smdate1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar1 = new GregorianCalendar();

        calendar1.setTime(new java.util.Date());
        calendar1.add(calendar1.DATE, 1);
        date1 = smdate1.format(calendar1.getTime());
        box5.addItem(date1);
        calendar1.add(calendar1.DATE, 1);
        date2 = smdate1.format(calendar1.getTime());
        box5.addItem(date2);
        calendar1.add(calendar1.DATE, 1);
        date3 = smdate1.format(calendar1.getTime());
        box5.addItem(date3);
        box5.addItemListener(this);
        box5.setSelectedIndex(-1);

        jb3=new JButton("确认");

        jp1=new JPanel();
        jp1.add(jl1);
        jp1.add(box1);
        jp1.add(box2);

        jp2=new JPanel();
        jp2.add(jl2);
        jp2.add(box3);
        jp2.add(box4);

        jp3=new JPanel();
        jp3.add(jl3);
        jp3.add(box5);

        jp4=new JPanel();
        jp4.add(jb3);

        this.setLayout(new GridLayout(4,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.setSize(500,700);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("普通预约");
        this.setVisible(true);
    }

    public static void main(String[] args) {new P_appoint("1");}



    @Override

    public void actionPerformed(ActionEvent e) {
        //确认预约，插入预约表
        if(e.getSource()==jb1) {
            if (box1.getSelectedIndex() == -1 || box3.getSelectedIndex() == -1 || box2.getSelectedIndex() == -1 || box4.getSelectedIndex() == -1 || box5.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog((Component) null, "预约信息不完整", "提示信息", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int maxi = 0;
                int patientID=pid;
                int doctorID=docID;
                int departId=deptID;
                Date date_=(Date)box5.getSelectedItem();

                //找出最大的预约id，加一
                try {
                    //1、加载驱动(把下需要的驱动程序加入内存中)
                    Class.forName(driver);
                    //2、得到连接(指定连接到哪个数据源)
                    ct = DriverManager.getConnection(url, user, password);
                    //3、创建ps
                    ps = ct.prepareStatement("select * from appointment");
                    //预编译语句对象
                    rs = ps.executeQuery();//返回查询结果
                    //如果有查询结果

                    while (rs.next()) {
                        if(maxi<rs.getInt(1)){
                            maxi=rs.getInt(1);
                        }
                    }

                } catch (Exception m) {
                    m.printStackTrace();
                } finally {
                    //关闭资源（关闭顺序：谁后创建则先关闭)
                    try {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                        if (ct != null) ct.close();
                    } catch (Exception m) {}
                }

                try {
                    //1、加载驱动(把下需要的驱动程序加入内存中)
                    Class.forName(driver);
                    //2、得到连接(指定连接到哪个数据源)
                    ct = DriverManager.getConnection(url, user, password);
                    //3、创建ps
                    ps = ct.prepareStatement("insert into appointment values (?,?,?,?,?)");
                    //预编译语句对象
                    ps.setInt(1,maxi);
                    ps.setInt(2,patientID);
                    ps.setInt(3,doctorID);
                    ps.setInt(4,departId);
                    ps.setDate(5,date_);
                    ps.executeUpdate();

                } catch (Exception x) {
                    x.printStackTrace();
                } finally {
                    //关闭资源（关闭顺序：谁后创建则先关闭)
                    try {
                        if (ps != null) ps.close();
                        if (ct != null) ct.close();
                    } catch (Exception d) {
                    }
                }

            }
            this.dispose();
        }
    }





    public void itemStateChanged(ItemEvent ie)
    {
        if(ie.getSource()==box1){
            box2.setSelectedIndex(box1.getSelectedIndex());
            deptID=(int)box1.getSelectedItem();
            String sql1="select * from doctor where department_id=deptID";
            update_box(box3,box4,sql1,"doctor_id","doctor_name");

            //然后是判断这两天的日期是不是约满了，从所有的预约表中查询
            //首先连接数据库，根据医生id查找date1和date2和date3的所有预约表并计数，如果当前计数大于20，则将该日期从表中删除
            docID=(int)box3.getSelectedItem();
            update_date(box5,docID,date1);
            update_date(box5,docID,date2);
            update_date(box5,docID,date3);
        }
        else if(ie.getSource()==box2){
            box1.setSelectedIndex(box2.getSelectedIndex());
            deptID=(int)box1.getSelectedItem();
            String sql1="select * from doctor where department_id=deptID";
            update_box(box3,box4,sql1,"doctor_id","doctor_name");

            //然后是判断这两天的日期是不是约满了，从所有的预约表中查询
            //首先连接数据库，根据医生id查找date1和date2和date3的所有预约表并计数，如果当前计数大于20，则将该日期从表中删除
            docID=(int)box3.getSelectedItem();
            update_date(box5,docID,date1);
            update_date(box5,docID,date2);
            update_date(box5,docID,date3);
        }
        else if(ie.getSource()==box3){
            box4.setSelectedIndex(box3.getSelectedIndex());
            //然后是判断这两天的日期是不是约满了，从所有的预约表中查询
            //首先连接数据库，根据医生id查找date1和date2和date3的所有预约表并计数，如果当前计数大于20，则将该日期从表中删除
            docID=(int)box3.getSelectedItem();
            update_date(box5,docID,date1);
            update_date(box5,docID,date2);
            update_date(box5,docID,date3);
        }
        else if(ie.getSource()==box4) {
            box3.setSelectedIndex(box4.getSelectedIndex());
            //然后是判断这两天的日期是不是约满了，从所有的预约表中查询
            //首先连接数据库，根据医生id查找date1和date2和date3的所有预约表并计数，如果当前计数大于20，则将该日期从表中删除
            docID = (int) box3.getSelectedItem();
            update_date(box5, docID, date1);
            update_date(box5, docID, date2);
            update_date(box5, docID, date3);
        }
    }



}