(function() {
'use strict';
	var commonAuth = angular.module('wdApp.storage', []);

	commonAuth.factory('UtilStorage', ['localStorageService', '$q', 'DemoData', function(localStorageService, $q, DemoData) {
		
		return {
			get: function(_STORAGE_ID, sFileDir, bRefresh, fnCallBack) 
			{debugger
				if(bRefresh)
				{
					localStorageService.remove(_STORAGE_ID);
				}
				if(!localStorageService.get(_STORAGE_ID))
				{	
					DemoData.getDemoData(sFileDir, function(res){
						localStorageService.set(_STORAGE_ID, JSON.stringify(res));	
						if(fnCallBack)
						{
							fnCallBack(res);
						}
					});
					
				}
				var oStorage = localStorageService.get(_STORAGE_ID);

				
				if(typeof oStorage === 'object')
				{
					oStorage = oStorage;
				}else{
					oStorage = JSON.parse(oStorage);
				}	
				
				if(fnCallBack)
				{
					fnCallBack(oStorage);
				}
				return oStorage;
			},
			put: function(_STORAGE_ID, oObject) 
			{
				return localStorageService.set(_STORAGE_ID, JSON.stringify(oObject));
			},
			refreshData: function(_localStorageID, _oData)
			{
				if(_oData)
				{
						localStorageService.remove(_localStorageID);
						localStorageService.set(_localStorageID, JSON.stringify(_oData));	
				}
			},
		};
	  }])
})();