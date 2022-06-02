package service;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.*;
import dao.VideoDao;
import model.Video;

@SuppressWarnings("unchecked")

public class VideoService {
	public static String getVideo() {
		VideoDao videoDao = new VideoDao();
		videoDao.conectar();
		
		List<Video> videos = videoDao.get();
		JSONArray videosJsonList = new JSONArray();
		
		for(int i = 0; i < videos.size(); i++) {
			Video video = videos.get(i);
			JSONObject videoJsonObj = new JSONObject();
			
            videoJsonObj.put("id_video", video.getId_video());
            videoJsonObj.put("id_cursofk", video.getId_cursofk());
            videoJsonObj.put("link_video", video.getLink_video());
            videoJsonObj.put("descricao_video", video.getDescricao_video());
            videoJsonObj.put("nome_video", video.getNome_video());
			
			videosJsonList.add(videoJsonObj);
		}
		
		videoDao.close();
		
		return videosJsonList.toJSONString();
	}
	
	public static String searchVideo(Request request) {
		VideoDao videoDao = new VideoDao();
		videoDao.conectar();
		
		int id_videoSearch = Integer.parseInt(request.params(":id_video"));

        Video video = videoDao.getVideo(id_videoSearch);
		
		JSONArray videoLista = new JSONArray();
		if(video.getId_video() != -1) {
			JSONObject videoObj = new JSONObject();
			
            videoObj.put("id_video", video.getId_video());
            videoObj.put("id_cursofk", video.getId_cursofk());
            videoObj.put("link_video", video.getLink_video());
            videoObj.put("descricao_video", video.getDescricao_video());
            videoObj.put("nome_video", video.getNome_video());

			videoLista.add(videoObj);
		}
		
		videoDao.close();
		
		return videoLista.toJSONString();
	}
	
	public static String searchByCurso(Request request) {
		VideoDao videoDao = new VideoDao();
		videoDao.conectar();
		
		int id_cursoSearch = Integer.parseInt(request.params(":id_cursofk"));
		
		List<Video> videos = videoDao.getByCurso(id_cursoSearch);
		JSONArray videosJsonList = new JSONArray();
		
		for(int i = 0; i < videos.size(); i++) {
			Video video = videos.get(i);
			JSONObject videoJsonObj = new JSONObject();
			
            videoJsonObj.put("id_video", video.getId_video());
            videoJsonObj.put("id_cursofk", video.getId_cursofk());
            videoJsonObj.put("link_video", video.getLink_video());
            videoJsonObj.put("descricao_video", video.getDescricao_video());
            videoJsonObj.put("nome_video", video.getNome_video());
			
			videosJsonList.add(videoJsonObj);
		}
		
		videoDao.close();
		
		return videosJsonList.toJSONString();
	}
	
	public static boolean deleteVideo(Request request) {
		VideoDao videoDao = new VideoDao();
		videoDao.conectar();
		
		int id_videoSearch = Integer.parseInt(request.params(":id_video"));
		
		boolean resp = videoDao.delete(id_videoSearch);
		videoDao.close();
		
		return resp;
	}

	public static boolean insertVideo(Request request) {

		VideoDao videoDao = new VideoDao();
		videoDao.conectar();
		
		int id_videoSearch = Integer.parseInt(request.params(":id_video"));
		int id_cursofk = Integer.parseInt(request.params(":id_cursofk"));
        String link_video = request.params(":link_video");
		String descricao_video = request.params(":descricao_video");
		String nome_video = request.params(":nome_video");

		boolean resp = videoDao.insert(new Video(id_videoSearch, id_cursofk, link_video, descricao_video, nome_video));
		
		videoDao.close();
		
		return resp;
	}

	public static boolean updateVideo(Request request) {
		
        VideoDao videoDao = new VideoDao();
		videoDao.conectar();
		
		int id_videoSearch = Integer.parseInt(request.params(":id_video"));
		int id_cursofk = Integer.parseInt(request.params(":id_cursofk"));
		String link_video = request.params(":link_video");
		String descricao_video = request.params(":descricao_video");
		String nome_video = request.params(":nome_video");
		
		boolean resp = videoDao.update(new Video(id_videoSearch, id_cursofk, link_video, descricao_video, nome_video));
		
        videoDao.close();

		return resp;
	}

	public static int getUltimoID() {
		VideoDao videoDao = new VideoDao();
		videoDao.conectar();
		List<Video> videos = videoDao.get();
		int ultimaId = 0;
		for(int i = 0; i < videos.size(); i++) {
			if (ultimaId < videos.get(i).getId_video()) {
				ultimaId = videos.get(i).getId_video();
			}
		}
		return ultimaId;
	}
}