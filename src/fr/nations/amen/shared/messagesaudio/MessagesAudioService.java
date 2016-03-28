package fr.nations.amen.shared.messagesaudio;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The service interface for the PhotoApp server-side service.
 */
@RemoteServiceRelativePath("serviceMessageAudio")
public interface MessagesAudioService extends RemoteService
{
//	/**
//	 * Return a list of days that have photos in a particular month and year
//	 * @param year year to check for
//	 * @param month month to check for
//	 * @param range
//	 * @return
//	 */
//	List<AsyncDays> getDaysList(int year, int month, int rangeStart, int rangeLength);
//	
//	/**
//	 * 
//	 * @param year
//	 * @param rangeStart
//	 * @param rangeLength
//	 * @return
//	 */
//	List<AsyncMonths> getMonthsList(int year, int rangeStart, int rangeLength);
//	
//	/**
//	 * 
//	 * @param year
//	 * @param month
//	 * @param day
//	 * @param rangeStart
//	 * @param rangeLength
//	 * @return
//	 */
//	List<Actu> getPhotoByDateList(int year, int month, int day, int rangeStart, int rangeLength);
//	
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 * @throws ActuNotFoundException
//	 */
//	Actu getPhotoDetails(String id) throws ActuNotFoundException;
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	//Vector<MessageAudio> getSouvenirList(int rangeStart, int rangeLength);
	
	String getBlobStoreUploadUrl();
	

//	/**
//	 * 
//	 * @param rangeStart
//	 * @param rangeLength
//	 * @param sortOn
//	 * @param isAscending
//	 * @return
//	 */
//	Vector<Actu> getPhotoList(int rangeStart, int rangeLength, String sortOn, boolean isAscending);
//	
//	/**
//	 * 
//	 * @param rangeStart
//	 * @param rangeLength
//	 * @return
//	 */
//	List<AsyncYears> getYearsList(int rangeStart, int rangeLength);
//
//	/**
//	 * 
//	 * @param photoDetails
//	 * @return
//	 */
//	Actu updatePhotoDetails(Actu photoDetails);


	void remove(MessageAudio actu);


	MessageAudio updateSouvenir(MessageAudio newActu);
	
	
	MessageAudio addSouvenir(MessageAudio actu);


	List<Commentaire> getComments(String idSouvenir, int start, int length);


	void storeComment(String id, Commentaire newInput);


	int getCountVisitor();


	List<Commentaire> getComments(String appellation);


	Vector<MessageAudio> getSouvenirList(String category, int rangeStart,
			int rangeLength);


	Vector<MessageAudio> getSouvenirAllList(int start, int length);


	List<MessageAudio> fetchPage(int start, int length);


	MessageAudio incrementNbPagesVues(String id);





}