app.controller('searchController', function ($scope, $location, $controller,
                                             searchService, fileService) {

    // 继承
    $controller('baseController', {
        $scope: $scope
    });

    $scope.tips = "";

    // 查询余额
    $scope.BHClientAIOGetAccountBalance = function () {
        searchService.BHClientAIOGetAccountBalance().success(
            function (response) {
                $scope.tips = "余额:" + response.Info.data;
            });
    }

    // 获取使用明细
    $scope.BHClientAIOGetAccountBalanceDetail = function () {
        searchService.BHClientAIOGetAccountBalanceDetail().success(
            function (response) {
                $scope.tips = response.ErrMsg;
            });
    }

    // 加班请求
    $scope.BHClientAIOOvertimeNotice = function () {
        searchService.BHClientAIOOvertimeNotice().success(function (response) {
            $scope.tips = response.info;
        });
    }

    // 获取统计信息
    $scope.statistics = function (startDate, endDate) {
        if (startDate == undefined) {
            startDate = '';
        }
        if (endDate == undefined) {
            endDate = '';
        }
        searchService.statistics(startDate, endDate).success(
            function (response) {
                $scope.tips = response;
            });
    }

    //
    $scope.statisticQuestions = function (startDate, endDate) {
        if (startDate == undefined) {
            startDate = '';
        }
        if (endDate == undefined) {
            endDate = '';
        }
        searchService.statisticQuestions(startDate, endDate).success(
            function (response) {
                $scope.tips = response;
            });
    }


    $scope.statisticBytes = function (startDate, endDate) {
        if (startDate == undefined) {
            startDate = '';
        }
        if (endDate == undefined) {
            endDate = '';
        }
        searchService.statisticBytes(startDate, endDate).success(
            function (response) {
                $scope.tips = response;
            });
    }


    /*
        中国人寿动态验证码获取
    */
    $scope.ChinalifeGetQRCode = function (wxId) {
        if (wxId != null && wxId != "") {
            wxId = wxId.trim();
            searchService.ChinalifeGetQRCode(wxId).success(
                function (response) {
                    /*格式化获取正确字段*/
                    var jsonResult = JSON.parse(response)
                    var data = eval('(' + jsonResult + ')');
                    $scope.tips = data.dynamic_code;
                });

        } else {
            alert("请填写企业微信ID");
        }

    }


    $scope.tools = [];
    //获取所有工具
    $scope.getTools = function () {
        fileService.getTools().success(function (response) {
            $scope.tools = response;
        });
    }

    //删除工具
    $scope.delTools = function (toolName) {
        //alert
        // alert("删除成功!");
        //confirm
        // var x;
        // var r = confirm("确定要删除吗!","是的","不是");
        // if (r == true) {
        //     x = "你按下的是\"确定\"按钮。";
        // } else {
        //     x = "你按下的是\"取消\"按钮。";
        // }
        // console.log(x);
        //confirm
        var pwd = prompt("请输入删除口令:", "")
        if (pwd != null && pwd != "") {
            if ('91bihu@admin' == pwd) {
                console.log(toolName);
                fileService.delTools(toolName).success(function (response) {
                    console.log(response);
                    if (response.code == "0") {
                        $scope.getTools();
                    } else {
                        alert("删除失败!" + response.message);
                    }
                });
            } else {
                alert("删除口令错误!");
            }
        }
    }
})