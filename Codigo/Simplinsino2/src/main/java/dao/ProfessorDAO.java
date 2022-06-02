package dao;

import java.sql.*;
import java.util.*;

import model.Professor;


public class ProfessorDAO extends DAO {
	public ProfessorDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Professor p) {
		boolean status = false;
		try {
			String query = "INSERT INTO professor (id_professor,usuario_professor,nome_professor,email_professor,senha_professor) VALUES"
					+ " (" +
					p.getId_professor() + ", '" + p.getUsuario_professor() + "', '" + p.getNome_professor() +  "', '" + p.getEmail_professor()  + "', '" + p.getSenha_professor()+"' );";
			PreparedStatement st = conexao.prepareStatement(query);
			st.executeUpdate();
			st.close();
			status = true;
		} catch(SQLException u) {
			throw new RuntimeException(u);
		}
		
		return status;
	}
	


	public boolean update(Professor p) {
		boolean status = false;
		Professor professor = getProfessor(p.getId_professor());
		delete(p.getId_professor());
		insert(p);
		status = true;
		return status;
	}
	
	public boolean delete(int id) {
		boolean status = false;
		
		try {
			String query = "DELETE FROM professor WHERE id_professor=" + id;
			Statement st = conexao.createStatement();
			st.executeUpdate(query);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}

	public List<Professor> get(){
		List<Professor> professors = new ArrayList<Professor>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM professor";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Professor professor = new Professor(rs.getInt("id_professor"),rs.getString("usuario_professor"),rs.getString("nome_professor"),rs.getString("email_professor"),rs.getString("senha_professor"));
				professors.add(professor);
			}
			
			st.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return professors;
	}

	public Professor getProfessor(int id) {
		Professor professor = new Professor();
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM professor WHERE id_professor=" + id);
			if(rs.next()) {
				professor.setId_professor(rs.getInt("id_professor"));
				professor.setUsuario_professor(rs.getString("usuario_professor"));
				professor.setNome_professor(rs.getString("nome_professor"));
				professor.setEmail_professor(rs.getString("email_professor"));
				
				professor.setSenha_professor(rs.getString("senha_professor"));
			}
			
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return professor;
	}
	

}
