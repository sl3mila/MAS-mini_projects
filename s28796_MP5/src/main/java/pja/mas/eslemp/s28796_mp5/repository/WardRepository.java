package pja.mas.eslemp.s28796_mp5.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.mas.eslemp.s28796_mp5.model.Ward;

import java.util.List;
import java.util.Optional;

public interface WardRepository extends CrudRepository<Ward, Long> {
    public List<Ward> findByName (String name);
    public List<Ward> findByBuilding (String building);

    @Query("from Ward as d where LEFT(d.building,1) > :letter")
    public List<Ward> findWardsWithBuildingAlphabeticallyAfter(@Param("letter") String letter);

    @Query("from Ward as d left join fetch d.staffMembers where d.id = :id")
    public Optional<Ward> findById(@Param("id") Long id);
}
