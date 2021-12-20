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
    JButton jb1;
    JTable jt;
    JScrollPane jsp;
    JTextField jtf;
    int pid;   //哪个病人要看病历记录
    String sql=null;
    public static void main(String[] args){
        new P_history("2");
    }
    public static int row_number;

    public void init(String sql) {
        jp1=new JPanel();
        jb1=new JButton("查看对应病历");
        jb1.addActionListener(this);

        //查询窗口;
        jp1.add(jb1);
        //展示所有预约信息
        RecordTable table_init = new RecordTable(sql);
        jt = new JTable(table_init);
        jsp = new JScrollPane(jt);

        this.add(jsp);
        this.add(jp1,"South");

        this.setSize(700,500);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("病历记录");
        this.setVisible(true);
    }


    public P_history(String pid_){
            pid=Integer.parseInt(pid_);
            sql="select * from medical_record where patient_id=pid";
            this.init(sql);
    }

    public P_history(){
            sql="select * from medical_record";
            this.init(sql);
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
    }

}

