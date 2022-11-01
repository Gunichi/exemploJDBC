package dao;
import bens.Pessoa;
import bens.Pessoa;
import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rafsantos
 */
public class DaoPessoa {
    public DaoPessoa() {
    }
    //GRAVAR
    //====================================
    public boolean grava(Pessoa pessoa) {
        try {
            PreparedStatement comandoSQL;
            comandoSQL = Conexao.getInstance().prepareStatement("insert into Pessoa (id, nome, email, telefone) values(?,?,?,?)");
            comandoSQL.setLong(1, pessoa.getId());
            comandoSQL.setString(2, pessoa.getNome());
            comandoSQL.setString(3, pessoa.getEmail());
            comandoSQL.setString(4, pessoa.getTelefone());
            comandoSQL.execute();
            Conexao.getInstance().commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //ALTERAR
    //====================================
    public boolean atualiza(Pessoa pessoa) {
        try {
            PreparedStatement comandoSQL;
            comandoSQL = Conexao.getInstance().prepareStatement("update Pessoa set nome = ? where id = ? ");
            comandoSQL.setString(1, pessoa.getNome());
            comandoSQL.setString(2, pessoa.getEmail());
            comandoSQL.setString(3, pessoa.getTelefone());
            comandoSQL.execute();
            Conexao.getInstance().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //DELETAR
    //=====================================
    public boolean deleta(Pessoa pessoa) {
        try {
            PreparedStatement comandoSQL;
            comandoSQL = Conexao.getInstance().prepareStatement("delete from Pessoa where id = ?");
            comandoSQL.setLong(1, pessoa.getId());
            comandoSQL.execute();
            Conexao.getInstance().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //LISTAR TODOS
    //=====================================
    public List lista() {
        try {
            PreparedStatement comandoSQL;
            comandoSQL = Conexao.getInstance().prepareStatement("select * from Pessoa");
                    ResultSet rs = comandoSQL.executeQuery();
            List arlPessoas = new ArrayList();
            while (rs.next()) {
                Pessoa pess = new Pessoa();
                pess.setId(rs.getLong("id"));
                pess.setNome(rs.getString("nome"));
                pess.setEmail(rs.getString("email"));
                pess.setTelefone(rs.getString("telefone"));
                arlPessoas.add(pess);
            }
            return arlPessoas;
            // conexao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //CONSULTA ESPECIFICA POR CAMPOS
    //=====================================
    public Pessoa consulta(int pk) {
        try {
            PreparedStatement comandoSQL;
            comandoSQL = Conexao.getInstance().prepareStatement("select * from Pessoa where id=?");
            comandoSQL.setInt(1, pk);
            ResultSet rs = comandoSQL.executeQuery();
            if (rs.next()) {
                Pessoa pess = new Pessoa();
                pess.setId(rs.getLong("id"));
                pess.setNome(rs.getString("nome"));
                pess.setEmail(rs.getString("email"));
                pess.setTelefone(rs.getString("telefone"));
                return pess;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

//Parado no 8 psso - PAG 19