(function() {
  angular.module('wdApp.apps.employees', []).controller('EmployeesController', 
  ['$scope', 'toastr', 'toastrConfig', 'UtilStorage', 'Employeesdata',
	function($scope, toastr, toastrConfig, UtilStorage, Employeesdata) {
		//why?
		var cvm = this;			
		var STORAGE_ID   = "EMPLOYEES";
		var STORAGE_FILE = "./demodata/employees.json";
		cvm.isActive = false;
		var skipPage = false;	//used to ensure not calling server multiple times
		toastrConfig.closeButton = true;		

		cvm.employeesOld = {};
		cvm.employees = {
			Fname  : "",
			Lname  : "",
			email  : "",
			phone  : "",
			title3 : ""
		};
				
		//grid column defs
		var employeesColumnDefs = [
			{displayName: "First Name", field: "Fname", enableColumnMenu: false, enableSorting: false, cellTooltip: true},
			{displayName: "Last Name", field: "Lname", enableColumnMenu: false, enableSorting: false, cellTooltip: true},
			{displayName: "Email", field: "email", enableColumnMenu: false, enableSorting: false, cellTooltip: true},
			{displayName: "Phone", field: "phone", enableColumnMenu: false, enableSorting: false, cellTooltip: true},
			{displayName: "Title", field: "title3", enableColumnMenu: false, enableSorting: false, cellTooltip: true}
	
		];
		
 		//grid options
		cvm.employeesGridOptions = {
			columnDefs: employeesColumnDefs,
			enableFullRowSelection: true,
			enableRowHeaderSelection: false,
			multiSelect: false,			
			paginationPageSizes: [20],			
			pagingPageSize: 20,
			enableColumnResizing: true,
			onRegisterApi: function (gridApi) {
				//why is this needed
				$scope.gridApi = gridApi;
				//grid row select function				
				gridApi.selection.on.rowSelectionChanged($scope,function(row){
					cvm.employees = new qat.model.employees(row.entity);
					cvm.employeesOld = new qat.model.employees(row.entity);
				});	
			}			
		};
		
		//initial data load
		getEmployeesdata();	


		

		function getEmployeesdata(_bArq)
		{
				fnCallback = function(res)
				{
					var dataThisPage = (res) ? res : new Array();
					cvm.gList =  dataThisPage;	
					cvm.employeesGridOptions.data = dataThisPage;
					cvm.isActive = false;	
					cvm.employeesGridOptions.totalItems = (res) ? res.length : 0;	
				}
				
				UtilStorage.get(STORAGE_ID, STORAGE_FILE, _bArq ? _bArq : false, fnCallback);
		};	

		function validEmail(sEmail) 
		{
			var oLocalStorage = UtilStorage.get(STORAGE_ID, STORAGE_FILE);
			var bEmailValid = true;
			for(var x = 0; x < oLocalStorage.length; x++)
			{
				if(sEmail === oLocalStorage[x].email)
				{
					if(cvm.employeesOld.email !== sEmail)
					{
						bEmailValid = false;
					}
				}
			}
			return bEmailValid;
		}
		
		//refresh employees function
		cvm.refreshEmployees = function(refreshCount) {
			cvm.isActive = !cvm.isActive;
			//clear form data
			cvm.clearForm();		
			//var send_url = refresh_url + "?refreshInt=" + refreshCount + "&retList=true&retPaged=true";
			getEmployeesdata(true);
		};			
		
		//form methods
		//reusable clear form logic
		cvm.clearForm = function (){
			//clear data
			cvm.employees.Fname  = "";
			cvm.employees.Lname  = "";
			cvm.employees.email  = "";
			cvm.employees.phone  = "";
			cvm.employees.title3 = "";

			cvm.employeesOld = {};
			//set form to pristine
			cvm.form_employees.$setPristine();	
			//clear grid selection //why?
			$scope.gridApi.selection.clearSelectedRows();
		};
		
		//reusable button form logic		
		cvm.employeesButtons = function(_btnType){	
			//console.log(_btnType);	
			//debugger	
			if (cvm.form_employees.$valid)
			{	
				switch (_btnType) {
				//Add Button							
				case 'A':
					if(validEmail(cvm.employees.email))
					{
						Employeesdata.add(cvm.employees);
						getEmployeesdata();	
						cvm.clearForm();
					}
					else
					{
						toastr.error('employees form error, email duplicate.', 'Error');	
					}
					break;
				//Update Button						
				case 'U':
					if(validEmail(cvm.employees.email))
					{
						Employeesdata.update(cvm.employees, cvm.employeesOld);	
						getEmployeesdata();
						cvm.clearForm();
					}
					else
					{
						toastr.error('employees form error, email duplicate.', 'Error');	
					}
					break;
				//Delete Button	
				case 'D':
					Employeesdata.deleteEmployees(cvm.employees);
					getEmployeesdata();
					cvm.clearForm();
				
					break;	
				//List Button	
				case 'L':
					getEmployeesdata();
					break;					
				default: 
					console.log('Invalid button type: ' + _btnType);					
				};
				//clear the form
				cvm.clearForm();
			}
			else{
				if (_btnType == 'L'){
					getEmployeesdata();
					//clear the form
					cvm.clearForm();
				}
				else{	
					toastr.error('employees form error, please correct and resubmit.', 'Error');
				}	
			}		
		};
		
    }
  ]);
}).call(this);

