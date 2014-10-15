L.TileLayer.OSmapTile = L.TileLayer.extend({
  initialize: function(options) {
    options=L.setOptions(this, options);
    // detecting retina displays, adjusting tileSize and zoom levels
    if (options.detectRetina && this.isRetina() && options.maxZoom > 0) {
      options.tileSize = Math.floor(options.tileSize / 2);
      options.zoomOffset++;

      options.minZoom = Math.max(0, options.minZoom-1);
      options.maxZoom--;
    }
  },
  getTileUrl: function(tilePoint, zoom, tile) {
    var z = this._getZoomForUrl()||0;
    var x = tilePoint.x;
    var y = tilePoint.y;
    var param = {
      x: x,
      y: y,
      z: z
    }

    osmap.getMapTile(param, function(res) {
      tile.src = res;
    }, function(er) {
      console.log('error with tile retriving', er);
    });
  },
  _loadTile: function(tile, tilePoint, zoom) {
    tile._layer = this;
    tile.onload = this._tileOnLoad;
    tile.onerror = this._tileOnError;
    this.getTileUrl(tilePoint, zoom, tile);
  },
  isRetina:function(){
    return L.Browser.retina;
  }
});
