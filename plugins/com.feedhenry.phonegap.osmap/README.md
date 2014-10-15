#Offline map PhoneGap Plugin
This plugin connects to a native implenetation which reads map tiles from a sqlite database. The plugin consists of:

* Native Sqlite DB loader
* Native PhoneGap Plugin
* Js PhoneGap Plugin 
* Leaflet.js 
* Leafletjs Tile plugin 

#Build 
For plugin developers, there is a build-www.py python script to build and generate production version of the plugin.


#Installation
To install the plugin, goto cordova project root folder and run 
```bash
cordova plugins add https://github.com/Keyang/osmap-pg.git
```
The installation will do the following:

* Embed plugin export module to cordova_plugin.js file as like other plugins 
* Generate osmap.js file in the root of www folder 
* Generate osmap.css file in the root of www folder

The generated files will only display in platforms folder once run cordova prepare

#Usage
Once the plugins is installed, we can use it from hybrid app.

##index.html
Add following lines to your cordova project's index.html:
```html
<script type="text/javascript" src="osmap.js"></script>
<link rel="stylesheet" type="text/css" href="osmap.css"/>
```

the osmap.js will include leaflet.js map library and osmap tile plugin while osmap.css file will introduce the required css for map rendering.

##OSMapTile
OSMapTile is a leaflet.js tile plugin specifically for osmap phonegap plugin.
It is used to retrieve map tiles from osmap phonegap plugin and render it on map.
The usage is very simple:
```Javascript 
var map=L.map("mymap");
var ostileLayer=new L.TileLayer.OSmapTile(options);
ostileLayer.addTo(map);
```
The options are standard leaflet tile layer options which can be found [here](http://leafletjs.com/reference.html#tilelayer).

##Leaftlet.js map 
Once the tile being added to leaflet.js map, it has full functionality of leaflet.js. Leaflet.js API doc can be found [here](http://leafletjs.com/reference.html).


##osmap lib 
osmap is a PhoneGap plugin responsible for all mapping data related activities.
It has following functions:

* Retrive mapping tiles from database based on x,y,z parameter. The x,y,z parameters are following [slippy map tilename](http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames)


osmap is an object attached to browser's window object. It has following functions:

* getMapTile (options, successcb, failcb): this function is called internally inside osmaptilelayer to retrieve tile data.



###getMapTile 
 Get a map tile from PhoneGap plugin returninng base64 encoded PNG image

  params:
```
  options:{
   x:int, x coord
   y:int, y coord 
   z:int, zoom level
  }
```
  successcb: successful callback function(base64encodedimgdata) 

  failcb: failed callback function(error)
