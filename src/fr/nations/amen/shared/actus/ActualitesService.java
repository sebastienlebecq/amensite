package fr.nations.amen.shared.actus;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The service interface for the serviceActu server-side service.
 */
@RemoteServiceRelativePath("serviceActu")
public interface ActualitesService extends RemoteService
{
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	Vector<Actu> getEventList(int rangeStart, int rangeLength);


	void remove(Actu actu);


	Actu updateActu(Actu newActu);
	
	
	Actu addActu(Actu actu);
}