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


public class choose_time extends JFrame implements ActionListener, ItemListener{
    //基本组件
    JLabel jl1,jl2,jl3,jl4;
    JButton jb1,jb2,jb3;
    JPanel jp1,jp2,jp3,jp4;
    JComboBox box1, box2, box3,box4,box5;
    String pid;
    String deptid;
    String docid;
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

    public static void main(String[] args) {new choose_time("1","2","2");}


    public choose_time(String pid_, String deptid_, String docid_) {
        pid=pid_;
        deptid=deptid_;
        docid=docid_;
        docID=Integer.parseInt(docid_);
        jl1=new JLabel("选择时间：");
        box1=new JComboBox();

        java.util.Date date0=new java.util.Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date0);

        calendar.add(calendar.DATE,1);
        java.util.Date uDate1=calendar.getTime();
        Date date11=new Date(uDate1.getTime());
        calendar.add(calendar.DATE,1);
        java.util.Date uDate2=calendar.getTime();
        Date date22=new Date(uDate2.getTime());
        calendar.add(calendar.DATE,1);
        java.util.Date uDate3=calendar.getTime();
        Date date33=new Date(uDate3.getTime());


        int cnt1=0, cnt2=0, cnt3=0;
        try {
            //1、加载驱动(把下需要的驱动程序加入内存中)
            Class.forName(driver);
            //2、得到连接(指定连接到哪个数据源)
            ct = DriverManager.getConnection(url, user, password);
            //3、创建ps
            ps1 = ct.prepareStatement("select * from appointment where doctor_id=?");
            ps1.setInt(1,docID);
            rs1 = ps1.executeQuery();//返回查询结果
            //如果有查询结果
            while (rs1.next()) {
                if(rs1.getDate(5)==date11)
                    cnt1++;
                else if(rs1.getDate(5)==date22)
                    cnt2++;
                else if(rs1.getDate(5)==date33)
                    cnt3++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //关闭资源（关闭顺序：谁后创建则先关闭)
            try{
                if(rs1!=null) rs1.close();
                if(ps1!=null) ps1.close();
                if(ct!=null) ct.close();
            }catch(Exception e){}
        }
        if(cnt1<20)
            box1.addItem(date11);
        if(cnt2<20)
            box1.addItem(date22);
        if(cnt3<20)
            box1.addItem(date33);

        box1.setSelectedIndex(-1);
        box1.addItemListener(this);


        jp4=new JPanel();
        jb3=new JButton("确认");
        jb3.addActionListener(this);
        jp4.add(jb3);

        jp1=new JPanel();
        jp1.add(jl1);
        jp1.add(box1);

        this.setLayout(new GridLayout(4,1));
        this.add(jp1);
        this.add(jp4);
        this.setSize(500,700);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("普通预约-选择时间");
        this.setVisible(true);
    }


    @Override

    public void actionPerformed(ActionEvent e) {
        //确认预约，插入预约表
        if (e.getSource() == jb3) {
            if (box1.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog((Component) null, "请选择时间", "提示信息", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                Date date_=(Date)box1.getSelectedItem();
                //System.out.println(date_);
                /*
                需要把预约表编号改为自增
                int maxi=0;
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
                }*/

                try {
                    //1、加载驱动(把下需要的驱动程序加入内存中)
                    Class.forName(driver);
                    //2、得到连接(指定连接到哪个数据源)
                    ct = DriverManager.getConnection(url, user, password);
                    //3、创建ps
                    ps = ct.prepareStatement("INSERT INTO appointment(patient_id,doctor_id,department_id,date) VALUES(?,?,?,?)");
                    //预编译语句对象
                   // ps.setInt(1,maxi+1);
                    ps.setInt(1,Integer.parseInt(pid));
                    ps.setInt(2,Integer.parseInt(docid));
                    ps.setInt(3,Integer.parseInt(deptid));
                    ps.setDate(4,date_);
                    ps.executeUpdate();

                } catch (Exception x) {
                    x.printStackTrace();
                } finally {
                    //关闭资源（关闭顺序：谁后创建则先关闭)
                    try {
                        if (ps != null) ps.close();
                        if (ct != null) ct.close();
                    } catch (Exception d) {}
                }
                JOptionPane.showMessageDialog((Component) null, "预约成功", "提示信息", JOptionPane.ERROR_MESSAGE);
                this.dispose();


            }
        }
    }



    public void itemStateChanged(ItemEvent ie)
    {
        if(ie.getSource()==box1){
            int aa=box1.getSelectedIndex();
            box2.setSelectedIndex(aa);
        }
        else if(ie.getSource()==box2){
            int aa=box2.getSelectedIndex();
            box1.setSelectedIndex(aa);
        }
    }



}