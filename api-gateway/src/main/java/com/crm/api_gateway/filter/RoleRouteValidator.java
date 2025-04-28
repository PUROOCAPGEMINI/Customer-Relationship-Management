package com.crm.api_gateway.filter;
 
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.stereotype.Component;
 
@Component
public class RoleRouteValidator {
    private final Map<String, List<String>> roleMap = new HashMap<>();
    public RoleRouteValidator() {
        roleMap.put("POST:/customers/", List.of("ROLE_ADMIN"));
        roleMap.put("GET:/customers/", List.of("ROLE_ADMIN", "ROLE_SUPPORT", "ROLE_SALES"));
        roleMap.put("GET:/customers/{id}", List.of("ROLE_ADMIN", "ROLE_SUPPORT", "ROLE_SALES"));
        roleMap.put("PUT:/customers/{id}", List.of("ROLE_ADMIN", "ROLE_SALES"));
        roleMap.put("DELETE:/customers/{id}", List.of("ROLE_ADMIN"));
        roleMap.put("POST:/marketing/campaigns*", List.of("ROLE_ADMIN"));
        roleMap.put("GET:/marketing/campaigns", List.of("ROLE_ADMIN", "ROLE_SUPPORT", "ROLE_SALES"));
 
    }
    public List<String> getAllowedRoles(String method, String path) {
        String key = method + ":" + path;
        if (roleMap.containsKey(key)) {
            return roleMap.get(key);
        }

        if (path.matches("^/customers/\\d+$") && method.equals("GET")) {
            return roleMap.get("GET:/customers/{id}");
        }
        
 
        if (path.matches("^/customers/\\d+$") && method.equals("PUT")) {
            return roleMap.get("PUT:/customers/{id}");
        }
        if (path.matches("^/customers/\\d+$") && method.equals("DELETE")) {
            return roleMap.get("DELETE:/customers/{id}");
        }
        if (path.matches("^/marketing/campaigns.*") && method.equals("GET")) {
            return roleMap.get("GET:/marketing/campaigns*");
        }
 
        if (path.matches("^/marketing/campaigns.*") && method.equals("POST")) {
            return roleMap.get("POST:/marketing/campaigns*");
        }
        return Collections.emptyList();
    }
}