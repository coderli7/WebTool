//品牌控制层 
app
    .controller(
        'baseController',
        function ($scope, $filter) {

            $scope.reload = true;

            //标记是否正在查询
            $scope.searching = false;

            // 分页控件配置
            $scope.paginationConf = {
                /*            currentPage: 0,
                            totalItems: 0,
                            itemsPerPage: 15,*/
                perPageOptions: [15, 30, 45, 100],
                onChange: function () {
                    if (!$scope.reload) {
                        return;
                    }
                    $scope.reloadList();// 重新加载
                    $scope.reload = false;
                    setTimeout(function () {
                        $scope.reload = true;
                    }, 500);

                }
            };

            // 重新加载列表 数据
            $scope.reloadList = function () {
                if ($scope.searching) {
                    console.log("正在查询！！！");
                    return;
                }

                // 切换页码
                $scope.search($scope.paginationConf.currentPage,
                    $scope.paginationConf.itemsPerPage);
            }


            $scope.selectIds = [];// 选中的ID集合

            // 标记行是否选中
            $scope.selectAllLineChkBox = false;

            // 更新复选
            $scope.updateSelection = function ($event, id) {
                if ($event.target.checked) {// 如果是被选中,则增加到数组
                    $scope.selectIds.push(id);
                } else {
                    var idx = $scope.selectIds.indexOf(id);
                    $scope.selectIds.splice(idx, 1);// 删除
                }
            }

            // 全选
            $scope.selectAllLine = function () {
                for (j = 0; j < $scope.list.length; j++) {
                    var idTmp = $scope.list[j].id;
                    var index = $scope.selectIds.indexOf(idTmp)
                    if (index < 0) {
                        // 添加
                        $scope.list[j].pageChecked = true;
                        $scope.selectIds.push(idTmp);
                    } else {

                        // 清空
                        $scope.list[j].pageChecked = false;
                        $scope.selectIds.splice(index, 1);// 删除
                    }
                }
                // alert($scope.selectIds);
            }

            $scope.jsonToString = function (jsonString, key) {
                var json = JSON.parse(jsonString);
                var value = "";
                for (var i = 0; i < json.length; i++) {
                    if (i > 0) {
                        value += ",";
                    }
                    value += json[i][key];
                }
                return value;
            }

            $scope.GUID = function () {
                this.date = new Date();

                /* 判断是否初始化过，如果初始化过以下代码，则以下代码将不再执行，实际中只执行一次 */
                if (typeof this.newGUID != 'function') {

                    /* 生成GUID码 */
                    GUID.prototype.newGUID = function () {
                        this.date = new Date();
                        var guidStr = '';
                        sexadecimalDate = this.hexadecimal(this
                            .getGUIDDate(), 16);
                        sexadecimalTime = this.hexadecimal(this
                            .getGUIDTime(), 16);
                        for (var i = 0; i < 9; i++) {
                            guidStr += Math.floor(Math.random() * 16)
                                .toString(16);
                        }
                        guidStr += sexadecimalDate;
                        guidStr += sexadecimalTime;
                        while (guidStr.length < 32) {
                            guidStr += Math.floor(Math.random() * 16)
                                .toString(16);
                        }
                        return this.formatGUID(guidStr);
                    }

                    /*
                     * 功能：获取当前日期的GUID格式，即8位数的日期：19700101
                     * 返回值：返回GUID日期格式的字条串
                     */
                    GUID.prototype.getGUIDDate = function () {
                        return this.date.getFullYear()
                            + this
                                .addZero(this.date.getMonth() + 1)
                            + this.addZero(this.date.getDay());
                    }

                    /*
                     * 功能：获取当前时间的GUID格式，即8位数的时间，包括毫秒，毫秒为2位数：12300933
                     * 返回值：返回GUID日期格式的字条串
                     */
                    GUID.prototype.getGUIDTime = function () {
                        return this.addZero(this.date.getHours())
                            + this.addZero(this.date.getMinutes())
                            + this.addZero(this.date.getSeconds())
                            + this.addZero(parseInt(this.date
                                .getMilliseconds() / 10));
                    }

                    /*
                     * 功能: 为一位数的正整数前面添加0，如果是可以转成非NaN数字的字符串也可以实现 参数:
                     * 参数表示准备再前面添加0的数字或可以转换成数字的字符串 返回值:
                     * 如果符合条件，返回添加0后的字条串类型，否则返回自身的字符串
                     */
                    GUID.prototype.addZero = function (num) {
                        if (Number(num).toString() != 'NaN' && num >= 0
                            && num < 10) {
                            return '0' + Math.floor(num);
                        } else {
                            return num.toString();
                        }
                    }

                    /*
                     * 功能：将y进制的数值，转换为x进制的数值
                     * 参数：第1个参数表示欲转换的数值；第2个参数表示欲转换的进制；第3个参数可选，表示当前的进制数，如不写则为10
                     * 返回值：返回转换后的字符串
                     */
                    GUID.prototype.hexadecimal = function (num, x, y) {
                        if (y != undefined) {
                            return parseInt(num.toString(), y)
                                .toString(x);
                        } else {
                            return parseInt(num.toString()).toString(x);
                        }
                    }

                    /*
                     * 功能：格式化32位的字符串为GUID模式的字符串 参数：第1个参数表示32位的字符串
                     * 返回值：标准GUID格式的字符串
                     */
                    GUID.prototype.formatGUID = function (guidStr) {
                        var str1 = guidStr.slice(0, 8) + '-', str2 = guidStr
                                .slice(8, 12)
                            + '-', str3 = guidStr.slice(12, 16)
                            + '-', str4 = guidStr.slice(16, 20)
                            + '-', str5 = guidStr.slice(20);
                        return str1 + str2 + str3 + str4 + str5;
                    }
                }
            }

            /**
             * 转化为本地时间
             */
            $scope.convertLocalTime = function (timespan) {
                if (timespan <= 0 || timespan == '' || timespan == null) {
                    return null;
                } else {
                    // console.log('非空值');
                    var result = $filter("date")(timespan + '000', "yyyy-MM-dd HH:mm:ss");
                    // var result = new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
                    return result;
                }
            }


            //显示loading窗口
            $scope.showLoadingDialog = function () {
                var html = ' <div class="loading">';
                html += '<div class="loading-body">';
                html += '<div class="loading-img">';
                html += '<img class="img-responsive" src="../img/loading.gif" />';
                html += '</div>';
                html += '<div  class="loading-text" id="loadingdiv">';
                html += '处理中...';
                html += '</div>';
                html += '</div>';
                html += '</div>';
                $('body').append(html);
            }

            //更新loading弹窗提示信息
            $scope.setLoadingDialogText = function (loadingtext) {
                if (!$scope.isNullOrEmptyOrUndefined(loadingtext)) {
                    document.querySelector("#loadingdiv").innerText = loadingtext;
                }
            }


            //移除loading窗口
            $scope.hideLoadingDialog = function () {
                $('.loading').remove();
            }

            $scope.isNullOrEmptyOrUndefined = function (obj) {
                if (obj == null) {
                    return true;
                }
                if (obj == undefined) {
                    return true;
                }
                if (obj == '') {
                    return true;
                }
                if (obj == ' ') {
                    return true;
                }
            }


            $scope.consolelog = function (logInfo) {
                if (!$scope.isNullOrEmptyOrUndefined(logInfo)) {
                    console.log(new Date().toLocaleString() + ":" + logInfo)
                }
            }
        });