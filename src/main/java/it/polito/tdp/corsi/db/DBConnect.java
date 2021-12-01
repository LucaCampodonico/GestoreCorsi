package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public static Connection getConnection()
	{
		String url="jdbc:mysql://localhost/iscritticorsi?user=root&password=MySQL2021!";
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.print("errore nella connessione al database");
			e.printStackTrace();
			return null;
		}
	}
}
