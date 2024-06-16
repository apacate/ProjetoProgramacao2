
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

public class TelaCadastrarFuncionarios extends JFrame implements ActionListener {

    ConexaoMysql conectar1 = new ConexaoMysql();
    Operacoes operar = new Operacoes();
    JPanel painel = new JPanel();
    JScrollPane scrol = new JScrollPane();
    JTable tabela = new JTable();
    JTextField txtNome = new JTextField();
    JTextField txtApelido = new JTextField();
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

        combNivel.setModel(new DefaultComboBoxModel<>(new String[]{"", "Admin", "Superivisor", "Tecnico"}));
        painel.add(combNivel);
        combNivel.setBounds(438, 50, 94, 25);

        label3.setText("Nivel");
        painel.add(label3);
        label3.setBounds(400, 53, 30, 20);

        label2.setText("Apelido");
        painel.add(label2);
        label2.setBounds(200, 50, 50, 20);

        label1.setText("Nome");
        painel.add(label1);
        label1.setBounds(10, 28, 50, 20);
        painel.add(txtApelido);
        txtApelido.setBounds(250, 50, 141, 25);
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
        txtId.setEditable(false); // ID é auto incremento

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
                txtApelido.setText(tabela.getValueAt(linha, 2).toString());
                combNivel.setSelectedItem(tabela.getValueAt(linha, 3).toString());
                txtUsuario.setText(tabela.getValueAt(linha, 4).toString());
                txtSenha.setText(tabela.getValueAt(linha, 5).toString());
            }
        });

        // Preencher tabela ao iniciar a janela
        preencherTabela();
    }

    public void salvar() {
        nome = txtNome.getText();
        apelido = txtApelido.getText();
        nivel = combNivel.getSelectedItem().toString();
        usuario = txtUsuario.getText();
        senha = txtSenha.getText();

        if (txtNome.getText().isEmpty() || txtApelido.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos campos");
        } else {
            operar.cadastrarFuncionarios(nome, apelido, nivel, usuario, senha);
            if (operar.getRes() == 1) {
                JOptionPane.showMessageDialog(this, "Cadastrado com sucesso");
                limparCampos();
            }
        }
        preencherTabela();
    }

    public void apagar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "informe o id do funcionario");
        } else {
            con = conectar1.ConectaBD();
            id = Integer.parseInt(txtId.getText());
            operar.apagarFuncionarios(id);
            if (operar.getRes() == 1) {
                JOptionPane.showMessageDialog(this, "Apagado com sucesso");
                limparCampos();
            }
        }
        preencherTabela();
    }

    public void atualizar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "informe o id do funcionario");
        } else {
            nome = txtNome.getText();
            apelido = txtApelido.getText();
            nivel = combNivel.getSelectedItem().toString();
            usuario = txtUsuario.getText();
            senha = txtSenha.getText();
            id = Integer.parseInt(txtId.getText());
            operar.atualizarFuncionarios(nome, apelido, nivel, usuario, senha, id);
            if (operar.getRes() == 1) {
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso");
                limparCampos();

            }
        }
        preencherTabela();
    }

    public void preencherTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        tabela.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("NOME DO FUNCIONARIO");
        modelo.addColumn("APELIDO");
        modelo.addColumn("NIVEL");
        modelo.addColumn("USUARIO");
        modelo.addColumn("SENHA");
        con = conectar1.ConectaBD();
        String sql = "SELECT idLogin, nomeFuncionario, apelido, nivel, usuario, senha FROM login;";
        try {
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
            while (rst.next()) {
                modelo.addRow(new Object[]{
                    rst.getString("idLogin"),
                    rst.getString("nomeFuncionario"),
                    rst.getString("apelido"),
                    rst.getString("nivel"),
                    rst.getString("usuario"),
                    rst.getString("senha")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVoltar) {
             new TelaPrincipal();
                   this.dispose();
        } else if (e.getSource() == btnAtualizar) {
            atualizar();
        } else if (e.getSource() == btnSalvar) {
            salvar();
        } else if (e.getSource() == btnApagar) {
            apagar();
        }
    }

    private void limparCampos() {
        txtId.setText(null);
        txtNome.setText(null);
        txtApelido.setText(null);
        txtUsuario.setText(null);
        txtSenha.setText(null);
        combNivel.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new TelaCadastrarFuncionarios();
    }
}
