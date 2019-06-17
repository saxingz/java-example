package org.saxing.a.thread2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {



    static class MR extends RecursiveTask<Map<String, Long>>{

        private String[] fc;
        private int start, end;

        public MR(String[] fc, int start, int end) {
            this.fc = fc;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1){
                return calc(fc[start]);
            }else{
                int mid = (start + end) / 2;
                MR mr1 = new MR(fc, start, mid);
                mr1.fork();
                MR mr2 = new MR(fc, mid, end);
                return merge(mr2.compute(), mr1.join());
            }
        }

        private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2){
            Map<String, Long> result = new HashMap<>();
            result.putAll(r1);
            r2.forEach((k, v) -> {
                Long c = result.get(k);
                if (c != null){
                    result.put(k, c + v);
                }else{
                    result.put(k, v);
                }
            });
            return result;
        }

        private Map<String, Long> calc(String line){
            Map<String, Long> result = new HashMap<>();
            String[] word = line.split("\\s+");
            for (String w : word){
                Long v = result.get(w);
                if (v != null){
                    result.put(w, v + 1);
                }else{
                    result.put(w, 1L);
                }
            }
            return result;
        }
    }


}
