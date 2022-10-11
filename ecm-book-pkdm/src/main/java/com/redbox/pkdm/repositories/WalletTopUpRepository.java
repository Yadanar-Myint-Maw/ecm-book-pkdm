package com.redbox.pkdm.repositories;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.Wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletTopUpRepository extends JpaRepository<Wallet, Long>{
	
	@Query(value = "select w from Wallet as w where w.erase = :erase") 
	List<Wallet> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select w from Wallet as w where w.securityInfo.createDate between :dateFrom and :dateTo")
	List<Wallet> findByDate(@Param("dateFrom") LocalDate deateFrom, @Param("dateTo") LocalDate dateTo);

	@Query(value = "select w from Wallet w join w.accountUser ac where ac.id = :id")
	List<Wallet> getWalletList(String id);
	
	@Query(value = "select w from Wallet as w where w.accountUser.id = :id")
	List<Wallet> findByUser (String id);

}
