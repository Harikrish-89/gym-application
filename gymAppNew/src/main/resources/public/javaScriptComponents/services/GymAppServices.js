/**
 * 
 */
var gymAppServices = angular.module('gymAppServices',['ngResource']);

gymAppServices.factory('dailyLoginService',['$resource',function($resource){
	return $resource('http://localhost:8080/daily/dailyLogin?id=:memberId', {}, {
	      dailyLogin: {
	    	  method:'GET', params:{memberId:'@id'}
	    	  }
	});
}]);

gymAppServices.factory('adminLoginService',['$resource',function($resource){
	return function(customHeaders){
		return $resource('http://localhost:8080/gym/user',{},{
		adminLogin:{
			method:'GET',
			headers: customHeaders
		}});}
	
}]);

gymAppServices.factory('memberService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/getMember?id=:memberId',{},{
		getDetails:{
			method:'GET',
			params: {memberId:'@id'},
			isArray: false
		}
	});
}]);

gymAppServices.factory('allMemberService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/getAllMembers',{},{
		getDetails:{
			method:'GET',
			isArray: true
		}
	});
}]);

gymAppServices.factory('memberDetailsService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/getMemberHistory?id=:memberId',{},{
		getDetails:{
			method:'GET',
			params: {memberId:'@id'},
			isArray: true
		}
	});
}]);

gymAppServices.factory('memberDailyLoginDetailsService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/getDailyLoginDetailsForMemberId?id=:memberId',{},{
		getDetails:{
			method:'GET',
			params: {memberId:'@id'},
			isArray: true
		}
	});
}]);

gymAppServices.factory('dailyLoginDetailsService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/getDailyLoginDetailsForDate?date=:dateValue',{},{
		getDetails:{
			method:'GET',
			params: {dateValue:'@date'},
			isArray: true
		}
	});
}]);
gymAppServices.factory('renewMemberService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/updateExisting',{},{
		postMember:{
			method:'POST'
			}
	});
}]);

gymAppServices.factory('extendExistingMemberService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/extendExisting',{},{
		postMember:{
			method:'POST'
			}
	});
}]);
gymAppServices.factory('addNewMemberService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/addmember',{},{
			addMember:{
				method:'POST'
			}
	});
}]);
gymAppServices.factory('logInDetailsService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/getLoginDetailsByDate?fromDate=:fDate&toDate=:tDate',{},{
			getDetails:{
				method:'GET',
				params: {fDate:'@fromDate',tDate:'@toDate'},
				isArray:true
					
			}
	});
}]);
gymAppServices.factory('packageDetailsService',['$resource',function($resource){
	return $resource('http://localhost:8080/gym/getPackageDetailsByDate?fromDate=:fDate&toDate=:tDate',{},{
			getDetails:{
				method:'GET',
				params: {fDate:'@fromDate',tDate:'@toDate'},
				isArray:true
					
			}
	});
}]);
