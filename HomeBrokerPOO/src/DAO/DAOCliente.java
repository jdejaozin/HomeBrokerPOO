package DAO;
import Connection.ConnectionFactory;
import Entities.Cliente;
import Entities.Conta;
import Entities.Enum.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jp_te
 */
public class DAOCliente {
    
    private final DAOConta daoConta = new DAOConta();
    private Cliente[] vetorComum = new Cliente[5];
    private Connection connection = null;
    
    
    public DAOCliente(){
        this.connection = new ConnectionFactory().getConnection();
        
        /*
        //Criação do cliente comum
        Cliente comum1 = new Cliente();
        comum1.setCpf("24520690005");
        comum1.setEndereco("Se essa rua fosse minha, 450");
        comum1.setLogin("comum1");
        comum1.setNome("Jojo Todyson");
        comum1.setTelefone("84933821382");
        comum1.setSenha("321");
        comum1.setTipoUsuario(Usuario.COMUM);
        //setei as data aqui tbm
        comum1.setDataCriacao(LocalDateTime.now());
        comum1.setDataModificacao(LocalDateTime.now());
        daoConta.criarConta(comum1, bolsa);

        Cliente comum2 = new Cliente();
        comum2.setCpf("24520690005");
        comum2.setEndereco("Se essa rua fosse minha, 450");
        comum2.setLogin("comum2");
        comum2.setNome("MC Carol");
        comum2.setTelefone("84933821382");
        comum2.setSenha("321");
        comum2.setTipoUsuario(Usuario.COMUM);
        //setei as data aqui tbm
        comum2.setDataCriacao(LocalDateTime.now());
        comum2.setDataModificacao(LocalDateTime.now());
        daoConta.criarConta(comum2, bolsa);

        Cliente comum3 = new Cliente();
        comum3.setCpf("4123");
        comum3.setEndereco("Se essa rua fsosse minha, 450");
        comum3.setLogin("comum3");
        comum3.setNome("Roberto Carlos");
        comum3.setTelefone("84933821382");
        comum3.setSenha("321");
        comum3.setTipoUsuario(Usuario.COMUM);
        //setei as data aqui tbm
        comum3.setDataCriacao(LocalDateTime.now());
        comum3.setDataModificacao(LocalDateTime.now());
        daoConta.criarConta(comum3, bolsa);
        
        vetorComum[0] = comum1;
        vetorComum[1] = comum2;
        vetorComum[2] = comum3;*/
  
    }

    
    public Cliente validarLogin(String[] loginSenha){
        List<Cliente> clientes = getClientes();
        
        for(Cliente cliente : clientes){
            if(cliente.getLogin().equals(loginSenha[0])){
                if(cliente.getSenha().equals(loginSenha[1])){
                    return cliente;
                }
            }
        }
        return null;
    }
    
    public List<Cliente> getClientes(){
        List<Cliente> clientes = new ArrayList<>();
        String sql = "select * from cliente";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){

            ResultSet resultQuery;
            resultQuery = stmt.executeQuery();

            while (resultQuery.next()) {
                int id = resultQuery.getInt("id_cliente");
                String login = resultQuery.getString("login");
                String senha = resultQuery.getString("senha");
                String nome = resultQuery.getString("nome");
                String cpf= resultQuery.getString("cpf");
                String endereco = resultQuery.getString("endereco");
                String telefone = resultQuery.getString("telefone");
                int conta = resultQuery.getInt("id_conta");
                String tipoUsuario = resultQuery.getString("tipo");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);

                Cliente clienteResult = new Cliente();
                clienteResult.setId(id);
                clienteResult.setLogin(login);
                clienteResult.setSenha(senha);
                clienteResult.setNome(nome);
                clienteResult.setCpf(cpf);
                clienteResult.setEndereco(endereco);
                clienteResult.setTelefone(telefone);
                clienteResult.setTipoUsuario(Usuario.valueOf(tipoUsuario));
                clienteResult.setDataCriacao(dataCriacao);
                clienteResult.setDataModificacao(dataAlteracao);
                if(conta != 0){
                    clienteResult.setConta(daoConta.retornarConta(conta, clienteResult));
                }
                
                
                clientes.add(clienteResult);
            }
            resultQuery.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
    
    public void criarCliente(String login, String senha, String nome, String cpf, 
            String endereco, String telefone, String tipoUsuario){
        String sql = "insert into cliente "
                + "(login, senha, nome, cpf, endereco, telefone, tipo, data_criacao, data_alteracao)" 
                + " values (?,?,?,?,?,?,?,?,?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.setString(3, nome);
            stmt.setString(4, cpf);
            stmt.setString(5, endereco);
            stmt.setString(6, telefone);
            stmt.setString(7, tipoUsuario);
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();

            System.out.println("Cliente inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void alterarCliente(String login, String senha, String nome, String cpf, 
            String endereco, String telefone, int id){
        String sql = "update cliente set "
                + "login = ?, senha = ?, nome = ?, cpf = ?, endereco = ?, telefone = ?, data_alteracao = ?" 
                + "where id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.setString(3, nome);
            stmt.setString(4, cpf);
            stmt.setString(5, endereco);
            stmt.setString(6, telefone);
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(8, id);
            stmt.execute();

            System.out.println("Cliente alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Não sei se precisa, se precisar já tá aqui
    public void removerCliente(int id){
        String sql = "delete from cliente where id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Cliente excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
