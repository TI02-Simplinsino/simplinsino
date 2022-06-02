package service;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.*;
import dao.CursoDao;
import dao.DisciplinaDao;
import dao.SalvacursoDAO;
import model.Curso;
import model.Disciplina;
import model.Salvacurso;

@SuppressWarnings("unchecked")

public class SalvacursoService {
	public static String getSalvacurso() {
		SalvacursoDAO cursoDao = new SalvacursoDAO();
		cursoDao.conectar();
		
		List<Salvacurso> salvacurso = cursoDao.get();
		JSONArray salvacursoJsonList = new JSONArray();
		
		for(int i = 0; i < salvacurso.size(); i++) {
			Salvacurso curso = salvacurso.get(i);
			JSONObject cursoJsonObj = new JSONObject();
			
            cursoJsonObj.put("id_alunofk", curso.getId_alunofk());
            cursoJsonObj.put("id_cursofk", curso.getId_cursofk());
			salvacursoJsonList.add(cursoJsonObj);
		}
		cursoDao.close();
		return salvacursoJsonList.toJSONString();
	}
	public static String searchSalvacurso(Request request) {
		SalvacursoDAO cursoDao = new SalvacursoDAO();
		cursoDao.conectar();
		
		int id_cursoSearch = Integer.parseInt(request.params(":id_alunofk"));

        Salvacurso curso = cursoDao.getSalvacurso(id_cursoSearch);
		
		JSONArray cursoLista = new JSONArray();
		if(curso.getId_alunofk() != -1) {
			JSONObject cursoObj = new JSONObject();
			
            cursoObj.put("id_alunodk", curso.getId_alunofk());
            cursoObj.put("id_cursofk", curso.getId_cursofk());
			cursoLista.add(cursoObj);
		}
		
		cursoDao.close();
		
		return cursoLista.toJSONString();
	}
	

	public static boolean deleteSalvacurso(Request request) {
		SalvacursoDAO cursoDao = new SalvacursoDAO();
		cursoDao.conectar();
		
		int id_cursoSearch = Integer.parseInt(request.params(":id_cursofk"));
		int id_aluno = Integer.parseInt(request.params(":id_alunofk"));
		boolean resp = cursoDao.delete(id_aluno,id_cursoSearch);
		cursoDao.close();
		
		return resp;
	}

	public static boolean insertSalvacurso(Request request) {
		SalvacursoDAO cursoDao = new SalvacursoDAO();
		cursoDao.conectar();
		int id_alunofk = Integer.parseInt(request.params(":id_alunofk"));
		int id_cursofk = Integer.parseInt(request.params(":id_cursofk"));
		boolean resp = cursoDao.insert(new Salvacurso(id_alunofk, id_cursofk));
		cursoDao.close();
		return resp;
		
	}
	public static String searchCursoSalvo(Request request) {
		SalvacursoDAO cursoDao = new SalvacursoDAO();
		cursoDao.conectar();
		int id_cursoSearch = Integer.parseInt(request.params(":id_alunofk"));
		
		
		List<Curso> cursos = cursoDao.getCursoSalvo(id_cursoSearch);
		JSONArray cursoJsonList = new JSONArray();
		
		for(int i = 0; i < cursos.size(); i++) {
			Curso curso = cursos.get(i);
			JSONObject cursoObj = new JSONObject();
            cursoObj.put("id_disciplinafk", curso.getid_disciplinafk());
            cursoObj.put("id_curso", curso.getId_curso());
            cursoObj.put("curso_nome", curso.getCurso_nome());
            cursoObj.put("curso_duracao", curso.getCurso_duracao());
            cursoObj.put("curso_descricao", curso.getCurso_descricao());
            cursoObj.put("curso_imagem", curso.getCurso_imagem());

            cursoJsonList.add(cursoObj);
		}
		cursoDao.close();
		return cursoJsonList.toJSONString();
	}



	

}