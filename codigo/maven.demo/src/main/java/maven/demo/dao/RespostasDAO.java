package maven.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import maven.demo.Resposta;

public class RespostasDAO {
	private Connection conexao;
	
	public RespostasDAO() {
		conexao = null;
	}
	
	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	
	// CRUD RESPOSTAS
	// INSERIR
    public boolean inserirResposta(Resposta resposta) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO respostas (id, id_pergunta, descricao) "
					       + "VALUES (" + resposta.getId()+ ", '" + resposta.getId_pergunta() + "', '"  
					       + resposta.getDescricao() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
    // ATUALIZAR
	public boolean atualizarResposta(Resposta resposta) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE respostas SET id = '" + resposta.getId() + "', id_pergunta = '"  
				       + resposta.getId_pergunta() + "', descricao = '" + resposta.getDescricao() + "'"
					   + " WHERE id = " + resposta.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	// EXCLUIR
	public boolean excluirResposta(int id) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM respostas WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	// LISTAR
	public Resposta[] getRespostas() {
		Resposta[] respostas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM respostas ORDER BY id");		
	         if(rs.next()){
	             rs.last();
	             respostas = new Resposta[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 respostas[i] = new Resposta(rs.getInt("id"), rs.getInt("id_pergunta"), 
	                		                  rs.getString("descricao"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return respostas;
	}
}

