package br.com.byamada.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.byamada.bean.Printer;
import br.com.byamada.conexao.ConexaoHibernate;

public class PrinterDAO {
	
	private Session session;
	
	public void salvar(Printer printer) {
		
		session = ConexaoHibernate.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = session.beginTransaction();
			session.save(printer);
			transacao.commit();
			
		} catch (Exception e) {
			transacao.rollback();
		} finally {
			session.close();
		}
			
	}
	
	public void alterar() {
		
	}
	
	public void excluir() {
		
	}
	
	public Printer buscar(long id) {
		
		Printer printer = new Printer();
		
		return printer;
		
	}
	
	public List<Printer> listar(){
		
		return null;
	}
	
	

}
