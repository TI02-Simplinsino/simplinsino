package app;

import static spark.Spark.*;
import dao.CursoDao;
import spark.*;
import service.CursoService;

public class CursoAplicacao {
	public static void main(String[] args){
		CursoDao cursoDao = new CursoDao();
		
		cursoDao.conectar();
		port(60475);
		after((Filter) (request,response) -> {
			response.header("Access-Control-Allow-Origin","*");
			response.header("Access-Control-Allow-Methods", "GET");
			response.header("Access-Control-Allow-Methods", "POST");
		});
		
		get("/getCursos",(request,response) -> CursoService.getCurso());
		get("/search/:id_curso", (request,response) -> CursoService.searchCurso(request));
		get("/getUltimoID", (request,response) -> CursoService.getUltimoID());
		get("/remove/:id_curso",(request,response) -> CursoService.deleteCurso(request));
		get("/insert/:id_disciplinafk/:id_curso/:curso_nome/:curso_duracao/:curso_descricao/:curso_imagem",(request,response) -> CursoService.insertCurso(request));
		get("/update/:id_disciplinafk/:id_curso/:curso_nome/:curso_duracao/:curso_descricao/:curso_imagem",(request,response) -> CursoService.updateCurso(request));
	}
}
