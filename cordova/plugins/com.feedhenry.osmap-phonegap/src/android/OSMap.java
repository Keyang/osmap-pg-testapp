package com.feedhenry.osmap-phonegap

import org.apache.cordova.*;
import org.json.*;

public class OSMap extends CordovaPlugin{
  
  public OSMap{

  }
  public void initialize(CordovaInterface cordova, CordovaWebView webView){
    super.initialize(cordova, webView);
    //More initialisation
  }

  /**
   * Executes the request and returns PluginResult.
   *
   * @param action            The action to execute.
   * @param args              JSONArry of arguments for the plugin.
   * @param callbackContext   The callback id used when calling back into JavaScript.
   * @return                  True if the action was valid, false if not.
   */
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("getMapTile")){
      getMapTile(args,callbackContext);
      return true;
    } 
    return false;
  }
  private void getMapTile(JSONArry args, CallbackContext callbackContext){
    JSONObject r=new JSONObject();
    r.put("hello","world");
    callbackContext.success(r);
  }
}
