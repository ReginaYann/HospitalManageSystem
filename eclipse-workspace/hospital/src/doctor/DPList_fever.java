package doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DPList_fever extends JFrame{

    //连接数据库要用的东西
    PreparedStatement ps= null;
    ResultSet rs=null;
    String URL = "jdbc:mysql://localhost/hospital?";
    String user="root";
    String password="123";

    //查看历史病人，即已经处理了的预约信息，展示预约列表，包括信息：预约编号，患者姓名，预约日期，是否确诊新冠
    JTable jt ;
    JScrollPane jsp ;

    //存放 数据 以及 列名
    String[] columnNames = new String[]{"预约编号", "患者姓名", "预约日期", "是否确诊新冠"};
    Object[][] rowData;

    public DPList_fever(int doctor_id){
        try(Connection ct= DriverManager.getConnection(URL,user,password)) {
            try {
                //3、创建ps
                ps = ct.prepareStatement("select count(1) from appointment where doctor_id =? and status = ?");
                ps.setInt(1, doctor_id);
                ps.setInt(2, 1);
                rs = ps.executeQuery();
                rs.next();
                int cnt = rs.getInt(1);     //确定记录条数

                rowData = new Object[cnt][4];

                ps = ct.prepareStatement("select t1.appointment_id,t2.patient_name,t1.date,t3.is_diagnosed " +
                        "from appointment as t1 " +
                        "left join patient as t2 on t1.patient_id = t2.patient_id " +
                        "left join fever_patient as t3 on t1.patient_id = t3.patient_id " +
                        "where t1.doctor_id = ? and t1.status = ?");
                ps.setInt(1, doctor_id);
                ps.setInt(2, 1);
                rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    rowData[i][0] = rs.getInt(1);
                    rowData[i][1] = rs.getString(2);
                    rowData[i][2] = rs.getString(3);
                    int f = rs.getInt(4);
                    if(f == 1){
                        rowData[i][3] = "是";
                    }else{
                        rowData[i][3] = "否";
                    }
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //关闭资源（关闭顺序：谁后创建则先关闭)
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (ct != null) ct.close();
                } catch (Exception e1) {
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        DefaultTableModel defaultModel = new DefaultTableModel(rowData, columnNames);
        jt = new JTable(defaultModel);
        jt.setPreferredScrollableViewportSize(new Dimension(150,80));  //JTable的高度和宽度设定
        jt.setFillsViewportHeight(true);

        jsp = new JScrollPane();
        jsp.setViewportView(jt);

        this.setLayout(new BorderLayout());
        this.add(jsp,BorderLayout.CENTER);
        this.setTitle("历史发热病人");
        this.setSize(500,300);
        //设置可见
        this.setVisible(true);
        this.setLocationRelativeTo(null);// 居中
    }
}
