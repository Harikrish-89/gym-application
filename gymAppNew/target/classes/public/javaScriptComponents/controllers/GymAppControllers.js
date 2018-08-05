/**
 * 
 */
var package_mapping={MONTHLY:30,QUARTERLY:90,HALFYEARLY:180,YEARLY:365}
var gymAppControllers=angular.module('gymAppControllers' ,[]);

gymAppControllers.controller('dailyLoginController',['$scope','dailyLoginService','$mdDialog','$timeout','$window','$filter',
   function($scope,dailyLoginService,$mdDialog,$timeout,$window,$filter){
		$scope.redirectToAdmin=function(){
			$window.open("http://localhost:8080/adminIndex.html")
		}
		$scope.clearInputId=function(){
			document.getElementById('memberDailyLoginForm').reset();
		}
		$scope.dailyLoginSubmit=function(){
			dailyLoginService.dailyLogin(
					{memberId:$scope.id},
					function(resp){	
						//$scope.memberDetails=true
						if(resp.logIn){
							var diff=new Date(resp.member.currPackageEndDate)-(new Date()).getTime()
							var noOfDays=Math.ceil(diff / (1000 * 3600 * 24)); 
							if(noOfDays >0){
								//$scope.message="Hi "+resp.member.name+" you have got "+ noOfDays +" days remaining!!!";
								message="Hi "+resp.member.name+" you have got "+ noOfDays +" days remaining!!!";
								messageValue="Hi you have got "+ noOfDays +" days remaining!!!";
								 $mdDialog.show({
							         template:
							        	 '<md-dialog aria-label="sample dialog" flex=65>' +
								           '  <md-content style="background-color:white;border: 20px solid #3b5998;border-radius:6px;"><div style="height:100px"><h2 style="color:black;position:absolute;top:15%;left:5%"><b>'+
								           		message + 
								           '</b></h2></div></md-content>' +
								           '</md-dialog>',
							       
							      });
								 $timeout(function(){$mdDialog.hide();},5000);
								 
								var u = new SpeechSynthesisUtterance();
							    u.text = messageValue;
							    u.lang = 'en-US';
							    speechSynthesis.speak(u);
							}
							else{
								//$scope.message="Hi "+resp.member.name+" your package has expired on"+ new Date(resp.member.currPackageEndDate)+"!!!";
								var dateToTell = $filter('date')(resp.member.currPackageEndDate, "dd-MMM-yy"); 
								message="Hi "+resp.member.name+" your package has expired on"+ dateToTell+"!!!";
								messageValue="Hi your package has expired !!!";
								$mdDialog.show({
									  template:
										  '<md-dialog aria-label="sample dialog" flex=65>' +
								           '  <md-content style="background-color:white;border: 20px solid #3b5998;border-radius:6px;"><div style="height:150px;"><h2 style="color:red;position:absolute;top:15%;left:5%"><b>'+
								           		message + 
								           '</b></h2></div></md-content>' +
								           '</md-dialog>',
							       
							      })
							    	  $timeout(function(){$mdDialog.hide();},5000);
							    						
								var u = new SpeechSynthesisUtterance();
							    u.text = messageValue;
							    u.lang = 'en-US';
							    speechSynthesis.speak(u);
							}
						}
						else{
							//$scope.message="Log out Successfull!!!!";
							message="Log out Successfull!!!!";
							messageValue="Log out Successfull!!!!";
							$mdDialog.show({
						          template:
						        	  '<md-dialog aria-label="sample dialog" flex=35>' +
							           '  <md-content style="background-color:white;border: 20px solid #3b5998;border-radius:6px;"><div style="height:100px"><h2 style="color:black;position:absolute;top:15%;left:15%"><b>'+
							           		message + 
							           '</b></h2></div></md-content>' +
							           '</md-dialog>',
						       
						      })
						    $timeout(function(){$mdDialog.hide();},5000);
						    
							
							var u = new SpeechSynthesisUtterance();
						    u.text = messageValue;
						    u.lang = 'en-US';
						    speechSynthesis.speak(u);
						}
						
					},function(failure){
						//$scope.memberDetails=true
						//$scope.message=failure.data.message;
						message=failure.data.message;
						$mdDialog.show({
							  template:
						           '<md-dialog aria-label="sample dialog" flex=65>' +
						           '  <md-content style="background-color:white;border: 20px solid #3b5998;border-radius:6px;"><div style="height:100px"><h2 style="color:black;position:absolute;top:15%;left:15%"><b>'+
						           		message + 
						           '</b></h2></div></md-content>' +
						           '</md-dialog>',
					       
					      });
						 $timeout(function(){$mdDialog.hide();},5000);
						 
					}
				);
			}
		}
	]);

gymAppControllers.controller('adminLoginController',['$scope','adminLoginService','$rootScope','Idle',
       function($scope,adminLoginService,$rootScope,Idle){
		
		$rootScope.showHome=false;
		$scope.showError=false;
		$scope.adminSubmit=function(){
			 var headers = $scope.credentials ? {authorization : "Basic "
			        + btoa($scope.credentials.userName + ":" + $scope.credentials.password)
			    } : {};
			  adminLoginService(headers).adminLogin(
				function(resp){
					$rootScope.authenticated=true;
					$rootScope.showLogout=true;
					Idle.watch();
					},
				function(error){
					$scope.showError=true;
				}
			  )
		}
	}
]);
gymAppControllers.controller('memberDetailsController',['$rootScope','$scope','$filter','allMemberService','memberService','memberDailyLoginDetailsService','memberDetailsService','dailyLoginDetailsService','renewMemberService','extendExistingMemberService','ngTableParams',
         function($rootScope,$scope,$filter,allMemberService,memberService,memberDailyLoginDetailsService,memberDetailsService,dailyLoginDetailsService,renewMemberService,extendExistingMemberService,ngTableParams){
			$rootScope.showHome=true;
			$scope.allGymMembers=[];
			$scope.getMemberDetails=function(){
					$scope.showMemberDetails=false;
					memberService.getDetails(
							{memberId:$scope.id},function(resp){
								$scope.showUserNotFound=false;
								$scope.packageDetailsShow=false;
								$scope.memberDailyLoginShow=false;
								$scope.dailyLoginDetailsShow=false;
								$scope.detailsError=false;
								$scope.extendDaysShow=false;
								$scope.searchMembersShow=false;
								$scope.renewMemberShow=false;
								$scope.showMemberDetails=true;
								resp.currPackRechargeDate= new Date(resp.currPackRechargeDate);
								resp.currPackageEndDate=new Date(resp.currPackageEndDate);
								$scope.member=resp;
								var address = $scope.member.address.split(',');
								$scope.member.addrOne =address[0];
								$scope.member.addrTwo =address[1];
								$scope.member.addrThree=address[2];
								$scope.member.addrCity =address[3];
								$scope.member.addrDist =address[4];
								$scope.member.addrState=address[5];
								$scope.member.addrPin=address[6];
							},function(error){
								$scope.member={};
								$scope.detailsError=true;
								$scope.detailsErrorMessage="The given user id is not found!!!";
								
							}
							
					);
				}
				var temArrayCopy=[];
				$scope.showPackageDetails=function(){
					memberDetailsService.getDetails(
							{memberId:$scope.member.id},function(resp){
								$scope.packageDetailsShow=true;
								$scope.memberDailyLoginShow=false;
								$scope.dailyLoginDetailsShow=false;
								$scope.renewMemberShow=false;
								$scope.searchMembersShow=false;
								$scope.detailsError=false;
								$scope.extendDaysShow=false;
								$scope.memberPackageDetails=resp;
								$scope.memberPackageTable= new ngTableParams({
					                page: 1,
					                count: 10
					            }, {
					                total: $scope.memberPackageDetails.length, 
					                getData: function ($defer, params) {
					                    $scope.memberPackageData = $scope.memberPackageDetails.slice((params.page() - 1) * params.count(), params.page() * params.count());
					                    $defer.resolve($scope.memberPackageData);
					                }
					            });
								
								resp.forEach(function(currentVal){
									var tempVar={};
									tempVar.packageType=currentVal.packageType;
									tempVar.packReachDate=new Date(currentVal.packReachDate);
									tempVar.packageEndDate=new Date(currentVal.packageEndDate);
									tempVar.rechargeType=currentVal.rechargeType;
									temArrayCopy.push(tempVar);
								});
								
							},function(error){
								$scope.detailsError=true;
								$scope.detailsErrorMessage="Unable to fetch package details for the member";
							}
					
					);
					
					
					
				}
				$scope.csvMemberPackDetails=temArrayCopy;
				$scope.getMemberPackHeader=function(){return ["Package Type","Package Recharge Date","Package End Date","Recharge Type"]};
				var membLoginTempArr=[];
				$scope.showLoginHistory=function(){
					memberDailyLoginDetailsService.getDetails(
							{memberId:$scope.member.id},function(resp){
								$scope.packageDetailsShow=false;
								$scope.dailyLoginDetailsShow=false;
								$scope.searchMembersShow=false;
								$scope.renewMemberShow=false;
								$scope.detailsError=false;
								$scope.memberDailyLoginShow=true;
								$scope.extendDaysShow=false;
								$scope.memberLoginDetails=resp;
								$scope.memberDailyLoginTable= new ngTableParams({
					                page: 1,
					                count: 10
					            }, {
					                total: $scope.memberLoginDetails.length, 
					                getData: function ($defer, params) {
					                    $scope.memberLoginData = $scope.memberLoginDetails.slice((params.page() - 1) * params.count(), params.page() * params.count());
					                    $defer.resolve($scope.memberLoginData);
					                }
					            });
								resp.forEach(function(currentVal){
									var tempVar={};
									tempVar.logInDate=new Date(currentVal.logInDate);
									tempVar.logInTime=new Date(currentVal.logInTime);
									tempVar.logOutTime=new Date(currentVal.logOutTime);
									membLoginTempArr.push(tempVar);
								});
							},function(error){
								$scope.detailsError=true;
								$scope.detailsErrorMessage="Unable to fetch daily login details for the member";
							}
							
					);
					
					
				}
				$scope.csvMemberDailyLogin=membLoginTempArr;
				$scope.getMemberLoginHeader=function(){return ["Date","Log In Time","Log Out Time"]};
				var dailyLoginTempArr=[];
				$scope.getLoginDetailsByDate=function(){
					$scope.dailyLoginDetailsShow=false;
					var dateToPost=$scope.dateForDetails;
					dateToPost = $filter('date')(dateToPost, "dd-MMM-yy"); 
					dailyLoginDetailsService.getDetails({dateValue:dateToPost},function(resp){
						$scope.dailyLoginDetailsShow=true;
						$scope.packageDetailsShow=false;
						$scope.memberDailyLoginShow=false;
						$scope.detailsError=false;
						$scope.showMemberDetails=false;
						$scope.renewMemberShow=false;
						$scope.extendDaysShow=false;
						$scope.packageDetailsShow=false;
						$scope.searchMembersShow=false;
						$scope.dailyLoginDetails=resp;
						$scope.dailyLoginDetailsTable= new ngTableParams({
			                page: 1,
			                count: 10
			            }, {
			                total: $scope.dailyLoginDetails.length, 
			                getData: function ($defer, params) {
			                    $scope.dailyLoginDetailsData = $scope.dailyLoginDetails.slice((params.page() - 1) * params.count(), params.page() * params.count());
			                    $defer.resolve($scope.dailyLoginDetailsData);
			                }
			            });
						resp.forEach(function(currentVal){
							var tempVar={};
							tempVar.id=currentVal.member.id;
							tempVar.logInTime=new Date(currentVal.logInTime);
							tempVar.logOutTime=new Date(currentVal.logOutTime);
							dailyLoginTempArr.push(tempVar);
						});
					},function(error){
						$scope.detailsError=true;
						$scope.detailsErrorMessage="Unable to get the daily login details for the given date";
					});
				}
				$scope.csvDailyLogin=dailyLoginTempArr;
				$scope.getDailyLoginHeader=function(){return ["Member Id","Login Time","Logout Time"]};
				
				$scope.renewMember=function(){
					$scope.packageDetailsShow=false;
					$scope.memberDailyLoginShow=false;
					$scope.searchMembersShow=false;
					$scope.extendDaysShow=false;
					$scope.renewMemberShow=true;
				}
			
				$scope.renewExistingMember=function(){
					renewMemberService.postMember($scope.member,function(resp){
						$scope.packageDetailsShow=false;
						$scope.renewMemberShow=false;
						$scope.memberDailyLoginShow=false;
						$scope.searchMembersShow=false;
						$scope.detailsError=true;
						$scope.detailsErrorMessage="Saved the member details successfully";
						
					},function(error){
						$scope.detailsError=true;
						$scope.detailsErrorMessage="Unable to Save the Member please logout and login and try again";
					});
				}
				$scope.extendFormShow=function(){
					$scope.extendDaysShow=true;
					$scope.packageDetailsShow=false;
					$scope.memberDailyLoginShow=false;
					$scope.searchMembersShow=false;
					$scope.renewMemberShow=false;
				}
				$scope.exdendMemberDays=function(){
					$scope.member.currPackageEndDate=new Date(($scope.member.currPackageEndDate.getTime()+($scope.extendDays*86400000)));
						
					extendExistingMemberService.postMember($scope.member,function(resp){
						$scope.packageDetailsShow=false;
						$scope.renewMemberShow=false;
						$scope.memberDailyLoginShow=false;
						$scope.searchMembersShow=false;
						$scope.detailsError=true;
						$scope.detailsErrorMessage="Extended Member Days Successfully";
						
					},function(error){
						$scope.detailsError=true;
						$scope.detailsErrorMessage="Unable to Save the Member please logout and login and try again";
					});
				}
				$scope.searchMembersQuery=function(){
					$scope.searchMembersShow=false;
					
					allMemberService.getDetails(function(resp){
						$scope.dailyLoginDetailsShow=false;
						$scope.packageDetailsShow=false;
						$scope.memberDailyLoginShow=false;
						$scope.detailsError=false;
						$scope.showMemberDetails=false;
						$scope.renewMemberShow=false;
						$scope.extendDaysShow=false;
						$scope.packageDetailsShow=false;
						$scope.searchMembersShow=true;
						$scope.allGymMembers=resp;
						$scope.memberDetailsTable= new ngTableParams({
			                page: 1,
			                count: 10
			            }, {
			                total: $scope.allGymMembers.length, 
			                getData: function ($defer, params) {
			                    $scope.allGymMembersData = $scope.allGymMembers.slice((params.page() - 1) * params.count(), params.page() * params.count());
			                    $defer.resolve($scope.allGymMembersData);
			                }
			            });
					},function(error){
						$scope.detailsError=true;
						$scope.detailsErrorMessage="Unable to fetch the Member details";
					});
					
				}
}]);

gymAppControllers.controller('addNewMemberController',['$rootScope','$scope','addNewMemberService',function($rootScope,$scope,addNewMemberService){
			$rootScope.showHome=true;
			$scope.addNewMember=function(){
				var memberToAdd={};
				memberToAdd.name=$scope.member.name;
				memberToAdd.email=$scope.member.email;
				memberToAdd.packageType=$scope.member.packageType;
				memberToAdd.currPackRechargeDate=$scope.member.currPackRechargeDate;
				var address=$scope.member.addrOne +","+$scope.member.addrTwo +","+$scope.member.addrThree+
								","+$scope.member.addrCity +","+$scope.member.addrDist +","+$scope.member.addrState
								+ ","+$scope.member.addrPin;
				memberToAdd.address=address;
				memberToAdd.phone=$scope.member.phone;
				addNewMemberService.addMember(memberToAdd,function(resp){
					$scope.memberAdded=true;
					$scope.showErrorMessage=false;
					$scope.addedMember=resp;
					},function(error){
					$scope.memberAdded=false;
					$scope.showErrorMessage=true;
					$scope.error=error;
				});
			}
}]);

gymAppControllers.controller('generateReportsController',['$rootScope','$scope','$filter','packageDetailsService','logInDetailsService','ngTableParams',function($rootScope,$scope,$filter,packageDetailsService,logInDetailsService,ngTableParams){
			$rootScope.showHome=true;
			var dailyLoginTempArr=[];
			$scope.getLoginDetailsBetween=function(){
				$scope.newLoginDetailsShow=false;
				var fromDateToPost=$scope.loginFromDate;
				fromDateToPost = $filter('date')(fromDateToPost, "dd-MMM-yy"); 
				var toDateToPost=$scope.loginToDate;
				toDateToPost = $filter('date')(toDateToPost, "dd-MMM-yy"); 
				logInDetailsService.getDetails({fDate:fromDateToPost,tDate:toDateToPost},
				function(resp){
					$scope.newLoginDetailsShow=true;
					$scope.newDetailsError=false;
					$scope.newPackDetailsShow=false;
					$scope.dailyLoginDetails=resp;
					$scope.dailyLoginTable= new ngTableParams({
		                page: 1,
		                count: 10
		            }, {
		                total: $scope.dailyLoginDetails.length, 
		                getData: function ($defer, params) {
		                    $scope.dailyLoginData = $scope.dailyLoginDetails.slice((params.page() - 1) * params.count(), params.page() * params.count());
		                    $defer.resolve($scope.dailyLoginData);
		                }
		            });
					resp.forEach(function(currentVal){
						var tempVar={};
						tempVar.id=currentVal.member.id;
						tempVar.name=currentVal.member.name
						tempVar.logInTime=new Date(currentVal.logInTime);
						tempVar.logOutTime=new Date(currentVal.logOutTime);
						dailyLoginTempArr.push(tempVar);
					});
					
				},function(error){
					$scope.newLoginDetailsShow=false;
					$scope.newDetailsError=true;
					$scope.newPackDetailsShow=false;
					$scope.detailsErrorMessage="Unable to fetch the details.Some Problem Occured!!!";
				});
			}
			$scope.csvDailyLogin=dailyLoginTempArr;
			$scope.getDailyLoginHeader=function(){return ["Member Id","Member Name","Log In Time","Log Out Time"]};
			var packageTempArray=[];
			$scope.getRenewalDetailsBetween=function(){
			
				var fromDateToPost=$scope.jandRFromDate;
				fromDateToPost = $filter('date')(fromDateToPost, "dd-MMM-yy"); 
				var toDateToPost=$scope.jandRToDate;
				toDateToPost = $filter('date')(toDateToPost, "dd-MMM-yy"); 
				packageDetailsService.getDetails({fDate:fromDateToPost,tDate:toDateToPost},
						function(resp){
					$scope.newLoginDetailsShow=false;
					$scope.newDetailsError=false;
					$scope.newPackDetailsShow=true;
					$scope.packageDetails=resp;
					$scope.packageDetailsTable= new ngTableParams({
		                page: 1,
		                count: 10
		            }, {
		                total: $scope.packageDetails.length, 
		                getData: function ($defer, params) {
		                    $scope.packageDetailData = $scope.packageDetails.slice((params.page() - 1) * params.count(), params.page() * params.count());
		                    $defer.resolve($scope.packageDetailData);
		                }
		            });
					resp.forEach(function(currentVal){
						var tempVar={};
						tempVar.id=currentVal.member.id;
						tempVar.name=currentVal.member.name
						tempVar.packageType=currentVal.packageType
						tempVar.logInTime=new Date(currentVal.packReachDate);
						tempVar.logOutTime=new Date(currentVal.packageEndDate);
						tempVar.rechargeType=currentVal.rechargeType;
						packageTempArray.push(tempVar);
					});
				},function(error){
					$scope.newLoginDetailsShow=false;
					$scope.newDetailsError=true;
					$scope.newPackDetailsShow=false;
					$scope.detailsErrorMessage="Unable to fetch the details.Some Problem Occured!!!";
				});
			}
			$scope.csvPackageDetails=packageTempArray;
			$scope.getPackageDetailsHeader=function(){return ["Member Id","Member Name","Package Type","Package Recharge Date","Package End Date","Recharge Type"]};
}]);

gymAppControllers.controller('logoutController',['$rootScope','$scope','$http','$location',function($rootScope,$scope,$http,$location){
	$scope.performLogOut = function() {
		  $rootScope.showHome=false;
		  $http.post('logout', {}).finally(function() {
		    $rootScope.authenticated = false;
		    $rootScope.showLogout=false;
		    $location.path("/");
		  });
		}
}]);