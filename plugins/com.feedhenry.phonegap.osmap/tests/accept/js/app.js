  setTimeout(function() {
    var map = L.map("maprender", {
      minZoom: 19,
      maxZoom: 21
    }).setView([51.3399417, -0.483258], 19);
    debugger;
    var ostile = new L.TileLayer.OSmapTile({
      minZoom:19,
      maxZoom:21
    });
    ostile.addTo(map);
  }, 1000);
