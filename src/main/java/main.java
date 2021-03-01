import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class main {
	public static void main(String[] args) {
		ArmazenaAluno armazenaAluno = new ArmazenaAluno();

		//Gera Token
		try {
			String token = GeraToken.TokenAbertura();
			JSONObject objetoJson = RecuperaAlunos.Recuperar(token);

			JSONArray lista = objetoJson.getJSONArray("result");

			// Pega Resultado
			String resultado = lista.getJSONObject(0).get("resultado").toString();


			for (int i = 0; i < lista.getJSONObject(0).getJSONArray("alunos").length(); i++) {
				// Pega Aluno
				int totalAulas = (Integer) lista.getJSONObject(0).getJSONArray("alunos").getJSONObject(i)
						.get("TOAL_AULAS");
				int aluCod = (Integer) lista.getJSONObject(0).getJSONArray("alunos").getJSONObject(i).get("COD");
				String aluNome = lista.getJSONObject(0).getJSONArray("alunos").getJSONObject(i).get("NOME").toString();

				// Pega Nota e Falta
				double nota1 = ((BigDecimal) lista.getJSONObject(0).getJSONArray("alunos").getJSONObject(i)
						.getJSONArray("nota").getJSONObject(0).get("NOTA")).doubleValue();
				int falta1 = (Integer) lista.getJSONObject(0).getJSONArray("alunos").getJSONObject(i)
						.getJSONArray("nota").getJSONObject(0).get("FALTAS");

				double nota2 = ((BigDecimal) lista.getJSONObject(0).getJSONArray("alunos").getJSONObject(i)
						.getJSONArray("nota").getJSONObject(1).get("NOTA")).doubleValue();
				int falta2 = (Integer) lista.getJSONObject(0).getJSONArray("alunos").getJSONObject(i)
						.getJSONArray("nota").getJSONObject(1).get("FALTAS");

				double nota3 = ((BigDecimal) lista.getJSONObject(0).getJSONArray("alunos").getJSONObject(i)
						.getJSONArray("nota").getJSONObject(2).get("NOTA")).doubleValue();
				int falta3 = (Integer) lista.getJSONObject(0).getJSONArray("alunos").getJSONObject(i)
						.getJSONArray("nota").getJSONObject(2).get("FALTAS");

				//Calcula média e faltas
				double media = ((nota1 + nota2 + nota3)/3);
				media = (Math.round(media * 100.0)/100.0);
				double falta = 100 - ((100 * falta1+falta2+falta3) / totalAulas);

				//Calcula situação so aluno
				String resulAlu;
				if (falta <= 70){
					resulAlu = "RF";
				}else if (media >= 7) {
					resulAlu = "AP";
				}else {
					resulAlu = "RM";
				}

				//Cria um aluno e armazena ele em Array
				Aluno aluno = new Aluno(totalAulas, aluCod, aluNome, nota1, falta1, nota2, falta2, nota3, falta3,falta,media,resulAlu);
				armazenaAluno.adicionaAluno(aluno);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		//Cria JSON para enviar
		JSONArray ja = new JSONArray();
		JSONObject mainObj = new JSONObject();

		for (int j = 1; j <= (armazenaAluno.consultaAlunos().length - 1); j++) {
			JSONObject jo = new JSONObject();
			jo.put("MEDIA", armazenaAluno.getAluno(j).getMedia());
			jo.put("RESULTADO", armazenaAluno.getAluno(j).getResulAlu());
			jo.put("COD_ALUNO", armazenaAluno.getAluno(j).getAluCod());
			jo.put("MEU_NOME", "André P. Martins");
			ja.put(jo);
		}
		mainObj.put("resultadoAluno", ja);

		String resultJson = mainObj.toString();
		System.out.println(resultJson);

		//Envia a Resposta ao Web Service
		try {
			String token2 = GeraToken.ToKenResultado();
			String resposta = EnviarResultado.Enviar(token2,resultJson);
			System.out.println(resposta);

		} catch (IOException e) {
			e.printStackTrace();
		}

		//Cria Relatório em PFD
		Document documentPDF = new Document();

		try {

			PdfWriter.getInstance(documentPDF, new FileOutputStream("Relatório Alunos.pdf"));
			documentPDF.open();
			documentPDF.setPageSize(PageSize.A4);

			//Adiciona Paragrafos
			Paragraph pTitulo = new Paragraph(new Phrase(20F , "Relatório Alunos", FontFactory.getFont(FontFactory.HELVETICA, 18F)));
			pTitulo.setAlignment(Element.ALIGN_CENTER);
			documentPDF.add(pTitulo);
			documentPDF.add(new Paragraph(" "));

			for (int i = 1; i <= (armazenaAluno.consultaAlunos().length - 1); i++) {

				documentPDF.add(new Paragraph("Nome do Aluno: " + armazenaAluno.getAluno(i).getAluNome()));
				documentPDF.add(new Paragraph("Notas:   " + armazenaAluno.getAluno(i).getNota1() + "   " + armazenaAluno.getAluno(i).getNota2() + "   " + armazenaAluno.getAluno(i).getNota3()));
				documentPDF.add(new Paragraph("Total de Faltas: " + armazenaAluno.getAluno(i).getFalta() + "%"));
				documentPDF.add(new Paragraph("Média: "+ armazenaAluno.getAluno(i).getMedia()));
				documentPDF.add(new Paragraph("Resultado: " + armazenaAluno.getAluno(i).getResulAlu()));
				documentPDF.add(new Paragraph(" "));
				documentPDF.add(new LineSeparator());
				documentPDF.add(new Paragraph(" "));
			}


		}catch (DocumentException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			documentPDF.close();
		}

	}
}