cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/com.feedhenry.phonegap.osmap/www/js/osmap.js",
        "id": "com.feedhenry.phonegap.osmap.osmap",
        "clobbers": [
            "osmap"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.feedhenry.phonegap.osmap": "0.1.0",
    "org.apache.cordova.geolocation": "0.3.11-dev"
}
// BOTTOM OF METADATA
});