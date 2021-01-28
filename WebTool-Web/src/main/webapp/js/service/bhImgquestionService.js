//服务层
app.service('bhImgquestionService', function ($http) {

        //读取列表数据绑定到表单中
        this.findAll = function () {
            return $http.get('../puapi/bhImgquestion/findAll.do');
        }
        //分页
        this.findPage = function (page, rows) {
            return $http.get('../puapi/bhImgquestion/findPage.do?page=' + page + '&rows=' + rows);
        }
        //查询实体
        this.findOne = function (id) {
            return $http.get('../puapi/bhImgquestion/findOne.do?id=' + id);
        }
        //增加
        this.add = function (entity) {
            return $http.post('../puapi/bhImgquestion/add.do', entity);
        }
        //修改
        this.update = function (entity) {
            return $http.post('../puapi/bhImgquestion/update.do', entity);
        }
        //删除
        this.dele = function (ids) {
            return $http.get('../puapi/bhImgquestion/delete.do?ids=' + ids);
        }
        //搜索
        this.search = function (page, rows, searchEntity) {
            return $http.post('../puapi/bhImgquestion/search.do?page=' + page + "&rows=" + rows, searchEntity);
        }

        this.findImgData = function (channelkey, guid) {
            return $http.get('../puapi/bhImgquestion/findImgData.do?channelkey=' + channelkey + "&guid=" + guid);
        }

        this.saveImgAnswer = function (channelkey, guid, answser) {
            return $http.get('../puapi/bhImgquestion/saveImgAnswer.do?channelkey=' + channelkey + "&guid=" + guid + "&answser=" + answser);
        }
    }
);
