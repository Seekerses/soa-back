package com.example.soa.services;

import com.example.soa.model.Chapter;
import com.example.soa.model.Coordinates;
import com.example.soa.model.MeleeWeapon;
import com.example.soa.model.SpaceMarine;
import com.example.soa.model.dto.SpaceMarinePage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SpaceMarineFilterRepository {

    @Resource
    EntityManager em;

    public SpaceMarinePage getByFilter(Pageable pageable,
                                         Long id,
                                         String name,
                                         String createdBefore,
                                         String createdAfter,
                                         Long xCoordinateGreaterThan,
                                         Long xCoordinateLesserThan,
                                         Long xCoordinateEquals,
                                         Integer yCoordinateGreaterThan,
                                         Integer yCoordinateLesserThan,
                                         Integer yCoordinateEquals,
                                         Long healthGreaterThan,
                                         Long healthLesserThan,
                                         Long healthEquals,
                                         Integer heartCountGreaterThan,
                                         Integer heartCountLesserThan,
                                         Integer heartCountEquals,
                                         Boolean loyal,
                                         MeleeWeapon meleeWeapon,
                                         String chapterName,
                                         String chapterWorld){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> q = cb.createQuery(SpaceMarine.class);
        Root<SpaceMarine> s = q.from(SpaceMarine.class);

        Predicate idPredicate = cb.equal(s.get("id"), id);
        Predicate namePredicate = cb.like(s.get("name"), "%" + name + "%");
        Predicate createdBeforePredicate = cb.lessThanOrEqualTo(s.get("creationDate"), createdBefore == null ? null : ZonedDateTime.parse(createdBefore));
        Predicate createdAfterPredicate = cb.greaterThanOrEqualTo(s.get("creationDate"), createdAfter == null ? null : ZonedDateTime.parse(createdAfter));
        Predicate xCoordinateLesserThanPredicate  = cb.lessThan(s.<Coordinates>get("coordinates").get("x"), xCoordinateLesserThan);
        Predicate xCoordinateGreaterThanPredicate = cb.greaterThan(s.<Coordinates>get("coordinates").get("x"), xCoordinateGreaterThan);
        Predicate xCoordinateEqualsPredicate = cb.equal(s.<Coordinates>get("coordinates").get("x"), xCoordinateEquals);
        Predicate yCoordinateLesserThanPredicate  = cb.lessThan(s.<Coordinates>get("coordinates").get("y"), yCoordinateLesserThan);
        Predicate yCoordinateGreaterThanPredicate = cb.greaterThan(s.<Coordinates>get("coordinates").get("y"), yCoordinateGreaterThan);
        Predicate yCoordinateEqualsPredicate = cb.equal(s.<Coordinates>get("coordinates").get("y"), yCoordinateEquals);
        Predicate healthLesserThanPredicate  = cb.lessThan(s.get("health"), healthLesserThan);
        Predicate healthGreaterThanPredicate = cb.greaterThan(s.get("health"), healthGreaterThan);
        Predicate healthEqualsPredicate = cb.equal(s.get("health"), healthEquals);
        Predicate heartCountLesserThanPredicate  = cb.lessThan(s.get("heartCount"), heartCountLesserThan);
        Predicate heartCountGreaterThanPredicate = cb.greaterThan(s.get("heartCount"), heartCountGreaterThan);
        Predicate heartCountEqualsPredicate = cb.equal(s.get("heartCount"), heartCountEquals);
        Predicate loyalPredicate = cb.equal(s.get("loyal"), loyal);
        Predicate meleeWeaponPredicate = cb.equal(s.get("meleeWeapon"), meleeWeapon);
        Predicate chapterNamePredicate = cb.like(s.<Chapter>get("chapter").get("chapterName"), "%" + chapterName + "%");
        Predicate chapterWorldPredicate = cb.like(s.<Chapter>get("chapter").get("world"), "%" + chapterWorld + "%");

        Predicate andPredicate = cb.and();
        List<Expression<Boolean>> activePredicates = andPredicate.getExpressions();

        if(id != null) activePredicates.add(idPredicate);
        if(name != null) activePredicates.add(namePredicate);
        if(createdBefore != null) activePredicates.add(createdBeforePredicate);
        if(createdAfter != null) activePredicates.add(createdAfterPredicate);
        if(xCoordinateEquals != null) activePredicates.add(xCoordinateEqualsPredicate);
        else {
            if(xCoordinateGreaterThan != null) activePredicates.add(xCoordinateGreaterThanPredicate);
            if(xCoordinateLesserThan != null) activePredicates.add(xCoordinateLesserThanPredicate);
        }
        if(yCoordinateEquals != null) activePredicates.add(yCoordinateEqualsPredicate);
        else {
            if(yCoordinateGreaterThan != null) activePredicates.add(yCoordinateGreaterThanPredicate);
            if(yCoordinateLesserThan != null) activePredicates.add(yCoordinateLesserThanPredicate);
        }
        if(healthEquals != null) activePredicates.add(healthEqualsPredicate);
        else {
            if(healthGreaterThan != null) activePredicates.add(healthGreaterThanPredicate);
            if(healthLesserThan != null) activePredicates.add(healthLesserThanPredicate);
        }
        if(heartCountEquals != null) activePredicates.add(heartCountEqualsPredicate);
        else {
            if(heartCountGreaterThan != null) activePredicates.add(heartCountGreaterThanPredicate);
            if(heartCountLesserThan != null) activePredicates.add(heartCountLesserThanPredicate);
        }
        if(loyal != null) activePredicates.add(loyalPredicate);
        if(meleeWeapon != null) activePredicates.add(meleeWeaponPredicate);
        if(chapterName != null) activePredicates.add(chapterNamePredicate);
        if(chapterWorld != null) activePredicates.add(chapterWorldPredicate);

        List<Order> orderList = new ArrayList<>();
        pageable.getSort().forEach(v ->
                orderList.add(v.getDirection().equals(Sort.Direction.ASC) ?
                        cb.asc(s.get(v.getProperty())) :
                        cb.desc(s.get(v.getProperty()))));

        q.select(s).where(andPredicate).orderBy(orderList);

        TypedQuery<SpaceMarine> resultQuery = em.createQuery(q);
        resultQuery.setMaxResults(pageable.getPageSize());
        List<SpaceMarine> list = resultQuery
                .setFirstResult((pageable.getPageNumber()-1) * pageable.getPageSize()).getResultList();

        CriteriaQuery<Long> qc = cb.createQuery(Long.class);
        Root<SpaceMarine> sc = qc.from(SpaceMarine.class);
        qc.select(cb.count(sc)).where(andPredicate);
        TypedQuery<Long> resultQueryCount = em.createQuery(qc);
        Long count = resultQueryCount.getSingleResult();

        SpaceMarinePage page = new SpaceMarinePage(list, count);

        return page;
    }
}
