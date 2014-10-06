package com.feedhenry.phonegap;

import org.apache.cordova.*;
import org.json.*;
import uk.co.spargonet.osofflinemapping.*;
import uk.co.ordnancesurvey.android.maps.MapTile;
import uk.co.ordnancesurvey.android.maps.TileFetcherDelegate;
import android.os.Environment;
import java.io.File;
import android.graphics.Bitmap;
import android.util.Log;
public class OSMap extends CordovaPlugin implements TileFetcherDelegate{
  private final static String DB_NAME = "/mapdata.ostiles";    
  private SpargonetTileFetcher tileFetcher;
  public void initialize(CordovaInterface cordova, CordovaWebView webView){
    super.initialize(cordova, webView);
    Log.i("osmap","start to init tilefetcher");
    File path = Environment.getExternalStorageDirectory();
    tileFetcher=new SpargonetTileFetcher(cordova.getActivity(),this);
    Log.i("osmap","tilefetcher initialised: "+path.getPath()+DB_NAME);
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
  private void getMapTile(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject options=args.getJSONObject(0);
        int x=options.getInt("x");
        int y=options.getInt("y");
        int z=options.getInt("z");
        try{
            String stringBase64ForTile = tileFetcher.requestBase64ForTile(x, y, z);
            callbackContext.success(stringBase64ForTile);
        }catch(UnSupportedZoomLevelException e){
            callbackContext.error(e.getMessage());
        }catch(Exception e){
            callbackContext.error(e.getMessage());
        }

    }
  @Override
  public void tileReadyAsyncCallback(MapTile tile, Bitmap bmp) {
	// TODO Auto-generated method stub

  }
}
