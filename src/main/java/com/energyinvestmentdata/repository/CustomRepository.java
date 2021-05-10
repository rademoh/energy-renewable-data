package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.model.response.EnergyProjectRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Repository
public class CustomRepository {
    private final EntityManager entityManager;

    public CustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<EnergyProjectRes> getEnergyProject(Long id) {
        String sqlQuery = "SELECT rep.id, rep.projectId , rep.projectName" +
                "   FROM RenewableEnergyProjectEntity AS rep " +
                "   INNER JOIN EnergySourceValueEntity AS esv ON rep.id = esv.energyProjectId " +
                "   INNER JOIN RenewableEnergySourceEntity AS res ON res.id = esv.energySourceId " +
                "   INNER JOIN company AS cm   ON rep.companyId = cm.id " +
                "   WHERE rep.id = :id";
        TypedQuery<EnergyProjectRes> typeQuery = entityManager.createQuery(sqlQuery, EnergyProjectRes.class);

        typeQuery.setParameter("id", id);
        return typeQuery.getResultList();
    }
}
