package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;

import java.sql.Statement;

public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public int eliminar(Integer id) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
		
		Statement statement = con.createStatement();
		
		statement.execute("DELETE FROM PRODUCTO WHERE ID ="+id);
		
		return statement.getUpdateCount(); //Devuelve cuantas filas fueron modificada luego de que se aplico el comando de SQL en el statement
		
	}

	public List<Map<String,String>> listar() throws SQLException{
		Connection con = new ConnectionFactory().recuperaConexion();
		
		Statement statement = con.createStatement();
		
		statement.execute("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
		
		ResultSet resultSet = statement.getResultSet();

		List<Map<String,String>> resultado = new ArrayList<>();
		
		while(resultSet.next()) {
			Map<String,String> fila = new HashMap<>();
			fila.put("ID", String.valueOf(resultSet.getInt("ID")));
			fila.put("NOMBRE", resultSet.getString("NOMBRE"));
			fila.put("DESCRIPCION", resultSet.getString("DESCRIPCION"));
			fila.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));
			
			resultado.add(fila);
		}

		con.close();
		
		return resultado;
	}

    public void guardar(Map<String, String> producto) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
		Statement statement = con.createStatement();
		statement.execute("INSERT INTO PRODUCTO (nombre, descripcion, cantidad)"
				+"VALUES('" + producto.get("NOMBRE")+"','"
				+producto.get("DESCRIPCION")+"',"
				+producto.get("CANTIDAD")+")", Statement.RETURN_GENERATED_KEYS); //Nos devuelve la clave (ID) generada en la tabla
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		while (resultSet.next()) {
			System.out.println(String.format("Fue insertado el producto de ID %d",
					resultSet.getInt(1)));
		}
	}

}
