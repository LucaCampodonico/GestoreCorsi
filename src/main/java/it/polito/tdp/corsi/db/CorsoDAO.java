package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {

	public List<Corso> corsisemestre(int s)
	{
		String sql="SELECT * "
				+ "FROM corso "
				+ "where pd= ? ";
		List<Corso> ris=new ArrayList<>();
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, s);
			ResultSet rs= st.executeQuery();
			
			while(rs.next())
			{
				Corso c= new Corso(rs.getString("codins"), rs.getInt("crediti"),
						rs.getString("nome"), rs.getInt("pd"));
				ris.add(c);
			}
			conn.close();
			
		}catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		
		return ris;
	}
	
	public Map<Corso,Integer> iscrizionicorsi(int s)
	{
		String sql="SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) as tot "
				+ "FROM corso c,iscrizione i "
				+ "where c.codins = i.codins and c.pd=? "
				+ "group by  c.codins, c.nome, c.crediti, c.pd";
		Map<Corso,Integer> ris=new HashMap<Corso,Integer>();
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, s);
			ResultSet rs= st.executeQuery();
			
			while(rs.next())
			{
				Corso c= new Corso(rs.getString("codins"), rs.getInt("crediti"),
						rs.getString("nome"), rs.getInt("pd"));
				int n= rs.getInt("tot");
				ris.put(c,n);
			}
			conn.close();
			
		}catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		
		return ris;
	}
}
