#import <Cordova/CDV.h>

@interface BackgroundAPI : CDVPlugin

@property (nonatomic, strong) CDVInvokedUrlCommand *theCommand;

- (void)call:(CDVInvokedUrlCommand *)command;
- (void)runCall:(NSDictionary *)params;
- (void)callReturn:(NSDictionary *)theResults;

@end;