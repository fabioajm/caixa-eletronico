package com.cecore.observer;

import com.cecore.model.Nota;

public interface CaixaEletronicoObserver {
	public void notificarSaque(Nota nota);
}
