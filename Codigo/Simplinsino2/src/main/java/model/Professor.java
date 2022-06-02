package model;

public class Professor {
	private int id_professor;
	private String usuario_professor;
	private String nome_professor;
	private String email_professor;

	private String senha_professor;

	
	public Professor() {
		this(-1,"nenhum","a","fas","sdafas");
	}
	
	public Professor(int id_professor, String usuario_professor,String nome_professor,String email_professor,String senha_professor) {
		super();
		this.id_professor = id_professor;
		this.usuario_professor = usuario_professor ;
		this.nome_professor = nome_professor;
        this.email_professor = email_professor;
        this.senha_professor = senha_professor;
	}
	

    public int getId_professor() {
        return id_professor;
    }
    public String getUsuario_professor() {
        return usuario_professor;
    }
    public String getNome_professor() {
        return nome_professor;
    }
    public String getEmail_professor() {
        return email_professor;
    }

    public String getSenha_professor() {
        return senha_professor;
    }
    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }
    public void setUsuario_professor(String usuario_professor) {
        this.usuario_professor = usuario_professor;
    }
    public void setNome_professor(String nome_professor) {
        this.nome_professor = nome_professor;
    }
    public void setEmail_professor(String email_professor) {
        this.email_professor = email_professor;
    }

    public void setSenha_professor(String senha_professor) {
        this.senha_professor = senha_professor;
    }
	@Override
	public String toString() {
		return "Id: " + this.getId_professor() + " Usuario: " + this.getUsuario_professor() + " Nome: " + this.getNome_professor()
        + " Email: " + this.getEmail_professor() +   " Senha: " + this.getSenha_professor(); 
	}
}
