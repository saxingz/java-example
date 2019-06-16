package org.saxing.a.ms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 2. 区间[begin, end]对应的类表示为：
 * class Range {
 * 	int begin; //区间开始
 * 	int end;   //区间结束
 * }
 *
 * 实现函数，进行区间的合并，注意ranges可能是无序的。必要时可以使用java库类进行辅助，使用时请尽量保证较好性能。
 * List<Range> mergeRanges(List<Range> ranges);
 *
 * 例如：
 * * 输入:[1, 4], [3, 5], [7, 9], [9, 11]; 输出:[1, 5], [7, 11]
 * * 输入:[10, 12], [3, 4], [9, 13]; 输出: [3, 4], [9, 13]
 */


class Range {
    int begin; //区间开始
    int end;   //区间结束

    public Range() {
    }

    public Range(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
}

public class RangeCombine {

    public static void main(String[] args) {
        Range r1 = new Range(1, 4);
        Range r2 = new Range(3, 5);
        Range r3 = new Range(7, 9);
        Range r4 = new Range(9, 11);

        List<Range> l1 = new ArrayList<>();
        l1.add(r1);
        l1.add(r2);
        l1.add(r3);
        l1.add(r4);

        List<Range> ranges = new RangeCombine().mergeRanges(l1);
        System.out.println(ranges);

        // -------------------------------------------
        List<Range> l2 = new ArrayList<>();
        Range rr1 = new Range(10, 12);
        Range rr2 = new Range(3, 4);
        Range rr3 = new Range(9, 13);
        l2.add(rr1);
        l2.add(rr2);
        l2.add(rr3);
        List<Range> result2 = new RangeCombine().mergeRanges(l2);
        System.out.println(result2);

    }

    List<Range> mergeRanges(List<Range> ranges){

        Collections.sort(ranges, new Comparator<Range>(){
            @Override
            public int compare(Range a, Range b){
                return a.begin - b.begin;
            }
        });


        List<Range> result = new ArrayList<>();
        List<Range> computed = new ArrayList<>();

        for	(int i = 0 ; i < ranges.size(); i++){
            Range curRange = ranges.get(i);
            if(computed.contains(curRange)){
                continue;
            }

            if (i != ranges.size() - 1){
                Range nextRange = ranges.get(i + 1);
                if(nextRange.begin <= curRange.end && nextRange.end > curRange.end){
                    curRange.end = nextRange.end;
                    ranges.set(i + 1, curRange);
                }else if(nextRange.end < curRange.end){
                    computed.add(nextRange);
                    ranges.set(i + 1, curRange);
                }else{
                    result.add(curRange);
                }
            }else{
                result.add(curRange);
            }
        }

        return result;
    }

}
