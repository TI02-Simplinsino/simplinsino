package app;

import static spark.Spark.*;
import dao.SalvacursoDAO;
import spark.*;
import service.SalvacursoService;

public class SalvacursoAplicacao {
	public static void main(String[] args){
		SalvacursoDAO cursoDao = new SalvacursoDAO();
		
		cursoDao.conectar();
		port(6810);
		after((Filter) (request,response) -> {
			response.header("Access-Control-Allow-Origin","*");
			response.header("Access-Control-Allow-Methods", "GET");
			response.header("Access-Control-Allow-Methods", "POST");
		});
		
		get("/getSalvacursos",(request,response) -> SalvacursoService.getSalvacurso());
		get("/search/:id_alunofk", (request,response) -> SalvacursoService.searchSalvacurso(request));
		get("/remove//:id_alunofk/:id_cursofk",(request,response) -> SalvacursoService.deleteSalvacurso(request));
		get("/insert/:id_alunofk/:id_cursofk",(request,response) -> SalvacursoService.insertSalvacurso(request));
		get("/getCursoSalvo/:id_alunofk",(request,response) -> SalvacursoService.searchCursoSalvo(request));
		
		
	}
}
