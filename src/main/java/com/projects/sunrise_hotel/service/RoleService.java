package com.projects.sunrise_hotel.service;

import com.projects.sunrise_hotel.exception.RoleAlreadyExistException;
import com.projects.sunrise_hotel.exception.UserAlreadyExistsException;
import com.projects.sunrise_hotel.model.Role;
import com.projects.sunrise_hotel.model.User;
import com.projects.sunrise_hotel.repository.RoleRepository;
import com.projects.sunrise_hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role theRole){
        String roleName = "ROLE_"+ theRole.getName().toUpperCase();
        Role role = new Role(roleName);
        if (roleRepository.existsByName(roleName)){
            throw new RoleAlreadyExistException(theRole.getName()+" role already exist" );
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId){
        this.removeAllUsersFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByName(String name){
        return  roleRepository.findByName(name).get();
    }

    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            User user = optionalUser.get();
            Role role = optionalRole.get();

            if (role.getUsers().stream().anyMatch(u -> u.getId().equals(user.getId()))) {
                role.removeUserFromRole(user);
                roleRepository.save(role);
                return user;
            }
        }

        throw new UsernameNotFoundException("User not found in the given role");
    }

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalUser.isEmpty() || optionalRole.isEmpty()) {
            throw new IllegalArgumentException("User or Role not found");
        }

        User user = optionalUser.get();
        Role role = optionalRole.get();

        if (user.getRoles().contains(role)) {
            throw new UserAlreadyExistsException(
                    user.getFirstName() + " is already assigned to the " + role.getName() + " role");
        }

        role.assignRoleToUser(user);
        roleRepository.save(role);
        return user;
    }



    @Override
    public Role removeAllUsersFromRole(Long roleId){
        Optional<Role> role = roleRepository.findById(roleId);
        role.get().removeAllUsersFromRole();
         //role.isPresent(Role::removeAllUsersFromRole);
        return  roleRepository.save(role.get());
    }

}
