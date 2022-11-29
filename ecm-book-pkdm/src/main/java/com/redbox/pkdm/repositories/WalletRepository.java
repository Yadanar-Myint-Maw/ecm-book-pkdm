package com.redbox.pkdm.repositories;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.Wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletRepository extends JpaRepository<Wallet, Long>{
	
	@Query(value = "select w from Wallet as w where w.erase = :erase ORDER BY w.id DESC") 
	List<Wallet> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select w from Wallet as w where w.securityInfo.createDate between :dateFrom and :dateTo")
	List<Wallet> findByDate(@Param("dateFrom") LocalDate deateFrom, @Param("dateTo") LocalDate dateTo);

	@Query(value = "select w from Wallet w join w.accountUser ac where ac.id = :id")
	List<Wallet> getWalletList(String id);
	
	@Query(value = "select w from Wallet as w where w.accountUser.id = :id")
	List<Wallet> findByUser (String id);
	
	@Query(value = "select w from Wallet as w where w.status = :status")
	List<Wallet> findByStatus (String status);
	
	@Query(value = "select w from Wallet as w where w.walletType = :type")
	List<Wallet> findBywalletType (String type);
	
	@Query(value = "select w from Wallet as w where w.walletType = :type and w.securityInfo.createDate between :dateFrom and :dateTo")
	List<Wallet> findBywalletTypeAndDateFromTo (String type, LocalDate dateFrom, LocalDate dateTo);

}
