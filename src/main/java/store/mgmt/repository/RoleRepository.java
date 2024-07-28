package store.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.mgmt.entity.Role;
import store.mgmt.entity.UserRole;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(UserRole name);
}
