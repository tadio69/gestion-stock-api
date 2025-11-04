package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.MvtStock;
import chijouProjects.gestion_stock_api.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    private Integer id;

    private String rolename;

    private UtilisateurDto utilisateur;

    private Integer identreprise;

    public static RoleDto fromEntity(Role role) {
        if (role == null) return null;

        return RoleDto.builder()
                .id(role.getId())
                .rolename(role.getRolename())
                .identreprise(role.getIdentreprise())
                .build();
    }

    public static Role toEntity(RoleDto roledto) {
        if (roledto == null) return null;
        Role role = new Role();
        role.setId(roledto.getId());
        role.setRolename(roledto.getRolename());
        role.setIdentreprise(roledto.getIdentreprise());
        return role;
    }
}
