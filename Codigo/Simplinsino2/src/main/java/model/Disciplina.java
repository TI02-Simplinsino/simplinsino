package model;

public class Disciplina {

    private int id_disciplina;
    private String disciplina_nome;
    private String disciplina_img;
    
	public Disciplina () {
		this(-1, "", "");
	}

    public Disciplina (int id_disciplina, String disciplina_nome, String disciplina_img) {
        super();
        this.id_disciplina = id_disciplina;
        this.disciplina_nome = disciplina_nome;
        this.disciplina_img = disciplina_img;
    }

    public int getId_disciplina() {
		return id_disciplina;
	}
	public void setId_disciplina(int id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public String getDisciplina_nome() {
		return disciplina_nome;
	}
	public void setDisciplina_nome(String disciplina_nome) {
		this.disciplina_nome = disciplina_nome;
	}
	

    public String getDisciplina_img() {
        return disciplina_img;
    }
    public void setDisciplina_img(String disciplina_img) {
        this.disciplina_img = disciplina_img;
    }

	@Override
	public String toString() {
		return ("id_disciplina: " + this.getId_disciplina() 
                + " disciplina_nome: " + this.getDisciplina_nome() 
                + " disciplina_imagem: " + this.getDisciplina_img());
	}
}