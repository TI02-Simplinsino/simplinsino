package service;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.*;
import dao.DisciplinaDao;
import model.Disciplina;

@SuppressWarnings("unchecked")

public class DisciplinaService {
	public static String getDisciplina() {
		DisciplinaDao disciplinaDao = new DisciplinaDao();
		disciplinaDao.conectar();
		
		List<Disciplina> disciplinas = disciplinaDao.get();
		JSONArray disciplinasJsonList = new JSONArray();
		
		for(int i = 0; i < disciplinas.size(); i++) {
			Disciplina disciplina = disciplinas.get(i);
			JSONObject disciplinaJsonObj = new JSONObject();
			
            disciplinaJsonObj.put("id_disciplina", disciplina.getId_disciplina());
            disciplinaJsonObj.put("disciplina_nome", disciplina.getDisciplina_nome());
            disciplinaJsonObj.put("disciplina_img", disciplina.getDisciplina_img());
			
			disciplinasJsonList.add(disciplinaJsonObj);
		}
		
		disciplinaDao.close();
		
		return disciplinasJsonList.toJSONString();
	}
	
	public static String searchDisciplina(Request request) {
		DisciplinaDao disciplinaDao = new DisciplinaDao();
		disciplinaDao.conectar();
		
		int id_disciplinaSearch = Integer.parseInt(request.params(":id_disciplina"));

        Disciplina disciplina = disciplinaDao.getDisciplina(id_disciplinaSearch);
		
		JSONArray disciplinaLista = new JSONArray();
		if(disciplina.getId_disciplina() != -1) {
			JSONObject disciplinaObj = new JSONObject();
			
            disciplinaObj.put("id_disciplina", disciplina.getId_disciplina());
            disciplinaObj.put("disciplina_nome", disciplina.getDisciplina_nome());
            disciplinaObj.put("disciplina_img", disciplina.getDisciplina_img());

			disciplinaLista.add(disciplinaObj);
		}
		
		disciplinaDao.close();
		
		return disciplinaLista.toJSONString();
	}
}