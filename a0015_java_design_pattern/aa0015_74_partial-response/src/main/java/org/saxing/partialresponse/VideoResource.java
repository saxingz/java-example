package org.saxing.partialresponse;

import java.util.Map;

/**
 * video resource
 *
 * @author saxing 2019/5/21 14:47
 */
public class VideoResource {

    private FieldJsonMapper fieldJsonMapper;
    private Map<Integer, Video> videos;

    /**
     * @param fieldJsonMapper map object to json.
     * @param videos          initialize resource with existing videos. Act as database.
     */
    public VideoResource(FieldJsonMapper fieldJsonMapper, Map<Integer, Video> videos) {
        this.fieldJsonMapper = fieldJsonMapper;
        this.videos = videos;
    }

    /**
     * @param id     video id
     * @param fields fields to get information about
     * @return full response if no fields specified else partial response for given field.
     */
    public String getDetails(Integer id, String... fields) throws Exception {
        if (fields.length == 0) {
            return videos.get(id).toString();
        }
        return fieldJsonMapper.toJson(videos.get(id), fields);
    }

}
