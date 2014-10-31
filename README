# Flurry PhoneGap/Cordova Plugin

### Platform Support

This plugin supports PhoneGap/Cordova apps running on both iOS and Android.

### Version Requirements

This plugin is meant to work with Cordova 3.5.0+ and the latest version of the Flurry library.

Flurry documentation and integration guides for iOS and Android:
[website](http://support.flurry.com/?title=Analytics/GettingStarted). 

## Installation

#### Automatic Installation using PhoneGap/Cordova CLI (iOS and Android)
1. Make sure you update your projects to Cordova iOS version 3.5.0+ before installing this plugin.

        cordova platform update ios
        cordova platform update android

2. Install this plugin using PhoneGap/Cordova cli:

        cordova local plugin add https://github.com/wnyc/cordova-plugin-flurry.git

3. For iOS, modify FlurryPlugin.m, replacing with your configuration setting:
     NSString * flurryKey = @"__FLURRY_KEY__";

   For Android, modify FlurryPlugin.java, replacing with your configuration setting:
     String flurryKey="__FLURRY_KEY__"; 

   Todo: pull Flurry key from configuration setting

## Usage

  // log an event
  window.flurry.logevent(  successCallback, failureCallback, eventName, params, label, timed );

  // log a screen view
  window.flurry.logscreenview(  successCallback, failureCallback, screen );

  // log an end to a timed event
  window.flurry.endtimedevent(  successCallback, failureCallback, eventName );   
    
