package com.example.stock.repository;

import com.example.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE) // 비관적 락
    @Query("select s from Stock s where s.id = :id")
    Stock findByIdWithPessimisticLock(Long id);

    @Lock(LockModeType.OPTIMISTIC) // 낙관적 락 - Stock에 version 추가
    @Query("select s from Stock s where s.id = :id")
    Stock findByIdWithOptimisticLock(Long id);
}
