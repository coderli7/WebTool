package com.lxl.webtool.controller;

import com.lxl.webtool.dao.BhImgquestionService;
import com.lxl.webtool.dao.pojo.TbBhImgquestion;
import com.lxl.webtool.pojo.BaseResult;
import com.lxl.webtool.pojo.PageResult;
import com.lxl.webtool.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/puapi/bhImgquestion")
public class BhImgquestionController {

    @Autowired
    private BhImgquestionService bhImgquestionService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBhImgquestion> findAll() {
        return bhImgquestionService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return bhImgquestionService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param bhImgquestion
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBhImgquestion bhImgquestion) {
        try {
            bhImgquestionService.add(bhImgquestion);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param bhImgquestion
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBhImgquestion bhImgquestion) {
        try {
            bhImgquestionService.update(bhImgquestion);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbBhImgquestion findOne(Long id) {
        return bhImgquestionService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            bhImgquestionService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBhImgquestion bhImgquestion, int page, int rows) {
        return bhImgquestionService.findPage(bhImgquestion, page, rows);
    }


    /**
     * 获取版本信息
     *
     * @param channelkey
     * @param guid
     * @return
     */
    @RequestMapping("/findImgData")
    public BaseResult findImgData(String channelkey, String guid) {
        return bhImgquestionService.findImgData(channelkey, guid);
    }

    /**
     * @param channelkey
     * @param guid
     * @param answser
     * @return
     */
    @RequestMapping("/saveImgAnswer")
    BaseResult saveImgAnswer(String channelkey, String guid, String answser) {
        // try {
        //     Thread.sleep(3000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        return bhImgquestionService.saveImgAnswer(channelkey, guid, answser);
    }

}
