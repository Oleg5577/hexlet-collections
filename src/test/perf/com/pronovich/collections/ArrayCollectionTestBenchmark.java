package com.pronovich.collections;

import org.openjdk.jmh.annotations.*;

import java.util.*;

import java.util.concurrent.TimeUnit;

//@BenchmarkMode(Mode.SingleShotTime)
//@BenchmarkMode(Mode.AverageTime)
@BenchmarkMode(Mode.Throughput)
//@OutputTimeUnit(TimeUnit.NANOSECONDS)
//@State(Scope.Thread)
@Warmup(iterations = 20, time = 200, timeUnit = TimeUnit.NANOSECONDS)
@Measurement(iterations = 20, time = 200, timeUnit = TimeUnit.NANOSECONDS)

public class ArrayCollectionTestBenchmark {

    @State(Scope.Thread)
    public static class ArrayCollectionState {
        Collection<Integer> arrCollection = new ArrayCollection<>();
        {
            for (Integer i=0; i < 1000; i++) {
                arrCollection.add(i);
            }
        }
    }

    @State(Scope.Thread)
    public static class ArrayListState {
        Collection<Integer> arrCollection = new ArrayList<>();
        {
            for (Integer i=0; i < 1000; i++) {
                arrCollection.add(i);
            }
        }
    }


    @Benchmark
    public void testArrayCollectionRemoveMethod(ArrayCollectionState arrayCollection) {
        final Collection<Integer> testInstance = arrayCollection.arrCollection;
        for (Integer i=0; i < 1000; i++) {
            testInstance.remove(i);
        }
    }

    @Benchmark
    public void testArrayListRemoveMethod(ArrayListState arrayList) {
        final Collection<Integer> testInstance = arrayList.arrCollection;
        for (Integer i=0; i < 1000; i++) {
            testInstance.remove(i);
        }
    }


    @Benchmark
    public void testArrayCollectionAddMethod() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        for (int i = 0; i < 1000; i++) {
            testInstance.add(i);
        }
    }

    @Benchmark
    public void testArrayListAddMethod() {
        final Collection<Integer> testInstance = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            testInstance.add(i);
        }
    }
}