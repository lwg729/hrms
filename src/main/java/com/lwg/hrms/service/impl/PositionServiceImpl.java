package com.lwg.hrms.service.impl;

import com.lwg.hrms.mapper.PositionMapper;
import com.lwg.hrms.model.Position;
import com.lwg.hrms.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/22 16:26
 * @Version 1.0
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    @Override
    public int addPosition(Position position) {
        position.setEnabled(true);
        position.setcreateDate(new Date());
        return positionMapper.insertSelective(position);
    }

    @Override
    public int updatePosition(Position position) {
        position.setcreateDate(new Date());
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    @Override
    public int deletePositionById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deletePositionByIds(Integer[] ids) {
        return positionMapper.deletePositionByIds(ids);
    }
}
