package model;

public class Video {

    private int id_video;
    private int id_curso;
    private String link_video;
    private String descricao_video;
    private String nome_video;
    
	public Video () {
		this(-1, -1, "", "", "");
	}

    public Video (int id_video, int id_curso, String link_video, String descricao_video, String nome_video) {
        super();

        this.id_video = id_video;
        this.id_curso = id_curso;
        this.link_video = link_video;
        this.descricao_video = descricao_video;
        this.nome_video = nome_video;
    }

    public int getId_video() {
        return id_video;
    }
    public void setId_video(int id_video) {
        this.id_video = id_video;
    }
    
    public int getId_curso() {
        return id_curso;
    }
    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getLink_video() {
        return link_video;
    }
    public void setLink_video(String link_video) {
        this.link_video = link_video;
    }

    public String getDescricao_video() {
        return descricao_video;
    }
    public void setDescricao_video(String descricao_video) {
        this.descricao_video = descricao_video;
    }

    public String getNome_video() {
        return nome_video;
    }
    public void setNome_video(String nome_video) {
        this.nome_video = nome_video;
    }

	@Override
	public String toString() {
		return ("id_video: " + this.getId_video()
				+ "id_curso: " + this.getId_curso()
                + " link_video: " + this.getLink_video() 
                + " descricao_video: " + this.getDescricao_video() 
                + " nome_video: " + this.getNome_video());
	}
}
