package com.redbox.pkdm.repositories;

import java.util.List;

import com.redbox.pkdm.entities.Wallet;
import com.redbox.pkdm.entities.WalletPoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletPointRepository extends JpaRepository<WalletPoint, Long>{
	
	@Query(value = "select w from WalletPoint as w where w.erase = :erase") 
	List<WalletPoint> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select w from WalletPoint as w where w.accountUser.id = :id")
	List<WalletPoint> findByUserId (String id);

	

}
