package chat;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "testeBean")
@RequestScoped
public class TesteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numero;

	public TesteBean() {
		numero = 0;

	}

	public void avancar() {
		numero += 25;
		System.out.println(numero);
	}

	public void voltar() {
		numero -= 25;
		if (numero < 0) {
			numero = 0;
		}
		System.out.println(numero);
	}

	public void zerar() {
		numero = 0;
		System.out.println(numero);
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
