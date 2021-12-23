/*
Department search for patient;
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

public class P_search_dept extends JFrame implements ActionListener{
    JPanel jp1, jp2;
    JLabel jl1;
    JButton jb1, jb2;
    JTable jt;
    JScrollPane jsp;
    JTextField jtf;
    public static void main(String[] args){
        new P_search_dept();
    }
    public static int row_number;

    public P_search_dept(){
        jp1=new JPanel();

        jl1=new JLabel("请输入科室名");
        jtf=new JTextField(10);
        jb1=new JButton("查询");
        jb1.addActionListener(this);
        jb2=new JButton("显示医生列表");

        //查询窗口
        jp1.add(jl1);
        jp1.add(jtf);
        jp1.add(jb1);
        jp1.add(jb2);

        //展示所有科室信息
        DeptTable table_init = new DeptTable();
        jt = new JTable(table_init);
        jsp = new JScrollPane(jt);

        this.add(jsp);
        this.add(jp1,"North");

        this.setSize(600,400);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("科室查询");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb1) {
            String query = this.jtf.getText().trim();
            DeptTable new_table;
            new_table = new DeptTable(query);
            jt.setModel(new_table);
        }
        else if(e.getSource()==jb2){
            row_number=this.jt.getSelectedRow();
            int deptID;
            if(row_number==-1){
                //提示
                JOptionPane.showMessageDialog(this, "请选择科室");
                return ;//谁调用就返回到哪
            }
            else {
                deptID=(int)this.jt.getValueAt(row_number, 0);
            }
            DoctorTable new_table;
            new_table = new DoctorTable(deptID);
            jt.setModel(new_table);

        }
    }

}
