package fr.nations.amen.client.mvp.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;


public interface HasTagDeletedHandlers extends HasHandlers {
  public HandlerRegistration addHasTagDeletedHandler(TagDeletedHandler handler); 
}
