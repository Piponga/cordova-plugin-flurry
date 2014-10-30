var exec = require("cordova/exec");

/**
 * This is a global variable called exposed by cordova
 */    
var Flurry = function(){};

Flurry.prototype.logevent = function(success, error, eventName, params, label, timed) {
  exec(success, error, "FlurryPlugin", "logevent", [eventName, params, label, timed]);
};

Flurry.prototype.logscreenview = function(success, error, screen) {
  exec(success, error, "FlurryPlugin", "logscreenview", [screen]);
};

Flurry.prototype.endtimedevent = function(success, error, eventName) {
  exec(success, error, "FlurryPlugin", "endtimedevent", [eventName]);
};

module.exports = new Flurry();
