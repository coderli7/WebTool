//控制层 
app.controller('imageCaseInfoController', function ($scope, $location,
                                                    $controller, imageCaseInfoService) {

    $controller('baseController', {
        $scope: $scope
    });// 继承

    // 读取列表数据绑定到表单中
    $scope.findAll = function () {
        imageCaseInfoService.findAll().success(function (response) {
            $scope.list = response;
        });
    }

    // 分页
    $scope.findPage = function (page, rows) {
        imageCaseInfoService.findPage(page, rows).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;// 更新总记录数
        });
    }

    // 查询实体
    $scope.findOne = function (id) {
        imageCaseInfoService.findOne(id).success(function (response) {
            $scope.entity = response;
            if ($scope.entity != null && $scope.entity.imageCaseInfo != null && $scope.entity.imageCaseInfo.result != null && $scope.entity.imageCaseInfo.result != '') {
                console.log("结果不为空");
                $scope.entity.imageCaseInfo.result = JSON.stringify(JSON.parse($scope.entity.imageCaseInfo.result), null, 4);
            }
        });
    }

    // 保存
    $scope.save = function () {
        var serviceObject;// 服务层对象
        if ($scope.entity.id != null) {// 如果有ID
            serviceObject = imageCaseInfoService.update($scope.entity); // 修改
        } else {
            serviceObject = imageCaseInfoService.add($scope.entity);// 增加
        }
        serviceObject.success(function (response) {
            if (response.success) {
                // 重新查询
                $scope.reloadList();// 重新加载
            } else {
                alert(response.message);
            }
        });
    }

    // 批量删除
    $scope.dele = function () {
// alert($scope.selectIds);
        // 获取选中的复选框
        imageCaseInfoService.dele($scope.selectIds).success(function (response) {
            if (response.success) {
                $scope.reloadList();// 刷新列表
                $scope.selectIds = [];
            }
        });
    }

    $scope.searchEntity = {};// 定义搜索对象

    // 标记行是否选中
    // $scope.selectAllLineChkBox = false;

    // 搜索
    $scope.search = function (page, rows) {
        $scope.selectAllLineChkBox = false;
        $scope.selectIds = [];
        imageCaseInfoService.search(page, rows, $scope.searchEntity).success(
            function (response) {

                // 默认不选中行
                //     response.rows.forEach(item = > {
                //         item.pageChecked = false;
                // })
                //     ;
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;// 更新总记录数
            });
    }

    $scope.openImgDetail = function (caseId, imgName) {
        window.open("image.html#?caseId=" + caseId);
        // location.href = "image.html#?caseId=" + caseId;
    }

    // 接受传入的图片详情信息
    $scope.caseId = "";
    $scope.imgName = "";
    $scope.imgDetailInfo = {};
    $scope.initImgDetail = function () {
        $scope.caseId = $location.search()["caseId"];
        $scope.imgName = $location.search()["imgName"];
        // 根据查到的图片信息，请求服务器，当前案件的当前图片信息

        imageCaseInfoService.searchByCaseIdAndImgName($scope.caseId,
            $scope.imgName).success(function (response) {
            $scope.imgDetailInfo = response;
        });

    }

    // 获取图片最新状态
    $scope.lastedStatus = function (caseId) {
        imageCaseInfoService.lastedStatus(caseId).success(function (response) {
            alert(response);
        });
    }

    // reSend
    $scope.reSend = function (caseId) {
        imageCaseInfoService.reSend(caseId).success(function (response) {
            alert(response);
        });
    }

    $scope.setColor = function (item) {
        /*
         * var p = ""; if (1 == status) { p = 'red'; } else if (2 == status) { p =
         * 'yellow'; } else if (3 == status) { p = 'green'; } else if (4 ==
         * status) { p = 'black'; } return {"background-color": p};
         */
        console.log(item);
        return "red";
    };

    $scope.openDetailInNewTab = function (caseid, imgname) {

        console.log(imgname);
        var newtab = {
            id: caseid,
            title: '详情' + caseid,
            url: 'image.html#?caseId=' + imgname + '&imgName=' + imgname,
            ptype: 'home'
        };
        parent.addTabs(newtab);
    }

});
