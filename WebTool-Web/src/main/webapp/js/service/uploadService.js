app.service('uploadService', function ($http) {

    // 上传文件
    this.uploadFile = function () {
        var formdata = new FormData();
        formdata.append('file', file.files[0]);// file 文件上传框的name

        return $http({
            url: '../upload.do',
            method: 'post',
            data: formdata,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        });
    };

    // 上传文件
    this.uploadFile1 = function (formdata) {
        return $http({
            url: '../upload.do',
            method: 'post',
            data: formdata,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        });
    };

// 上传文件
    this.uploadFile2 = function (formdata) {
        return $http({
            url: '../fileUpload.do',
            method: 'post',
            data: formdata,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        });
    }
});