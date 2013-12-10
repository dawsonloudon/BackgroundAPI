#import "BackgroundAPI.h"

@implementation BackgroundAPI

- (void)makeApiCall:(CDVInvokedUrlCommand *)command
{
    _theCommand = command;
    //Format call
    NSDictionary *params = [command.arguments objectAtIndex:0];

    //Run call in background
    [self performSelectorInBackground:@selector(runApiCall:) withObject:params];
}

- (void)runApiCall:(NSDictionary *)params
{
    //Run call
    NSString *urlAsString = [NSString stringWithFormat:@"%@",[params objectForKey:@"url"]];
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:urlAsString]];
    [request setHTTPMethod:[params objectForKey:@"dataType"]];
    [request setValue:@"application/x-www-form-urlencoded; charset=utf-8" forHTTPHeaderField:@"Content-Type"];
    [NSURLConnection sendAsynchronousRequest:request queue:[[NSOperationQueue alloc] init] completionHandler:^(NSURLResponse *response, NSData *data, NSError *error) {
        
        if (error) {
            //TODO Add error return
        } else {
            //Pass return data to main thread
            NSError *myError = nil;
            NSDictionary *res = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&myError];
            [self performSelectorOnMainThread:@selector(apiCallReturn:) withObject:res waitUntilDone:NO];
        }
    }];
}

- (void)apiCallReturn:(NSDictionary *)theResults
{
    //Send CDVPluginResult with return data
    CDVPluginResult* pluginResult = nil;
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:theResults];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:_theCommand.callbackId];
}

@end