package com.vow.demo.dao;

import com.vow.demo.domain.vo.CarsItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: wushaopeng
 * @date: 2023/1/31 14:06
 */
public interface CarsDao extends ElasticsearchRepository<CarsItem, Void> {
}
