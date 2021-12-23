/*
Medical history query;
Parameter: pid(String) or none (none can be used by admin)
Written by zjy;
*/
package patient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import patient.AppointTable;

public class P_history extends JFrame implements ActionListener{
    JPanel jp1, jp2;
    JLabel jl1;
    JButton jb1,jb2;
    JTable jt;
    JScrollPane jsp;
    JTextField jtf;
    int pid;   //哪个病人要看病历记录
    String sql=null;
    public static void main(String[] args){
        new P_history("2");
    }
    public static int row_number;


    //连接数据库要用的东西
    Connection ct=null;
    PreparedStatement ps= null;
    ResultSet rs=null;
    PreparedStatement ps1= null;
    ResultSet rs1=null;
    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/hospital";
    String user="root";
    String password="A20001112a";

    public P_history(String pid_) {
        jp1=new JPanel();
        jb1=new JButton("查看对应病历");
        jb1.addActionListener(this);
        jb2=new JButton("查看对应处方");
        jb2.addActionListener(this);

        //查询窗口;
        jp1.add(jb1);
        jp1.add(jb2);
        //展示所有预约信息
        RecordTable table_init = new RecordTable(pid_);
        jt = new JTable(table_init);
        jsp = new JScrollPane(jt);

        this.add(jsp);
        this.add(jp1,"South");

        this.setSize(700,500);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("病历记录");
        this.setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb1) {
            //查看对应病历
            int row_number=this.jt.getSelectedRow();
            if(row_number==-1){
                JOptionPane.showMessageDialog((Component)null,"请选择病历记录","提示信息",JOptionPane.ERROR_MESSAGE);
            }
            else {
                int recID = (int) this.jt.getValueAt(row_number, 0);
                new RecordDetails(recID, "PATIENT");
            }
        }
        if(e.getSource()==jb2) {
            //查看对应处方
            int row_number=this.jt.getSelectedRow();
            if(row_number==-1){
                JOptionPane.showMessageDialog((Component)null,"请选择病历记录","提示信息",JOptionPane.ERROR_MESSAGE);
            }
            else {
                //病历id
                int recID = (int) this.jt.getValueAt(row_number, 0);
                int presID = 0;
                try{
                    //1、加载驱动(把下需要的驱动程序加入内存中)
                    Class.forName(driver);
                    //2、得到连接(指定连接到哪个数据源)
                    ct = DriverManager.getConnection(url,user,password);
                    //3、创建ps
                    //默认-1，显示所有科室的医生
                    ps=ct.prepareStatement("select * from prescription where record_id=?");
                    ps.setInt(1,recID);
                    //4、执行(如果是增加，删除，修改使用executeUpdate();查询executeQuery)
                    rs=ps.executeQuery();

                    while(rs.next()){
                        presID=rs.getInt("prescription_id");
                    }
                }catch(Exception ee){
                    ee.printStackTrace();
                }finally{
                    //关闭资源（关闭顺序：谁后创建则先关闭)
                    try{
                        if(rs!=null) rs1.close();
                        if(ps!=null) ps1.close();
                        if(ct!=null) ct.close();
                    }catch(Exception ee){ }
                }
                new ShowPresDetails(presID, "PATIENT");  //显示病历对应的处方
            }
        }
    }

}

