package de.gothaer.application;


import de.gothaer.gui.Euro2DollarRechnerView;
import de.gothaer.gui.IEuro2DollarRechnerView;
import de.gothaer.gui.presenter.Euro2DollarPresenter;
import de.gothaer.gui.presenter.IEuro2DollarPresenter;
import de.gothaer.model.Euro2DollarRechnerImpl;
import de.gothaer.model.IEuro2DollarRechner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IEuro2DollarRechner model = new Euro2DollarRechnerImpl();
		
		IEuro2DollarPresenter presenter = new Euro2DollarPresenter();
		
		IEuro2DollarRechnerView view = new Euro2DollarRechnerView();
		
		
		presenter.setView(view);
		presenter.setModel(model);
		
		view.setPresenter(presenter);
		
		presenter.onPopulateItems();
		
		view.show();

	}

}
