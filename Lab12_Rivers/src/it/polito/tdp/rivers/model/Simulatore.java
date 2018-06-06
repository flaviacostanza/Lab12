package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Simulatore {

	//Dati in input
	private int K;//fattore di scala
	private float f_med; //flusso medio in m^3 al secondo convertito in giorno nel costruttore
	private float Q;//capienza massima
	private float f_out_min; //flusso minimo in uscita
	
	//Modello del mondo
	private float C;//occupazione istantanea del bacino
	
	//Coda degli eventi
	//private PriorityQueue<Flow> queue;
	private List<Flow> flows;//processo direttamente la coda dei flussi ordinati per data
	
	//Dati in output
	private int gg_non_coperti;//giorni in cui non si è potuta garantire l'erogazione minima
	private float c_med;//occupazione media del bacino nel corso della simulazione

	public Simulatore(int k, List<Flow> flows, float f_med) {
		this.K=k;
		this.flows= new LinkedList<Flow>(flows);
		this.f_med= f_med*60*60*24;
		
		//operazioni di inizializzazione
		//calcolo capienza massima
				this.Q = K*f_med*30 ;
				//imposto foutmin
				this.f_out_min= (float) (0.8* f_med);
				//inizializzo C
				this.C= Q/2;
				//inizializzo dati output
				this.gg_non_coperti=0;
				this.c_med=0;
				
	}
	
	
	public void run() {
		
		int cntGiorni=0;
		float sumC=0;
		
		for(Flow f: flows) {
			cntGiorni++;
			
			//flussi in ingresso e in uscita del giorno
			float f_in= (float) f.getFlow();
			float f_out= f_out_min;
			if(Math.random()<= 0.05) {
				f_out= f_out_min*10;//il flusso richiesto in uscita è abbondante
			}

			
			if(f_in > f_out) {
				//verifico se c'è tracimazione e aggiorno C
				if((f_in-f_out)+C > Q) {
					C=Q;
				}else {
					C= C+(f_in-f_out);
				}
			}else if(f_in< f_out){
				//verifico se posso far uscire acqua e diminuisco il livello
				if((f_out-f_in)<C) {
					this.gg_non_coperti++;
				}else {
					C= C+(f_in-f_out);
				}
			}
			
				
				sumC+= C;
				
			}
			
			
			this.c_med= sumC / cntGiorni;
		}


	public int getGg_non_coperti() {
		return gg_non_coperti;
	}


	public float getC_med() {
		return c_med;
	}
		
	
}
