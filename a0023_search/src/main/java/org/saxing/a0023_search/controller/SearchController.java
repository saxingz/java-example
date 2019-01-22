package org.saxing.a0023_search.controller;

import org.saxing.a0023_search.domain.entity.AnsDO;
import org.saxing.a0023_search.domain.param.PageResult;
import org.saxing.a0023_search.domain.param.SearchParam;
import org.saxing.a0023_search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SearchService searchService;

    @GetMapping("/keyword")
    public PageResult<AnsDO> queryByKeyWork(SearchParam searchParam){
        String keyword = searchParam.getKeyword();
        switch (keyword){
            case "制裁":
                searchParam.setKeyword("什么是制裁及金融制裁");
                break;
            case "制裁风险":
                searchParam.setKeyword("什么是制裁风险");
                break;
            case "制裁合规":
                searchParam.setKeyword("什么是制裁合规");
                break;
            case "客户准入":
                searchParam.setKeyword("客户准入环节管控要点有哪些");
                break;
            case "业务办理":
                searchParam.setKeyword("客户业务办理环节管控要点有哪些");
                break;
            case "客户重检":
                searchParam.setKeyword("客户重检环节管控要点有哪些");
                break;
            case "客户退出":
                searchParam.setKeyword("客户退出环节管控要点有哪些");
                break;
            default:
                break;
        }
        return searchService.searchByKeyword(searchParam);
    }

    @GetMapping("/id/{id}")
    public AnsDO searchById(@PathVariable (value = "id") Long id){
        return searchService.searchById(id);
    }

}
