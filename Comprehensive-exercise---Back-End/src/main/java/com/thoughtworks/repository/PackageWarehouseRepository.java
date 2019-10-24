package com.thoughtworks.repository;

import com.thoughtworks.core.PackageWarehouseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageWarehouseRepository extends JpaRepository<PackageWarehouseModel, Long> {
    PackageWarehouseModel findOneByWayBillNumber(@Param("wayBillNumber") Long wayBillNumber);
}
