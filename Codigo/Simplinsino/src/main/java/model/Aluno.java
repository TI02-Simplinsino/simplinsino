package model;

public class Aluno {
	private int id_aluno;
	private String usuario_aluno;
	private String nome_aluno;
	private String email_aluno;
	private String pagamento_aluno;
	private String senha_aluno;

	
	public Aluno() {
		this(-1,"nenhum","a","fas","sdfa","sdafas");
	}
	
	public Aluno(int id_aluno, String usuario_aluno,String nome_aluno,String email_aluno,String pagamento_aluno,String senha_aluno) {
		super();
		this.id_aluno = id_aluno;
		this.usuario_aluno = usuario_aluno ;
		this.nome_aluno = nome_aluno;
        this.email_aluno = email_aluno;
        this.pagamento_aluno = pagamento_aluno;
        this.senha_aluno = senha_aluno;
	}
	

    public int getId_aluno() {
        return id_aluno;
    }
    public String getUsuario_aluno() {
        return usuario_aluno;
    }
    public String getNome_aluno() {
        return nome_aluno;
    }
    public String getEmail_aluno() {
        return email_aluno;
    }
    public String getPagamento_aluno() {
        return pagamento_aluno;
    }
    public String getSenha_aluno() {
        return senha_aluno;
    }
    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }
    public void setUsuario_aluno(String usuario_aluno) {
        this.usuario_aluno = usuario_aluno;
    }
    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }
    public void setEmail_aluno(String email_aluno) {
        this.email_aluno = email_aluno;
    }
    public void setPagamento_aluno(String pagamento_aluno) {
        this.pagamento_aluno = pagamento_aluno;
    }
    public void setSenha_aluno(String senha_aluno) {
        this.senha_aluno = senha_aluno;
    }
	@Override
	public String toString() {
		return "Id: " + this.getId_aluno() + " Usuario: " + this.getUsuario_aluno() + " Nome: " + this.getNome_aluno()
        + " Email: " + this.getEmail_aluno() + " Pagamento: " +  this.getPagamento_aluno() +  " Senha: " + this.getSenha_aluno(); 
	}
}
