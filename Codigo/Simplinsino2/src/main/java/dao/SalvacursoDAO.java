package dao;

import java.sql.*;
import java.util.*;

import model.Curso;
import model.Salvacurso;
import model.Tarefa;


public class SalvacursoDAO extends DAO {
	public SalvacursoDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Salvacurso p) {
		boolean status = false;
		try {
			String query = "INSERT INTO curso_aluno (id_alunofk,id_cursofk) VALUES"
					+ " (" +
					p.getId_alunofk() + ", '"  + p.getId_cursofk()+"' );";
			PreparedStatement st = conexao.prepareStatement(query);
			st.executeUpdate();
			st.close();
			status = true;
		} catch(SQLException u) {
			throw new RuntimeException(u);
		}
		
		return status;
	}
	



	
	public boolean delete(int id,int id_curso) {
		boolean status = false;
		
		try {
			String query = "DELETE FROM curso_aluno WHERE id_alunofk=" + id + "AND id_cursofk = " + id_curso;
			Statement st = conexao.createStatement();
			st.executeUpdate(query);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}

	public List<Salvacurso> get(){
		List<Salvacurso> salvacursos = new ArrayList<Salvacurso>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM curso_aluno";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Salvacurso salvacurso = new Salvacurso(rs.getInt("id_alunofk"),rs.getInt("id_cursofk"));
				salvacursos.add(salvacurso);
			}
			
			st.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return salvacursos;
	}
	public List<Curso> getCursoSalvo(int id) {
		List<Curso> cursos = new ArrayList<Curso>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM curso_aluno inner join curso on curso_aluno.id_cursofk = curso.id_curso where curso_aluno.id_alunofk = " + id);
			while(rs.next()) {
				Curso curso = new Curso(rs.getInt("id_disciplinafk"), rs.getInt("id_curso"), rs.getString("curso_nome"), rs.getString("curso_duracao"), rs.getString("curso_descricao"), rs.getString("curso_imagem"));
				cursos.add(curso);
			}
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return cursos;
	}
	public Salvacurso getSalvacurso(int id) {
		Salvacurso salvacurso = new Salvacurso();
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM curso_aluno WHERE id_alunofk=" + id);
			if(rs.next()) {
				salvacurso.setId_alunofk(rs.getInt("id_alunofk"));
				salvacurso.setId_cursofk(rs.getInt("id_cursofk"));
			}
			
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return salvacurso;
	}
	

}
