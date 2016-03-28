package fr.nations.amen.shared.eglise;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface MenuEgliseServiceAsync {

void remove(MenuEglise actu, AsyncCallback<Void> callback);


void getComments(String appellation,
		AsyncCallback<List<Commentaire>> asyncCallback);

void storeComment(String id, Commentaire newInput, AsyncCallback<Void> asyncCallback);

void getCountVisitor(AsyncCallback<Integer> asyncCallback);

void getComments(String idSouvenir, int start, int length,
		AsyncCallback<List<Commentaire>> asyncCallback);

void addMenuEglise(MenuEglise actu, AsyncCallback<MenuEglise> callback);


void getMenuEglisebyCategory(String category, int rangeStart, int rangeLength,
		AsyncCallback<List<MenuEglise>> callback);


void getMenuEgliseList(int start, int length,
		AsyncCallback<List<MenuEglise>> callback);


void updateMenuEglise(MenuEglise newActu, AsyncCallback<MenuEglise> callback);


void getMenuEgliseById(Long menuItem, AsyncCallback<MenuEglise> callback);


void getCountMax(AsyncCallback<Integer> asyncCallback);

}
