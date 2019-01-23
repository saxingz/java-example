package org.saxing.a0023_search.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import org.saxing.a0023_search.domain.entity.AnsDO;
import org.saxing.a0023_search.domain.param.PageResult;
import org.saxing.a0023_search.domain.param.SearchParam;
import org.saxing.a0023_search.mapper.AnsDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * search
 *
 * @author saxing 2019/1/22 11:11
 */
@Service
public class SearchService {

    @Autowired
    private AnsDOMapper ansDOMapper;

    public PageResult<AnsDO> searchByKeyword(SearchParam searchParam){
        Integer pageNumber = searchParam.getPage();
        if (pageNumber == null || pageNumber < 1){
            pageNumber = 1;
        }
        Integer pageSize = searchParam.getPageSize();
        if (pageSize == null || pageSize < 1){
            pageSize = 10;
        }
        PageResult<AnsDO> pageResult = new PageResult<>();
        Page<Object> page = PageHelper.startPage(pageNumber, pageSize, true);
        List<AnsDO> ansDOList = new ArrayList<>();
        String keyword = searchParam.getKeyword();
        if (StringUtils.isEmpty(keyword.trim())){
            pageResult.setTotal(0);
        }else{
            ansDOList = ansDOMapper.selectTitleByCondition(searchParam);
            pageResult.setTotal((int) page.getTotal());
        }
        pageResult.setPage(pageNumber);
        pageResult.setPageSize(pageSize);

        pageResult.setList(ansDOList);
        return pageResult;
    }

    public AnsDO searchById(Long id) {
        return ansDOMapper.selectByPrimaryKey(id);
    }
}
