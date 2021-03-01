import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GeraToken {

	public static String TokenAbertura() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(
				"http://desenvolvimento.edusoft.com.br/desenvolvimentoMentorWebG5/rest/servicoexterno/token/recuperaAlunos")
				.get().addHeader("usuario", "mentor").addHeader("senha", "123456").build();

		Response response = client.newCall(request).execute();
		return response.body().string();

	}
	
	public static String ToKenResultado() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(
				"http://desenvolvimento.edusoft.com.br/desenvolvimentoMentorWebG5/rest/servicoexterno/token/gravaResultado")
				.get().addHeader("usuario", "mentor").addHeader("senha", "123456").build();

		Response response = client.newCall(request).execute();
		return response.body().string();

	}
	

}
