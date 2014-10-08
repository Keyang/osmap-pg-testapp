#Offline map PhoneGap Plugin
This plugin connects to a native implenetation which reads map tiles from a sqlite database. The plugin consists of:

* Native Sqlite DB loader
* Native PhoneGap Plugin
* Js PhoneGap Plugin 
* Leaflet.js 
* Leafletjs Tile plugin 

#Installation
To install the plugin, goto cordova project root folder and run 
```bash
cordova plugins add [path to plugin]
```

#Usage
Once the plugins is installed, we can use it from hybrid app.

##index.html
Add following lines to index.html:
```html
<script type="text/javascript" src="osmap.js"></script>
<link rel="stylesheet" type="text/css" href="osmap.css"/>
