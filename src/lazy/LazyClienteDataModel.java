package lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Cliente;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dao.ClienteDAO;
import dao.HibernateDAO;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real
 * datasource like a database.
 */
public class LazyClienteDataModel extends LazyDataModel<Cliente> {

	ClienteDAO clienteDao;
	HibernateDAO<Cliente, Integer> regHBR;
	private static final long serialVersionUID = 1L;
	List<Cliente> data;
	Integer dataSize;

	public LazyClienteDataModel() {
		clienteDao = new ClienteDAO();
		
		data = new ArrayList<Cliente>();
		dataSize = clienteDao.countClientesTotal();
	}

	@Override
	public Cliente getRowData(String rowKey) {
		Integer id = Integer.valueOf(rowKey);
		for (Cliente car : data) {
			if (car.getCodCliente() == id)
				return car;
		}

		return null;
	}

	@Override
	public Object getRowKey(Cliente cliente) {
		return cliente.getCodCliente();
	}

	@Override
	public List<Cliente> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		regHBR = new HibernateDAO<Cliente, Integer>(Cliente.class);
		// rowCount
		this.setRowCount(dataSize);
		// paginate
		if (dataSize > pageSize) {
			try {
				System.out.println("Paginando !!! de : " + first + " Até: "
						+ (first + pageSize));
				data = regHBR.pagina(first, pageSize);
				return data;
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}
}