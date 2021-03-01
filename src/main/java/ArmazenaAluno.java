public class ArmazenaAluno {
	private Aluno alunos[] = new Aluno[1];

	public Aluno[] consultaAlunos() {
		return alunos;
	}

	public Aluno getAluno(int aluCod) {
		for (int i = 0; i < alunos.length; i++) {
			if (alunos[i].getAluCod() == aluCod) {
				return alunos[i]; 
			}
		}
		return null; 
	}


	public void adicionaAluno(Aluno aluno) {
		alunos = expandeArray(alunos); 
		int i; 
		for (i = 0; i < alunos.length; i++) {
			if (alunos[i] == null) {
				alunos[i] = aluno;
				break;
			}
		}
	}


	private Aluno[] expandeArray(Aluno alunos[]) {
		Aluno novoAlu[] = new Aluno[alunos.length + 1];
		for (int i = 0; i < alunos.length; i++) {
			novoAlu[i] = alunos[i]; 
		}

		return novoAlu; 
	}


}
