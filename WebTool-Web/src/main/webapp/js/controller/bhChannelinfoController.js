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
                if ($scope.entity.remark != null && $scope.entity.remark != '') {
                    console.log("格式化展示");
                    var channelInfo = $scope.entity.remark;
                    if (channelInfo != null && channelInfo != '') {
                        console.log("渠道信息不为空");
                        var newChannelInfo = channelInfo.replace(/\\/g, '');
                        newChannelInfo = newChannelInfo.replace('"[', '').replace(']"', '');
                        $scope.entity.remark = newChannelInfo;
                    }
                    $scope.entity.remark = JSON.stringify(JSON.parse($scope.entity.remark), null, 4);
                }
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
        var pwd = prompt("请输入删除口令:", "")
        if (pwd != null && pwd != "") {
            if ('91bihu@admin' == pwd) {
                //获取选中的复选框
                bhChannelinfoService.dele($scope.selectIds).success(
                    function (response) {
                        if (response.success) {
                            $scope.reloadList();//刷新列表
                            $scope.selectIds = [];
                        }
                    }
                );
            } else {
                alert("口令错误!");
            }
        }
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
        } else if ("4" == newValue) {
            //后天
            myDate = new Date(myDate.getTime() + (3 * 24 * 60 * 60 * 1000));
        }


        if ("5" == newValue) {
            //全部
            $scope.searchEntity.startDate = "";
            $scope.searchEntity.endDate = "";
        } else {
            var curDt = myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-' + myDate.getDate();
            $scope.searchEntity.startDate = curDt + ' 00:00:00';
            $scope.searchEntity.endDate = curDt + ' 23:59:59';
        }
    });


    $scope.brandList = {data: [{id: 1, text: '联想'}, {id: 2, text: '华为'}, {id: 3, text: '小米'}]};


    //展示加密信息
    $scope.showDetailInfo = function () {
        var pwd = prompt("请输入口令:", "")
        if (pwd != null && pwd != "") {
            if ('91bihu@admin' == pwd) {
                $("#detailInfo").attr("style", "block")
            } else {
                alert("口令错误!");
            }
        }
    }


});
