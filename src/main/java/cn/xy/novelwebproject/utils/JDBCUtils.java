package cn.xy.novelwebproject.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtils {
		private final static String url = "jdbc:mysql://localhost:3306/novelweb?characterEncoding=UTF-8&amp;useUnicode=true&amp;serverTimezone=UTC&amp;useSSL=false";
		private final static String driver = "com.mysql.jdbc.Driver";
		private final static String username = "root";
		private final static String password="123456";
		private static Connection conn;
		private static PreparedStatement pst;
		
		public static  Connection getConn() throws Exception {
				// 加载数据库驱动
				Class.forName("com.mysql.jdbc.Driver");
				// 通过驱动管理类获取数据库链接
				conn = DriverManager.getConnection(url, username, password);
				return conn;
		}
		public static void closeConnect(){
				try {
						pst.close();
						conn.close();
				} catch (SQLException throwables) {
						throwables.printStackTrace();
				}
		}
		public static  boolean InsertCatlog(String text,String catlogName,String novleName){
				boolean flag=false;
				String sql = "insert into catlog_content(novelname,catlogname,content) values (?,?,?)";
				try {
						conn = getConn();
						pst = conn.prepareStatement(sql);
						pst.setString(1,novleName);
						pst.setString(2,catlogName);
						pst.setString(3,text);
						pst.execute();
						flag =true;
				}catch (Exception e){
						e.printStackTrace();
				}finally {
						closeConnect();
				}
				return flag;
		}

}
