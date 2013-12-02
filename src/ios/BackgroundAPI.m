#import "BackgroundAPI.h"

@implementation BackgroundAPI

- (void)call:(CDVInvokedUrlCommand *)command
{
    //TODO Format call
    //TODO Run call in background
}

- (void)runCall:(NSMutableArray *)params
{
    //TODO Run call
    //TODO Pass return data to main thread
}

- (void)callReturn:(NSString *)theString
{
    //TODO Send CDVPluginResult with return data
}

@end