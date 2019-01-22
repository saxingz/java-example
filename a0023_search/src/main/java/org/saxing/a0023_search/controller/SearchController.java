package org.saxing.a0023_search.controller;

import org.saxing.a0023_search.domain.entity.AnsDO;
import org.saxing.a0023_search.domain.entity.PageResult;
import org.saxing.a0023_search.domain.vo.SearchVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/keyword")
    public PageResult<AnsDO> queryByKeyWork(SearchVO searchVO){
        return null;
    }

}
