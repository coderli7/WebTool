app.controller('questionController', function ($scope, $location, $controller, $timeout,
                                               searchService, fileService, bhImgquestionService) {
    // 继承
    $controller('baseController', {$scope: $scope});

    //记录回答信息
    $scope.result = {};
    //定义请求对象
    $scope.requestEntity = {};

    $scope.channelkey = "";
    $scope.guid = "";
    $scope.answer = "";

    /**
     *初始化获取图片信息
     */
    $scope.initPageInfo = function () {
        $scope.channelkey = $location.search()["channelkey"];
        $scope.guid = $location.search()["guid"];
        console.log("init开始获取图片信息");
        $scope.getImgInfo();
    }

    /**
     * 查询图片信息
     */
    $scope.getImgInfo = function () {
        if (!$scope.isNullOrEmptyOrUndefined($scope.channelkey) && !$scope.isNullOrEmptyOrUndefined($scope.guid)) {
            $scope.showLoadingDialog();
            bhImgquestionService.findImgData($scope.channelkey, $scope.guid).success(function (response) {
                $scope.hideLoadingDialog();
                console.log(response);
                if (response != null && response.code == 0) {
                    console.log("信息获取成功");
                    $("#qrImg").attr("src", response.data.imgdata)
                    $("#imgdiv").attr("style", "block")
                } else {
                    alert(channelkey + "获取失败,请刷新重试!!!");
                }
            })
        } else {
            alert("当前页面基础信息为空,请重新刷新！");
        }
    }


    /**
     * 提交回复
     */
    $scope.saveImgAnswer = function () {
        $scope.showLoadingDialog();
        bhImgquestionService.saveImgAnswer($scope.channelkey, $scope.guid, $scope.answer).success(function () {
            //保存成功后，30秒内,机器人端,每隔断2s轮训服务端是否允许，并给出回复，如失败，直接将最新的img信息回传至服务器更新，并标记出需要更新
            //注意客户端可能会出现超时，不生效的情况，此种情况，需要重新获取验证码即可。如果超过最大时间，获取验证码次数限制，登录次数限制，同步至服务器超时限制
            //浏览器客户端,启动定时查询，查询够30秒后，提示超时，其中这部分时间一直遮罩即可，客户端查询到结果后，直接根据状态判断
            //给出用户提示，是否需要重新加载最新版即可,不需要重新提交即可


        })
    }
})