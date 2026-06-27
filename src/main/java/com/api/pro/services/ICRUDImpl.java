package com.api.pro.services;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pro.interfaces.ICRUD;

public abstract class ICRUDImpl<T,ID> implements ICRUD<T, ID>   {
	
	abstract JpaRepository<T,ID>getRepository();
	
	@Override
	public T registrar(T ben) throws Exception {
		return getRepository().save(ben);
	}

	@Override
	public T actualizar(T ben) throws Exception {
		return getRepository().save(ben);
	}

	@Override
	public void eliminar(ID cod) throws Exception {
		getRepository().deleteById(cod);
		
	}

	@Override
	public List<T> listar() throws Exception {
		return getRepository().findAll();
	}

	@Override
	public T buscarPorCodigo(ID cod) throws Exception {
		return getRepository().findById(cod).orElse(null);
	}
}
