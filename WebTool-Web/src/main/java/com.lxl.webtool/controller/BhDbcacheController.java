package com.lxl.webtool.controller;

import com.lxl.webtool.dao.BhDbcacheService;
import com.lxl.webtool.dao.pojo.TbBhDbcache;
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
@RequestMapping("/bhDbcache")
public class BhDbcacheController {

    @Autowired
    private BhDbcacheService bhDbcacheService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBhDbcache> findAll() {
        return bhDbcacheService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return bhDbcacheService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param bhDbcache
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBhDbcache bhDbcache) {
        try {
            bhDbcacheService.add(bhDbcache);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param bhDbcache
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBhDbcache bhDbcache) {
        try {
            bhDbcacheService.update(bhDbcache);
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
    public TbBhDbcache findOne(Long id) {
        return bhDbcacheService.findOne(id);
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
            bhDbcacheService.delete(ids);
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
    public PageResult search(@RequestBody TbBhDbcache bhDbcache, int page, int rows) {
        return bhDbcacheService.findPage(bhDbcache, page, rows);
    }


    @RequestMapping("/searchByKey")
    public BaseResult searchByKey(String key) {
        BaseResult result = new BaseResult();
        try {
            TbBhDbcache tbBhDbcache = bhDbcacheService.searchByKey(key);
            if (tbBhDbcache != null) {
                result.setData(tbBhDbcache.getDbcontent());
                result.setMessage("获取成功");
            } else {
                result.setCode(-10002);
                result.setMessage("未查询到相关数据");
            }
        } catch (Exception e) {
            result.setCode(-10001);
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
