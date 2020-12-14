package maven.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import maven.demo.Pergunta;

public class PerguntasDAO {
	private Connection conexao;
	
	public PerguntasDAO() {
		conexao = null;
	}
	
	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	
	// CRUD PERGUNTAS
    // INSERIR
    public boolean inserirPergunta(Pergunta pergunta) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO perguntas (id, veracidade, descricao) "
					       + "VALUES (" + (pergunta.getId())+ ", '" + (pergunta.getVeracidade()) + "', '"  
					       + (pergunta.getDescricao()) + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
    // ATUALIZAR
	public boolean atualizarPergunta(Pergunta pergunta) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE perguntas SET id = '" + pergunta.getId() + "', veracidade = '"  
				       + pergunta.getVeracidade() + "', descricao = '" + pergunta.getDescricao() + "'"
					   + " WHERE id = " + pergunta.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	// EXCLUIR
	public boolean excluirPergunta(int id) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM perguntas WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	// LISTAR
	public Pergunta[] getPerguntas() {
		Pergunta[] perguntas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM perguntas ORDER BY id;");		
	         if(rs.next()){
	             rs.last();
	             perguntas = new Pergunta[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 perguntas[i] = new Pergunta(rs.getInt("id"), rs.getString("veracidade"), 
	                		                  rs.getString("descricao"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return perguntas;
	}
}

