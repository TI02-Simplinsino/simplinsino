package dao;

import java.sql.*;
import java.util.*;

import model.Tarefa;


public class TarefaDAO extends DAO {
	public TarefaDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Tarefa tarefa) {
		boolean status = false;
		try {
			String query = "INSERT INTO tarefa (id_tarefa, tarefa_nome, tarefa_prioridade, id_alunofk) VALUES (" +
					tarefa.getId_tarefa() + ", '" + tarefa.getTarefa_nome() + "', '" + tarefa.getTarefa_prioridade() + "', '" + tarefa.getId_alunofk() + "');";
			PreparedStatement st = conexao.prepareStatement(query);
			st.executeUpdate();
			st.close();
			status = true;
		} catch(SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean update(Tarefa t) {
		boolean status = false;
		
		Tarefa tarefa = getTarefa(t.getId_tarefa());
		delete(t.getId_tarefa());
		insert(t);
		status = true;
	
		return status;
	}
	
	public boolean delete(int id_tarefa) {
		boolean status = false;
		
		try {
			String query = "DELETE FROM tarefa WHERE id_tarefa=" + id_tarefa;
			Statement st = conexao.createStatement();
			st.executeUpdate(query);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}

	public List<Tarefa> get(){
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM tarefa";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Tarefa tarefa = new Tarefa(rs.getInt("id_tarefa"),rs.getString("tarefa_nome"),rs.getInt("tarefa_prioridade"), rs.getInt("id_alunofk"));
				tarefas.add(tarefa);
			}
			
			st.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return tarefas;
	}

	public Tarefa getTarefa(int id_tarefa) {
		Tarefa tarefa= new Tarefa();
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM tarefa WHERE id_tarefa=" + id_tarefa);
			if(rs.next()) {
				tarefa.setId_tarefa(rs.getInt("id_tarefa"));
				tarefa.setTarefa_nome(rs.getString("tarefa_nome"));
				tarefa.setTarefa_prioridade(rs.getInt("tarefa_prioridade"));
				tarefa.setId_alunofk(rs.getInt("id_alunofk"));
			}
			
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return tarefa;
	}
	
	public List<Tarefa> getTarefaNome(String tarefa_nome) {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM tarefa WHERE tarefa_nome LIKE " + tarefa_nome);
			while(rs.next()) {
				Tarefa tarefa = new Tarefa(rs.getInt("id_tarefa"),rs.getString("tarefa_nome"),rs.getInt("tarefa_prioridade"), rs.getInt("id_alunofk"));
				tarefas.add(tarefa);
			}
			
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return tarefas;
	}
	
}