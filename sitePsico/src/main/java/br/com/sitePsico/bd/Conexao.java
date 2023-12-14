package br.com.sitePsico.bd;

import java.sql.Connection;
import java.sql.DriverManager;
public class Conexao {
	private Connection conexao;
	
	public Connection abrirConexao() {

		try {
			String url = "jdbc:mysql://localhost/psico?"; 
			String usuario = "root"; 
			String senha = "1234"; 
			Class.forName("com.mysql.cj.jdbc.Driver");  
			conexao = DriverManager.getConnection(url,usuario,senha);
			System.out.println("aqui entrou no banco psico");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
	
	public void fecharConexao() {
		try {
			conexao.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
