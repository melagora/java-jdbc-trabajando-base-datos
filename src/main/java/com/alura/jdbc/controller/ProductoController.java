package com.alura.jdbc.controller;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import com.alura.jdbc.dao.ProductoDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

import java.sql.Statement;
import java.sql.Struct;

public class ProductoController {

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();
		try (con) {

			final PreparedStatement statement = con.prepareStatement(
					"UPDATE PRODUCTO SET " + " NOMBRE = ?" + ", DESCRIPCION = ?" + ", CANTIDAD = ?" + " WHERE ID = ?");
			try (statement) {

				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, cantidad);
				statement.setInt(4, id);

				statement.execute();

				int updateCount = statement.getUpdateCount();

				con.close();

				return updateCount;

			}
		}
	}

	public int eliminar(Integer id) throws SQLException {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con) {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
			try (statement) {

				statement.setInt(1, id);

				statement.execute();

				return statement.getUpdateCount(); // Devuelve cuantas filas fueron modificada luego de que se aplico el
													// comando
													// de SQL en el statement
			}
		}
	}

	public List<Map<String, String>> listar() throws SQLException {

		final Connection con = new ConnectionFactory().recuperaConexion();

		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
			statement.execute();
			try (statement) {
				ResultSet resultSet = statement.getResultSet();

				List<Map<String, String>> resultado = new ArrayList<>();

				while (resultSet.next()) {
					Map<String, String> fila = new HashMap<>();
					fila.put("ID", String.valueOf(resultSet.getInt("ID")));
					fila.put("NOMBRE", resultSet.getString("NOMBRE"));
					fila.put("DESCRIPCION", resultSet.getString("DESCRIPCION"));
					fila.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));

					resultado.add(fila);
				}
				return resultado;
			}
		}
	}

	public void guardar(Producto producto) throws SQLException {
		ProductoDAO productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
		productoDAO.guardar(producto);
	}

}
