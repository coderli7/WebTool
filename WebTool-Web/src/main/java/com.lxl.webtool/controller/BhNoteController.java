package com.lxl.webtool.controller;

import com.lxl.webtool.commonutils.MyCommonUtils;
import com.lxl.webtool.dao.BhNoteService;
import com.lxl.webtool.dao.pojo.TbBhNote;
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
@RequestMapping("/bhNote")
public class BhNoteController {

    @Autowired
    private BhNoteService bhNoteService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBhNote> findAll() {
        return bhNoteService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return bhNoteService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param bhNote
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBhNote bhNote) {
        try {
            bhNote.setCreateuser(MyCommonUtils.getCurUser());
            bhNoteService.add(bhNote);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param bhNote
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBhNote bhNote) {
        try {
            bhNoteService.update(bhNote);
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
    public TbBhNote findOne(Long id) {
        return bhNoteService.findOne(id);
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
            bhNoteService.delete(ids);
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
    public PageResult search(@RequestBody TbBhNote bhNote, int page, int rows) {
        return bhNoteService.findPage(bhNote, page, rows);
    }

}
