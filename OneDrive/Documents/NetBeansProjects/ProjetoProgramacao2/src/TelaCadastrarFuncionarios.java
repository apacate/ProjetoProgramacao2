
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Conexao;

public class TelaCadastrarFuncionarios extends JFrame implements ActionListener {

    Operacoes operar = new Operacoes();
    JPanel painel = new JPanel();
    JScrollPane scrol = new JScrollPane();
    JTable tabela = new JTable();
    JTextField txtNome = new JTextField();
    //JTextField txtApelido = new JTextField();
    JTextField txtUsuario = new JTextField();
    JTextField txtSenha = new JTextField();
    JTextField txtId = new JTextField();
    JComboBox<String> combNivel = new JComboBox<>();
    JLabel label3 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label1 = new JLabel();
    JLabel label4 = new JLabel();
    JLabel label5 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JPanel painel2 = new JPanel();
    JButton btnSalvar = new JButton("Salvar");
    JButton btnApagar = new JButton("Apagar");
    JButton btnAtualizar = new JButton("Atualizar");
    JButton btnVoltar = new JButton("Voltar");
    JTextField txtNovaSenha = new JTextField();
    java.sql.PreparedStatement pst = null;
    ResultSet rst = null;
    int res = 0;
    Connection con = null;

    String nome;
    String apelido;
    String nivel;
    String usuario;
    String senha;
    int id;

    public TelaCadastrarFuncionarios() {
        this.setSize(680, 380);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        painel.setBackground(new Color(255, 255, 255));
        painel.setLayout(null);

        scrol.setViewportView(tabela);
        painel.add(scrol);
        scrol.setBounds(10, 140, 521, 155);
        painel.add(txtNome);
        txtNome.setBounds(10, 50, 180, 25);

        combNivel.setModel(new DefaultComboBoxModel<>(new String[]{"Admin", "Superivisor", "Tecnico"}));
        painel.add(combNivel);
        combNivel.setBounds(438, 50, 94, 25);

        label3.setText("Nivel");
        painel.add(label3);
        label3.setBounds(400, 53, 30, 20);

        label2.setText("Apelido");
        painel.add(label2);
        label2.setBounds(200, 50, 50, 20);

        //label1.setText("Nome");
        //painel.add(label1);
        //label1.setBounds(10, 28, 50, 20);
        //painel.add(txtApelido);
        //txtApelido.setBounds(250, 50, 141, 25);
        label4.setText("CADASTRAR FUNCIONARIOS");
        painel.add(label4);
        label4.setBounds(490, 10, 170, 13);
        painel.add(txtUsuario);
        txtUsuario.setBounds(65, 90, 125, 25);

        label5.setText("Usuario");
        painel.add(label5);
        label5.setBounds(10, 90, 50, 20);

        jLabel7.setText("Senha");
        painel.add(jLabel7);
        jLabel7.setBounds(220, 90, 80, 20);

        painel.add(txtSenha);
        txtSenha.setBounds(280, 90, 80, 25);

        painel2.setBackground(new Color(0, 0, 255));
        painel2.setLayout(null);

        painel2.add(btnSalvar);
        btnSalvar.setBounds(10, 10, 90, 25);
        btnSalvar.addActionListener(this);

        painel2.add(btnApagar);
        btnApagar.setBounds(10, 41, 90, 25);
        btnApagar.addActionListener(this);

        jLabel6.setText("ID");
        painel.add(jLabel6);
        jLabel6.setBounds(585, 50, 15, 20);
        painel.add(txtId);
        txtId.setBounds(605, 50, 55, 25);

        painel2.add(btnAtualizar);
        btnAtualizar.setBounds(10, 72, 90, 25);
        btnAtualizar.addActionListener(this);

        painel2.add(btnVoltar);
        btnVoltar.setBounds(10, 120, 90, 25);
        btnVoltar.addActionListener(this);

        painel.add(painel2);
        painel2.setBounds(550, 140, 110, 150);

        this.add(painel);
        painel.setBounds(0, 0, 680, 340);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int linha = tabela.getSelectedRow();
                txtId.setText(tabela.getValueAt(linha, 0).toString());
                txtNome.setText(tabela.getValueAt(linha, 1).toString());
                //txtApelido.setText(tabela.getValueAt(linha, 2).toString());
            }
        });

    }

    public void salvar() {
        nome = txtNome.getText();
        //apelido = txtApelido.getText();
        nivel = combNivel.getSelectedItem().toString();
        usuario = txtUsuario.getText();
        senha = txtSenha.getText();

        if (txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos campos");
        } else {
            Conexao.conector();
            operar.cadastrarFuncionarios(nome, nivel, usuario, senha);
            if (operar.getRes() == 1) {
                JOptionPane.showMessageDialog(this, "Cadastrado com sucesso");
                txtNome.setText(null);
                //txtApelido.setText(null);
                txtUsuario.setText(null);
            }
        }
        // Preencher tabela após salvar
        preencherTabela();
    }

    public void apagar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "informe o id do funcionario");
        } else {
                       Conexao.conector();
            id = Integer.parseInt(txtId.getText());
            operar.apagarFuncionarios(id);
            txtNome.setText(null);
            //txtApelido.setText(null);
            txtUsuario.setText(null);
            txtSenha.getText();
            if (operar.getRes() == 1) {
                JOptionPane.showMessageDialog(this, "Apagado com sucesso");
            }
        }
        preencherTabela();
    }

    public void atualizar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "informe o id do funcionario");
        } else {
            nome = txtNome.getText();
            // apelido = txtApelido.getText();
            nivel = combNivel.getSelectedItem().toString();
            usuario = txtUsuario.getText();
            senha = txtSenha.getText();
            id = txtId.getX();
                       Conexao.conector();
            operar.atualizarFuncionarios(nome, nivel, usuario, senha, id);
            txtNome.setText(null);
            // txtApelido.setText(null);
            txtUsuario.setText(null);
            txtSenha.setText(null);
        }
        preencherTabela();
    }

    public void preencherTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        tabela.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("NOME DO FUNCIONARIO");
        modelo.addColumn("APELIDO");
        con = Conexao.conector();
        String sql = "select * from login";
        try {
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
            while (rst.next()) {
                modelo.addRow(new Object[]{rst.getString("idLogin"), rst.getString("nome")});
 }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Fechar conexões e recursos
            if (rst != null) {
                try {
                    rst.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void voltar() {
        new TelaPrincipal();
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVoltar) {
            voltar();
            preencherTabela();
        } else if (e.getSource() == btnAtualizar) {
            atualizar();
            preencherTabela();
        } else if (e.getSource() == btnSalvar) {
            salvar();
            preencherTabela();
        } else if (e.getSource() == btnApagar) {

            apagar();
            preencherTabela();
        }

    }

    public static void main(String[] args) {
        new TelaCadastrarFuncionarios();
    }

}
