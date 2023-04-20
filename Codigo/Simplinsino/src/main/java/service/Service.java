package service;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.*;
import dao.AlunoDAO;
import dao.TarefaDAO;
import model.Aluno;
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
		alunoJsonObj.put("pagamento_aluno",p.getPagamento_aluno());
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
        alunoObj.put("pagamento_aluno",aluno.getPagamento_aluno());
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
	String pagamento_aluno = request.params(":pagamento_aluno");
	String senha_aluno = request.params(":senha_aluno");
	 
	/*	
	 * 	alunoJsonObj.put("id_aluno",p.getId_aluno());
		alunoJsonObj.put("usuario_aluno",p.getNome_aluno());
		alunoJsonObj.put("nome_aluno",p.getNome_aluno());
		alunoJsonObj.put("email_aluno",p.getEmail_aluno());
		alunoJsonObj.put("pagamento_aluno",p.getPagamento_aluno());
		alunoJsonObj.put("senha_aluno",p.getSenha_aluno() + "");*/
	boolean resp = AlunoDAO.insert(new Aluno(id_aluno,usuario_aluno,nome_aluno,email_aluno,pagamento_aluno,senha_aluno));
	
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

public static boolean updateAluno(Request request) {
	AlunoDAO alunoDAO = new AlunoDAO();
	alunoDAO.conectar();
	
	int id_aluno = Integer.parseInt(request.params(":id_aluno"));
	String usuario_aluno = request.params(":usuario_aluno");
	String nome_aluno= request.params(":nome_aluno");
	String email_aluno = request.params(":email_aluno");
	String pagamento_aluno= request.params(":pagamento_aluno");
	String senha_aluno = request.params(":senha_nome");
	
	boolean resp = alunoDAO.update(new Aluno(id_aluno,usuario_aluno, nome_aluno, email_aluno,pagamento_aluno,senha_aluno));
	
	alunoDAO.close();
	
	return resp;
	}
}

