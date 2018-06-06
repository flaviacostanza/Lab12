package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}

	/**
	 * restituisce tutti i flussi del fiume ordinati per data crescente (il primo è il più antico)
	 * @param river
	 * @return
	 */
	public List<Flow> getAllFlowsByRiver(River river) {
		
		final String sql = "SELECT id, day, flow FROM flow WHERE river= ? ORDER BY day ASC"; //ho prima la data più vecchia

		List<Flow> results = new LinkedList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();
			
			

			while (res.next()) {
				results.add(new Flow(res.getDate("day").toLocalDate(), res.getFloat("flow")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return results;
	}
}
