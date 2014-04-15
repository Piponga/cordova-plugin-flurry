//
//  FlurryPlugin.m
//  NYPRNative
//
//  Created by Brad Kammin on 4/1/14.
//
//

#import "FlurryPlugin.h"
#import "Flurry.h"

@implementation FlurryPlugin

#pragma mark Initialization

- (void)pluginInitialize
{
    NSString * flurryKey = @"__FLURRY_KEY__";
    
    if( flurryKey!=nil && [flurryKey isEqualToString:@""]==NO && [flurryKey rangeOfString:@"FLURRY_KEY"].location == NSNotFound ){
        NSLog(@"Starting Flurry Session: %@", flurryKey);
        [Flurry startSession:flurryKey];
    }
    
    NSLog(@"Flurry Plugin initialized");
}

#pragma mark Plugin methods

- (void)logevent:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* event = [command.arguments objectAtIndex:0];
    
    NSLog (@"Flurry Plugin logging event (%@)", event );
    
    NSDictionary  * parameters = [command.arguments  objectAtIndex:1];
    NSString * label = [command.arguments  objectAtIndex:2];
    bool timed = FALSE;
    if( [command.arguments count] >= 4){
        if ( [command.arguments objectAtIndex:3] != (id)[NSNull null] ) {
            timed = [[command.arguments objectAtIndex:3]boolValue];
        }
    }
    
    [self _logEvent:event withParameters:parameters label:label timed:timed];
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)logscreenview:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* screen = [command.arguments objectAtIndex:0];
    
    NSLog (@"Flurry Plugin logging screen (%@)", screen );
    
    [self _logScreenView:screen];
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)endtimedevent:(CDVInvokedUrlCommand *)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* event = [command.arguments objectAtIndex:0];
    
    NSLog (@"Flurry Plugin logging end to timed event (%@)", event );
    
    [self _endTimedEvent:event];
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

#pragma mark Internal helpers

- (void)_logEvent:(NSString *)eventName withParameters:(NSDictionary *)parameters label:(NSString*)label timed:(BOOL)timed {
    
    if ( parameters && timed ){
        [Flurry logEvent:eventName withParameters:parameters timed:timed];
    } else if ( parameters ) {
        [Flurry logEvent:eventName withParameters:parameters];
    } else if ( timed ) {
        [Flurry logEvent:eventName timed:timed];
    } else {
        [Flurry logEvent:eventName];
    }
}

- (void)_endTimedEvent:(NSString *)eventName {
    [Flurry endTimedEvent:eventName withParameters:nil];
}

- (void)_logScreenView:(NSString*) screen {
    [Flurry logPageView];
}

@end
