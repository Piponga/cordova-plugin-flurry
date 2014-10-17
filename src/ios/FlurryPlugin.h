//
//  FlurryPlugin.h
//

#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVPluginResult.h>

@interface FlurryPlugin : CDVPlugin

- (void)logevent:(CDVInvokedUrlCommand*)command;
- (void)logscreenview:(CDVInvokedUrlCommand*)command;
- (void)endtimedevent:(CDVInvokedUrlCommand*)command;

@end
