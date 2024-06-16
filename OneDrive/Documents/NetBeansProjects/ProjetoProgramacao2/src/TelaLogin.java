import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexao;

public class TelaLogin extends JFrame implements ActionListener {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet res = null;

    JPanel painel = new JPanel();
    JPanel painel2 = new JPanel();
    JTextField t_usuario = new JTextField();
    JPasswordField t_senha = new JPasswordField();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JButton bt_sair = new JButton();
    JButton bt_entrar = new JButton();

    public TelaLogin() {
        this.setSize(482, 315);
        this.setLocationRelativeTo(null);
        this.add(painel);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel.setLayout(null);
        painel.add(t_usuario);
        painel.setBackground(new Color(255, 255, 255));
        t_usuario.setBounds(62, 91, 257, 30);
        t_senha.setBounds(62, 174, 257, 30);
        painel.add(t_senha);

        jLabel1.setFont(new Font("Tahoma", 0, 24));
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setText("LOGIN");
        jLabel1.setBounds(5, 5, 70, 29);
        painel2.add(jLabel1);

        painel2.setLayout(null);
        painel2.setBackground(new Color(10, 10, 255));
        painel2.setBounds(385, 0, 80, 42);
        painel.add(painel2);

        jLabel2.setText("USUARIO");
        jLabel2.setBounds(62, 68, 70, 13);
        painel.add(jLabel2);

        jLabel3.setText("SENHA");
        jLabel3.setBounds(62, 151, 50, 13);
        painel.add(jLabel3);

        bt_entrar.setText("ENTRAR");
        bt_entrar.setBounds(62, 235, 88, 35);
        bt_entrar.addActionListener(this);
        painel.add(bt_entrar);

        bt_sair.setText("SAIR");
        bt_sair.setBounds(255, 235, 67, 35);
        bt_sair.addActionListener(this);
        painel.add(bt_sair);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt_entrar) {
            con = Conexao.conector();
            try {
                String sql = "select * from login where usuario=? and senha=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, t_usuario.getText());
                pst.setString(2, new String(t_senha.getPassword()));
                res = pst.executeQuery();
                
                if (res.next()) {
                    JOptionPane.showMessageDialog(null, "Bem vindo ao sistema");
                    new TelaPrincipal();
                } else {
                    JOptionPane.showMessageDialog(null, "senha ou usuario ivalido");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            } finally {
                try {
                    if (res != null) res.close();
                    if (pst != null) pst.close();
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (e.getSource() == bt_sair) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new TelaLogin().setVisible(true);
    }
}
