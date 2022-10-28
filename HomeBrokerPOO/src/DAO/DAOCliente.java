package DAO;
import Entities.Cliente;
import Entities.Conta;
import Entities.Enum.Usuario;

/**
 *
 * @author jp_te
 */
public class DAOCliente {
    
    private Cliente[] vetorAdm = new Cliente[5];
    private Cliente[] vetorComum = new Cliente[5];
    private Cliente[] vetorBolsa = new Cliente[5];
    
    public DAOCliente(){
        //Criação do cliente adm
        Cliente adm1 = new Cliente();
        adm1.setCpf("95550664003");
        adm1.setEndereco("Rua dos Bobos, 0");
        adm1.setLogin("adm1");
        adm1.setNome("João Paulo");
        adm1.setTelefone("34987641029");
        adm1.setSenha("123");
        adm1.setTipoUsuario(Usuario.ADM);
        Conta contaAdm = new Conta(adm1);
        adm1.setConta(contaAdm);
        
        Cliente adm2 = new Cliente();
        adm2.setCpf("72510999001");
        adm2.setEndereco("Rua dos Bobos, 10");
        adm2.setLogin("adm2");
        adm2.setNome("João Pedro");
        adm2.setTelefone("3488755301");
        adm2.setSenha("123");
        adm2.setTipoUsuario(Usuario.ADM);
        
        Cliente adm3 = new Cliente();
        adm3.setCpf("77328134054");
        adm3.setEndereco("Rua dos Bobos, 340");
        adm3.setLogin("adm3");
        adm3.setNome("Danete Danone");
        adm3.setTelefone("34976378219");
        adm3.setSenha("123");
        adm3.setTipoUsuario(Usuario.ADM);
        
        vetorAdm[0] = adm1;
        vetorAdm[1] = adm2;
        vetorAdm[2] = adm3;
        
        //Criação do cliente comum
        Cliente comum1 = new Cliente();
        comum1.setCpf("24520690005");
        comum1.setEndereco("Se essa rua fosse minha, 450");
        comum1.setLogin("comum1");
        comum1.setNome("Jojo Todyson");
        comum1.setTelefone("84933821382");
        comum1.setSenha("321");
        comum1.setTipoUsuario(Usuario.COMUM);
        Conta conta = new Conta(comum1);
        comum1.setConta(conta);
        
        
        Cliente comum2 = new Cliente();
        comum2.setCpf("24520690005");
        comum2.setEndereco("Se essa rua fosse minha, 450");
        comum2.setLogin("comum2");
        comum2.setNome("MC Carol");
        comum2.setTelefone("84933821382");
        comum2.setSenha("321");
        comum2.setTipoUsuario(Usuario.COMUM);
        Conta conta2 = new Conta(comum2);
        comum2.setConta(conta2);
        
        
        Cliente comum3 = new Cliente();
        comum3.setCpf("4123");
        comum3.setEndereco("Se essa rua fsosse minha, 450");
        comum3.setLogin("comum3");
        comum3.setNome("Roberto Carlos");
        comum3.setTelefone("84933821382");
        comum3.setSenha("321");
        comum3.setTipoUsuario(Usuario.COMUM);
        Conta conta3 = new Conta(comum3);
        comum3.setConta(conta3);
        
        vetorComum[0] = comum1;
        vetorComum[1] = comum2;
        vetorComum[2] = comum3;
        
        //Criação do cliente bolsa
        
        Cliente bolsa = new Cliente();
        bolsa.setCpf("51234");
        bolsa.setEndereco("Rua das lamentações, 90");
        bolsa.setLogin("bolsa1");
        bolsa.setNome("Ibovespa");
        bolsa.setTelefone("84933821334");
        bolsa.setSenha("567");
        bolsa.setTipoUsuario(Usuario.BOLSA);
        Conta contaBolsa = new Conta(bolsa);
        bolsa.setConta(contaBolsa);
        

        Cliente bolsa2 = new Cliente();
        bolsa2.setCpf("51234");
        bolsa2.setEndereco("Rua das lamentações, 90");
        bolsa2.setLogin("bolsa2");
        bolsa2.setNome("Ibovespa");
        bolsa2.setTelefone("84933821334");
        bolsa2.setSenha("567");
        bolsa2.setTipoUsuario(Usuario.BOLSA);
        Conta contaBolsa2 = new Conta(bolsa2);
        bolsa2.setConta(contaBolsa2);
        
        
        Cliente bolsa3 = new Cliente();
        bolsa3.setCpf("51234");
        bolsa3.setEndereco("Rua das lamentações, 90");
        bolsa3.setLogin("bolsa3");
        bolsa3.setNome("Ibovespa");
        bolsa3.setTelefone("84933821334");
        bolsa3.setSenha("567");
        bolsa3.setTipoUsuario(Usuario.BOLSA);
        Conta contaBolsa3 = new Conta(bolsa3);
        bolsa3.setConta(contaBolsa3);
        
        vetorBolsa[0] = bolsa;
        vetorBolsa[1] = bolsa2;
        vetorBolsa[2] = bolsa3;        
    }

    public Cliente[] getVetorAdm() {
        return vetorAdm;
    }

    public Cliente[] getVetorComum() {
        return vetorComum;
    }
    
    public Cliente[] getVetorBolsa() {
        return vetorBolsa;
    }
    
    public Cliente validarLogin(String[] loginSenha){
        for(int i = 0; i < vetorAdm.length; i++){
            if(vetorAdm[i] != null){
                System.out.println(vetorAdm[i]);
            }
        }
        for(int i = 0; i < vetorComum.length; i++){
            if(vetorComum[i] != null){
                System.out.println(vetorComum[i]);
            }
        }
        
        for(int i = 0; i < vetorAdm.length; i++){
            if(vetorAdm[i] != null && (loginSenha[0].equals(vetorAdm[i].getLogin()))){
                if(loginSenha[1].equals(vetorAdm[i].getSenha())){
                    return vetorAdm[i];
                }
            }
        }
        
        for(int i = 0; i < vetorComum.length; i++){
            if(vetorComum[i] != null && (loginSenha[0].equals(vetorComum[i].getLogin()))){
                if(loginSenha[1].equals(vetorComum[i].getSenha())){
                    return vetorComum[i];
                }
            }
        }
        
        for(int i = 0; i < vetorBolsa.length; i++){
            if(vetorBolsa[i] != null && (loginSenha[0].equals(vetorBolsa[i].getLogin()))){
                if(loginSenha[1].equals(vetorBolsa[i].getSenha())){
                    return vetorBolsa[i];
                }
            }
        }
        
        return null;
    }
    
    public int novaPosicao(Cliente[] vetor){
        for(int i = 0; i < vetorComum.length; i++){
            if(vetorComum[i] == null){
                return i;
            }
        }
        return -1;
    }
    
    public void criarCliente(String login, String senha, String nome, String cpf, String endereco, String telefone, String tipoUsuario){
        Cliente cliente = new Cliente();
        
        cliente.setCpf(cpf);
        cliente.setEndereco(endereco);
        cliente.setLogin(login);
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setSenha(senha);
        cliente.setTipoUsuario(Usuario.valueOf(tipoUsuario));
        
        if(tipoUsuario.equals("COMUM")){
            vetorComum[novaPosicao(vetorComum)] = cliente;
        }else if (tipoUsuario.equals("ADM")){
            vetorAdm[novaPosicao(vetorAdm)] = cliente;
        }else{
            vetorBolsa[novaPosicao(vetorBolsa)] = cliente;
        }
    }
}
