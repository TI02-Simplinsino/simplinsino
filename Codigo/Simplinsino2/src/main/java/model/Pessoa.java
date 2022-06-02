package model;

public class Pessoa {
	private String nome;
	private int id;
	private char sexo;
	
	public Pessoa() {
		this(-1,"nenhum",'x');
	}
	
	public Pessoa(int id, String nome, char sexo) {
		super();
		this.nome = nome;
		this.id = id;
		this.sexo = sexo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() + " Id: " + this.getId() + " Sexo: " + this.getSexo(); 
	}
}
