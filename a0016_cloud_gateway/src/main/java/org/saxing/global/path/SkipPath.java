package org.saxing.global.path;

/**
 * 跳过的路径
 *
 * @author 刘罕  2018/7/23 11:33
 */
//@Configuration
//@ConfigurationProperties(prefix = "listen.filter.skip")
public class SkipPath {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
