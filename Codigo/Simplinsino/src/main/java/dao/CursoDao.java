package dao;

import java.sql.*;
import java.util.*;

import model.Curso;

public class CursoDao extends DAO {
	public CursoDao() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Curso curso) {
		boolean status = false;
		try {
			String query = "INSERT INTO curso (id_disciplinafk, id_curso, curso_nome, curso_duracao, curso_descricao, curso_imagem) VALUES ('" +
					curso.getid_disciplinafk() + "', '" + curso.getId_curso() + "', '" + curso.getCurso_nome() + "', '" + curso.getCurso_duracao() + "', '" + curso.getCurso_descricao() + "', '" + curso.getCurso_imagem() + "');";
			PreparedStatement st = conexao.prepareStatement(query);
			st.executeUpdate();
			st.close();
			status = true;
		} catch(SQLException u) {
			throw new RuntimeException(u);
		}
		
		return status;
	}


	public boolean update(Curso curso) {
		boolean status = false;
		
		Curso novo_curso = getCurso(curso.getId_curso());
		delete(curso.getId_curso());
		insert(curso);
		status = true;
		
		
		return status;
	}
	
	public boolean delete(int id_curso) {
		boolean status = false;
		
		try {
			String query = "DELETE FROM curso WHERE id_curso =" + id_curso;
			Statement st = conexao.createStatement();
			st.executeUpdate(query);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}

	public List<Curso> get(){
		List<Curso> cursos = new ArrayList<Curso>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM curso";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Curso curso = new Curso(rs.getInt("id_disciplinafk"), rs.getInt("id_curso"), rs.getString("curso_nome"), rs.getString("curso_duracao"), rs.getString("curso_descricao"), rs.getString("curso_imagem"));
				cursos.add(curso);
			}
			
			st.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return cursos;
	}

	public Curso getCurso(int id_curso) {
		Curso curso = new Curso();
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM curso WHERE id_curso =" + id_curso);
			if(rs.next()) {
				curso.setid_disciplinafk(rs.getInt("id_disciplinafk"));
				curso.setId_curso(rs.getInt("id_curso"));
				curso.setCurso_nome(rs.getString("curso_nome"));
				curso.setCurso_duracao(rs.getString("curso_duracao"));
				curso.setCurso_descricao(rs.getString("curso_descricao"));
				curso.setCurso_imagem(rs.getString("curso_imagem"));
			}
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return curso;
	}
	
	public List<Curso> getCursoNome(String curso_nome) {
		List<Curso> cursos = new ArrayList<Curso>();
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM curso WHERE curso_nome LIKE " + curso_nome);
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
	
}