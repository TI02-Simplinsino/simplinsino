package app;

import static spark.Spark.*;
import dao.VideoDao;
import spark.*;
import service.VideoService;

public class VideoAplicacao {
	public static void main(String[] args){
		VideoDao videoDao = new VideoDao();
		
		videoDao.conectar();
		port(6815);
		after((Filter) (request,response) -> {
			response.header("Access-Control-Allow-Origin","*");
			response.header("Access-Control-Allow-Methods", "GET");
			response.header("Access-Control-Allow-Methods", "POST");
		});
		
		get("/getVideos",(request,response) -> VideoService.getVideo());
		get("/search/:id_video", (request,response) -> VideoService.searchVideo(request));
		get("/searchByCurso/:id_cursofk", (request,response) -> VideoService.searchByCurso(request));
		get("/getUltimoID", (request,response) -> VideoService.getUltimoID());
		get("/remove/:id_video",(request,response) -> VideoService.deleteVideo(request));
		get("/insert/:id_video/:id_cursofk/:link_video/:descricao_video/:nome_video",(request,response) -> VideoService.insertVideo(request));
		get("/update/:id_video/:id_cursofk/:link_video/:descricao_video/:nome_video",(request,response) -> VideoService.updateVideo(request));
	}
}
