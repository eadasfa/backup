package spittr.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import spittr.entity.Spitter;

@Component
public class SqlHelper {
	
	private static final String SQL_INSERT_SPITER = "INSERT INTO user values"
			+ "(?,?)";
	
	@Autowired
	@Qualifier("qaData")
	private DataSource dataSource;
	
	//基于jdbc的添加方式
	public void add(Spitter spitter){
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(SQL_INSERT_SPITER);
			stmt.setString(1, spitter.getUserName());
			stmt.setString(2, spitter.getPassword());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt!=null){
					stmt.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	//基于jdbcTemplate 的方法
	public void addSpitter(Spitter spitter){
		
	}
}






















