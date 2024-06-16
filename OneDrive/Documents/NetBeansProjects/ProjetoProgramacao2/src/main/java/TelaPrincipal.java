import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

        
public class TelaPrincipal extends JFrame{
    
    Operacoes operar = new Operacoes();
    ConexaoMysql conectar1 = new ConexaoMysql();
    java.sql.PreparedStatement pst = null;
    ResultSet rst = null;
    int res = 0;
    Connection con = null;
    
    JPanel painel1 = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel lblNomeFuncionario = new JLabel();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JLabel lblNivel = new JLabel();
    JLabel lblId = new JLabel();
    JTextField txtNome = new JTextField();
    JTextField txtApelido = new JTextField();
    JTextField txtId = new JTextField();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable tabela = new JTable();
    JComboBox combSexo = new JComboBox<>();
    JTextField txtNumeroEstudante = new JTextField();
    JComboBox combCurso = new JComboBox<>();
    JComboBox combPeriodo = new JComboBox<>();
    JComboBox combNivel = new JComboBox<>();
    JRadioButton rbEntrar = new JRadioButton();
    JRadioButton rbSair = new JRadioButton();
    JButton btnSalvar = new JButton();
    JButton btnApagar = new JButton();
    JButton btnAtualizar = new JButton();
    JButton btnLimpar = new JButton();
    JMenuBar menuBara = new JMenuBar();
    JMenu menu = new JMenu();
    JMenuItem menuItem1 = new JMenuItem();
    
    String nome;
    String nomeUsuario;
    String apelido;
    String sexo;
    String numeroEstudante;
    String curso;
    String periodo;
    String nivel;
    String nivelUsuario;
    String horaEntrada;
    String horaSaida;
    String data;
    String mov;
    int id;
    
 
    
    public TelaPrincipal(){
        
        this.setSize(1150, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        painel1.setBackground(new java.awt.Color(255, 255, 255));
        painel1.setLayout(null);

        jLabel1.setText("Nome");
        painel1.add(jLabel1);
        jLabel1.setBounds(10, 8, 40, 20);
        painel1.add(txtNome);
        txtNome.setBounds(10, 29, 181, 25);

        jLabel2.setText("Apelido");
        painel1.add(jLabel2);
        jLabel2.setBounds(210, 29, 50, 20);
        painel1.add(txtApelido);
        txtApelido.setBounds(265, 29, 120, 25);

        jLabel3.setText("Sexo");
        painel1.add(jLabel3);
        jLabel3.setBounds(434, 10, 31, 20);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "Numero de Estudante", "Nome", "Apelido", "Curso"
            }
        ));
        jScrollPane1.setViewportView(tabela);

        painel1.add(jScrollPane1);
        jScrollPane1.setBounds(550, 10, 573, 407);

        combSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        
        painel1.add(combSexo);
        combSexo.setBounds(434, 29, 50, 25);

        jLabel4.setText("Nurmero de Estudante");
        painel1.add(jLabel4);
        jLabel4.setBounds(10, 60, 150, 20);
        painel1.add(txtNumeroEstudante);
        txtNumeroEstudante.setBounds(10, 85, 102, 25);

        jLabel5.setText("Curso");
        painel1.add(jLabel5);
        jLabel5.setBounds(150, 60, 35, 20);

        combCurso.setModel(new DefaultComboBoxModel<>(new String[] { "EI", "EM", "EPM", "CA", "CAP" }));
        painel1.add(combCurso);
        combCurso.setBounds(150, 85, 55, 25);
       

        jLabel6.setText("Periodo");
        painel1.add(jLabel6);
        jLabel6.setBounds(230, 88, 50, 20);

        combPeriodo.setModel(new DefaultComboBoxModel<>(new String[] { "Laboral", "Pos Laboral" }));
        painel1.add(combPeriodo);
        combPeriodo.setBounds(280, 85, 100, 25);

        jLabel7.setText("Nivel");
        painel1.add(jLabel7);
        jLabel7.setBounds(395, 88, 30, 20);
        
        lblId.setText("ID");
        painel1.add(lblId);
        lblId.setBounds(434, 130, 15, 20);
        painel1.add(txtId);
        txtId.setBounds(460, 130, 80, 25);

        combNivel.setModel(new DefaultComboBoxModel<>(new String[] { "Primeiro", "Segundo", "Terceiro", "Quarto" }));
        painel1.add(combNivel);
        combNivel.setBounds(434, 85, 100, 25);

        rbEntrar.setText("Entrar");
        painel1.add(rbEntrar);
        rbEntrar.setBounds(10, 122, 100, 20);

        rbSair.setText("Sair");
        painel1.add(rbSair);
        rbSair.setBounds(10, 145, 100, 20);

        painel2.setBackground(new Color(0, 0, 255));
        painel2.setLayout(null);

        lblNomeFuncionario.setForeground(new Color(255, 255, 255));
        lblNomeFuncionario.setText(nomeUsuario);
        painel2.add(this.lblNomeFuncionario);
        lblNomeFuncionario.setBounds(180, 75, 213, 20);
        jLabel8.setForeground(new Color(255, 255, 255));
        jLabel8.setFont(new Font("Tahoma",1,14));
        jLabel8.setText("Nome Do Funcionario:");
        painel2.add(jLabel8);
        jLabel8.setBounds(10, 75, 180, 20);

        jLabel10.setForeground(new Color(255, 255, 255));
        jLabel10.setText("Nivel:");
        painel2.add(jLabel10);
        jLabel10.setBounds(366, 75, 35, 20);

        lblNivel.setForeground(new Color(255, 255, 255));
        lblNivel.setText(this.nivelUsuario);
        painel2.add(lblNivel);
        lblNivel.setBounds(410, 75, 100, 20);

        painel1.add(painel2);
        painel2.setBounds(10, 184, 530, 102);

        jPanel3.setBackground(new Color(0, 51, 255));
        jPanel3.setLayout(null);

        btnSalvar.setText("Salvar");
        jPanel3.add(btnSalvar);
        btnSalvar.setBounds(10, 10, 80, 25);

        btnApagar.setText("Apagar");
        jPanel3.add(btnApagar);
        btnApagar.setBounds(440, 10, 80, 25);

        btnAtualizar.setText("Atualizar");
        jPanel3.add(btnAtualizar);
        btnAtualizar.setBounds(345, 10, 85, 25);

        painel1.add(jPanel3);
        jPanel3.setBounds(10, 427, 530, 41);

        jPanel4.setBackground(new Color(0, 0, 255));
        jPanel4.setLayout(null);

        btnLimpar.setText("Limpar");
        jPanel4.add(btnLimpar);
        btnLimpar.setBounds(10, 10, 80, 25);

        painel1.add(jPanel4);
        jPanel4.setBounds(1020, 427, 100, 45);

        getContentPane().add(painel1);
        painel1.setBounds(0, 0, 1150, 500);

        menu.setText("Operacoes");

        menuItem1.setText("Cadastrar Usuarios");
        menu.add(menuItem1);
        menuItem1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                Telacadastro();
            }
        });

        menuBara.add(menu);

        this.setJMenuBar(menuBara);
   
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        btnSalvar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            salvar();
            preencherTabela();
        }
    });
        
        btnLimpar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent evt){
                txtNome.setText(null);
                txtApelido.setText(null);
                txtNumeroEstudante.setText(null);
                txtId.setText(null);
                btnSalvar.setEnabled(true);
            }
        });
        
        btnAtualizar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent evt){
                atualizar();
                preencherTabela();
                btnSalvar.setEnabled(true);
            }
        });
        
        btnApagar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                apagar();
                preencherTabela();
                btnSalvar.setEnabled(true);
            }
        });
        
        rbEntrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(rbSair.isSelected()){
                    rbSair.setSelected(false);
                    mov = "Entrada";
                    System.out.println(mov);
                } else{
                    mov = "Entrada";
                    System.out.println(mov);
                }
            }
        });
        
        rbSair.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(rbEntrar.isSelected()){
                    rbEntrar.setSelected(false);
                    mov = "Saida";
                    System.out.println(mov);
                } else{
                    mov = "Saida";
                    System.out.println(mov);
                }
            }
        });
        
        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                 int linha = tabela.getSelectedRow();
                 txtNumeroEstudante.setText(tabela.getValueAt(linha, 0).toString());
                 txtNome.setText(tabela.getValueAt(linha, 1).toString());
                 txtApelido.setText(tabela.getValueAt(linha, 2).toString());
                 btnSalvar.setEnabled(false);
                 obterDados();
                 
            }
        });
        
        preencherTabela();
        
    }
    
public void salvar() {
    nome = txtNome.getText();
    apelido = txtApelido.getText();
    sexo = combSexo.getSelectedItem().toString();
    numeroEstudante = txtNumeroEstudante.getText();
    curso = combCurso.getSelectedItem().toString();
    periodo = combPeriodo.getSelectedItem().toString();
    nivel = combNivel.getSelectedItem().toString();
    
    if (txtNome.getText().isEmpty() || txtApelido.getText().isEmpty() || txtNumeroEstudante.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos");
    } else {
        try (Connection con = conectar1.ConectaBD()) {
            if (operar.numeroEstudanteExiste(numeroEstudante)) {
                JOptionPane.showMessageDialog(this, "O número de estudante já existe ao salvar: " + numeroEstudante);
            } else {
                operar.cadastrar(nome, apelido, sexo, numeroEstudante, curso, periodo, nivel, horaEntrada, horaSaida, data);
                JOptionPane.showMessageDialog(this, "Cadastrado com Sucesso");
                txtNome.setText(null);
                txtApelido.setText(null);
                txtNumeroEstudante.setText(null);
                preencherTabela();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    preencherTabela();
}

public boolean numeroEstudanteExiste(Connection con, String numeroEstudante) throws SQLException {
    String sql = "SELECT * FROM registro WHERE numero_estudante = ?";
    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, numeroEstudante);
        try (ResultSet rst = pst.executeQuery()) {
            return rst.next();
        }
    }
}
    
    public void Telacadastro(){
        new TelaCadastrarFuncionarios().setVisible(true);
        this.dispose();
    }
    
public void apagar(){
        if(txtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Informe o ID");
        } else {
            int id = Integer.parseInt(txtId.getText());
            operar.apagarEstudantes(id);
            if(operar.getRes() == 1){
                JOptionPane.showMessageDialog(this, "Apagado com sucesso");
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao apagar");
            }
            preencherTabela(); // Atualiza a tabela após a exclusão
        }
    }
    
    public void atualizar(){
        if(txtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Informe o ID");
        } else{
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            String apelido = txtApelido.getText();
            String sexo = combSexo.getSelectedItem().toString();
            String numeroEstudante = txtNumeroEstudante.getText();
            String curso = combCurso.getSelectedItem().toString();
            String periodo = combPeriodo.getSelectedItem().toString();
            String nivel = combNivel.getSelectedItem().toString();
            operar.atualizarEstudantes(nome, apelido, sexo, numeroEstudante, curso, periodo, nivel, id);
            if(operar.getRes() == 1){
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso");
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao atualizar");
            }
            preencherTabela(); // Atualiza a tabela após a atualização
        }
    }

    
    public void preencherTabela(){
        DefaultTableModel modelo = new DefaultTableModel();
        tabela.setModel(modelo);
        modelo.addColumn("NUMERO ESTUDANTE");
        modelo.addColumn("NOME DO ESTUDANTE");
        modelo.addColumn("APELIDO");
        modelo.addColumn("CURSO");
        con = conectar1.ConectaBD();
        String sql = "select * from registro";            
        try {
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()){
               modelo.addRow(new Object[]{
                   rst.getString("numero_estudante"), 
                   rst.getString("nome"), 
                   rst.getString("apelido"), 
                   rst.getString("curso")});
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void obterDados(){
        con = conectar1.ConectaBD();
        String sql = "select * from registro where numero_estudante =?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtNumeroEstudante.getText());
            rst = pst.executeQuery();
            
            if(rst.next()){
                
                combSexo.setSelectedItem(rst.getString("sexo"));
                combPeriodo.setSelectedItem(rst.getString("periodo"));
                combCurso.setSelectedItem(rst.getString("curso"));
                combNivel.setSelectedItem(rst.getString("nivel")); 
                txtId.setText(rst.getString("id"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        new TelaPrincipal();
    }
    
}
