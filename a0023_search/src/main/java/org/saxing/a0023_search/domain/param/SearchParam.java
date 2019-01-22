package org.saxing.a0023_search.domain.param;

import lombok.Data;

/**
 * @author saxing 2019/1/22 10:20
 */
@Data
public class SearchParam extends PageParam {
    private static final long serialVersionUID = 8506284080778757036L;

    private String keyword;

}
