package com.api.pro.interfaces;

import java.util.List;

public interface ICRUD<T,ID> {
	
	T registrar(T ben) throws Exception;
	T actualizar(T ben) throws Exception;
	void eliminar(ID cod) throws Exception;
	List<T> listar() throws Exception;
	T buscarPorCodigo(ID cod) throws Exception;

}
