package jpa.specification;

import jpa.entity.Group;
import jpa.entity.Group_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GroupByDuration implements Specification<Group>{

    private double duration;

    public GroupByDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public Predicate getPredicate(Root<Group> groupRoot, CriteriaBuilder builder) {
        // "SELECT * FROM school_group WHERE duration >" + duration;
        return builder.gt(groupRoot.get(Group_.duration), duration);
    }
}
