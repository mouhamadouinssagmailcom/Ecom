package com.myvision.Super.Repository;

import com.myvision.Super.Entity.Order;
import com.myvision.Super.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
}
