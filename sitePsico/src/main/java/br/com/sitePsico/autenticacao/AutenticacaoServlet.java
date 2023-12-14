package br.com.sitePsico.autenticacao;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import br.com.sitePsico.bd.Conexao;
import br.com.sitePsico.jdbc.JDBCAutenticaDAO;
import br.com.sitePsico.modelo.User;

public class AutenticacaoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {
		
		User dadosautentica = new User();
		
		dadosautentica.setUsuario(request.getParameter("usuario"));
		//String textodeserializado = new String(Base64.decodeBase64(request.getParameter("senha")));
		String senmd5 = "";
		MessageDigest md = null;
		
		
		
		dadosautentica.setSenha(request.getParameter("senha"));				
		//String senmd5 = "";
		//MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		BigInteger hash = new BigInteger(1, md.digest(request.getParameter("senha").getBytes()));
		senmd5 = hash.toString(16);		
		dadosautentica.setSenha(senmd5);
		
		System.out.println(senmd5);
		System.out.println("senhasenha");
		
		Conexao conec = new Conexao();
		Connection conexao = (Connection) conec.abrirConexao();
		JDBCAutenticaDAO jdbcAutentica = new JDBCAutenticaDAO(conexao);
		boolean retorno = jdbcAutentica.consultar(dadosautentica);
		if (retorno) {
			System.out.println("tem usuario");
			HttpSession sessao = request.getSession();
			sessao.setAttribute("login", request.getParameter("cpf"));
			response.sendRedirect("pages/adm/erro.html");
		} else {
			System.out.println("não tem o usuario");
			response.sendRedirect("index.html");
		}

	}

	protected void doGET(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			process(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}