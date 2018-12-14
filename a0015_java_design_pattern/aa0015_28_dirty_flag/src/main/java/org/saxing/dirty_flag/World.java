package org.saxing.dirty_flag;

import java.util.ArrayList;
import java.util.List;

/**
 * World
 *
 * @author saxing 2018/12/14 14:31
 */
public class World {

    private List<String> countries;
    private DataFetcher dataFetcher;

    public World() {
        this.countries = new ArrayList<>();
        this.dataFetcher = new DataFetcher();
    }

    public List<String> fetch(){
        List<String> data = dataFetcher.fetch();

        countries = data.isEmpty() ? countries : data;

        return countries;
    }
}
