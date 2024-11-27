package ru.batievsky.personalAccountViewer.repositories;

import ru.batievsky.personalAccountViewer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}