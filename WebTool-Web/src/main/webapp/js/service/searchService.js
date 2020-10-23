app.service('searchService', function ($http) {

    // 下拉列表数据
    this.BHClientAIOGetAccountBalance = function () {
        return $http.get('../service/BHClientAIOGetAccountBalance.do');
    }

    // 下拉列表数据
    this.BHClientAIOGetAccountBalanceDetail = function () {
        return $http.get('../service/BHClientAIOGetAccountBalanceDetail.do');
    }

    // 下拉列表数据
    this.BHClientAIOOvertimeNotice = function () {
        return $http.get('../service/BHClientAIOOvertimeNotice.do');
    }

    // 下拉列表数据
    this.statistics = function (startDate, endDate) {
        return $http.post('../imageCaseInfo/statistics.do?startDate='
            + startDate + "&endDate=" + endDate);
    }


    this.statisticQuestions = function (startDate, endDate) {
        return $http.post('../imageCaseInfo/statisticQuestions.do?startDate='
            + startDate + "&endDate=" + endDate);
    }


    this.statisticBytes = function (startDate, endDate) {
        return $http.post('../imageCaseInfo/statisticBytes.do?startDate='
            + startDate + "&endDate=" + endDate);
    }

    this.ChinalifeGetQRCode = function (id) {
        var postData = "userid_weixin=" + id + "&dynamic_type=1";
        var config = {headers: {'Content-type': 'application/x-www-form-urlencoded'}};
        return $http.post('http://wxqy.chinalife-p.com.cn:8084/dynamic_pass_web/TwoFactor/getCode', postData, config);
    }
})