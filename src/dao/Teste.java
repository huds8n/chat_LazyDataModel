package dao;

import model.Cliente;

public class Teste {

	public static void main(String[] args) {
		HibernateDAO<Cliente, Integer> regHBR = new HibernateDAO<Cliente, Integer>(
				Cliente.class);
		regHBR.listaAll();
	}

}
