package br.edu.ifsp.dsw1;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastro.do")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<Aluno> alunos;

	public CadastroServlet() {
		super();
		alunos = new LinkedList<>();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("text_dados");
		String mensagem;
		if(str!=null && !str.isEmpty()){
			String alunoInfo[] = str.split(";");
			if(alunoInfo.length==3) {
				Aluno aluno = new Aluno(
						alunoInfo[0].trim(),
						alunoInfo[1].trim(),
						alunoInfo[2].trim()
						);
				alunos.add(aluno);
				mensagem = "Aluno cadastrado com sucesso.";
			}else{
				mensagem = "Formato de dados inválido.";
			}
		}else{
			mensagem = "Não foi possível recuperar os dados.";
		}
		
		response.setContentType("text/html");
		try(var out = response.getWriter()){
			out.println("<!DOCTYPE html>");
			out.println("<html lang='pt-br'>");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<link href=\"css/style.css\" rel=\"stylesheet\">");
			out.println("<title>Cadastro</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='container'>");
			out.println("<h1>"+mensagem+"</h1>");
			out.println("<a href=\"input.html\">Voltar</a>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
