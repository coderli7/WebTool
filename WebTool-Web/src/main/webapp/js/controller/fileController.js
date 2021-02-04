//控制层
app.controller('fileController', function ($scope, $controller, bhVersionService, fileService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        fileService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        fileService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne = function (id) {
        fileService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = fileService.update($scope.entity); //修改
        } else {
            serviceObject = fileService.add($scope.entity);//增加
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
        fileService.dele($scope.selectIds).success(
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
        fileService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }


    $scope.selectEntity = [];

    $scope.localStoreSign = "1";

    $scope.selectFile = function () {
        $scope.selectEntity = [];
        for (var i = 0; i < file.files.length; i++) {
            var curFile = file.files[i];
            Object
            curFileInfo = {
                name: curFile.name,
                dateTime: Date.now(),
                uptStatus: '未上传',
                delStatus: false,
                versionType: null,
                versionNumber: null
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
            formdata.append("localStoreSign", $scope.localStoreSign);
            formdata.append("versionType", $scope.selectEntity[i].versionType);
            formdata.append("versionNumber", $scope.selectEntity[i].versionNumber);
            fileService.uploadFile2(formdata).success(function (response) {
                //重置状态
                $scope.selectEntity[response.message].uptStatus = '上传成功!!!';
            });
        }
        
    }


    //标记选项
    $scope.verionItemArr = [];

    $scope.initFileUploadPage = function () {
        bhVersionService.findAll().success(function (response) {
            // console.log(response.len());
            let arr = Array.from(response)

            for (let i = 0; i < arr.length; i++) {
                Object
                curVerionItem = {
                    id: arr[i].versionCode,
                    name: arr[i].versionDesc
                };
                $scope.verionItemArr.push(curVerionItem);
            }
        });
        searchEntity = {'status': ''};
    }


});
