//控制层 
app.controller('uploadController', function ($scope, $controller,
                                             uploadService) {

    $controller('baseController', {
        $scope: $scope
    });// 继承

    // 读取列表数据绑定到表单中
    $scope.findAll = function () {
        sellerService.findAll().success(function (response) {
            $scope.list = response;
        });
    }

    // 分页
    $scope.findPage = function (page, rows) {
        sellerService.findPage(page, rows).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;// 更新总记录数
        });
    }

    // 查询实体
    $scope.findOne = function (id) {
        sellerService.findOne(id).success(function (response) {
            $scope.entity = response;
        });
    }

    // 保存
    $scope.save = function () {
        var serviceObject;// 服务层对象
        if ($scope.entity.id != null) {// 如果有ID
            serviceObject = sellerService.update($scope.entity); // 修改
        } else {
            serviceObject = sellerService.add($scope.entity);// 增加
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
        // 获取选中的复选框
        sellerService.dele($scope.selectIds).success(function (response) {
            if (response.success) {
                $scope.reloadList();// 刷新列表
                $scope.selectIds = [];
            }
        });
    }

    $scope.searchEntity = {};// 定义搜索对象


    $scope.uploadResults = ""; //定义上传结果

    // 搜索
    $scope.search = function (page, rows) {
        sellerService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;// 更新总记录数
            });
    }

    // 审核
    $scope.updateStatus = function (sellerId, status) {
        console.log('审核' + sellerId);
        sellerService.updateStatus(sellerId, status).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();// 重新加载
                } else {
                    alert(response.message);
                }
            });
    }


    $scope.newGuid = function () {
        return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g,
            function (a) {
                var b, c;
                return b = 16 * Math.random() | 0, c = "x" == a ? b
                    : 3 & b | 8, c.toString(16)
            })
    }

    $scope.selectEntity = [];


    $scope.selectFile = function () {
        $scope.selectEntity = [];
        for (var i = 0; i < file.files.length; i++) {
            var curFile = file.files[i];
            Object
            curFileInfo = {
                name: curFile.name,
                dateTime: Date.now(),
                uptStatus: '未上传',
                delStatus: false
            };
            $scope.selectEntity.push(curFileInfo);
        }
        var sltedMsg = "选中" + file.files.length + "个文件";
        // $("#fileCount").val(sltedMsg);
        $scope.uploadResults = "";
        $scope.$apply();
    }

    $scope.delFile = function (index) {
        if (index >= 0) {
            $scope.selectEntity.splice(index, 1);
            $scope.$apply();
        }
    }

    // 上传图片
    $scope.uploadFile = function () {
        for (var i = 0; i < file.files.length; i++) {
            var formdata = new FormData();
            var curFile = file.files[i];
            formdata.append('file', curFile);
            formdata.append("index", i);
            uploadService.uploadFile2(formdata).success(function (response) {
                //重置状态
                console.log($scope.selectEntity[response.message]);
                $scope.selectEntity[response.message].uptStatus = '上传成功!!!';
            });
        }
    }

});
