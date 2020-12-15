package com.xwtec.infrastructure.uid.worker.repository;

import com.xwtec.infrastructure.uid.worker.entity.IdDemoEntity;
import com.xwtec.infrastructure.uid.worker.entity.WorkerNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdDemoRepository extends JpaRepository<IdDemoEntity, Long> {
}
