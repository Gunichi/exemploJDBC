package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexao {
    // atributos
    private static Connection conexao = null;
    //esta é a variável fonte recebe o mesmo nome da base de dados
    //criada no postgresql
    private String fonte = "MiniCurso";
    //Conexao Para a Base de Dados do PostgresSQL utilizando JDBC
    private Conexao() {
        try {
            //Driver para fazer conexao com um Banco postgresql
            Class.forName("org.postgresql.Driver");
            //comando para fazer conexao via JDBC com um banco postgresql
            //sendo informado o servidor e sua porta, no caso localhost na porta 5432
            // + o nome da base de dados, o usuario e a senha.
            conexao = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/" + fonte, "usuario do banco", "senha do banco");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro de class não encontrada!!!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro na conexao com o banco de dados!!!");
        }
    }
    public static Connection getInstance() {
        if (conexao == null) {
            new Conexao();
        }
        return conexao;
    }
}

