package RN;

import java.util.List;

import DAO.EnderecoDAO;
import entities.Aluno;
import entities.Endereco;
import entities.Sexo;
import entities.Telefone;
import entities.Uf;
import util.DAOFactory;

public class EnderecoRN {
	
	private EnderecoDAO enderecoDAO = new EnderecoDAO();
	
	public EnderecoRN() {
		
		//this.enderecoDAO = DAOFactory.criarEnderecoDAO();
		
	}
	
	public void salvar(Endereco endereco) {
		
		Integer id = endereco.getIdEndereco();
		
		if(id == 0 || id == null) {
			
			this.enderecoDAO.salvar(endereco);
			
		} else {
			
			this.enderecoDAO.atualizar(endereco);
			
		}
		
	}
	
	public void excluir(Endereco endereco) {
		
		this.enderecoDAO.excluir(endereco);
		
	}
	
	public List<Endereco> buscarTodos() {
		
		return (List) this.enderecoDAO.buscarTodos();
		
	}
	
	//	
	public Endereco buscarEnderecoPorId(int id) {
		
		return this.enderecoDAO.buscarEnderecoPorId(id);
		
	}
	
	public List<Uf> buscarUf() {
		
		return this.enderecoDAO.buscarUf();
		
	}
	
	public Uf buscarUfPorId(int id) {
		
		return this.enderecoDAO.buscarUfPorId(id);
		
	}
}
