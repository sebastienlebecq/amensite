package fr.nations.amen.client.messagesaudiopublic.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.media.client.Audio;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

public class AudioWidget extends Composite{

	@UiField FlowPanel media;
	@UiField CheckBox loop;
	@UiField CheckBox autoplay;
	//@UiField Button showplayer;
	@UiField LayoutPanel player;
	//@UiField Label songName;
	@UiField RadioButton normal;
	@UiField RadioButton dble;
	@UiField RadioButton half;
	static int uniqueIndex = 0;
	
	/**
	 * For binding the user interface
	 */
    interface AudioWidgetUiBinder extends UiBinder<Widget, AudioWidget> {}
    private static AudioWidgetUiBinder uiBinder = GWT.create(AudioWidgetUiBinder.class);

    Audio audio;
	private String urlmp3;
	private String urlogg;
	private String urlwav;
    
    public AudioWidget(){
        initWidget(uiBinder.createAndBindUi(this));
    	setUp();
		player.animate(4);
		normal.setName(normal.getName()+uniqueIndex);
		half.setName(half.getName()+uniqueIndex);
		dble.setName(dble.getName()+uniqueIndex++);
    }
    
    private void setUp(){
		  audio = Audio.createIfSupported();
		  if (audio == null) {
			  media.add(new HTML(
						"<p>Your browser does not support audio playback, download the file:"
								+ "<a href='/amen/getMessageAudioUploaded?blob-key="
								+ urlmp3
								+ "'>MP3</a>,"
								+ "<a href='/amen/getMessageAudioUploaded?blob-key="
								+ urlogg
								+ "'>Vorbis</a>,"
								+ "<a href='/amen/getMessageAudioUploaded?blob-key="
								+ urlwav
								+ "'>WAV</a>"));
		    return;
		  }   
		  // Show audio controls.
		  audio.setControls(true);
		  audio.setAutoplay(autoplay.getValue());
		  audio.setLoop(loop.getValue());
		  media.add(audio);
    }
    public void setSrcMp3(String url){
		  if(audio==null) return;
		  this.urlmp3 = url;
		  
		  
			if (MediaElement.CAN_PLAY_PROBABLY.equals(audio
					.canPlayType(AudioElement.TYPE_MP3))) {
				audio.setSrc(urlmp3);
				System.out.println("/amen/getMessageAudioUploaded?blob-key="+ urlmp3);
				
			} else if (MediaElement.CAN_PLAY_PROBABLY.equals(audio
					.canPlayType(AudioElement.TYPE_OGG))) {
				audio.setSrc(urlogg);
			} else if (MediaElement.CAN_PLAY_PROBABLY.equals(audio
					.canPlayType(AudioElement.TYPE_WAV))) {
				audio.setSrc(urlwav);
			} else {
			
				audio.setSrc(urlmp3);
			}
		  
		  audio.setSrc(url);
		  
		  
		
		  
  }

	public void setSrcogg(String url) {
		if(audio==null) return;
		this.urlogg = url;
		audio.setSrc(url);
	}

	public void setSrcwav(String url) {
		 if(audio==null) return;
		 this.urlwav = url;
		 audio.setSrc(url);
	}
    
    @UiHandler({"loop","autoplay"})
    public void onValueChange(ValueChangeEvent<Boolean> evt){
    	if (audio==null) return;
    	Object src = evt.getSource();
    	if (src.equals(loop)){
    		audio.setLoop(evt.getValue());
    	}
    	if (src.equals(autoplay)){
    		audio.setAutoplay(evt.getValue());
    	}
    }
    
    @UiHandler({"normal","half", "dble"})
    public void onChangeSpeed(ValueChangeEvent<Boolean> evt){
    	if (audio==null) return;
    	Object src = evt.getSource();
    	if (src.equals(normal)){
    		audio.setPlaybackRate(1.0);
    	} else if (src.equals(dble)){
    		audio.setPlaybackRate(2.0);
    	} else if (src.equals(half)){
    		audio.setPlaybackRate(0.5);
    	}
    }
    
    boolean playerShown = true;
    
//    @UiHandler("showplayer")
//    public void onShowClick(ClickEvent evt){
//    	if(audio==null) return;
//    	playerShown = !playerShown;
//    	if (playerShown){
//    		player.setWidgetLeftWidth(media, 100, Unit.PX, 300, Unit.PX);
//    		player.setWidgetTopHeight(media, 0, Unit.PX, 150, Unit.PX);
//    	} else {
//    		player.setWidgetLeftWidth(media, 100, Unit.PX, 0, Unit.PX);
//    		player.setWidgetTopHeight(media, 0, Unit.PX, 0, Unit.PX);
//    	}
//    	player.animate(500);
//    }

    
}
