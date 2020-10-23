//服务层
app.service('fileService', function ($http) {

    //读取列表数据绑定到表单中
    this.findAll = function () {
        return $http.get('../file/findAll.do');
    }
    //分页
    this.findPage = function (page, rows) {
        return $http.get('../file/findPage.do?page=' + page + '&rows=' + rows);
    }
    //查询实体
    this.findOne = function (id) {
        return $http.get('../file/findOne.do?id=' + id);
    }
    //增加
    this.add = function (entity) {
        return $http.post('../file/add.do', entity);
    }
    //修改
    this.update = function (entity) {
        return $http.post('../file/update.do', entity);
    }
    //删除
    this.dele = function (ids) {
        return $http.get('../file/delete.do?ids=' + ids);
    }
    //搜索
    this.search = function (page, rows, searchEntity) {
        return $http.post('../file/search.do?page=' + page + "&rows=" + rows, searchEntity);
    }


    // 上传文件
    this.uploadFile2 = function (formdata) {
        return $http({
            url: '../file/fileUpload.do',
            method: 'post',
            data: formdata,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        });
    }
});
