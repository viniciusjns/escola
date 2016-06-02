package DAO;

import java.util.List;

import entities.Aluno;

public interface InterfaceDAO {
	
	public void salvar(Object obj);
	
	public void atualizar(Object obj);
	
	public void excluir(Object obj);
	
	public Object buscarPorId(int id);
	
	public List<Object> buscarTodos();

}
