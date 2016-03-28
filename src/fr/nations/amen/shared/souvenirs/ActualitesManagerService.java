package fr.nations.amen.shared.souvenirs;

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
	 * @param year
	 * @param month
	 * @param day
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	List<Souvenir> getPhotoByDateList(int year, int month, int day, int rangeStart, int rangeLength);
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ActuNotFoundException
	 */
	Souvenir getPhotoDetails(String id) throws ActuNotFoundException;
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	Vector<Souvenir> getActuList(int rangeStart, int rangeLength);
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @param sortOn
	 * @param isAscending
	 * @return
	 */
	Vector<Souvenir> getActuList(int rangeStart, int rangeLength, String sortOn, boolean isAscending);
	

	/**
	 * 
	 * @param photoDetails
	 * @return
	 */
	Souvenir updatePhotoDetails(Souvenir photoDetails);
}