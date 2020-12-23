package com.lwg.hrms.controller.system.basic;

import com.lwg.hrms.model.Position;
import com.lwg.hrms.service.impl.PositionServiceImpl;
import com.lwg.hrms.untils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/22 16:25
 * @Version 1.0
 */

@RestController
@RequestMapping("/system/config/pos")
public class PositionController {

    @Autowired
    private PositionServiceImpl positionService;

    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        if (positionService.addPosition(position) == 1) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败");
        }
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updatePosition(position) == 1) {
            return RespBean.ok("更新成功");
        } else {
            return RespBean.error("更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id){
        if (positionService.deletePositionById(id)==1){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids){
        if (positionService.deletePositionByIds(ids)==ids.length){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

}
