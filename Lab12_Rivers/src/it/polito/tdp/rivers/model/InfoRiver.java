package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class InfoRiver {
	
	private LocalDate primaData;
	private LocalDate ultimaData;
	private int nMisure;
	private float f_med;
	
	public InfoRiver(LocalDate primaData, LocalDate ultimaData, int nMisure, float f_med) {
		super();
		this.primaData = primaData;
		this.ultimaData = ultimaData;
		this.nMisure = nMisure;
		this.f_med = f_med;
	}
	public LocalDate getPrimaData() {
		return primaData;
	}
	public LocalDate getUltimaData() {
		return ultimaData;
	}
	public int getnMisure() {
		return nMisure;
	}
	public float getF_med() {
		return f_med;
	}
	public String toString() {
		return "Prima data: "+primaData+" Ultima data: "+ultimaData+" nMisure: "+nMisure+" flusso medio: "+f_med+"\n";
	}
}
