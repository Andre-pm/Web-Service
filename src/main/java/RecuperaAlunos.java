
import java.io.IOException;
import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecuperaAlunos {

	public static JSONObject Recuperar(String token) throws IOException {

		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"ingressoId\":99,\"periodoLetivoId\":1}");
		Request request = new Request.Builder().url(
				"http://desenvolvimento.edusoft.com.br/desenvolvimentoMentorWebG5/rest/servicoexterno/execute/recuperaAlunos")
				.post(body).addHeader("content-type", "application/json").addHeader("token", token).build();

		Response response = client.newCall(request).execute();
		JSONObject objetoJson = new JSONObject("{result:" + response.body().string() + "}");
		return objetoJson;

	}
}