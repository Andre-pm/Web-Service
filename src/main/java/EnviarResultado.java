import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EnviarResultado {

	public static String Enviar(String token2, String resultJson) throws IOException {
		OkHttpClient client = new OkHttpClient();
		 
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, resultJson);
        Request request = new Request.Builder()
          .url("http://desenvolvimento.edusoft.com.br/desenvolvimentoMentorWebG5/rest/servicoexterno/execute/gravaResultado")
          .post(body)
          .addHeader("content-type", "application/json")
          .addHeader("token", token2)
          .build();

        Response response = client.newCall(request).execute();
		return response.body().string();
	}
}
