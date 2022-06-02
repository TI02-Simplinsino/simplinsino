package dao;

import java.sql.*;
import java.util.*;

import model.Video;

public class VideoDao extends DAO {
	public VideoDao() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Video video) {
		boolean status = false;
		try {
			String query = "INSERT INTO video (id_video, id_cursofk, link_video, descricao_video, nome_video) VALUES ('" +
					video.getId_video() + "', '" + video.getId_cursofk() + "', '" + video.getLink_video() + "', '" + video.getDescricao_video() + "', '" + video.getNome_video() + "');";
			PreparedStatement st = conexao.prepareStatement(query);
			st.executeUpdate();
			st.close();
			status = true;
		} catch(SQLException u) {
			throw new RuntimeException(u);
		}
		
		return status;
	}


	public boolean update(Video video) {
		boolean status = false;
		
		Video novo_video = getVideo(video.getId_video());
		delete(video.getId_video());
		insert(video);
		status = true;
		
		
		return status;
	}
	
	public boolean delete(int id_video) {
		boolean status = false;
		
		try {
			String query = "DELETE FROM video WHERE id_video = " + id_video;
			Statement st = conexao.createStatement();
			st.executeUpdate(query);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}

	public List<Video> get(){
		List<Video> videos = new ArrayList<Video>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM video";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Video video = new Video(rs.getInt("id_video"), rs.getInt("id_cursofk"), rs.getString("link_video"), rs.getString("descricao_video"), rs.getString("nome_video"));
				videos.add(video);
			}
			
			st.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return videos;
	}

	public Video getVideo(int id_video) {
		Video video = new Video();
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM video WHERE id_video = " + id_video);
			if(rs.next()) {
				video.setId_video(rs.getInt("id_video"));
				video.setId_cursofk(rs.getInt("id_cursofk"));
				video.setLink_video(rs.getString("link_video"));
				video.setDescricao_video(rs.getString("descricao_video"));
				video.setNome_video(rs.getString("nome_video"));
			}
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return video;
	}
	
	public List<Video> getByCurso(int id_cursofk){
		List<Video> videos = new ArrayList<Video>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM video WHERE id_cursofk = " + id_cursofk);
			while(rs.next()) {
				Video video = new Video(rs.getInt("id_video"), rs.getInt("id_cursofk"), rs.getString("link_video"), rs.getString("descricao_video"), rs.getString("nome_video"));
				videos.add(video);
			}
			
			st.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return videos;
	}
}