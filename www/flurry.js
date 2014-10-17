var exec = require("cordova/exec");

/**
 * This is a global variable called exposed by cordova
 */    
var Flurry = function(){};

Flurry.prototype.logevent = function(success, error, options) {
  exec(success, error, "FlurryPlugin", "logevent", options);
};

Flurry.prototype.logscreenview = function(success, error, options) {
  exec(success, error, "FlurryPlugin", "logscreenview", options);
};

Flurry.prototype.logscreenview = function(success, error, options) {
  exec(success, error, "FlurryPlugin", "endtimedevent", options);
};

module.exports = new Flurry();
