#import <Cordova/CDV.h>

@interface BackgroundAPI : CDVPlugin

- (void)call:(CDVInvokedUrlCommand *)command;
- (void)runCall:(NSMutableArray *)params;
- (void)callReturn:(NSString *)theString;

@end;