public class Aluno {
    private int idAluno;
    private String nome;
    private int idade;
    private int idCurso;

    public Aluno(int idAluno, String nome, int idade, int idCurso) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.idade = idade;
        this.idCurso = idCurso;
    }

    public Aluno(String nome, int idade, int idCurso) {
        this.nome = nome;
        this.idade = idade;
        this.idCurso = idCurso;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}
