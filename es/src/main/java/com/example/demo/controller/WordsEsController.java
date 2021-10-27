package com.example.demo.controller;

import com.example.demo.model.WordEs;
import com.example.demo.service.WordEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2020/8/13 4:56 下午
 * @Version 1.0
 **/
@Controller("wordsEsController")
public class WordsEsController {

    @Autowired
    WordEsService wordEsService;


    /**
     * @description 根据id获取word文档内容
     * @Param [id]
     * @return cn.com.sinosoft.monitor.model.WordEs
     * @author chenpengwei
     * @date 2020/8/13 10:11 上午
     */
    @ResponseBody
    @RequestMapping("getWord")
    public List<WordEs> getWord(){


        List<WordEs> res = new ArrayList<>();

        WordEs vo = new WordEs();
        vo.setText("一种");

        wordEsService.save(vo);
        Iterable<WordEs> all = wordEsService.findAll();
        Iterator<WordEs> iterator = all.iterator();
        while(iterator.hasNext()) {
            WordEs next = iterator.next();
            res.add(next);
            System.out.println(next);
        }


        return res;
    }


}
