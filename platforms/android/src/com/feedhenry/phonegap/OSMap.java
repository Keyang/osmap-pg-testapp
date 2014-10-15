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
  private String mapNotFound="iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAMAAABrrFhUAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBNYWNpbnRvc2giIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6REU5N0YxQUY0NTkyMTFFNDlENjhBRDQzQUFGQTlBRUUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6REU5N0YxQjA0NTkyMTFFNDlENjhBRDQzQUFGQTlBRUUiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpERTk3RjFBRDQ1OTIxMUU0OUQ2OEFENDNBQUZBOUFFRSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpERTk3RjFBRTQ1OTIxMUU0OUQ2OEFENDNBQUZBOUFFRSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PiRGir4AAAEdUExURdTU1L+/v+rq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";
  public void initialize(CordovaInterface cordova, CordovaWebView webView){
    super.initialize(cordova, webView);
    File path = Environment.getExternalStorageDirectory();
    tileFetcher=new SpargonetTileFetcher(cordova.getActivity(),this);
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
        Log.d("osmap","Retrieving map x="+String.valueOf(x)+"; y="+String.valueOf(y)+"; z="+String.valueOf(z));
        try{
            String stringBase64ForTile = tileFetcher.requestBase64ForTile(x, y, z);
            if (stringBase64ForTile.equals("")){
              callbackContext.success(mapNotFound); 
            }else{
              callbackContext.success(stringBase64ForTile);
            }
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
