package org.example.ecommservicecustomer.repositories;

import org.example.ecommservicecustomer.models.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWalletTransactionRepository extends JpaRepository<WalletTransaction,String> {
}
