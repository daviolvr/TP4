import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();

        try {
            Aluno aluno = new Aluno("Davi Alves", 20, 1);
            alunoDAO.addAluno(aluno);

            alunoDAO.updateAluno(21, "Davi de Oliveira", 21, 1);

            Aluno buscado = alunoDAO.getAluno(21);
            if (buscado != null) {
                System.out.println("Aluno buscado: " + buscado.getNome());
            } else {
                System.out.println("Aluno não encontrado.");
            }

            alunoDAO.removeAluno(21);

            List<Aluno> alunos = List.of(
                    new Aluno("Beatriz Vasconcelos", 22, 1),
                    new Aluno("Pedro Silva", 23, 2)
            );
            alunoDAO.addAlunos(alunos);

            List<Aluno> alunosEncontrados = alunoDAO.searchAlunosByName("Beatriz");
            for (Aluno a : alunosEncontrados) {
                System.out.println("LIKE: " + a.getNome());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

