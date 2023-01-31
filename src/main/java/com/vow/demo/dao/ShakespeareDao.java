package com.vow.demo.dao;

import com.vow.demo.domain.vo.Shakespeare;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: wushaopeng
 * @date: 2023/1/31 14:17
 */
public interface ShakespeareDao extends ElasticsearchRepository<Shakespeare, Long> {
}
