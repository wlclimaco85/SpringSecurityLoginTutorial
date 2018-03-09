(function() {
'use strict';
angular.module('wdApp.apps.employeesdata', [])
	.factory('Employeesdata', ['$http', 'toastr', 'toastrConfig', 'DemoData', 'localStorageService', 'UtilStorage', function($http, toastr, toastrConfig, DemoData, localStorageService, UtilStorage){

		toastrConfig.closeButton = true;
		var STORAGE_ID   = "EMPLOYEES";
		var STORAGE_FILE = "./demodata/employees.json";
		
		function compareTwoEmployees(oObject1, oObject2)
		{
			var bValid = false;

			if (oObject1.Fname  === oObject2.Fname && 
			oObject1.Lname  === oObject2.Lname && 
			oObject1.email  === oObject2.email && 
			oObject1.phone  === oObject2.phone && 
			oObject1.title3  === oObject2.title3)
			{
				bValid = true;
			}
			
			return bValid;
		}

		return{

			add: function (oEmployees) 
			{
				if (oEmployees.length === 0) {
					  return;
				}
				UtilStorage.set("EMPLOYEES", oEmployees);
				toastr.success('New Employees: "' + oEmployees.email + '" added');
			},
	
			update: function (oEmployees, oEmployeesOld)
			{	
				if (oEmployees.length === 0) {
				  return;
				}
				var oLocalStorage = UtilStorage.get(STORAGE_ID, STORAGE_FILE);
				for(var x = 0; x < oLocalStorage.length; x++)
				{
						if(compareTwoEmployees(oLocalStorage[x], oEmployeesOld))
						{
							oLocalStorage[x] = oEmployees;
						}
				}
				UtilStorage.refreshData("EMPLOYEES", oLocalStorage);
				toastr.success('Employees updated.', 'Information');
			},
	
			deleteEmployees: function (oEmployees) 
			{
				
				if (oEmployees.length === 0) {
				  return;
				}
				
				var oLocalStorage = UtilStorage.get(STORAGE_ID, STORAGE_FILE);
				var iIndex = 0;
				if(oLocalStorage)
				{
					for(var x = 0; x < oLocalStorage.length;x++)
					{
							if(compareTwoEmployees(oLocalStorage[x], oEmployees))
							{
								oLocalStorage.splice(x, 1);
							}
					}
					
					UtilStorage.refreshData("EMPLOYEES", oLocalStorage);
				}
				toastr.success('Employees deleted.', 'Information');
			}

	};
}]);

}).call(this);

