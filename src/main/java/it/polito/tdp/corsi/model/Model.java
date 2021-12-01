package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	private CorsoDAO corsoDao;
	
	public Model()
	{
		corsoDao= new CorsoDAO();
	}
	public List<Corso> getcorsibyperiodo(Integer pd)
	{
		return corsoDao.corsisemestre(pd);
	}
	public Map<Corso,Integer> getiscritti(Integer pd)
	{
		return corsoDao.iscrizionicorsi(pd);
	}

	
}
