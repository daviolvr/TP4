import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public void addAluno(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (nome, idade, id_curso) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, aluno.getNome());
                    pstmt.setInt(2, aluno.getIdade());
                    pstmt.setInt(3, aluno.getIdCurso());
                    pstmt.executeUpdate();
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }
    }

    public void removeAluno(int idAluno) throws SQLException {
        String sql = "DELETE FROM aluno WHERE id_aluno = ?";
        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, idAluno);
                    pstmt.executeUpdate();
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }
    }

    public void updateAluno(int idAluno, String nome, int idade, int idCurso) throws SQLException {
        String sql = "UPDATE aluno SET nome = ?, idade = ?, id_curso = ? WHERE id_aluno = ?";
        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, nome);
                    pstmt.setInt(2, idade);
                    pstmt.setInt(3, idCurso);
                    pstmt.setInt(4, idAluno);
                    pstmt.executeUpdate();
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }
    }


    public Aluno getAluno(int idAluno) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE id_aluno = ?";
        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, idAluno);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        return new Aluno(
                                rs.getInt("id_aluno"),
                                rs.getString("nome"),
                                rs.getInt("idade"),
                                rs.getInt("id_curso")
                        );
                    }
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }
        return null;
    }

    public List<Aluno> getAllAlunos() throws SQLException {
        String sql = "SELECT * FROM aluno";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql);
                     ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        alunos.add(new Aluno(
                                rs.getInt("id_aluno"),
                                rs.getString("nome"),
                                rs.getInt("idade"),
                                rs.getInt("id_curso")
                        ));
                    }
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }
        return alunos;
    }

    public void addAlunos(List<Aluno> alunos) throws SQLException {
        String sql = "INSERT INTO aluno (nome, idade, id_curso) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    for (Aluno aluno : alunos) {
                        pstmt.setString(1, aluno.getNome());
                        pstmt.setInt(2, aluno.getIdade());
                        pstmt.setInt(3, aluno.getIdCurso());
                        pstmt.addBatch();
                    }
                    pstmt.executeBatch();
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }
    }

    public List<Aluno> searchAlunosByName(String name) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE nome LIKE ?";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, "%" + name + "%");
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        alunos.add(new Aluno(
                                rs.getInt("id_aluno"),
                                rs.getString("nome"),
                                rs.getInt("idade"),
                                rs.getInt("id_curso")
                        ));
                    }
                }
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        }
        return alunos;
    }
}
