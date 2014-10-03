module.exports = {
  /**
  Get a map tile from PhoneGap plugin returninng base64 encoded PNG image
  params:
  options:{
   x:int, x coord
   y:int, y coord 
   z:int, zoom level
  }
  successcb: successful callback function(base64encodedimgdata) 
  failcb: failed callback function(error)
  **/
  getMapTile: function(options, successcb, failcb) {
    _exec("getMapTile",options,successcb,failcb); 
  }
}

function _exec(action, options, succb, failcb) {
  if (cordova && cordova.exec) {
    cordova.exec(successcb, failcb, "OSMap", action, [options]);
  } else {
    failcb("Cordova is not ready.");
  }
}
