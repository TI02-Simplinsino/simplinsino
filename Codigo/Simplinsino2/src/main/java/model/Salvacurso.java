package model;

public class Salvacurso {

    private int id_alunofk;
    private int id_cursofk;

    
	public Salvacurso () {
		this(-1, -1);
	}

    public Salvacurso (int id_alunofk, int id_cursofk) {
        super();
        this.id_alunofk = id_alunofk;
        this.id_cursofk = id_cursofk;

    }

    public int getId_alunofk() {
		return id_alunofk;
	}
	public void setId_alunofk(int id_alunofk) {
		this.id_alunofk = id_alunofk;
	}

	public int getId_cursofk() {
		return id_cursofk;
	}
	public void setId_cursofk(int id_cursofk) {
		this.id_cursofk = id_cursofk;
	}

	@Override
	public String toString() {
		return ("id_alunofk: " + this.getId_alunofk() 
                + " id_cursofk: " + this.getId_cursofk());
	}
}