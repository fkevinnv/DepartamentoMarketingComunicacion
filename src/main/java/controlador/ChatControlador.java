package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@WebServlet("/ChatControlador")
public class ChatControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		String mensaje = request.getParameter("mensaje");

		String webhookUrl = "http://localhost:5678/webhook-test/chatbox";

		String mensajeJson = "{" + "\"mensaje\": \"" + mensaje + "\"" + "}";

		try {
			HttpClient cliente = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

			HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create(webhookUrl))
					.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(mensajeJson))
					.timeout(java.time.Duration.ofSeconds(10)).build();

			HttpResponse<String> respuestaN8N = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

			PrintWriter out = response.getWriter();
			out.print(respuestaN8N.body());
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.print("{\"respuesta\": \"Error al conectar con el asistente\"}");
			out.flush();
		}
	}
}
