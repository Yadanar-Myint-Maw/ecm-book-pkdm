package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.AlertNotification;
import com.redbox.pkdm.entities.Wallet;


public interface AlertNotificationRepository extends JpaRepository<AlertNotification, Long>{
	
	@Query(value = "select a from AlertNotification as a where a.erase = :erase ORDER BY a.id") 
	List<AlertNotification> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select a from AlertNotification a join a.accountUser ac where ac.id = :id")
	List<AlertNotification> getAlertNotificationList(String id);

}
