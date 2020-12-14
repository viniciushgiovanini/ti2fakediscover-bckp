package maven.service;

import java.sql.Connection;
import org.json.JSONObject;
import maven.demo.dao.DAO;
import maven.demo.dao.PerguntasDAO;
import maven.demo.dao.RespostasDAO;
import maven.demo.Pergunta;
import maven.demo.Resposta;

public class Service {
	private static Connection conexao = null;
	private PerguntasDAO pd = null;
	private RespostasDAO rd = null;

	public Service() {
		conexao = DAO.getConexao();
		pd = new PerguntasDAO();
		rd = new RespostasDAO();
		pd.setConexao(conexao);
		rd.setConexao(conexao);
	}
	
	public JSONObject[] getAllPerguntasJson() {
		Pergunta[] perguntas = pd.getPerguntas();
		JSONObject[] perguntasJson = new JSONObject[perguntas.length];
		
		for (int i = 0; i < perguntas.length; i++) 
			perguntasJson[i] = perguntas[i].toJson();
		
		return perguntasJson;
	}
	
	public JSONObject[] getAllRespostasJson() {
		Resposta[] respostas = rd.getRespostas();
		JSONObject[] respostasJson = new JSONObject[respostas.length];
		
		for (int i = 0; i < respostas.length; i++) 
			respostasJson[i] = respostas[i].toJson();
		
		return respostasJson;
	}
	
	public JSONObject getAllJson() {
		Pergunta[] p = pd.getPerguntas();
		Resposta[] r = rd.getRespostas();
		JSONObject obj = new JSONObject();
		
		obj.put("Perguntas", p);
		obj.put("Respostas", r);
		
		return obj;
	}
	
	public void close() {
		this.pd = null;
		this.rd = null;
		DAO.close();
	}
	
}
