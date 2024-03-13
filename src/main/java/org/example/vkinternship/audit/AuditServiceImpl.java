package org.example.vkinternship.audit;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.vkinternship.models.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService{
    private final AuditRepository auditRepository;
    public void save() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userPrincipal = null;
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            userPrincipal = (MyUserDetails) authentication.getPrincipal();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();
        Audit audit = Audit
                .builder()
                .url(url)
                .method(method)
                .userRole(userPrincipal == null ? null : userPrincipal.getRole())
                .username(userPrincipal == null ? null : userPrincipal.getUsername())
                .build();
        auditRepository.save(audit);
    }
}
