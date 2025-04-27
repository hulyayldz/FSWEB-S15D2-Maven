package org.example.entity;

import java.util.HashSet;
import java.util.Set;

public class TaskData {
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;
    private Set<Task> unassignedTasks;

    public TaskData(Set<Task> annsTasks, Set<Task> bobsTasks, Set<Task> carolsTasks, Set<Task> unassignedTasks) {
        this.annsTasks = annsTasks;
        this.bobsTasks = bobsTasks;
        this.carolsTasks = carolsTasks;
        this.unassignedTasks = unassignedTasks;
    }

    public Set<Task> getTasks(String assignee) {
        switch (assignee.toLowerCase()) {
            case "ann":
                return annsTasks;
            case "bob":
                return bobsTasks;
            case "carol":
                return carolsTasks;
            case "all":
                return getUnion(annsTasks, bobsTasks, carolsTasks, unassignedTasks);
            default:
                return new HashSet<>();
        }
    }

    public Set<Task> getUnion(Set<Task>... sets) {
        Set<Task> union = new HashSet<>();
        for (Set<Task> set : sets) {
            union.addAll(set);
        }
        return union;
    }

    public Set<Task> getIntersection(Set<Task> set1, Set<Task> set2) {
        Set<Task> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection;
    }

    public Set<Task> getDifferences(Set<Task> set1, Set<Task> set2) {
        Set<Task> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }

    public Set<Task> getUnassignedTasks() {
        return unassignedTasks;
    }

    public Set<Task> getMultipleAssignedTasks() {
        Set<Task> allTasks = getUnion(annsTasks, bobsTasks, carolsTasks);
        Set<Task> duplicates = new HashSet<>();
        for (Task task : allTasks) {
            int count = 0;
            if (annsTasks.contains(task)) count++;
            if (bobsTasks.contains(task)) count++;
            if (carolsTasks.contains(task)) count++;
            if (count > 1) duplicates.add(task);
        }
        return duplicates;
    }
}
