package fr.nations.amen.client.youtube;

import com.google.gwt.user.client.ui.SimplePanel;

//import com.bramosystems.oss.player.core.client.AbstractMediaPlayer;
//import com.bramosystems.oss.player.core.client.PlayerUtil;
//import com.bramosystems.oss.player.core.client.PluginNotFoundException;
//import com.bramosystems.oss.player.core.client.PluginVersionException;
//import com.bramosystems.oss.player.youtube.client.YouTubePlayer;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.SimplePanel;


public class ServiceYoutube {
	
	public static SimplePanel getYoutubePanel(String refYoutube) {
		
		SimplePanel panel = new SimplePanel();
		
//		YouTubeEmbeddedPlayer youTubeEmbeddedPlayer = new YouTubeEmbeddedPlayer(refYoutube);
//		youTubeEmbeddedPlayer.setWidth("427px");
//		youTubeEmbeddedPlayer.setHeight("320px");
		
		
		YoutubePanel youPanel = new YoutubePanel();
		
		youPanel.displayVideo(refYoutube);
		
		panel.add(youPanel);
		
		return panel;
	}
	
	
//	public static SimplePanel getYoutubePanel(String refYoutube) {
//		SimplePanel panel = new SimplePanel();
//		AbstractMediaPlayer player = null;
//		try {
//			// create the player, specifing URL of media
//			player = new YouTubePlayer(refYoutube,
//					"640px", "360px");
//			panel.setWidget(player); // add player to panel.
//		} catch (PluginVersionException e) {
//			// required Flash plugin version is not available,
//			// alert user possibly providing a link to the plugin
//			// download page.
//			panel.setWidget(new HTML(
//					"...Please download the plugin first..."));
//		} catch (PluginNotFoundException e) {
//			// required Flash plugin not found, display a friendly
//			// notice.
//			panel.setWidget(PlayerUtil.getMissingPluginNotice(e
//					.getPlugin()));
//		}
//
//		return panel;
//	}


}
