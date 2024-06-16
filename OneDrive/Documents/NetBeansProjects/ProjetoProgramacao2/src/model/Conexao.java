package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/programacao2";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection conector() {
        Connection conexao = null;
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            // Em vez de exibir a exceção, você pode registrar em um logger ou usar ex.printStackTrace()
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados:\n" + ex.getMessage());
        } finally {
            // Fechando a conexão no bloco finally para garantir que seja fechada, mesmo se ocorrer uma exceção
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    // Se ocorrer um erro ao fechar a conexão, registre ou imprima a exceção
                    ex.printStackTrace();
                }
            }
        }
        return conexao;
    }
}
