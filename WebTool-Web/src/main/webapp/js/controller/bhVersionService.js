//服务层
app.service('bhVersionService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../bhVersion/findAll.do');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../bhVersion/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../bhVersion/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../bhVersion/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../bhVersion/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../bhVersion/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../bhVersion/search.do?page='+page+"&rows="+rows, searchEntity);
	}    	
});
