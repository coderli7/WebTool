app.controller('questionController', function ($scope, $location, $interval, $controller, $timeout,
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
                    alert(channelkey + "获取失败,请刷新重试");
                }
            })
        } else {
            alert("未查询到信息,请检查浏览器地址栏,输入完整地址哦 ^_^");
        }
    }


    // var timer = $interval(function(){},100, 10);
    //
    // this.cancel = function(){
    //     $interval.cancel(timer);
    // }

    /**
     * 提交回复
     */
    $scope.saveImgAnswer = function () {
        $scope.showLoadingDialog();
        bhImgquestionService.saveImgAnswer($scope.channelkey, $scope.guid, $scope.answer).success(function (response) {
            console.log(response);
            if (response != null && response.code == 0) {
                var startDt = new Date();
                //1.回复保存成功
                $scope.setLoadingDialogText("保存成功,后台验证中,请稍等...")

                //配置查询次数
                var reSearchResultCount = 30;
                //标记是否正在搜索
                var searchingSign = false;
                //当前查询次数
                var curSearchCount = 0;

                //标记是否已经查询到结果,并为结束标记
                var hasResultOK = false;

                //1.1开始循环查询服务器数据
                $interval(function () {
                    curSearchCount++;
                    var timeInfo = "第" + curSearchCount + "次,";
                    $scope.consolelog(timeInfo + "开始");

                    if (searchingSign) {
                        $scope.consolelog(timeInfo + "前次操作正在执行,直接返回");
                        return;
                    }
                    if (hasResultOK) {
                        $scope.consolelog(timeInfo + "结果已定,直接返回");
                        return;
                    }

                    searchingSign = true;
                    //1.1.1查询服务器数据状态
                    bhImgquestionService.findImgData($scope.channelkey, $scope.guid).success(function (response) {
                        searchingSign = false;
                        $scope.consolelog("有结果了" + response.data.imganswerstatus);
                        if (!$scope.isNullOrEmptyOrUndefined(response) && response.code == 0 && !$scope.isNullOrEmptyOrUndefined(response.data)) {
                            //图片识别结果（是否正确）1 未识别 2 识别错误

                            if (response.data.imganswerstatus == 0) {
                                $scope.hideLoadingDialog();
                                hasResultOK = true;
                                //保存成功,校验成功
                                alert("提交成功,请返回报价");
                                $scope.consolelog("提交成功,请返回报价");
                            } else if (response.data.imganswerstatus == 1) {
                                $scope.setLoadingDialogText("保存成功,后台验证中,请稍等...")
                            } else if (response.data.imganswerstatus == 2) {
                                $scope.hideLoadingDialog();
                                hasResultOK = true;
                                alert("验证码错误,不允许提交了!");
                            } else if (response.data.imganswerstatus == 3) {
                                $scope.hideLoadingDialog();
                                //保存成功,校验失败,需要重新识别
                                //清空保存数据，更新img src 并给出新的提示
                                $("#qrImg").attr("src", response.data.imgdata)
                                alert("验证码错误,请重新提交!");
                            } else {
                                $scope.consolelog("不识别返回状态" + response.data.imganswerstatus);
                            }
                        }
                    })

                    if (curSearchCount == reSearchResultCount && !hasResultOK) {
                        $scope.hideLoadingDialog();
                        alert('等待校验结果超时,请稍后重试');
                    }
                }, 2000, reSearchResultCount);
            } else {
                $scope.hideLoadingDialog();
                //2.报价回复保存失败,提醒请重新保存
                alert('保存失败,请重新提交：' + response.message);
            }

            //保存成功后，30秒内,机器人端,每隔断2s轮训服务端是否允许，并给出回复，如失败，直接将最新的img信息回传至服务器更新，并标记出需要更新
            //注意客户端可能会出现超时，不生效的情况，此种情况，需要重新获取验证码即可。如果超过最大时间，获取验证码次数限制，登录次数限制，同步至服务器超时限制
            //浏览器客户端,启动定时查询，查询够30秒后，提示超时，其中这部分时间一直遮罩即可，客户端查询到结果后，直接根据状态判断
            //给出用户提示，是否需要重新加载最新版即可,不需要重新提交即可
        })
    }
})