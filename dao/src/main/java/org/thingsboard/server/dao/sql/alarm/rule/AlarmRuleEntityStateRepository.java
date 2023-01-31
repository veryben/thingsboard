/**
 * Copyright © 2016-2022 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.dao.sql.alarm.rule;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.thingsboard.server.dao.model.sql.AlarmRuleEntityStateEntity;

import java.util.List;
import java.util.UUID;

public interface AlarmRuleEntityStateRepository extends JpaRepository<AlarmRuleEntityStateEntity, UUID>{

    @Query("SELECT ares FROM AlarmRuleEntityStateEntity ares WHERE ares.entityId in (:entityIds)")
    List<AlarmRuleEntityStateEntity> findAllByIds(@Param("entityIds") List<UUID> entityIds);

    @Query(value = "SELECT ares FROM AlarmRuleEntityStateEntity ares")
    Page<AlarmRuleEntityStateEntity> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("DELETE FROM AlarmRuleEntityStateEntity ares where ares.entityId = :entityId")
    void deleteByEntityId(@Param("entityId") UUID entityId);
}
