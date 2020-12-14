package maven.demo;

import org.json.JSONObject;

public class Pergunta {
	/*
	 * id int not null,
     * veracidade char(11) not null,
     * descricao text not null,
     * primary key (id)
	 */
	private int id;
    private String veracidade;
    private String descricao;
    
    public Pergunta() {
    	this(-1, "", "");
    }
    
    public Pergunta(int id, String veracidade, String descricao) {
    	this.id = id;
    	this.veracidade = veracidade;
    	this.descricao = descricao;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getVeracidade() {
		return veracidade;
	}
	public void setVeracidade(String veracidade) {
		this.veracidade = veracidade;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", veracidade=" + veracidade + ", descricao=" + descricao + "]";
	}
	
	/**
	* Converte uma pergunta para formato JSON
	*/
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("veracidade", this.getVeracidade());
		obj.put("descricao", this.getDescricao());		
		
		return obj;
	}
}
