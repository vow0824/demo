package com.vow.demo.service;

import com.vow.demo.dao.CarsDao;
import com.vow.demo.dao.ShakespeareDao;
import com.vow.demo.domain.vo.CarsItem;
import com.vow.demo.domain.vo.Shakespeare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wushaopeng
 * @date: 2023/1/31 13:56
 */
@Service
public class EsService {

    @Autowired
    private ShakespeareDao shakespeareDao;

    public List<Shakespeare> searchShakespeare() {
        Iterable<Shakespeare> all = shakespeareDao.findAll(PageRequest.of(1, 10));
        List<Shakespeare> list = new ArrayList<>();
        for (Shakespeare shakespeare : all) {
            list.add(shakespeare);
        }
        return list;
    }
}
