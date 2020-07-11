package org.saxing.java8;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

/**
 * calc num
 *
 * @author saxing 2020/6/30 23:10
 */
public class CalcNum {

    public static void main(String[] args) {
        String[] schoolIds = new String[]{

           "86",
           "85",
           "82",
           "69",
           "75",
           "87",
           "107",
           "88",
           "92",
           "91",
           "90",
           "102",
           "101",
           "100",
           "99",
           "98",
           "97",
           "96",
           "95",
           "94",
           "126",
           "125",
           "124",
           "123",
           "121",
           "120",
           "119",
           "118",
           "117",
           "116",
           "115",
           "114",
           "113",
           "112",
           "111",
           "110",
           "109",
           "108",
        };

        for (String id : schoolIds){
            System.out.print(id + "\t");
            int count = countschool(id);
            System.out.println(count);
        }



































    }

    private static int countschool(String schoolId){
        Integer count = 0;

        String jsonStr = originJsonStr;
        Gson gson = new Gson();
        Map map = gson.fromJson(jsonStr, Map.class);
//        System.out.println(map);

        Set set = map.keySet();
        for (Object next : set) {
            Object o = map.get(next);
            if (o instanceof Map){
                Map subTable = (Map) o;
                Set subKeySet = subTable.keySet();
//                System.out.println(subKeySet);

                for (Object subkey : subKeySet){
                    Object o2 = subTable.get(subkey);
                    if (o2 instanceof Map){
                        Map subsubTable = (Map) o2;
                        Set set2 = subsubTable.keySet();
//                        System.out.println(set2);
                        for (Object s2 : set2){
                            Object result = subsubTable.get(s2);
                            String str2 = (String) s2;
                            if (str2.endsWith("_" + schoolId)){
                                String countStr = (String) result;
                                int count1 = Integer.parseInt(countStr);
                                count += count1;
                            }
                        }
                    }
                }

            }
        }

        return count;
    }

    static String originJsonStr = "{\n" +
            "    \"ims_wx_school_index/business_licence\":\"1\",\n" +
            "    \"ims_zh_school_kq_img/info_img_url\":\"367321\",\n" +
            "    \"ims_zh_teach_plan/thumb\":\"56\",\n" +
            "    \"ims_zh_school_show_image/show_img\":\"18\",\n" +
            "    \"ims_zh_school_student_head/head_img_down\":\"7312\",\n" +
            "    \"ims_zh_students_pick_log_/first_img\":{\n" +
            "        \"subTables\":{\n" +
            "            \"ims_zh_students_pick_log_109\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_108\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_89\":\"19\",\n" +
            "            \"ims_zh_students_pick_log_107\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_106\":\"7\",\n" +
            "            \"ims_zh_students_pick_log_128\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_69\":\"2870\",\n" +
            "            \"ims_zh_students_pick_log_105\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_86\":\"97\",\n" +
            "            \"ims_zh_students_pick_log_104\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_126\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_85\":\"381\",\n" +
            "            \"ims_zh_students_pick_log_103\":\"1\",\n" +
            "            \"ims_zh_students_pick_log_125\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_88\":\"2956\",\n" +
            "            \"ims_zh_students_pick_log_102\":\"17251\",\n" +
            "            \"ims_zh_students_pick_log_124\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_87\":\"24607\",\n" +
            "            \"ims_zh_students_pick_log_112\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_82\":\"595\",\n" +
            "            \"ims_zh_students_pick_log_111\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_110\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_84\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_83\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_119\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_118\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_117\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_116\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_75\":\"84993\",\n" +
            "            \"ims_zh_students_pick_log_97\":\"26781\",\n" +
            "            \"ims_zh_students_pick_log_115\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_96\":\"16849\",\n" +
            "            \"ims_zh_students_pick_log_114\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_99\":\"7025\",\n" +
            "            \"ims_zh_students_pick_log_113\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_98\":\"22803\",\n" +
            "            \"ims_zh_students_pick_log_101\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_123\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_93\":\"31491\",\n" +
            "            \"ims_zh_students_pick_log_100\":\"14158\",\n" +
            "            \"ims_zh_students_pick_log_122\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_92\":\"29512\",\n" +
            "            \"ims_zh_students_pick_log_121\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_95\":\"16181\",\n" +
            "            \"ims_zh_students_pick_log_120\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_94\":\"2\",\n" +
            "            \"ims_zh_students_pick_log_91\":\"34820\",\n" +
            "            \"ims_zh_students_pick_log_90\":\"33193\"\n" +
            "        },\n" +
            "        \"subTotal\":\"366592\"\n" +
            "    },\n" +
            "    \"ims_cook_image/image_path\":\"2682\",\n" +
            "    \"ims_wx_school_index/qr_img\":\"10\",\n" +
            "    \"ims_wx_school_index/school_license\":\"1\",\n" +
            "    \"ims_zh_students_pick_log_/first_img_family\":{\n" +
            "        \"subTables\":{\n" +
            "            \"ims_zh_students_pick_log_109\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_108\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_89\":\"14\",\n" +
            "            \"ims_zh_students_pick_log_107\":\"282\",\n" +
            "            \"ims_zh_students_pick_log_106\":\"7\",\n" +
            "            \"ims_zh_students_pick_log_128\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_69\":\"2177\",\n" +
            "            \"ims_zh_students_pick_log_105\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_86\":\"95\",\n" +
            "            \"ims_zh_students_pick_log_104\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_126\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_85\":\"720\",\n" +
            "            \"ims_zh_students_pick_log_103\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_125\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_88\":\"2956\",\n" +
            "            \"ims_zh_students_pick_log_102\":\"17229\",\n" +
            "            \"ims_zh_students_pick_log_124\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_87\":\"24066\",\n" +
            "            \"ims_zh_students_pick_log_112\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_82\":\"509\",\n" +
            "            \"ims_zh_students_pick_log_111\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_110\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_84\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_83\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_119\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_118\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_117\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_116\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_75\":\"65125\",\n" +
            "            \"ims_zh_students_pick_log_97\":\"25683\",\n" +
            "            \"ims_zh_students_pick_log_115\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_96\":\"16696\",\n" +
            "            \"ims_zh_students_pick_log_114\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_99\":\"7021\",\n" +
            "            \"ims_zh_students_pick_log_113\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_98\":\"20965\",\n" +
            "            \"ims_zh_students_pick_log_101\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_123\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_93\":\"24910\",\n" +
            "            \"ims_zh_students_pick_log_100\":\"12498\",\n" +
            "            \"ims_zh_students_pick_log_122\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_92\":\"29415\",\n" +
            "            \"ims_zh_students_pick_log_121\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_95\":\"14681\",\n" +
            "            \"ims_zh_students_pick_log_120\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_94\":\"3\",\n" +
            "            \"ims_zh_students_pick_log_91\":\"34820\",\n" +
            "            \"ims_zh_students_pick_log_90\":\"32788\"\n" +
            "        },\n" +
            "        \"subTotal\":\"332660\"\n" +
            "    },\n" +
            "    \"ims_zh_school_student_head/head_img_left\":\"7312\",\n" +
            "    \"ims_zh_school_student_head/head_img_front\":\"7312\",\n" +
            "    \"ims_wx_school_index/logo\":\"25\",\n" +
            "    \"ims_zh_school_kq_banner/url\":\"38\",\n" +
            "    \"ims_wx_school_teachers/thumb\":\"547\",\n" +
            "    \"ims_zh_school_student_parent/head_img\":\"19281\",\n" +
            "    \"ims_zh_school_class_images_/video_url\":{\n" +
            "        \"subTables\":{\n" +
            "            \"ims_zh_school_class_images_69\":\"4644\",\n" +
            "            \"ims_zh_school_class_images_114\":\"100\",\n" +
            "            \"ims_zh_school_class_images_84\":\"0\",\n" +
            "            \"ims_zh_school_class_images_115\":\"1554\",\n" +
            "            \"ims_zh_school_class_images_85\":\"0\",\n" +
            "            \"ims_zh_school_class_images_112\":\"3204\",\n" +
            "            \"ims_zh_school_class_images_82\":\"272\",\n" +
            "            \"ims_zh_school_class_images_113\":\"3939\",\n" +
            "            \"ims_zh_school_class_images_83\":\"0\",\n" +
            "            \"ims_zh_school_class_images_110\":\"10149\",\n" +
            "            \"ims_zh_school_class_images_88\":\"5184\",\n" +
            "            \"ims_zh_school_class_images_111\":\"14119\",\n" +
            "            \"ims_zh_school_class_images_89\":\"80\",\n" +
            "            \"ims_zh_school_class_images_86\":\"83\",\n" +
            "            \"ims_zh_school_class_images_87\":\"12225\",\n" +
            "            \"ims_zh_school_class_images_109\":\"1172\",\n" +
            "            \"ims_zh_school_class_images_107\":\"4130\",\n" +
            "            \"ims_zh_school_class_images_108\":\"28343\",\n" +
            "            \"ims_zh_school_class_images_105\":\"0\",\n" +
            "            \"ims_zh_school_class_images_106\":\"0\",\n" +
            "            \"ims_zh_school_class_images_128\":\"0\",\n" +
            "            \"ims_zh_school_class_images_103\":\"4\",\n" +
            "            \"ims_zh_school_class_images_125\":\"198\",\n" +
            "            \"ims_zh_school_class_images_95\":\"8429\",\n" +
            "            \"ims_zh_school_class_images_104\":\"0\",\n" +
            "            \"ims_zh_school_class_images_126\":\"164\",\n" +
            "            \"ims_zh_school_class_images_96\":\"2335\",\n" +
            "            \"ims_zh_school_class_images_101\":\"0\",\n" +
            "            \"ims_zh_school_class_images_123\":\"11632\",\n" +
            "            \"ims_zh_school_class_images_93\":\"2203\",\n" +
            "            \"ims_zh_school_class_images_102\":\"8631\",\n" +
            "            \"ims_zh_school_class_images_124\":\"5221\",\n" +
            "            \"ims_zh_school_class_images_94\":\"0\",\n" +
            "            \"ims_zh_school_class_images_121\":\"59\",\n" +
            "            \"ims_zh_school_class_images_99\":\"733\",\n" +
            "            \"ims_zh_school_class_images_100\":\"3218\",\n" +
            "            \"ims_zh_school_class_images_122\":\"0\",\n" +
            "            \"ims_zh_school_class_images_75\":\"20995\",\n" +
            "            \"ims_zh_school_class_images_97\":\"7348\",\n" +
            "            \"ims_zh_school_class_images_120\":\"1127\",\n" +
            "            \"ims_zh_school_class_images_98\":\"8699\",\n" +
            "            \"ims_zh_school_class_images_118\":\"0\",\n" +
            "            \"ims_zh_school_class_images_91\":\"12725\",\n" +
            "            \"ims_zh_school_class_images_119\":\"950\",\n" +
            "            \"ims_zh_school_class_images_92\":\"7071\",\n" +
            "            \"ims_zh_school_class_images_116\":\"14\",\n" +
            "            \"ims_zh_school_class_images_117\":\"106\",\n" +
            "            \"ims_zh_school_class_images_90\":\"8499\"\n" +
            "        },\n" +
            "        \"subTotal\":\"199559\"\n" +
            "    },\n" +
            "    \"ims_wx_school_teachers/avatar\":\"6\",\n" +
            "    \"ims_zh_school_students/avatar_show\":\"3560\",\n" +
            "    \"ims_wx_school_teachers/certification\":\"1\",\n" +
            "    \"ims_zh_school_students/avatar\":\"21\",\n" +
            "    \"ims_zh_school_student_info_images/image\":\"131\",\n" +
            "    \"ims_zh_school_student_head/head_img_up\":\"7312\",\n" +
            "    \"ims_mc_members/avatar\":\"2622\",\n" +
            "    \"ims_zh_students_pick_log_/last_img_family\":{\n" +
            "        \"subTables\":{\n" +
            "            \"ims_zh_students_pick_log_109\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_108\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_89\":\"10\",\n" +
            "            \"ims_zh_students_pick_log_107\":\"52\",\n" +
            "            \"ims_zh_students_pick_log_106\":\"5\",\n" +
            "            \"ims_zh_students_pick_log_128\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_69\":\"1946\",\n" +
            "            \"ims_zh_students_pick_log_105\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_86\":\"67\",\n" +
            "            \"ims_zh_students_pick_log_104\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_126\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_85\":\"642\",\n" +
            "            \"ims_zh_students_pick_log_103\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_125\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_88\":\"1612\",\n" +
            "            \"ims_zh_students_pick_log_102\":\"14294\",\n" +
            "            \"ims_zh_students_pick_log_124\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_87\":\"15263\",\n" +
            "            \"ims_zh_students_pick_log_112\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_82\":\"325\",\n" +
            "            \"ims_zh_students_pick_log_111\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_110\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_84\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_83\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_119\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_118\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_117\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_116\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_75\":\"44848\",\n" +
            "            \"ims_zh_students_pick_log_97\":\"12962\",\n" +
            "            \"ims_zh_students_pick_log_115\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_96\":\"6363\",\n" +
            "            \"ims_zh_students_pick_log_114\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_99\":\"5180\",\n" +
            "            \"ims_zh_students_pick_log_113\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_98\":\"13972\",\n" +
            "            \"ims_zh_students_pick_log_101\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_123\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_93\":\"23388\",\n" +
            "            \"ims_zh_students_pick_log_100\":\"7261\",\n" +
            "            \"ims_zh_students_pick_log_122\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_92\":\"24347\",\n" +
            "            \"ims_zh_students_pick_log_121\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_95\":\"6954\",\n" +
            "            \"ims_zh_students_pick_log_120\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_94\":\"2\",\n" +
            "            \"ims_zh_students_pick_log_91\":\"29690\",\n" +
            "            \"ims_zh_students_pick_log_90\":\"27745\"\n" +
            "        },\n" +
            "        \"subTotal\":\"236928\"\n" +
            "    },\n" +
            "    \"ims_zh_students_pick_log_/last_img\":{\n" +
            "        \"subTables\":{\n" +
            "            \"ims_zh_students_pick_log_109\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_108\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_89\":\"10\",\n" +
            "            \"ims_zh_students_pick_log_107\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_106\":\"5\",\n" +
            "            \"ims_zh_students_pick_log_128\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_69\":\"2784\",\n" +
            "            \"ims_zh_students_pick_log_105\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_86\":\"65\",\n" +
            "            \"ims_zh_students_pick_log_104\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_126\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_85\":\"305\",\n" +
            "            \"ims_zh_students_pick_log_103\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_125\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_88\":\"1612\",\n" +
            "            \"ims_zh_students_pick_log_102\":\"14294\",\n" +
            "            \"ims_zh_students_pick_log_124\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_87\":\"16232\",\n" +
            "            \"ims_zh_students_pick_log_112\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_82\":\"356\",\n" +
            "            \"ims_zh_students_pick_log_111\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_110\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_84\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_83\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_119\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_118\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_117\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_116\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_75\":\"51138\",\n" +
            "            \"ims_zh_students_pick_log_97\":\"13279\",\n" +
            "            \"ims_zh_students_pick_log_115\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_96\":\"6411\",\n" +
            "            \"ims_zh_students_pick_log_114\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_99\":\"5183\",\n" +
            "            \"ims_zh_students_pick_log_113\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_98\":\"15415\",\n" +
            "            \"ims_zh_students_pick_log_101\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_123\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_93\":\"23458\",\n" +
            "            \"ims_zh_students_pick_log_100\":\"7744\",\n" +
            "            \"ims_zh_students_pick_log_122\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_92\":\"24519\",\n" +
            "            \"ims_zh_students_pick_log_121\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_95\":\"7075\",\n" +
            "            \"ims_zh_students_pick_log_120\":\"0\",\n" +
            "            \"ims_zh_students_pick_log_94\":\"2\",\n" +
            "            \"ims_zh_students_pick_log_91\":\"29690\",\n" +
            "            \"ims_zh_students_pick_log_90\":\"28017\"\n" +
            "        },\n" +
            "        \"subTotal\":\"247594\"\n" +
            "    },\n" +
            "    \"ims_zh_school_student_head/head_img_right\":\"7312\",\n" +
            "    \"ims_zh_school_class_images_/images\":{\n" +
            "        \"subTables\":{\n" +
            "            \"ims_zh_school_class_images_69\":\"14864\",\n" +
            "            \"ims_zh_school_class_images_114\":\"46\",\n" +
            "            \"ims_zh_school_class_images_84\":\"0\",\n" +
            "            \"ims_zh_school_class_images_115\":\"2105\",\n" +
            "            \"ims_zh_school_class_images_85\":\"0\",\n" +
            "            \"ims_zh_school_class_images_112\":\"5180\",\n" +
            "            \"ims_zh_school_class_images_82\":\"3510\",\n" +
            "            \"ims_zh_school_class_images_113\":\"1760\",\n" +
            "            \"ims_zh_school_class_images_83\":\"0\",\n" +
            "            \"ims_zh_school_class_images_110\":\"7688\",\n" +
            "            \"ims_zh_school_class_images_88\":\"9437\",\n" +
            "            \"ims_zh_school_class_images_111\":\"3685\",\n" +
            "            \"ims_zh_school_class_images_89\":\"1601\",\n" +
            "            \"ims_zh_school_class_images_86\":\"1743\",\n" +
            "            \"ims_zh_school_class_images_87\":\"27728\",\n" +
            "            \"ims_zh_school_class_images_109\":\"1664\",\n" +
            "            \"ims_zh_school_class_images_107\":\"10496\",\n" +
            "            \"ims_zh_school_class_images_108\":\"8334\",\n" +
            "            \"ims_zh_school_class_images_105\":\"0\",\n" +
            "            \"ims_zh_school_class_images_106\":\"0\",\n" +
            "            \"ims_zh_school_class_images_128\":\"0\",\n" +
            "            \"ims_zh_school_class_images_103\":\"20\",\n" +
            "            \"ims_zh_school_class_images_125\":\"89\",\n" +
            "            \"ims_zh_school_class_images_95\":\"34306\",\n" +
            "            \"ims_zh_school_class_images_104\":\"0\",\n" +
            "            \"ims_zh_school_class_images_126\":\"345\",\n" +
            "            \"ims_zh_school_class_images_96\":\"18846\",\n" +
            "            \"ims_zh_school_class_images_101\":\"0\",\n" +
            "            \"ims_zh_school_class_images_123\":\"3735\",\n" +
            "            \"ims_zh_school_class_images_93\":\"13719\",\n" +
            "            \"ims_zh_school_class_images_102\":\"12071\",\n" +
            "            \"ims_zh_school_class_images_124\":\"2344\",\n" +
            "            \"ims_zh_school_class_images_94\":\"0\",\n" +
            "            \"ims_zh_school_class_images_121\":\"251\",\n" +
            "            \"ims_zh_school_class_images_99\":\"7393\",\n" +
            "            \"ims_zh_school_class_images_100\":\"24209\",\n" +
            "            \"ims_zh_school_class_images_122\":\"0\",\n" +
            "            \"ims_zh_school_class_images_75\":\"53336\",\n" +
            "            \"ims_zh_school_class_images_97\":\"52216\",\n" +
            "            \"ims_zh_school_class_images_120\":\"4084\",\n" +
            "            \"ims_zh_school_class_images_98\":\"26712\",\n" +
            "            \"ims_zh_school_class_images_118\":\"74\",\n" +
            "            \"ims_zh_school_class_images_91\":\"21104\",\n" +
            "            \"ims_zh_school_class_images_119\":\"543\",\n" +
            "            \"ims_zh_school_class_images_92\":\"5949\",\n" +
            "            \"ims_zh_school_class_images_116\":\"192\",\n" +
            "            \"ims_zh_school_class_images_117\":\"78\",\n" +
            "            \"ims_zh_school_class_images_90\":\"18740\"\n" +
            "        }\n" +
            "    }\n" +
            "}";


}
