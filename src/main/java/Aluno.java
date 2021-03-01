
public class Aluno {
	private int totalAulas;
	private int aluCod;
	private String aluNome;

	private double nota1;
	private int falta1;
	private double nota2;
	private int falta2;
	private double nota3;
	private int falta3;
	
	private double media;
	private double falta;
	private String resulAlu;
	

	public Aluno(int totalAulas, int aluCod, String aluNome, double nota1, int falta1, double nota2, int falta2,
			double nota3, int falta3, double falta, double media, String resulAlu) {
		this.totalAulas = totalAulas;
		this.aluCod = aluCod;
		this.aluNome = aluNome;
		this.nota1 = nota1;
		this.falta1 = falta1;
		this.nota2 = nota2;
		this.falta2 = falta2;
		this.nota3 = nota3;
		this.falta3 = falta3;
		this.media = media;
		this.falta = falta;
		this.resulAlu = resulAlu;
	}
	
	public int getTotalAulas() {
		return totalAulas;
	}
	public void setTotalAulas(int totalAulas) {
		this.totalAulas = totalAulas;
	}
	public int getAluCod() {
		return aluCod;
	}
	public void setAluCod(int aluCod) {
		this.aluCod = aluCod;
	}
	public String getAluNome() {
		return aluNome;
	}
	public void setAluNome(String aluNome) {
		this.aluNome = aluNome;
	}
	public double getNota1() {
		return nota1;
	}
	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}
	public int getFalta1() {
		return falta1;
	}
	public void setFalta1(int falta1) {
		this.falta1 = falta1;
	}
	public double getNota2() {
		return nota2;
	}
	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}
	public int getFalta2() {
		return falta2;
	}
	public void setFalta2(int falta2) {
		this.falta2 = falta2;
	}
	public double getNota3() {
		return nota3;
	}
	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}
	public int getFalta3() {
		return falta3;
	}
	public void setFalta3(int falta3) {
		this.falta3 = falta3;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double getFalta() {
		return falta;
	}

	public void setFalta(double falta) {
		this.falta = falta;
	}

	public String getResulAlu() {
		return resulAlu;
	}

	public void setResulAlu(String resulAlu) {
		this.resulAlu = resulAlu;
	}

	
}
