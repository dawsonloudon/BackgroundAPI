/*-
 * cordova BackgroundAPI Plugin for Android and iOS
 *
 * Created by Dawson Loudon 2013 MIT Licensed
 *
 * Usages:
 *
 * plugins.backgroundAPI.call({ url: 'http://www.google.com/api', type: 'GET', data: { name: 'Some One', age-range: '20-30' } }, functionName); //full example
 * plugins.backgroundAPI.call({ url: 'http://www.google.com/api' }, null); //minimum usage
 */

	/**
	 * Empty Constructor
	 */
	var BackgroundAPI = function() {
	};

	/**
	 * Make a call
	 */
	BackgroundAPI.prototype.call = function(params,callback) {
		var default = {
			url: null,
			type: 'GET',
			data: null
		};

		for(var key in default) {
			if(typeof params[key] !== 'undefined') {
				default[key] = params[key];
			}
		}

		cordova.exec(callback,null,'BackgroundAPI','call', new Array(default));
	}

        /**
         * Register this plugin with cordova
         */
        var backgroundAPI = new BackgroundAPI();

        module.exports = backgroundAPI;
