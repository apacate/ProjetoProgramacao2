
import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Operacoes {

    ConexaoMysql conectar1 = new ConexaoMysql();
    PreparedStatement pst = null;
    ResultSet rst = null;
    int res = 0;
    Connection con = null;

    public void cadastrar(String nome, String apelido, String sexo, String numeroEstudante,
            String curso, String periodo, String nivel, String horaEntrada, String horaSaida, String data) {

        con = conectar1.ConectaBD();
        String sql = "insert into registro (nome,apelido,sexo,numero_estudante,curso,periodo,nivel,"
                + "hora_entrada,hora_saida,data) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, apelido);
            pst.setString(3, sexo);
            pst.setString(4, numeroEstudante);
            pst.setString(5, curso);
            pst.setString(6, periodo);
            pst.setString(7, nivel);
            pst.setString(8, horaEntrada);
            pst.setString(9, horaSaida);
            pst.setString(10, data);

            this.res = pst.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cadastrarFuncionarios(String nome, String apelido, String nivel, String usuario, String senha) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = conectar1.ConectaBD();
            String sql = "INSERT INTO login (nomeFuncionario, apelido, nivel, usuario, senha) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, apelido);
            pst.setString(3, nivel);
            pst.setString(4, usuario);
            pst.setString(5, senha);

            this.res = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fechar conexões e recursos
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean numeroEstudanteExiste(String numeroEstudante) {
        boolean existe = false;
        con = conectar1.ConectaBD();
        String sql = "SELECT * FROM registro WHERE numero_estudante = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, numeroEstudante);
            rst = pst.executeQuery();
            // Se houver algum resultado, significa que o número de estudante já existe
            if (rst.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fechar conexões e recursos
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return existe;
    }

    public void pesquisarEstudantes(int id) {
        con = conectar1.ConectaBD();
        String sql = "select * from registro where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            this.res = pst.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pesquisarEstudantes2(String nome) {
        con = conectar1.ConectaBD();
        String sql = "select * from registro where id like?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome + "%");
            this.res = pst.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagarEstudantes(int id) {
        con = conectar1.ConectaBD();
        String sql = "DELETE FROM registro WHERE numero_estudante=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            // Executa a deleção e verifica se afetou alguma linha
            if (pst.executeUpdate() > 0) {
                this.res = 1; // Define como 1 para indicar sucesso
            } else {
                this.res = 0; // Define como 0 para indicar falha
            }
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
            this.res = 0; // Define como 0 para indicar falha em caso de exceção
        } finally {
            // Fechar conexões e recursos
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void atualizarEstudantes(String nome, String apelido, String sexo, String numeroEstudante,
            String curso, String periodo, String nivel, int id) {
        con = conectar1.ConectaBD();
        String sql = "UPDATE registro SET nome=?, apelido=?, sexo=?, numero_estudante=?, curso=?, periodo=?, nivel=? WHERE numero_estudante=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, apelido);
            pst.setString(3, sexo);
            pst.setString(4, numeroEstudante);
            pst.setString(5, curso);
            pst.setString(6, periodo);
            pst.setString(7, nivel);
            pst.setInt(8, id);

            this.res = pst.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
            this.res = 0; // Define como 0 para indicar falha em caso de exceção
        } finally {
            // Fechar conexões e recursos
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void apagarFuncionarios(int id) {
        con = conectar1.ConectaBD();
        String sql = "delete from login where idLogin=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            this.res = pst.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizarFuncionarios(String nome, String apelido, String nivel, String usuario, String senha, int id) {
        con = conectar1.ConectaBD();
        String sql = "update login set nomeFuncionario=?,apelido=?,nivel=?,usuario=?,senha=? where idLogin=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(3, apelido);            
            pst.setString(4, nivel);
            pst.setString(5, usuario);
            pst.setString(6, senha);
            pst.setInt(7, id);

            this.rst = pst.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pesquisarFuncionarios(int id) {

        try {
            con = conectar1.ConectaBD();
            String sql = "SELECT * FROM login WHERE idLogin=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            this.rst = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fechar conexões e recursos
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ResultSet getRst() {
        return rst;
    }

    public void setRst(ResultSet rst) {
        this.rst = rst;
    }

    public int getRes() {
        return res;
    }

}
