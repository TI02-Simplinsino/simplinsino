package app;

import static spark.Spark.*;
import dao.TarefaDAO;
import spark.*;
import service.Service;

public class Aplicacao {
	public static void main(String[] args){
		TarefaDAO tarefaDAO = new TarefaDAO();
		
		tarefaDAO.conectar();
		port(6795);
		after((Filter) (request,response) -> {
			response.header("Access-Control-Allow-Origin","*");
			response.header("Access-Control-Allow-Methods", "GET");
			response.header("Access-Control-Allow-Methods", "POST");
		});
		//(int id_tarefa, String tarefa_nome, int tarefa_prioridade, int id_alunoFK)
		get("/getSizeTarefa",(request,response) -> Service.getSizeTarefa());
		get("/getTarefas",(request,response) -> Service.getTarefa());
		get("/searchTarefa/:id_tarefa", (request,response) -> Service.searchTarefa(request));
		get("/removeTarefas/:id_tarefa",(request,response) -> Service.deleteTarefa(request));
		get("/insertTarefas/:id_tarefa/:tarefa_nome/:tarefa_prioridade/:id_alunofk",(request,response) -> Service.insertTarefa(request));
		get("/getUltimoIDTarefa", (request,response) -> Service.getUltimoIDTarefa());
		get("/updateTarefas/:id_tarefa/:tarefa_nome/:tarefa_prioridade/:id_alunofk",(request,response) -> Service.updateTarefa(request));
		
		//Codigo Pedro
		get("/getAluno",(request,response) -> Service.getAluno());
		get("/searchAluno/:id_aluno", (request,response) -> Service.searchAluno(request));
		get("/removeAluno/:id_aluno",(request,response) -> Service.deleteAluno(request));
		get("/insertAluno/:id_aluno/:usuario_aluno/:nome_aluno/:email_aluno/:senha_aluno",(request,response) -> Service.insertAluno(request));
		get("/updateAluno/:id_aluno/:usuario_aluno/:nome_aluno/:email_aluno/:senha_aluno",(request,response) -> Service.updateAluno(request));
		get("/getLogin/:usuario_aluno/:senha_aluno",(request,response) -> Service.getLogin(request));
		// teste
		get("/getSizeAluno",(request,response) -> Service.getSizeAluno());
		get("/getUltimoID", (request,response) -> Service.getUltimoID());
		
		get("/getProfessor",(request,response) -> Service.getProfessor());
		get("/searchProfessor/:id_professor", (request,response) -> Service.searchProfessor(request));
		get("/removeProfessor/:id_professor",(request,response) -> Service.deleteProfessor(request));
		get("/insertProfessor/:id_professor/:usuario_professor/:nome_professor/:email_professor/:senha_professor",(request,response) -> Service.insertProfessor(request));
		get("/updateProfessor/:id_professor/:usuario_professor/:nome_professor/:email_professor/:senha_professor",(request,response) -> Service.updateProfessor(request));
		get("/getUltimoIDProfessor", (request,response) -> Service.getUltimoIDProfessor());
		get("/getLoginProfessor/:usuario_professor/:senha_professor",(request,response) -> Service.getLoginProfessor(request));
	}
}
