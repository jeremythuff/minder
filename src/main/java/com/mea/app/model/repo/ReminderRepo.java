package com.mea.app.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mea.app.model.impl.ReminderImpl;

public interface ReminderRepo extends JpaRepository <ReminderImpl, Long>{

}
