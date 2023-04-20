package dao;

import java.sql.*;
import java.util.*;

import model.Disciplina;

public class DisciplinaDao extends DAO {
	public DisciplinaDao() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}

	public List<Disciplina> get(){
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM disciplina";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Disciplina disciplina = new Disciplina(rs.getInt("id_disciplina"), rs.getString("disciplina_nome"), rs.getString("disciplina_img"));
				disciplinas.add(disciplina);
			}
			
			st.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return disciplinas;
	}

	public Disciplina getDisciplina(int id_disciplina) {
		Disciplina disciplina = new Disciplina();
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM disciplina WHERE id_disciplina =" + id_disciplina);
			if(rs.next()) {
				disciplina.setId_disciplina(rs.getInt("id_disciplina"));
				disciplina.setDisciplina_nome(rs.getString("disciplina_nome"));
				disciplina.setDisciplina_img(rs.getString("disciplina_img"));
			}
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return disciplina;
	}
}