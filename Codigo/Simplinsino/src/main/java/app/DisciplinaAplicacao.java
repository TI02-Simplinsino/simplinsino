package app;

import static spark.Spark.*;
import dao.DisciplinaDao;
import spark.*;
import service.DisciplinaService;

public class DisciplinaAplicacao {
	public static void main(String[] args){
		DisciplinaDao disciplinaDao = new DisciplinaDao();
		
		disciplinaDao.conectar();
		port(63456);
		after((Filter) (request,response) -> {
			response.header("Access-Control-Allow-Origin","*");
			response.header("Access-Control-Allow-Methods", "GET");
			response.header("Access-Control-Allow-Methods", "POST");
		});
		
		get("/getDisciplinas",(request,response) -> DisciplinaService.getDisciplina());
		get("/search/:id_disciplina", (request,response) -> DisciplinaService.searchDisciplina(request));
	}
}
