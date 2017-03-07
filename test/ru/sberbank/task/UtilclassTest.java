package ru.sberbank.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;
import org.junit.internal.ComparisonCriteria;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by btow on 06.03.2017.
 */
public class UtilclassTest extends ComparisonCriteria {

    private byte[][] inBytes0 = {{'H','e','l','l','o',' '}, {'t','h','e',' '},{'w','o','r','l','d','1'}},
                     inBytes1 = {{'D','o','n','n','e','r','-'},{'w','e','t','t','e','r','!'}},
                     expBytes0 = {{'H','e','l','o',' '}, {'t','h','e',' '},{'w','o','r','l','d','1'}},
                     expBytes1 = {{'D','o','n','e','r','-'},{'w','e','t','r','!'}};
    private Set<byte[]> inSet0 = new HashSet<>(),
                        inSet1 = new HashSet<>(),
                        expSet0 = new HashSet<>(),
                        expSet1 = new HashSet<>();
    private Utilclass[] inUtilclasses;

    @Test
    public void testSetListElements() throws Exception {

        inSet0.add(inBytes0[0]);
        inSet0.add(inBytes0[1]);
        inSet0.add(inBytes0[2]);
        inSet1.add(inBytes1[0]);
        inSet1.add(inBytes1[1]);
        Set[] inputValues = {inSet0,inSet1};
        inUtilclasses = new Utilclass[inputValues.length];
        int index = 0;
        for (Set<byte[]> inputValue: inputValues) {
            Utilclass curIn = inUtilclasses[index];
            curIn.setListElements(inputValue);
            index++;
        }

        expSet0.add(expBytes0[0]);
        expSet0.add(expBytes0[1]);
        expSet0.add(expBytes0[2]);
        expSet1.add(expBytes1[0]);
        expSet1.add(expBytes1[1]);
        Set[] expextedValues = {expSet0,expSet1};
        index = 0;
        for (Set<byte[]> expextedValue : expextedValues) {
            assertListElemrntsEquals(expextedValue,inUtilclasses[index]);
            index++;
        }
    }

    private void assertListElemrntsEquals(Set<byte[]> expecteds, Utilclass actuals) throws ArrayComparisonFailure {

        String message = (String) null;

        if(!Arrays.deepEquals(new Object[]{expecteds}, new Object[]{actuals})) {

            String header = message == null ? "" : message + ": ";
            int expectedsLength = this.assertCollectionAreSameLength(expecteds, actuals, header);
            Iterator<byte[]> expectIterator = expecteds.iterator(),
                            actualIterator = actuals.getListElements().iterator();
            byte[] actual, expected;
            int index = 0;

            do {
                actual = actualIterator.next();
                expected = expectIterator.next();
                if (isArray((Object)expected) && isArray((Object)actual)) {
                    try {
                        this.arrayEquals(message, expected, actual);
                    } catch (ArrayComparisonFailure var10) {
                        var10.addDimension(index);
                        throw var10;
                    }
                } else {
                    try {
                        this.assertElementsEqual(expected, actual);
                    } catch (AssertionError var11) {
                        throw new ArrayComparisonFailure(header, var11, index);
                    }
                }
                index++;

            } while (actualIterator.hasNext() && expectIterator.hasNext());

        }
    }

    private int assertCollectionAreSameLength(Set<byte[]> expecteds, Utilclass actuals, String header) {
        if(expecteds == null) {
            Assert.fail(header + "expected array was null");
        }

        if(actuals == null) {
            Assert.fail(header + "actual array was null");
        }

        int actualsLength = actuals.getListElements().size();
        int expectedsLength = expecteds.size();
        if(actualsLength != expectedsLength) {
            Assert.fail(header + "array lengths differed, expected.length=" + expectedsLength + " actual.length=" + actualsLength);
        }

        return expectedsLength;
    }

    @Override
    protected void assertElementsEqual(Object expecteds, Object actuals) {
        String message = (String) null;
        if(expecteds != actuals && !Arrays.deepEquals(new Object[]{expecteds}, new Object[]{actuals})) {
            String header = message == null?"":message + ": ";
            int expectedsLength = this.assertArraysAreSameLength(expecteds, actuals, header);

            for(int i = 0; i < expectedsLength; ++i) {
                Object expected = Array.get(expecteds, i);
                Object actual = Array.get(actuals, i);
                if(this.isArray(expected) && this.isArray(actual)) {
                    try {
                        this.arrayEquals(message, expected, actual);
                    } catch (ArrayComparisonFailure var10) {
                        var10.addDimension(i);
                        throw var10;
                    }
                } else {
                    try {
                        this.assertElementsEqual(expected, actual);
                    } catch (AssertionError var11) {
                        throw new ArrayComparisonFailure(header, var11, i);
                    }
                }
            }

        }
    }

    private boolean isArray(Object expected) {
        return expected != null && expected.getClass().isArray();
    }

    private int assertArraysAreSameLength(Object expecteds, Object actuals, String header) {
        if(expecteds == null) {
            Assert.fail(header + "expected array was null");
        }

        if(actuals == null) {
            Assert.fail(header + "actual array was null");
        }

        int actualsLength = Array.getLength(actuals);
        int expectedsLength = Array.getLength(expecteds);
        if(actualsLength != expectedsLength) {
            Assert.fail(header + "array lengths differed, expected.length=" + expectedsLength + " actual.length=" + actualsLength);
        }

        return expectedsLength;
    }

//    private void assertElementsEqual(Object expected, Object actual) {
//
//    }
//
//    private boolean isArray(Object expected) {
//        return expected != null && expected.getClass().isArray();
//    }

//    @Test
//    public void testAddElement() throws Exception {
//
//        int index = 0;
//
//        for (Utilclass inUtilclass : inUtilclasses) {
//
//
//
//        }
//
//    }
//
//    @Test
//    public void testGetListElements() throws Exception {
//
//    }
//
//    @Test
//    public void testCompareNumberWithZero() throws Exception {
//
//    }
//
//    @Test
//    public void testLogData() throws Exception {
//
//    }
//
//    @Test
//    public void testGetDatePlus5WorkDaysPlus1DayPlus1Year() throws Exception {
//
//    }

}