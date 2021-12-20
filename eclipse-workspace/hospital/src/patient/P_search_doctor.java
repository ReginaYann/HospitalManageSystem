/*
Doctor search for patient;
Written by zjy;
 */

package patient;
import patient.DeptTable;

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

import patient.DoctorTable;

public class P_search_doctor extends JFrame implements ActionListener{
    JPanel jp1, jp2;
    JLabel jl1;
    JButton jb1, jb2;
    JTable jt;
    JScrollPane jsp;
    JTextField jtf;
    public static void main(String[] args){
        new P_search_doctor();
    }
    public static int row_number;

    public P_search_doctor(){
        jp1=new JPanel();

        jl1=new JLabel("请输入医生名");
        jtf=new JTextField(10);
        jb1=new JButton("查询");
        jb1.addActionListener(this);

        //查询窗口
        jp1.add(jl1);
        jp1.add(jtf);
        jp1.add(jb1);

        //展示所有科室信息
        DoctorTable table_init = new DoctorTable();
        jt = new JTable(table_init);
        jsp = new JScrollPane(jt);

        this.add(jsp);
        this.add(jp1,"North");

        this.setSize(600,400);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("医生查询");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb1) {
            String query = this.jtf.getText().trim();
            String sql = "select * from doctor where doctor_name = '"+query+" order by doctor_id";
            DoctorTable new_table;
            new_table = new DoctorTable(sql);
            jt.setModel(new_table);
        }
    }

}