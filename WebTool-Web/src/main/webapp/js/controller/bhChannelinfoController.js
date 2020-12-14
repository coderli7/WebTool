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
        $scope.searching = true;
        var obj = {'startDate': '', 'endDate': ''};
        obj.startDate = document.querySelector("#startDate").value;
        obj.endDate = document.querySelector("#endDate").value;
        $scope.searchEntity.remark = JSON.stringify(obj);
        bhChannelinfoService.search(page, rows, $scope.searchEntity).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;//更新总记录数
            $scope.unWatchPage = false;
            $scope.searching = false;
        });
    }


    $scope.searchNew = function () {
        if ($scope.searching) {
            return;
        }
        $scope.paginationConf.currentPage = 1;
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }


    //自动刷新数据

    $scope.$watch('searchEntity.quickDateRange', function (newValue, oldValue) {
        //根据选择日期,自动修改
        var myDate = new Date(); //毫秒，1970.1.1开始
        if ("0" == newValue) {
            //昨天
            myDate = new Date(myDate.getTime() - (24 * 60 * 60 * 1000));
        } else if ("1" == newValue) {
            //今天
        } else if ("2" == newValue) {
            //明天
            myDate = new Date(myDate.getTime() + (24 * 60 * 60 * 1000));
        } else if ("3" == newValue) {
            //后天
            myDate = new Date(myDate.getTime() + (2 * 24 * 60 * 60 * 1000));
        }
        var curDt = myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-' + myDate.getDate();
        $scope.searchEntity.startDate = curDt + ' 00:00:00';
        $scope.searchEntity.endDate = curDt + ' 23:59:59';
    });


    $scope.brandList = {data: [{id: 1, text: '联想'}, {id: 2, text: '华为'}, {id: 3, text: '小米'}]};


});
