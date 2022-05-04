package de.gothaer.gui.presenter;


import de.gothaer.gui.IEuro2DollarRechnerView;
import de.gothaer.model.IEuro2DollarRechner;

public interface IEuro2DollarPresenter {

	public abstract IEuro2DollarRechnerView getView();

	public abstract void setView(IEuro2DollarRechnerView view);

	public abstract IEuro2DollarRechner getModel();

	public abstract void setModel(IEuro2DollarRechner model);

	public abstract void onRechnen();

	public abstract void onBeenden();

	public abstract void onPopulateItems();
	
	public abstract void updateRechnenActionState(); // Nicht beachten

}