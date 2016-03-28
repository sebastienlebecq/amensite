package fr.nations.amen.shared.eglise;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The service interface for the PhotoApp server-side service.
 */
@RemoteServiceRelativePath("serviceEglise")
public interface MenuEgliseService extends RemoteService
{

	public MenuEglise getMenuEgliseById(Long menuItem);
	void remove(MenuEglise actu);


	MenuEglise updateMenuEglise(MenuEglise newActu);
	
	
	MenuEglise addMenuEglise(MenuEglise actu);


	List<Commentaire> getComments(String idMenuEglise, int start, int length);


	void storeComment(String id, Commentaire newInput);


	int getCountVisitor();


	List<Commentaire> getComments(String appellation);


	List<MenuEglise> getMenuEgliseList(int start, int length);


	List<MenuEglise> getMenuEglisebyCategory(String category, int rangeStart,
			int rangeLength);
	int getCountMax();





}