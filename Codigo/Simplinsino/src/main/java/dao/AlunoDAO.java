package dao;

import java.sql.*;
import java.util.*;

import model.Aluno;
import model.Tarefa;


public class AlunoDAO extends DAO {
	public AlunoDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Aluno p) {
		boolean status = false;
		try {
			String query = "INSERT INTO aluno (id_aluno,usuario_aluno,nome_aluno,email_aluno,pagamento_aluno,senha_aluno) VALUES"
					+ " (" +
					p.getId_aluno() + ", '" + p.getUsuario_aluno() + "', '" + p.getNome_aluno() +  "', '" + p.getEmail_aluno() + "', '" + p.getPagamento_aluno() + "', '" + p.getSenha_aluno()+"' );";
			PreparedStatement st = conexao.prepareStatement(query);
			st.executeUpdate();
			st.close();
			status = true;
		} catch(SQLException u) {
			throw new RuntimeException(u);
		}
		
		return status;
	}
	


	public boolean update(Aluno p) {
		boolean status = false;
		Aluno aluno = getAluno(p.getId_aluno());
		delete(p.getId_aluno());
		insert(p);
		status = true;
		return status;
	}
	
	public boolean delete(int id) {
		boolean status = false;
		
		try {
			String query = "DELETE FROM aluno WHERE id_aluno=" + id;
			Statement st = conexao.createStatement();
			st.executeUpdate(query);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}

	public List<Aluno> get(){
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM aluno";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Aluno aluno = new Aluno(rs.getInt("id_aluno"),rs.getString("usuario_aluno"),rs.getString("nome_aluno"),rs.getString("email_aluno"),rs.getString("pagamento_aluno"),rs.getString("senha_aluno"));
				alunos.add(aluno);
			}
			
			st.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return alunos;
	}

	public Aluno getAluno(int id) {
		Aluno aluno = new Aluno();
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM aluno WHERE id_aluno=" + id);
			if(rs.next()) {
				aluno.setId_aluno(rs.getInt("id_aluno"));
				aluno.setUsuario_aluno(rs.getString("usuario_aluno"));
				aluno.setNome_aluno(rs.getString("nome_aluno"));
				aluno.setEmail_aluno(rs.getString("email_aluno"));
				aluno.setPagamento_aluno(rs.getString("pagamento_aluno"));
				aluno.setSenha_aluno(rs.getString("senha_aluno"));
			}
			
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return aluno;
	}
	

}
