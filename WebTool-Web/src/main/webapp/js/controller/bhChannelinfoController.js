//控制层
app.controller('bhChannelinfoController', function ($scope, $controller, $q, bhChannelinfoService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        bhChannelinfoService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        bhChannelinfoService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne = function (id) {
        bhChannelinfoService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = bhChannelinfoService.update($scope.entity); //修改
        } else {
            serviceObject = bhChannelinfoService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    }


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        bhChannelinfoService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    }

    $scope.searchEntity = {};//定义搜索对象


    //搜索
    $scope.search = function (page, rows) {
        console.log("begin!!!");
        console.log(new Date().getTime());
        var obj = {'startDate': '', 'endDate': ''};
        obj.startDate = document.querySelector("#startDate").value;
        obj.endDate = document.querySelector("#endDate").value;
        $scope.searchEntity.remark = JSON.stringify(obj);

        bhChannelinfoService.search(page, rows, $scope.searchEntity).success(function (response) {
            console.log("得到结果:" + response.total)
            console.log(new Date().getTime());
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;//更新总记录数
            console.log(new Date().getTime());
            $scope.unWatchPage = false;
            console.log("得到结果:" + "赋值完毕！！！")
        });
        console.log("OK!!!");
    }


    $scope.searchNew = function () {
        $scope.paginationConf.currentPage = 1;
        search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }


});
