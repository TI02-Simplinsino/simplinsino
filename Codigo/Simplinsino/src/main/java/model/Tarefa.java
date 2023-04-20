package model;

public class Tarefa {
	private String tarefa_nome;
	private int id_tarefa;
	private int tarefa_prioridade;
	private int id_alunofk;
	
	public Tarefa() {
		this(-1, "", -1, -1);
	}
	
	public Tarefa(int id_tarefa, String tarefa_nome, int tarefa_prioridade, int id_alunofk) {
		super();
		this.tarefa_nome = tarefa_nome;
		this.id_tarefa = id_tarefa;
		this.tarefa_prioridade = tarefa_prioridade;
		this.id_alunofk = id_alunofk;
	}
	
	public String getTarefa_nome() {
		return tarefa_nome;
	}
	public void setTarefa_nome(String tarefa_nome) {
		this.tarefa_nome = tarefa_nome;
	}

	public int getId_tarefa() {
		return id_tarefa;
	}
	public void setId_tarefa(int id_tarefa) {
		this.id_tarefa = id_tarefa;
	}
	
	public int getTarefa_prioridade() {
		return tarefa_prioridade;
	}
	public void setTarefa_prioridade(int tarefa_prioridade) {
		this.tarefa_prioridade = tarefa_prioridade;
	}
	
	public int getId_alunofk() {
		return id_alunofk;
	}
	public void setId_alunofk(int id_alunofk) {
		this.id_alunofk = id_alunofk;
	}
	
	
	@Override
	public String toString() {
		return "Nome da Tarefa: " + this.getTarefa_nome() + " Prioridade da Tarefa: " + this.getTarefa_prioridade() + " Id da Tarefa: " + this.getId_tarefa() + " Id do aluno: " + this.getId_alunofk(); 
	}
}
