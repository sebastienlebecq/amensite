/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package fr.nations.amen.client.eglise.commonpublic;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.shared.eglise.Commentaire;
import fr.nations.amen.shared.eglise.MenuEglise;
import fr.nations.amen.shared.eglise.MenuEgliseService;
import fr.nations.amen.shared.eglise.MenuEgliseServiceAsync;

/**
 * A form that allows users to enter and submit a comment.
 */
public class CommentSubmitForm extends Composite {
	
	MenuEgliseServiceAsync souvenirService = GWT.create(MenuEgliseService.class);

  private static CommentSubmitFormUiBinder uiBinder = GWT
      .create(CommentSubmitFormUiBinder.class);

  interface CommentSubmitFormUiBinder extends
      UiBinder<Widget, CommentSubmitForm> {
  }

  /**
   * Classes implementing this interface cann be called when the user submits a
   * comment.
   */
  public static interface CommentSubmitHandler {
    /**
     * A comment is to be submitted.
     */
    public void submitComment(String comment);
  }

  @UiField
  Label label;

  @UiField
  RichTextArea commentTextArea;

  @UiField
  Button submitButton;
  private CommentSubmitHandler submitHandler;

  public CommentSubmitForm(final MenuEglise souvenirInfo, final CommentsPanel commentsPanel) {
    initWidget(uiBinder.createAndBindUi(this));
    
    this.setCommentSubmitHandler(new CommentSubmitHandler() {
		 
		 
        public void submitComment(String comment) {
      	  
      	  Commentaire newInput = new Commentaire(souvenirInfo.getId(),"internaute",
  					comment, new Date().getTime(), souvenirInfo.getAppellation());
      	//  Window.alert("idSouvenir(GUI):"+souvenirInfo.getId());
      	  
      	  souvenirService.storeComment(souvenirInfo.getId(), newInput,
              new AsyncCallback<Void>() {
                public void onFailure(Throwable caught) {
                  // TODO: Show an error message
                }

                public void onSuccess(Void result) {
              	  commentsPanel.loadComments(souvenirInfo.getId());
              	
                }
              });
        }
      });

  }

  /**
   * Sets the handler that will be called when the user submits a comment.
   */
  public void setCommentSubmitHandler(CommentSubmitHandler handler) {
    submitHandler = handler;
  }

  @UiHandler("submitButton")
  void submitButtonClicked(ClickEvent e) {
    if (submitHandler != null) {
      submitHandler.submitComment(commentTextArea.getHTML());
      commentTextArea.setText("");
    }
  }
  
  

}
