package org.coursera.stanford;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by murali on 12-04-2015.
 */
public class UnionFindBasic {

    List<Set<UnionFindBasicElement>> partitions = new ArrayList<>();

    public int getPartitionCount() {
        return partitions.size();
    };

    public void addNewPartition(UnionFindBasicElement element) {

        Set<UnionFindBasicElement> partition = new HashSet<UnionFindBasicElement>();
        partition.add(element);
        element.setPartition(partition);
        partitions.add(partition);
    }

    public void initialize(List<UnionFindBasicElement> elements) {
        for (UnionFindBasicElement element: elements) {
            addNewPartition(element);
        }
    }

    public void initialize(int count, Class clazz) {
        UnionFindBasicElement element = null;

        try {
            element = (UnionFindBasicElement)clazz.newInstance();
        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        };

        for (int i = 0; i < count; i++) {
            Set<UnionFindBasicElement> partition = new HashSet<>();
            partition.add(element);
            element.setPartition(partition);
            partitions.add(partition);
        }
    }
    public void union(UnionFindBasicElement firstElement, UnionFindBasicElement secondElement) {

        Set<UnionFindBasicElement> firstPartition = (Set<UnionFindBasicElement>) firstElement.getPartition();
        Set<UnionFindBasicElement> secondPartition =(Set<UnionFindBasicElement>) secondElement.getPartition();

        if (firstPartition != secondPartition) {

            if (firstPartition.size() > secondPartition.size()) {
                for (UnionFindBasicElement unionFindBasicElement : secondPartition) {
                    unionFindBasicElement.setPartition(firstPartition);
                    firstPartition.add(unionFindBasicElement);
                }
                partitions.remove(secondPartition);
            } else {
                for (UnionFindBasicElement unionFindBasicElement : firstPartition) {
                    unionFindBasicElement.setPartition(secondPartition);
                    secondPartition.add(unionFindBasicElement);
                }
                partitions.remove(firstPartition);
            }
        }
    }

    public boolean find(UnionFindBasicElement firstElement, UnionFindBasicElement secondElement) {
        return firstElement.getPartition() == secondElement.getPartition();
    }
}
