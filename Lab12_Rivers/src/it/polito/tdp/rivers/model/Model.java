package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private List<River> allRivers;
	private RiversDAO dao;
	private List<Flow> flussi;
	private float f_med;
	
	public Model() {
		allRivers= new ArrayList<River>();
		flussi= new ArrayList<Flow>();
		dao=new RiversDAO();
	}

	public List<River> getAllRivers() {
		List<River> result= new ArrayList(dao.getAllRivers());
		return result;
	}

	public InfoRiver getInfoRiver(River river) {
		//dato il river selezinato, devo restituire le info richeste
		flussi= dao.getAllFlowsByRiver(river);
		LocalDate prima= flussi.get(0).getDay();
		LocalDate ultima= flussi.get(flussi.size()-1).getDay();
		int nMisure= flussi.size();
		f_med= average(flussi);
		//System.out.println(new InfoRiver(prima, ultima, nMisure, f_med));
		return new InfoRiver(prima, ultima, nMisure, f_med);
	}

	private float average(List<Flow> flussi) {
		float sum=0;
		for(Flow f: flussi) {
			sum+= f.getFlow();
		}
		return sum/flussi.size();
	}
	
	public SimResult doSimula(int k) {
		
		Simulatore sim= new Simulatore(k, flussi, f_med);
		
		sim.run();
		
		SimResult result= new SimResult(sim.getGg_non_coperti(), sim.getC_med());
		
		return result;
		
		
	}

}
