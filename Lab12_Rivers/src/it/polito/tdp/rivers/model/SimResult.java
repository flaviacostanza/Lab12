package it.polito.tdp.rivers.model;

public class SimResult {
	
	private int gg_non_coperti;//giorni in cui non si è potuta garantire l'erogazione minima
	private float c_med;//occupazione media del bacino nel corso della simulazione
	
	public SimResult(int gg_non_coperti, float c_med) {
		super();
		this.gg_non_coperti = gg_non_coperti;
		this.c_med = c_med;
	}

	public int getGg_non_coperti() {
		return gg_non_coperti;
	}

	public float getC_med() {
		return c_med;
	}

	

}
