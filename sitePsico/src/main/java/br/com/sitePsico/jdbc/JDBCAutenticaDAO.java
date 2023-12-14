package br.com.sitePsico.jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.sitePsico.modelo.User;

public class JDBCAutenticaDAO {
	
	private Connection conexao;
	public JDBCAutenticaDAO (Connection conexao) {
		
		this.conexao = conexao;
	}
	
	public boolean consultar (User dadosautentica) {
		
		
		try {
			String comando = "SELECT * FROM dadosusuario WHERE usuario = '"
			+dadosautentica.getUsuario()+"' and senha = '"+dadosautentica.getSenha()+"'";
			
			System.out.println(dadosautentica.getUsuario());
			System.out.println(comando +"  comando1");
			
			Statement stmt = conexao.createStatement();
			System.out.println("sysout2");
			
			ResultSet rs = stmt.executeQuery(comando);
			System.out.println("sysout3");
			
			System.out.println(comando +"  comando2");
			if(rs.next()) {
				
				System.out.println("oiaki");
			
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			return false;
		}
	}
}