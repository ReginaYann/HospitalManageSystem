/*
Prescription generation;
Parameter: sql for selection(can be empty);
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
import patient.PresDetails;
import patient.RecordDetails;
import patient.RecordTable;

public class ShowPresDetails extends JFrame {
    JPanel jp1;
    JLabel jl;
    JTextField jtf;
    JTable jt;
    JScrollPane jsp;

    public ShowPresDetails(int id, String root_) {
        jp1 = new JPanel();
        jl=new JLabel("处方编号");
        jtf=new JTextField(3);
        jtf.setText(Integer.toString(id));
        jtf.setEditable(false);

        //查询窗口;
        jp1.add(jl);
        jp1.add(jtf);

        //展示所有预约信息
        PresDetails table_init = new PresDetails(id,root_);
        jt = new JTable(table_init);
        jsp = new JScrollPane(jt);

        this.add(jsp);
        this.add(jp1, "North");

        this.setSize(700, 500);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("处方详情");
        this.setVisible(true);
    }

    public static void main(String[] args){
        new ShowPresDetails(0,"p");
    }
}
