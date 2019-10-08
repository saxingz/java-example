package org.saxing.xiutest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 *
 * test data
 *
 * @author saxing 2019/9/21 15:23
 */
@RestController
public class TestController {



    @GetMapping(value = "/api/large_screen/total/get_data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String totalGetData(){
        return "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"large_screen_id\": 1,\n" +
                "        \"output_yd\": 25.92,\n" +
                "        \"output_cr_d\": " + new Random().nextInt(100) + ",\n" +
                "        \"output_cr_m\": " + new Random().nextInt(100) + ",\n" +
                "        \"sales_cm\": 2052.9,\n" +
                "        \"sales_cm_yoy\": 1.14,\n" +
                "        \"sales_cm_cc\": 1,\n" +
                "        \"cs_cra\": 49.06,\n" +
                "        \"sales_area_cm\": 494.63,\n" +
                "        \"sales_area_cm_yoy\": 0.86,\n" +
                "        \"sales_area_cm_cc\": 1.01,\n" +
                "        \"cr_cra\": null,\n" +
                "        \"bp_rep_tv\": 2651.11,\n" +
                "        \"bp_rep_tw\": 7013,\n" +
                "        \"fp_rep_tv\": 714.87,\n" +
                "        \"fp_rep_ta\": 178.93\n" +
                "    }\n" +
                "]";
    }

    @GetMapping(value = "/api/large_screen/plant/get_data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String plantGetData(){
        return "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"plant_id\": 1,\n" +
                "        \"large_screen_id\": 1,\n" +
                "        \"plant_name\": \"杭州胜铭\",\n" +
                "        \"output_cr_cm\": 60.32,\n" +
                "        \"output_cm\": 302.86,\n" +
                "        \"output_lm\": 9.12,\n" +
                "        \"output_value_cm\": 1384.89,\n" +
                "        \"output_value_target_cm\": 2600,\n" +
                "        \"sales_incr_rank\": 1,\n" +
                "        \"sales\": 1378.31,\n" +
                "        \"sales_cr\": 53.51,\n" +
                "        \"sales_area\": 298.95,\n" +
                "        \"sales_area_cr\": 59.79,\n" +
                "        \"sales_y\": 16892.51,\n" +
                "        \"sales_target_y\": 32000,\n" +
                "        \"output_rank_y\": 2,\n" +
                "        \"fp_rep_tr_mean\": 6.14,\n" +
                "        \"fp_rep_tr_cm\": 19.68,\n" +
                "        \"bp_rep_tr_mean\": 0.63,\n" +
                "        \"bp_rep_tr_cm\": 0.81,\n" +
                "        \"fp_rep_value\": 357.67,\n" +
                "        \"fp_rep_area\": 72.41,\n" +
                "        \"output_pc\": 18.31,\n" +
                "        \"pro_pc\": 7.07,\n" +
                "        \"bpr\": 90.42,\n" +
                "        \"return_lr\": 0.15,\n" +
                "        \"electricity\": 461.3,\n" +
                "        \"water\": 4.09\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"plant_id\": 4,\n" +
                "        \"large_screen_id\": 1,\n" +
                "        \"plant_name\": \"预印厂\",\n" +
                "        \"output_cr_cm\": 36.69,\n" +
                "        \"output_cm\": 184.93,\n" +
                "        \"output_lm\": 0.23,\n" +
                "        \"output_value_cm\": 630.35,\n" +
                "        \"output_value_target_cm\": 1700,\n" +
                "        \"sales_incr_rank\": 2,\n" +
                "        \"sales\": 659.09,\n" +
                "        \"sales_cr\": 38.77,\n" +
                "        \"sales_area\": 190.82,\n" +
                "        \"sales_area_cr\": 38.16,\n" +
                "        \"sales_y\": 9784.5,\n" +
                "        \"sales_target_y\": 18000,\n" +
                "        \"output_rank_y\": 1,\n" +
                "        \"fp_rep_tr_mean\": 3.16,\n" +
                "        \"fp_rep_tr_cm\": 23.24,\n" +
                "        \"bp_rep_tr_mean\": 0.44,\n" +
                "        \"bp_rep_tr_cm\": 0.54,\n" +
                "        \"fp_rep_value\": 359.73,\n" +
                "        \"fp_rep_area\": 108.21,\n" +
                "        \"output_pc\": 15.59,\n" +
                "        \"pro_pc\": 7.87,\n" +
                "        \"bpr\": 90.54,\n" +
                "        \"return_lr\": 0.23,\n" +
                "        \"electricity\": 514.87,\n" +
                "        \"water\": 3.5\n" +
                "    }\n" +
                "]";
    }

    @GetMapping(value = "/api/intelp/machine/get_data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String testMachineData(){

        String[] colors = new String[]{"Y", "G", "R", ""};

        int max = getIntRandom(10);
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < max; i++) {

            Integer planed = getIntRandom(10000) + 1000;
            float step = planed * (getIntRandom(100) + 1) / 100;

            sb.append("{\n" +
                    "        \"machine_id\": "+ (i + 1) +",\n" +
                    "        \"plant_id\": 8,\n" +
                    "        \"machine_name\": \"BHS"+ getIntRandom(9999) +"\",\n" +
                    "        \"cu_machine\": 0.2411,\n" +
                    "        \"speed\": 0,\n" +
                    "        \"speed_unit\": \"m/min\",\n" +
                    "        \"average_speed\": 46,\n" +
                    "        \"average_speed_unit\": \"m/min\",\n" +
                    "        \"produced_surface\": 260324,\n" +
                    "        \"produced_surface_unit\": \"m2\",\n" +
                    "        \"stop_duration\": 0,\n" +
                    "        \"stop_duration_unit\": \"h\",\n" +
                    "        \"order_id_in_prod\": \"X1909210015\",\n" +
                    "        \"order_section\": 1,\n" +
                    "        \"machine_status\": \""+ colors[getIntRandom(4) - 1] +"\",\n" +
                    "        \"customer_name\": \"杭州优哈箱包有限公司\",\n" +
                    "        \"product_name\": \"物料代码150100030\",\n" +
                    "        \"step_start_tm\": \"2019-09-23T15:02:05Z\",\n" +
                    "        \"step_end_setup_tm\": \"2019-09-23T15:02:05Z\",\n" +
                    "        \"step_end_tm\": \"2019-09-23T16:37:00Z\",\n" +
                    "        \"step_planned_boards\": " + planed + ",\n" +
                    "        \"step_mfg_boards\": "+step+",\n" +
                    "        \"insert_tm\": \"2019-09-23T11:05:51Z\"\n" +
                    "    },");
        }

        String res = sb.toString();
        res = res.substring(0, res.length() - 1);
        return res + "]";

//        return "[\n" +
//                "    {\n" +
//                "        \"machine_id\": 1,\n" +
//                "        \"plant_id\": 8,\n" +
//                "        \"machine_name\": \"BHS2800\",\n" +
//                "        \"cu_machine\": 0.2411,\n" +
//                "        \"speed\": 153,\n" +
//                "        \"speed_unit\": \"m/min\",\n" +
//                "        \"average_speed\": 46,\n" +
//                "        \"average_speed_unit\": \"m/min\",\n" +
//                "        \"produced_surface\": 260324,\n" +
//                "        \"produced_surface_unit\": \"m2\",\n" +
//                "        \"stop_duration\": 0,\n" +
//                "        \"stop_duration_unit\": \"h\",\n" +
//                "        \"order_id_in_prod\": \"X1909210015\",\n" +
//                "        \"order_section\": 1,\n" +
//                "        \"machine_status\": \"R\",\n" +
//                "        \"customer_name\": \"杭州优哈箱包有限公司\",\n" +
//                "        \"product_name\": \"物料代码150100030\",\n" +
//                "        \"step_start_tm\": \"2019-09-23T15:02:05Z\",\n" +
//                "        \"step_end_setup_tm\": \"2019-09-23T15:02:05Z\",\n" +
//                "        \"step_end_tm\": \"2019-09-23T16:37:00Z\",\n" +
//                "        \"step_planned_boards\": 9612.0,\n" +
//                "        \"step_mfg_boards\": 0.0,\n" +
//                "        \"insert_tm\": \"2019-09-23T11:05:51Z\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"machine_id\": 2,\n" +
//                "        \"plant_id\": 8,\n" +
//                "        \"machine_name\": \"EMBA245\",\n" +
//                "        \"cu_machine\": 0.0571,\n" +
//                "        \"speed\": 122,\n" +
//                "        \"speed_unit\": \"boards/min\",\n" +
//                "        \"average_speed\": 48,\n" +
//                "        \"average_speed_unit\": \"boards/min\",\n" +
//                "        \"produced_surface\": 0,\n" +
//                "        \"produced_surface_unit\": \"m2\",\n" +
//                "        \"stop_duration\": 0,\n" +
//                "        \"stop_duration_unit\": \"h\",\n" +
//                "        \"order_id_in_prod\": \"X1909170005\",\n" +
//                "        \"order_section\": 1,\n" +
//                "        \"machine_status\": \"R\",\n" +
//                "        \"customer_name\": \"浙江集商优选电子商务有限公司\",\n" +
//                "        \"product_name\": \"YJBOX000007会员批发价\",\n" +
//                "        \"step_start_tm\": \"2019-09-23T08:00:00Z\",\n" +
//                "        \"step_end_setup_tm\": \"2019-09-23T08:00:00Z\",\n" +
//                "        \"step_end_tm\": \"2019-09-23T09:34:02Z\",\n" +
//                "        \"step_planned_boards\": 13998.0,\n" +
//                "        \"step_mfg_boards\": 4.0,\n" +
//                "        \"insert_tm\": \"2019-09-23T11:05:51Z\"\n" +
//                "    }\n" +
//                "]";
    }

    @GetMapping(value = "/api/intelp/stat/get_data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getStatData(){
        return "[\n" +
                "  {\n" +
                "        \"id\": 1,\n" +
                "      \"plant_id\": 8,\n" +
                "      \"plant_name\": \"智能工厂\",\n" +
                "      \"output_td\": 3.160887,\n" +
                "      \"output_plan_td\": 20.67,\n" +
                "      \"output_pc_td\": null,\n" +
                "      \"output_pc_w\": null,\n" +
                "      \"output_pc_m\": null,\n" +
                "      \"bp_rep_weight\": 3236.47,\n" +
                "      \"fp_rep_area\": 87.27,\n" +
                "      \"starch_rep_weight\": 40.58,\n" +
                "      \"steam_d\": null,\n" +
                "      \"steam_w\": null,\n" +
                "      \"steam_m\": null,\n" +
                "      \"electricity_d\": 1676704.01,\n" +
                "      \"electricity_w\": null,\n" +
                "      \"electricity_m\": null,\n" +
                "      \"water_d\": null,\n" +
                "      \"water_w\": null,\n" +
                "      \"water_m\": null,\n" +
                "      \"insert_tm\": \"2019-09-25T14:56:18Z\"\n" +
                "  }\n" +
                "]";
    }

    @GetMapping(value = "/api/intelp/stat_week/get_data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getStatWeekData(){
        return "[\n" +
                "  {\n" +
                "   \"id\":\n" +
                "   \"plant_id\": \n" +
                "   \"plant_name\": \n" +
                "   \"fp_rep_area\": \n" +
                "   \"pipeline_lr\": \n" +
                "   \"stat_d\": \n" +
                "   \"insert_tm\"\n" +
                "  }\n" +
                "]";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(new Random().nextInt(10) + 1);
//            System.out.println(new Random().nextFloat());
        }
    }

    public int getIntRandom(int max){
        return new Random().nextInt(max) + 1;
    }

}
