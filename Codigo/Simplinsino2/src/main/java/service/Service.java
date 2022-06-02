package service;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.*;
import dao.AlunoDAO;
import dao.ProfessorDAO;
import dao.TarefaDAO;
import model.Aluno;
import model.Professor;
import model.Tarefa;;


@SuppressWarnings("unchecked")

public class Service {
	//Service Fernanda
	public static String getTarefa() {
		TarefaDAO tarefaDAO = new TarefaDAO();
		tarefaDAO.conectar();
		
		List<Tarefa> tarefas = tarefaDAO.get();
		JSONArray tarefasJsonList = new JSONArray();
		
		for(int i = 0; i < tarefas.size(); i++) {
			Tarefa t = tarefas.get(i);
			JSONObject tarefaJsonObj = new JSONObject();
			tarefaJsonObj.put("id_tarefa",t.getId_tarefa());
			tarefaJsonObj.put("tarefa_nome",t.getTarefa_nome());
			tarefaJsonObj.put("tarefa_prioridade",t.getTarefa_prioridade());
			tarefaJsonObj.put("id_alunofk",t.getId_alunofk());
			
			tarefasJsonList.add(tarefaJsonObj);
		}
		
		tarefaDAO.close();
		
		return tarefasJsonList.toJSONString();
	}
	public static String searchTarefa(Request request) {
	    TarefaDAO tarefaDAO = new TarefaDAO();
	    tarefaDAO.conectar();

	    int idSearch = Integer.parseInt(request.params(":id_tarefa"));
	    Tarefa tarefa = tarefaDAO.getTarefa(idSearch);

	    JSONArray tarefaLista = new JSONArray();
	    if(tarefa.getId_tarefa() != -1) {
	        JSONObject tarefaObj = new JSONObject();
	        tarefaObj.put("id_tarefa",tarefa.getId_tarefa());
	        tarefaObj.put("tarefa_nome",tarefa.getTarefa_nome());
	        tarefaObj.put("tarefa_prioridade",tarefa.getTarefa_prioridade());
	        tarefaObj.put("id_alunofk",tarefa.getId_alunofk());

	        tarefaLista.add(tarefaObj);
	    }

	    tarefaDAO.close();

	    return tarefaLista.toJSONString();
	}
	public static boolean deleteTarefa(Request request) {
		TarefaDAO tarefaDAO = new TarefaDAO();
		tarefaDAO.conectar();
		
		int id = Integer.parseInt(request.params(":id_tarefa"));
		
		boolean resp = tarefaDAO.delete(id);
		tarefaDAO.close();
		
		return resp;
	}
	public static int getSizeTarefa() {
		TarefaDAO TarefaDAO = new TarefaDAO();
		TarefaDAO.conectar();
		List<Tarefa> tarefas = TarefaDAO.get();
		return tarefas.size();
	}
	public static boolean insertTarefa(Request request) {

		TarefaDAO tarefaDAO = new TarefaDAO();
		tarefaDAO.conectar();
		
		int id_tarefa = Integer.parseInt(request.params(":id_tarefa"));
		String tarefa_nome = request.params(":tarefa_nome");
		int tarefa_prioridade = Integer.parseInt(request.params(":tarefa_prioridade"));
		int id_alunofk = Integer.parseInt(request.params(":id_alunofk"));
		
		boolean resp = tarefaDAO.insert(new Tarefa(id_tarefa,tarefa_nome, tarefa_prioridade, id_alunofk));
		
		tarefaDAO.close();
		
		return resp;
	}
	public static boolean updateTarefa(Request request) {
		TarefaDAO tarefaDAO = new TarefaDAO();
		tarefaDAO.conectar();
		
		int id_tarefa = Integer.parseInt(request.params(":id_tarefa"));
		String tarefa_nome = request.params(":tarefa_nome");
		int tarefa_prioridade = Integer.parseInt(request.params(":tarefa_prioridade"));
		int id_alunofk = Integer.parseInt(request.params(":id_alunofk"));
		
		boolean resp = tarefaDAO.update(new Tarefa(id_tarefa,tarefa_nome, tarefa_prioridade, id_alunofk));
		
		tarefaDAO.close();
		
		return resp;
	}

    public static int getUltimoIDTarefa() {
	TarefaDAO TarefaDAO = new TarefaDAO();
	TarefaDAO.conectar();
	List<Tarefa> tarefas = TarefaDAO.get();
	int ultimaId = 0;
	for(int i = 0; i < tarefas.size(); i++) {
		if (ultimaId < tarefas.get(i).getId_tarefa()) {
			ultimaId = tarefas.get(i).getId_tarefa();
		}
	}
	return ultimaId;
}

	//Aluno
	public static String getAluno() {
	AlunoDAO AlunoDAO = new AlunoDAO();
	AlunoDAO.conectar();
	
	List<Aluno> alunos = AlunoDAO.get();
	JSONArray alunosJsonList = new JSONArray();
	
	for(int i = 0; i < alunos.size(); i++) {
		Aluno p = alunos.get(i);
		
		JSONObject alunoJsonObj = new JSONObject();
		alunoJsonObj.put("id_aluno",p.getId_aluno());
		alunoJsonObj.put("usuario_aluno",p.getNome_aluno());
		alunoJsonObj.put("nome_aluno",p.getNome_aluno());
		alunoJsonObj.put("email_aluno",p.getEmail_aluno());
		alunoJsonObj.put("senha_aluno",p.getSenha_aluno() + "");
		
		alunosJsonList.add(alunoJsonObj);
		
	}
	
	AlunoDAO.close();
	
	return alunosJsonList.toJSONString();
}

    public static int getLogin(Request request) {
	AlunoDAO AlunoDAO = new AlunoDAO();
	AlunoDAO.conectar();
	int certo =0;

	String usuario = request.params(":usuario_aluno");
	String senha = request.params(":senha_aluno");
	List<Aluno> alunos = AlunoDAO.get();
	for(int i = 0; i < alunos.size(); i++) {
		if(alunos.get(i).getUsuario_aluno().trim().equals(usuario) == true && alunos.get(i).getSenha_aluno().trim().equals(senha) == true) {
			certo =alunos.get(i).getId_aluno();
		}
	}
	
	return certo;
}
    public static int getSizeAluno() {
	AlunoDAO AlunoDAO = new AlunoDAO();
	AlunoDAO.conectar();
	List<Aluno> alunos = AlunoDAO.get();
	return alunos.size();
}


    public static String searchAluno(Request request) {
    AlunoDAO alunoDAO = new AlunoDAO();
    alunoDAO.conectar();

    int idSearch = Integer.parseInt(request.params(":id_aluno"));
    Aluno aluno = alunoDAO.getAluno(idSearch);

    JSONArray alunoLista = new JSONArray();
    if(aluno.getId_aluno() != -1) {
        JSONObject alunoObj = new JSONObject();
        alunoObj.put("id_aluno",aluno.getId_aluno());
        alunoObj.put("usuario_aluno",aluno.getUsuario_aluno());
        alunoObj.put("nome_aluno",aluno.getNome_aluno());
        alunoObj.put("email_aluno",aluno.getEmail_aluno());
        alunoObj.put("senha_aluno",aluno.getSenha_aluno());
        

        alunoLista.add(alunoObj);
    }

    alunoDAO.close();

    return alunoLista.toJSONString();

}

    public static boolean deleteAluno(Request request) {
	AlunoDAO AlunoDAO = new AlunoDAO();
	AlunoDAO.conectar();
	
	int id = Integer.parseInt(request.params(":id_aluno"));
	
	boolean resp = AlunoDAO.delete(id);
	AlunoDAO.close();
	
	return resp;
}

    public static boolean insertAluno(Request request) {

	AlunoDAO AlunoDAO = new AlunoDAO();
	AlunoDAO.conectar();
	
	int id_aluno = Integer.parseInt(request.params(":id_aluno"));
	String usuario_aluno = (request.params(":usuario_aluno"));
	String nome_aluno = request.params(":nome_aluno");
	String email_aluno = request.params(":email_aluno");
	String senha_aluno = request.params(":senha_aluno");
	 
	/*	
	 * 	alunoJsonObj.put("id_aluno",p.getId_aluno());
		alunoJsonObj.put("usuario_aluno",p.getNome_aluno());
		alunoJsonObj.put("nome_aluno",p.getNome_aluno());
		alunoJsonObj.put("email_aluno",p.getEmail_aluno());
		alunoJsonObj.put("senha_aluno",p.getSenha_aluno() + "");*/
	boolean resp = AlunoDAO.insert(new Aluno(id_aluno,usuario_aluno,nome_aluno,email_aluno,senha_aluno));
	
	AlunoDAO.close();
	
	return resp;
}

    public static int getUltimoID() {
	AlunoDAO alunoDAO = new AlunoDAO();
	alunoDAO.conectar();
	List<Aluno> alunos = alunoDAO.get();
	int ultimaId = 0;
	for(int i = 0; i < alunos.size(); i++) {
		if (ultimaId < alunos.get(i).getId_aluno()) {
			ultimaId = alunos.get(i).getId_aluno();
		}
	}
	return ultimaId;
}


    public static boolean updateAluno(Request request) {
	AlunoDAO alunoDAO = new AlunoDAO();
	alunoDAO.conectar();
	
	int id_aluno = Integer.parseInt(request.params(":id_aluno"));
	String usuario_aluno = request.params(":usuario_aluno");
	String nome_aluno= request.params(":nome_aluno");
	String email_aluno = request.params(":email_aluno");
	String senha_aluno = request.params(":senha_aluno");
	
	boolean resp = alunoDAO.update(new Aluno(id_aluno,usuario_aluno, nome_aluno, email_aluno,senha_aluno));
	
	alunoDAO.close();
	
	return resp;
	}

public static String getProfessor() {
	ProfessorDAO ProfessorDAO = new ProfessorDAO();
	ProfessorDAO.conectar();
	
	List<Professor> professors = ProfessorDAO.get();
	JSONArray professorsJsonList = new JSONArray();
	
	for(int i = 0; i < professors.size(); i++) {
		Professor p = professors.get(i);
		
		JSONObject professorJsonObj = new JSONObject();
		professorJsonObj.put("id_professor",p.getId_professor());
		professorJsonObj.put("usuario_professor",p.getNome_professor());
		professorJsonObj.put("nome_professor",p.getNome_professor());
		professorJsonObj.put("email_professor",p.getEmail_professor());
		professorJsonObj.put("senha_professor",p.getSenha_professor() + "");
		
		professorsJsonList.add(professorJsonObj);
		
	}
	
	ProfessorDAO.close();
	
	return professorsJsonList.toJSONString();
}

    public static int getLoginProfessor(Request request) {
	ProfessorDAO ProfessorDAO = new ProfessorDAO();
	ProfessorDAO.conectar();
	int certo =0;

	String usuario = request.params(":usuario_professor");
	String senha = request.params(":senha_professor");
	List<Professor> professors = ProfessorDAO.get();
	for(int i = 0; i < professors.size(); i++) {
		if(professors.get(i).getUsuario_professor().trim().equals(usuario) == true && professors.get(i).getSenha_professor().trim().equals(senha) == true) {
			certo =professors.get(i).getId_professor();
		}
	}
	
	return certo;
}
    public static int getSizeProfessor() {
	ProfessorDAO ProfessorDAO = new ProfessorDAO();
	ProfessorDAO.conectar();
	List<Professor> professors = ProfessorDAO.get();
	return professors.size();
}


    public static String searchProfessor(Request request) {
    ProfessorDAO professorDAO = new ProfessorDAO();
    professorDAO.conectar();

    int idSearch = Integer.parseInt(request.params(":id_professor"));
    Professor professor = professorDAO.getProfessor(idSearch);

    JSONArray professorLista = new JSONArray();
    if(professor.getId_professor() != -1) {
        JSONObject professorObj = new JSONObject();
        professorObj.put("id_professor",professor.getId_professor());
        professorObj.put("usuario_professor",professor.getUsuario_professor());
        professorObj.put("nome_professor",professor.getNome_professor());
        professorObj.put("email_professor",professor.getEmail_professor());
        professorObj.put("senha_professor",professor.getSenha_professor());
        

        professorLista.add(professorObj);
    }

    professorDAO.close();

    return professorLista.toJSONString();

}

    public static boolean deleteProfessor(Request request) {
	ProfessorDAO ProfessorDAO = new ProfessorDAO();
	ProfessorDAO.conectar();
	
	int id = Integer.parseInt(request.params(":id_professor"));
	
	boolean resp = ProfessorDAO.delete(id);
	ProfessorDAO.close();
	
	return resp;
}

    public static boolean insertProfessor(Request request) {

	ProfessorDAO ProfessorDAO = new ProfessorDAO();
	ProfessorDAO.conectar();
	
	int id_professor = Integer.parseInt(request.params(":id_professor"));
	String usuario_professor = (request.params(":usuario_professor"));
	String nome_professor = request.params(":nome_professor");
	String email_professor = request.params(":email_professor");

	String senha_professor = request.params(":senha_professor");
	 

	boolean resp = ProfessorDAO.insert(new Professor(id_professor,usuario_professor,nome_professor,email_professor,senha_professor));
	
	ProfessorDAO.close();
	
	return resp;
}

    public static int getUltimoIDProfessor() {
	ProfessorDAO professorDAO = new ProfessorDAO();
	professorDAO.conectar();
	List<Professor> professors = professorDAO.get();
	int ultimaId = 0;
	for(int i = 0; i < professors.size(); i++) {
		if (ultimaId < professors.get(i).getId_professor()) {
			ultimaId = professors.get(i).getId_professor();
		}
	}
	return ultimaId;
}


    public static boolean updateProfessor(Request request) {
	ProfessorDAO professorDAO = new ProfessorDAO();
	professorDAO.conectar();
	
	int id_professor = Integer.parseInt(request.params(":id_professor"));
	String usuario_professor = request.params(":usuario_professor");
	String nome_professor= request.params(":nome_professor");
	String email_professor = request.params(":email_professor");
	
	String senha_professor = request.params(":senha_professor");
	
	boolean resp = professorDAO.update(new Professor(id_professor,usuario_professor, nome_professor, email_professor,senha_professor));
	
	professorDAO.close();
	
	return resp;
	}
}



