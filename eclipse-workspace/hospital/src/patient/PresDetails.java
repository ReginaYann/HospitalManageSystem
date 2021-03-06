/*
Prescription generation;
Parameter: sql for selection(can be empty);
Written by zjy;
*/
package patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

//一个表的模型
public class PresDetails extends AbstractTableModel {
    //rowData用来存放行数据，columnNames存放列名
    Vector rowData, columnNames;
    //连接数据库要用的东西
    Connection ct = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    PreparedStatement ps1 = null;
    ResultSet rs1 = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/hospital";
    String user = "root";
    String password = "A20001112a";


    public void init(int id, String root_) {

        columnNames = new Vector();
        //设置列名
        columnNames.add("药品编号");
        columnNames.add("药品名称");
        columnNames.add("药品单价");
        columnNames.add("药品数量");

        rowData = new Vector();//可存放多行

        try {
            //1、加载驱动(把下需要的驱动程序加入内存中)
            Class.forName(driver);
            //2、得到连接(指定连接到哪个数据源)
            ct = DriverManager.getConnection(url, user, password);
            //3、创建ps
            //默认-1，显示所有科室的医生
            ps = ct.prepareStatement("select * from pre_drug_ref where prescription_id=?");
            ps.setInt(1, id);

            //4、执行(如果是增加，删除，修改使用executeUpdate();查询executeQuery)
            rs = ps.executeQuery();

            while (rs.next()) {
                Vector col = new Vector();
                col.add(rs.getInt("drug_id"));
                ps1 = ct.prepareStatement("select * from drug where drug_id=?");
                ps1.setInt(1, rs.getInt("drug_id"));
                rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    col.add(rs1.getString(2));
                    col.add(rs1.getInt(3));
                }
                col.add(rs.getInt(4));
                rowData.add(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源（关闭顺序：谁后创建则先关闭)
            try {
                if (rs != null) rs1.close();
                if (ps != null) ps1.close();
                if (rs1 != null) rs1.close();
                if (ps1 != null) ps1.close();
                if (ct != null) ct.close();
            } catch (Exception e) {
            }
        }
    }


    //通过传递的sql语句来获得数据模型
    public PresDetails(int id, String root){
        this.init(id,"PATIENT");
    }


    @Override
    //得到共有多少行
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.rowData.size();
    }

    @Override
    //得到共有多少列
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String) this.columnNames.get(column);
    }
    @Override
    //得到某行某列的数据
    public Object getValueAt(int row, int column) {
        // TODO Auto-generated method stub
        return ((Vector)this.rowData.get(row)).get(column);
    }

}
