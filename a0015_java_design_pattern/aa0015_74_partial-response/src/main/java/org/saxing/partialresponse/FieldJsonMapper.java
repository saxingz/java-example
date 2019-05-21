package org.saxing.partialresponse;

import java.lang.reflect.Field;

/**
 * Map a video to json
 *
 * @author saxing 2019/5/21 14:43
 */
public class FieldJsonMapper {


    /**
     * to json
     *
     * @param video
     * @param fields
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public String toJson(Video video, String[] fields) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder json = new StringBuilder().append("{");

        for (int i = 0, fieldsLength = fields.length; i < fieldsLength; i++) {
            json.append(getString(video, Video.class.getDeclaredField(fields[i])));
            if (i != fieldsLength - 1) {
                json.append(",");
            }
        }
        json.append("}");
        return json.toString();
    }

    /**
     * getString
     *
     * @param video
     * @param declaredField
     * @return
     * @throws IllegalAccessException
     */
    private String getString(Video video, Field declaredField) throws IllegalAccessException {
        declaredField.setAccessible(true);
        Object value = declaredField.get(video);
        if (declaredField.get(video) instanceof Integer) {
            return "\"" + declaredField.getName() + "\"" + ": " + value;
        }
        return "\"" + declaredField.getName() + "\"" + ": " + "\"" + value.toString() + "\"";
    }

}
