package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private DataSource datasource;
	
	public ConnectionFactory() {
		
		var poolDataSource = new ComboPooledDataSource();
		poolDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");
		poolDataSource.setUser("root");
		poolDataSource.setPassword("1234");
		poolDataSource.setMaxPoolSize(10);
		
		this.datasource = poolDataSource;
	}
	
	public Connection recuperaConexion() throws SQLException {
		return this.datasource.getConnection();
	}
}
