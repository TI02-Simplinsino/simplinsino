package service;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.*;
import dao.CursoDao;
import model.Curso;

@SuppressWarnings("unchecked")

public class CursoService {
	public static String getCurso() {
		CursoDao cursoDao = new CursoDao();
		cursoDao.conectar();
		
		List<Curso> cursos = cursoDao.get();
		JSONArray cursosJsonList = new JSONArray();
		
		for(int i = 0; i < cursos.size(); i++) {
			Curso curso = cursos.get(i);
			JSONObject cursoJsonObj = new JSONObject();
			
            cursoJsonObj.put("id_disciplinafk", curso.getid_disciplinafk());
            cursoJsonObj.put("id_curso", curso.getId_curso());
            cursoJsonObj.put("curso_nome", curso.getCurso_nome());
            cursoJsonObj.put("curso_duracao", curso.getCurso_duracao());
            cursoJsonObj.put("curso_descricao", curso.getCurso_descricao());
            cursoJsonObj.put("curso_imagem", curso.getCurso_imagem());
			
			cursosJsonList.add(cursoJsonObj);
		}
		
		cursoDao.close();
		
		return cursosJsonList.toJSONString();
	}
	
	public static String searchCurso(Request request) {
		CursoDao cursoDao = new CursoDao();
		cursoDao.conectar();
		
		int id_cursoSearch = Integer.parseInt(request.params(":id_curso"));

        Curso curso = cursoDao.getCurso(id_cursoSearch);
		
		JSONArray cursoLista = new JSONArray();
		if(curso.getId_curso() != -1) {
			JSONObject cursoObj = new JSONObject();
			
            cursoObj.put("id_disciplinafk", curso.getid_disciplinafk());
            cursoObj.put("id_curso", curso.getId_curso());
            cursoObj.put("curso_nome", curso.getCurso_nome());
            cursoObj.put("curso_duracao", curso.getCurso_duracao());
            cursoObj.put("curso_descricao", curso.getCurso_descricao());
            cursoObj.put("curso_imagem", curso.getCurso_imagem());

			cursoLista.add(cursoObj);
		}
		
		cursoDao.close();
		
		return cursoLista.toJSONString();
	}
	
	public static String searchCursoNome(Request request) {

        CursoDao cursoDao = new CursoDao();
		cursoDao.conectar();
		
		String nomeSearch = request.params(":curso_nome");
		
		List<Curso> cursos = cursoDao.getCursoNome(nomeSearch);
		JSONArray cursosJsonList = new JSONArray();
		
		for(int i = 0; i < cursos.size(); i++) {
			Curso curso = cursos.get(i);
			JSONObject cursoJsonObj = new JSONObject();
			
            cursoJsonObj.put("id_disciplinafk", curso.getid_disciplinafk());
            cursoJsonObj.put("id_curso", curso.getId_curso());
            cursoJsonObj.put("curso_nome", curso.getCurso_nome());
            cursoJsonObj.put("curso_duracao", curso.getCurso_duracao());
            cursoJsonObj.put("curso_descricao", curso.getCurso_descricao());
            cursoJsonObj.put("curso_imagem", curso.getCurso_imagem());
			
			cursosJsonList.add(cursoJsonObj);
		}

		
		cursoDao.close();
		
		return cursosJsonList.toJSONString();
	}

	public static boolean deleteCurso(Request request) {
		CursoDao cursoDao = new CursoDao();
		cursoDao.conectar();
		
		int id_cursoSearch = Integer.parseInt(request.params(":id_curso"));
		
		boolean resp = cursoDao.delete(id_cursoSearch);
		cursoDao.close();
		
		return resp;
	}

	public static boolean insertCurso(Request request) {

		CursoDao cursoDao = new CursoDao();
		cursoDao.conectar();
		
		int id_diciplinaSearch = Integer.parseInt(request.params(":id_disciplinafk"));
		int id_cursoSearch = Integer.parseInt(request.params(":id_curso"));
		String curso_nome = request.params(":curso_nome");
		String curso_duracao = request.params(":curso_duracao");
		String curso_descricao = request.params(":curso_descricao");
		String curso_imagem = request.params(":curso_imagem");


		boolean resp = cursoDao.insert(new Curso(id_diciplinaSearch, id_cursoSearch, curso_nome, curso_duracao, curso_descricao, curso_imagem));
		
		cursoDao.close();
		
		return resp;
	}

	public static boolean updateCurso(Request request) {
		
        CursoDao cursoDao = new CursoDao();
		cursoDao.conectar();
		
		int id_diciplinaSearch = Integer.parseInt(request.params(":id_disciplinafk"));
		int id_cursoSearch = Integer.parseInt(request.params(":id_curso"));
		String curso_nome = request.params(":curso_nome");
		String curso_duracao = request.params(":curso_duracao");
		String curso_descricao = request.params(":curso_descricao");
		String curso_imagem = request.params(":curso_imagem");
		
		boolean resp = cursoDao.update(new Curso(id_diciplinaSearch, id_cursoSearch, curso_nome, curso_duracao, curso_descricao, curso_imagem));
		
        cursoDao.close();

		return resp;
	}
	
	public static int getUltimoID() {
		CursoDao cursoDao = new CursoDao();
		cursoDao.conectar();
		List<Curso> cursos = cursoDao.get();
		int ultimaId = 0;
		for(int i = 0; i < cursos.size(); i++) {
			if (ultimaId < cursos.get(i).getId_curso()) {
				ultimaId = cursos.get(i).getId_curso();
			}
		}
		return ultimaId;
	}
}