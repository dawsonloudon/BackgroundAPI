/*-
 * cordova BackgroundAPI Plugin for iOS
 *
 * Created by Dawson Loudon 2013 MIT Licensed
 *
 * Usages:
 *
 * backgroundAPI.makeApiCall({ url: 'http://www.google.com/api', type: 'GET', data: { name: 'Some One', age-range: '20-30' } }, functionName); //full example
 * backgroundAPI.makeApiCall({ url: 'http://www.google.com/api' }, null); //minimum usage
 */

var exec = require('cordova/exec');

var BackgroundAPI = {
	makeApiCall: function(params,callback) {
		var defaults = {
			url: null,
			dataType: 'GET',
			dataItems: null
		};

		for(var key in defaults) {
			if(typeof params[key] !== 'undefined') {
				defaults[key] = params[key];
			}
		}

		exec(callback,null,'BackgroundAPI','makeApiCall', [defaults]);
	}

};

module.exports = BackgroundAPI;
