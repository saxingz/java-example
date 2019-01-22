package org.saxing.a0023_search.controller;

import org.saxing.a0023_search.domain.entity.AnsDO;
import org.saxing.a0023_search.domain.param.PageResult;
import org.saxing.a0023_search.domain.param.SearchParam;
import org.saxing.a0023_search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * search
 *
 * @author saxing 2019/1/22 10:30
 */
@RestController
@RequestMapping("/ans")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/keyword")
    public PageResult<AnsDO> queryByKeyWork(SearchParam searchParam){
        return searchService.searchByKeyword(searchParam);
    }

}
