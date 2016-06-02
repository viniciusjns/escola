package model.aluno;

import java.util.List;

import pojo.Aluno;
import pojo.Sexo;
import pojo.Uf;
import util.DAOFactory;

public class AlunoRN {
	
	private AlunoDAO alunoDAO;
	
	public AlunoRN() {
		
		this.alunoDAO = DAOFactory.criarAlunoDAO();
		
	}
	
	public void salvar(Aluno aluno) {
		
		Integer id = aluno.getIdPessoa();
		
		if(id == 0 || id == null) {
			
			this.alunoDAO.salvar(aluno);
			
		} else {
			
			this.alunoDAO.atualizar(aluno);
			
		}
		
	}
	
	public void excluir(Aluno aluno) {
		
		this.alunoDAO.excluir(aluno);
		
	}
	
	public List<Aluno> buscarTodos() {
		
		return (List) this.alunoDAO.buscarTodos();
		
	}
	
	
	//
	public List<Sexo> buscarSexo() {
		
		return this.alunoDAO.buscarSexo();
		
	}
	
	public Sexo buscarSexoPorId(int id) {
		
		return this.alunoDAO.buscarSexoPorId(id);
	}
	
	public List<Uf> buscarUf() {
		
		return (List) this.alunoDAO.buscarUf();
		
	}
	
	public Uf buscarUfPorId(int id) {
		
		return this.alunoDAO.buscarUfPorId(id);
		
	}

}
