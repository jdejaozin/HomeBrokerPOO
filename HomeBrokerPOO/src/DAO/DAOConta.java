package DAO;

import Connection.ConnectionFactory;
import Entities.Ativos;
import Entities.Cliente;
import Entities.Conta;
import Entities.Enum.Usuario;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp_te
 */
public class DAOConta {
   
    private Connection connection = null;
    
    public DAOConta(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public Conta retornarConta(int idConta, Cliente cliente){
        Conta conta = new Conta(cliente);
        String sql = "select * from conta where id_conta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, idConta);
            ResultSet resultQuery;
            resultQuery = stmt.executeQuery();
            while (resultQuery.next()) {

                BigDecimal saldo = BigDecimal.valueOf(resultQuery.getDouble("saldo"));
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);
                
                conta.setSaldo(saldo);
                conta.setDataCriacao(dataCriacao);
                conta.setDataModificacao(dataAlteracao);
                
            }
            resultQuery.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conta;
    }
    
    public void criarConta(Cliente cliente){
        int idConta = 0;
        int idCliente = cliente.getId();
        
        String sqlInsertConta = "insert into conta "
                + "(id_cliente, saldo, data_criacao, data_alteracao)" 
                + " values (?,?,?,?)";

        
        try (PreparedStatement stmt = connection.prepareStatement(sqlInsertConta);){
            stmt.setInt(1, idCliente);
            stmt.setDouble(2, 20000.00);
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();
            System.out.println("Conta criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        String sqlSelectIdConta = "select * from conta where id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlSelectIdConta);){
            stmt.setInt(1, idCliente);
            ResultSet resultQuery;
            resultQuery = stmt.executeQuery();
            while (resultQuery.next()) {
                idConta = resultQuery.getInt("id_conta");
            }
            resultQuery.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sqlUpdateCliente = "update cliente set id_conta = ?, data_alteracao where id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlUpdateCliente);){
            stmt.setInt(1, idConta);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(3, idCliente);
            stmt.execute();
            System.out.println("Conta atrelada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //depositar(bolsa, BigDecimal.valueOf(500000.00));

    }
    
    public void depositar(Cliente cliente, BigDecimal valor){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().add(valor));
        
        //cliente.setDataModificacao(LocalDateTime.now());
    }
    
    public void sacar(Cliente cliente, BigDecimal valor){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
        
        //cliente.setDataModificacao(LocalDateTime.now());
    }
    
    public void pagar(Cliente cliente, BigDecimal valor, Cliente adm){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
        adm.getConta().setSaldo(adm.getConta().getSaldo().add(valor));
        
        //cliente.setDataModificacao(LocalDateTime.now());
        //adm.setDataModificacao(LocalDateTime.now());
    }
    
    public void transferir(Cliente clienteInicial, BigDecimal valor, Cliente clienteFinal){
        clienteInicial.getConta().setSaldo(clienteInicial.getConta().getSaldo().subtract(valor));
        clienteFinal.getConta().setSaldo(clienteFinal.getConta().getSaldo().add(valor));
        
        //clienteInicial.setDataModificacao(LocalDateTime.now());
        //clienteFinal.setDataModificacao(LocalDateTime.now());
    }
    
    /*public void comprarAtivos(Cliente cliente, Ativos ativo, String numAtivos){
        
        BigDecimal valor = ativo.getPrecoInicial().multiply(new BigDecimal(numAtivos));
        
        
        
        cliente.getConta().setAtivos(ativo);
    }
    
    public void venderAtivos(Cliente cliente, BigDecimal novoValor, Ativos ativoEscolhido){
        for(int i = 0; i < cliente.getConta().getAtivos().length; i++){
            if(cliente.getConta().getAtivos()[i] != null){
                if(cliente.getConta().getAtivos()[i].getId() == ativoEscolhido.getId()){
                    cliente.getConta().getAtivos()[i] = null;
                    ativoEscolhido.setPrecoUltimaVenda(novoValor);
                    cliente.getConta().setSaldo(cliente.getConta().getSaldo().add(novoValor));
                }
            }
        }
    }
    
    public void pagarDividendos(Cliente[] cliente){
        for(Cliente temp : cliente){
            if(temp != null){
                if(temp.getConta().getAtivos() != null){
                    for(int i = 0; i < temp.getConta().getAtivos().length; i++){
                        if(temp.getConta().getAtivos()[i] != null){
                            temp.getConta().setSaldo(temp.getConta().getSaldo().add(temp.getConta().getAtivos()[i].getPrecoInicial()));
                        }
                    }
                }
            }
        }
    }  */
    
}
