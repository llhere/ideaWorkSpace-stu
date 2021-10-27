package com.example.demo.service;

import com.example.demo.model.WordEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2020/8/13 5:43 下午
 * @Version 1.0
 **/
@Service
public interface WordEsService extends ElasticsearchRepository<WordEs, String> {
}