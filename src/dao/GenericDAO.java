package dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
	public boolean inclui(T registro);

	public boolean exclui(T registro);

	public boolean altera(T registro);

	public Object consulta(ID id);

	public List<T> listaTudo();

	public List<T> pagina(int inicio, int quantia);

	public List<T> listaLike(String nomeColuna, String texto);
}
