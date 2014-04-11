package org.nypr.cordova.flurryplugin;

import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.flurry.android.FlurryAgent;

import android.util.Log;

public class FlurryPlugin extends CordovaPlugin {

	protected static final String LOG_TAG = "FlurryPlugin";
	
	public FlurryPlugin() {
		Log.d(LOG_TAG, "Flurry Plugin constructed");
	}
	
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    	String flurryKey="__FLURRY_KEY__";  // replaced by build script. better to pull from a a config file? could use config.xml but would have to re-load (outside of cordova's load)
    	if(flurryKey!=null && !flurryKey.equals("") && !flurryKey.contains("FLURRY_KEY")){
    		Log.d(LOG_TAG, "Starting Flurry Session: " + flurryKey);
    		FlurryAgent.onStartSession(webView.getContext(), flurryKey);
    	}
		super.initialize(cordova, webView);
		Log.d(LOG_TAG, "Flurry Plugin initialized");
	}
	
	@Override
	public void onDestroy() {
		Log.d(LOG_TAG, "Flurry Plugin ending session");
		FlurryAgent.onEndSession(this.webView.getContext());
		super.onDestroy();
	}

	@Override
	public void onReset() {
		Log.d(LOG_TAG, "Flurry Plugin onReset--WebView has navigated to new page or refreshed.");
		super.onReset();
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		boolean ret=true;
		try {
			if(action.equalsIgnoreCase("logevent")){
				_logEvent(args.getString(0), args.optJSONObject(1), args.optString(2), args.optBoolean(3));	
				callbackContext.success();
			}else if (action.equalsIgnoreCase("logscreenview")){
				_logScreenView(args.getString(0));
				callbackContext.success();
			}else if (action.equalsIgnoreCase("endtimedevent")){
				_endTimedEvent(args.getString(0));
				callbackContext.success();
			}else{
				callbackContext.error(LOG_TAG + " error: invalid action (" + action + ")");
				ret=false;
			}
		} catch (JSONException e) {
			callbackContext.error(LOG_TAG + " error: invalid json");
			ret = false;
		} catch (Exception e) {
			callbackContext.error(LOG_TAG + " error: " + e.getMessage());
			ret = false;
		}
		return ret;
	}

	protected void _logEvent(String eventName, JSONObject options, String label, boolean timed) throws JSONException {
		if(options != null){
			Map<String, String> params = new HashMap<String, String>();
			JSONArray names = options.names(); 
			int namesCount = names.length();
			for(int i=0;i<namesCount;i++){
				params.put(names.getString(i), options.getString(names.getString(i)));
			}
			Log.d(LOG_TAG, "Flurry Plugin logging event (" + eventName + ")");
			FlurryAgent.logEvent(eventName, params, timed);
		}else{
			Log.d(LOG_TAG, "Flurry Plugin logging event with no params (" + eventName + ")");	
			FlurryAgent.logEvent(eventName, timed);
		}
	}
	
	protected void _endTimedEvent(String eventName){
		Log.d(LOG_TAG, "Flurry Plugin ending timed event (" + eventName + ")");
		FlurryAgent.endTimedEvent(eventName);
	}
	
	protected void _logScreenView(String screen) throws JSONException {
		Log.d(LOG_TAG, "Flurry Plugin logging screen view (" + screen + ")");
		FlurryAgent.onPageView();
	}
}
