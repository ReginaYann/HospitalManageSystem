/*
Appointment history query;
Parameter: pid(String) or none (none can be used by admin)
Written by zjy;
*/
package patient;

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

public class P_checka extends JFrame implements ActionListener{
    JPanel jp1, jp2;
    JLabel jl1;
    JButton jb1;
    JTable jt;
    JScrollPane jsp;
    JTextField jtf;
    String pid;   //哪个病人要看预约记录
    String sql=null;
    public static void main(String[] args){
        new P_checka("2");
    }
    public static int row_number;


    public P_checka(String pid_) {
        jp1=new JPanel();
        jb1=new JButton("查看对应病历");
        jb1.addActionListener(this);

        //查询窗口;
        jp1.add(jb1);

        //展示所有病历信息
        AppointTable table_init = new AppointTable(pid_);
        jt = new JTable(table_init);
        jsp = new JScrollPane(jt);

        this.add(jsp);
        this.add(jp1,"South");

        this.setSize(700,500);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("挂号记录");
        this.setVisible(true);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb1) {
            int row_number=this.jt.getSelectedRow();
            int recID=(int)this.jt.getValueAt(row_number, 0);
            new RecordDetails(recID,"PATIENT");
        }
    }

}