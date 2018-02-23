package br.com.byamada.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Printer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String Nome;
	private String ip;
	private String serie;
	private String contador;
	private String nivelToner;
	private String nivelColor;
	private Date ultimaLeitura;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getContador() {
		return contador;
	}
	public void setContador(String contador) {
		this.contador = contador;
	}
	public String getNivelToner() {
		return nivelToner;
	}
	public void setNivelToner(String nivelToner) {
		this.nivelToner = nivelToner;
	}
	public String getNivelColor() {
		return nivelColor;
	}
	public void setNivelColor(String nivelColor) {
		this.nivelColor = nivelColor;
	}
	public Date getUltimaLeitura() {
		return ultimaLeitura;
	}
	public void setUltimaLeitura(Date ultimaLeitura) {
		this.ultimaLeitura = ultimaLeitura;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		result = prime * result + ((contador == null) ? 0 : contador.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((nivelColor == null) ? 0 : nivelColor.hashCode());
		result = prime * result + ((nivelToner == null) ? 0 : nivelToner.hashCode());
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
		result = prime * result + ((ultimaLeitura == null) ? 0 : ultimaLeitura.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Printer other = (Printer) obj;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		if (contador == null) {
			if (other.contador != null)
				return false;
		} else if (!contador.equals(other.contador))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (nivelColor == null) {
			if (other.nivelColor != null)
				return false;
		} else if (!nivelColor.equals(other.nivelColor))
			return false;
		if (nivelToner == null) {
			if (other.nivelToner != null)
				return false;
		} else if (!nivelToner.equals(other.nivelToner))
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		if (ultimaLeitura == null) {
			if (other.ultimaLeitura != null)
				return false;
		} else if (!ultimaLeitura.equals(other.ultimaLeitura))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Printer [id=" + id + ", Nome=" + Nome + ", ip=" + ip + ", serie=" + serie + ", contador=" + contador
				+ ", nivelToner=" + nivelToner + ", nivelColor=" + nivelColor + ", ultimaLeitura=" + ultimaLeitura
				+ "]";
	}
	
	

}
