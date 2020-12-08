package com.lxl.webtool.controller;

import com.lxl.webtool.dao.BhChannelinfoService;
import com.lxl.webtool.dao.pojo.TbBhChannelinfo;
import com.lxl.webtool.model.BhLoginInfoRequest;
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
@RequestMapping("/bhChannelinfo")
public class BhChannelinfoController {

    @Autowired
    private BhChannelinfoService bhChannelinfoService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBhChannelinfo> findAll() {
        return bhChannelinfoService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return bhChannelinfoService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param bhChannelinfo
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBhChannelinfo bhChannelinfo) {
        try {
            bhChannelinfoService.add(bhChannelinfo);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param bhChannelinfo
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBhChannelinfo bhChannelinfo) {
        try {
            bhChannelinfoService.update(bhChannelinfo);
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
    public TbBhChannelinfo findOne(Long id) {
        return bhChannelinfoService.findOne(id);
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
            bhChannelinfoService.delete(ids);
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
    public PageResult search(@RequestBody TbBhChannelinfo bhChannelinfo, int page, int rows) {
        return bhChannelinfoService.findPage(bhChannelinfo, page, rows);
    }

    /**
     * 新增渠道cookie数据
     *
     * @param loginInfoRequest
     * @return
     */
    @RequestMapping("/addChannelCookie")
    public BaseResult addChannelCookie(@RequestBody BhLoginInfoRequest loginInfoRequest) {
        return bhChannelinfoService.addChannelCookie(loginInfoRequest);
    }

    /**
     * 获取渠道cookie数据
     *
     * @param loginInfoRequest
     * @return
     */
    @RequestMapping("/getChannelCookie")
    public BaseResult getChannelCookie(@RequestBody BhLoginInfoRequest loginInfoRequest) {
        String channelKey = loginInfoRequest.getChannelKey();
        return bhChannelinfoService.getChannelCookie(channelKey);
    }
}
