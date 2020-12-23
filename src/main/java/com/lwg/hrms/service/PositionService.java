package com.lwg.hrms.service;

import com.lwg.hrms.model.Position;

import java.util.List;

/**
 * @Author lwg
 * @Date 2020/12/22 16:25
 * @Version 1.0
 */
public interface PositionService {
    List<Position> getAllPositions();

    int addPosition(Position position);

    int updatePosition(Position position);

    int deletePositionById(Integer id);

    int deletePositionByIds(Integer[] ids);
}
