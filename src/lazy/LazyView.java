package lazy;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Cliente;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
 
@ManagedBean(name="clienteLazyView")
@ViewScoped
public class LazyView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<Cliente> lazyModel;
     
    private Cliente clienteSelecionado;
     
  
     
    @PostConstruct
    public void init() {
        lazyModel = new LazyClienteDataModel();
    }
 

     
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Cliente Selected", ((Cliente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }



	public LazyDataModel<Cliente> getLazyModel() {
		return lazyModel;
	}



	public void setLazyModel(LazyDataModel<Cliente> lazyModel) {
		this.lazyModel = lazyModel;
	}



	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}



	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
}