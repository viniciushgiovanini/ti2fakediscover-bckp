package maven.demo;

import org.json.JSONObject;

public class Resposta {
	/*
	 * id int not null,
	 * id_pergunta int not null,
     * descricao text not null,
     * primary key (id), 
     * foreign key (id_pergunta) references Perguntas(id)
     * on update cascade on delete cascade
	 */
	private int id;
	private int id_pergunta;
	private String descricao;
	
	public Resposta() {
		this(-1, -1, "");
	}
	
	public Resposta(int id, int id_pergunta, String descricao) {
		this.id = id;
		this.id_pergunta = id_pergunta;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_pergunta() {
		return id_pergunta;
	}
	public void setId_pergunta(int id_pergunta) {
		this.id_pergunta = id_pergunta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Resposta [id=" + id + ", id_pergunta=" + id_pergunta + ", descricao=" + descricao + "]";
	}
	
	/**
	* Converte uma resposta para formato JSON
	*/
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("id_pergunta", this.getId_pergunta());
		obj.put("descricao", this.getDescricao());		
		
		return obj;
	}
}
