package fr.nations.amen.shared.actus;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The service interface for the PhotoApp server-side service.
 */
@RemoteServiceRelativePath("serviceActuManager")
public interface ActualitesManagerService extends RemoteService
{
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	Vector<Actu> getActuList(int rangeStart, int rangeLength);
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @param sortOn
	 * @param isAscending
	 * @return
	 */
	Vector<Actu> getActuList(int rangeStart, int rangeLength, String sortOn, boolean isAscending);
	
	

	/**
	 * 
	 * @param photoDetails
	 * @return
	 */
	Actu updatePhotoDetails(Actu photoDetails);
}