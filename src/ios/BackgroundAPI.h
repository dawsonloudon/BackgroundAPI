#import <Cordova/CDV.h>

@interface BackgroundAPI : CDVPlugin

@property (nonatomic, strong) CDVInvokedUrlCommand *theCommand;

- (void)makeApiCall:(CDVInvokedUrlCommand *)command;
- (void)runApiCall:(NSDictionary *)params;
- (void)apiCallReturn:(NSDictionary *)theResults;

@end;