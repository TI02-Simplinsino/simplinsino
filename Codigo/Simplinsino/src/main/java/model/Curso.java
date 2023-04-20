package model;

public class Curso {

    private int id_disciplinafk;
    private int id_curso;
    private String curso_nome;
    private String curso_duracao;
    private String curso_descricao;
    private String curso_imagem;
    
	public Curso () {
		this(-1, -1, "", "", "", "");
	}

    public Curso (int id_disciplinafk, int id_curso, String curso_nome, String curso_duracao, String curso_descricao, String curso_imagem) {
        super();
        this.id_disciplinafk = id_disciplinafk;
        this.id_curso = id_curso;
        this.curso_nome = curso_nome;
        this.curso_duracao = curso_duracao;
        this.curso_descricao = curso_descricao;
        this.curso_imagem = curso_imagem;
    }

    public int getid_disciplinafk() {
		return id_disciplinafk;
	}
	public void setid_disciplinafk(int id_disciplinafk) {
		this.id_disciplinafk = id_disciplinafk;
	}

	public int getId_curso() {
		return id_curso;
	}
	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	public String getCurso_nome() {
		return curso_nome;
	}
	public void setCurso_nome(String curso_nome) {
		this.curso_nome = curso_nome;
	}
	
    public String getCurso_descricao() {
        return curso_descricao;
    }
    public void setCurso_descricao(String curso_descricao) {
        this.curso_descricao = curso_descricao;
    }

    public String getCurso_duracao() {
        return curso_duracao;
    }
    public void setCurso_duracao(String curso_duracao) {
        this.curso_duracao = curso_duracao;
    }

    public String getCurso_imagem() {
        return curso_imagem;
    }
    public void setCurso_imagem(String curso_imagem) {
        this.curso_imagem = curso_imagem;
    }

	@Override
	public String toString() {
		return ("id_disciplinafk: " + this.getid_disciplinafk() 
                + " id_curso: " + this.getId_curso() 
                + " curso_nome: " + this.getCurso_nome() 
                + " curso_duracao: " + this.getCurso_duracao() 
                + " curso_descricao: " + this.getCurso_descricao() 
                + " curso_imagem: " + this.getCurso_imagem());
	}
}